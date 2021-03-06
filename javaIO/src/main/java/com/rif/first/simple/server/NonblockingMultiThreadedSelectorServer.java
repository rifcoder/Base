package com.rif.first.simple.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * It could be
 * 'Exception in thread "main" java.io.IOException: Too many open files in system '
 * but it configure to 1M up to machine.
 * <p>
 * User: rifcoder
 * Date: 03/05/14
 */
public class NonblockingMultiThreadedSelectorServer {
    private static final Logger logger = LoggerFactory.getLogger(NonblockingMultiThreadedSelectorServer.class);
    private static long counter;

    private static Queue<SocketChannel> toWrite = new ConcurrentLinkedQueue<>();
    private static Map<SocketChannel, Queue<ByteBuffer>> pendingData = new ConcurrentHashMap<>();

    private static ExecutorService pool = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 7676));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            SocketChannel changeToWrite;
            while ((changeToWrite = toWrite.poll()) != null) {
                changeToWrite.register(selector, SelectionKey.OP_WRITE);
            }
            for (Iterator<SelectionKey> iterator = selector.selectedKeys().iterator(); iterator.hasNext(); ) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isValid()) {
                    if (key.isAcceptable()) {
                        // someone connected to our ServerSocketChannel
                        accept(key);
                    }
                    if (key.isReadable()) {
                        read(key);
                    }
                    if (key.isWritable()) {
                        write(key);
                    }
                }
            }
        }
    }

    private static void accept(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = ssc.accept(); //nonblocking, never null!!!
        socketChannel.configureBlocking(false);
        socketChannel.register(key.selector(), SelectionKey.OP_READ);
        pendingData.put(socketChannel, new ConcurrentLinkedQueue<>());
    }

    private static void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = socketChannel.read(byteBuffer);
        if (read == -1) {
            pendingData.remove(socketChannel);
            return;
        }

        byteBuffer.flip();

        pool.submit(() -> {
            for (int i = 0; i < byteBuffer.limit(); i++) {
                byteBuffer.put(i, (byte) Util.transmogrify(byteBuffer.get(i)));
            }
            pendingData.get(socketChannel).add(byteBuffer);
            toWrite.add(socketChannel);
            key.selector().wakeup();
            return null;
        });
    }

    private static void write(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

        Queue<ByteBuffer> queue = pendingData.get(socketChannel);
        ByteBuffer byteBuffer;
        while ((byteBuffer = queue.peek()) != null) {
            socketChannel.write(byteBuffer);
            if (!byteBuffer.hasRemaining()) {
                queue.poll();
            } else {
                return;
            }
        }
        socketChannel.register(key.selector(), SelectionKey.OP_READ);
    }
}
