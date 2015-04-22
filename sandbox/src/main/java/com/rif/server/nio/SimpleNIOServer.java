package com.rif.server.nio;

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
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * User: rifcoder
 * Date: 07/12/14
 */
public class SimpleNIOServer extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(SimpleNIOServer.class);
    private static Map<SocketChannel, Queue<ByteBuffer>> pendingData = new ConcurrentHashMap<>();
    private volatile boolean running;

    public void run() {
        running = true;
        try {
            startServer();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    private void startServer() throws IOException {
        logger.info("Server started...");
        ServerSocketChannel ssc = ServerSocketChannel.open();
        InetSocketAddress port = new InetSocketAddress(9999);
        ssc.bind(port);
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (running) {
            selector.select();
            for (Iterator<SelectionKey> iterator = selector.selectedKeys().iterator(); iterator.hasNext(); ) {
                SelectionKey key = iterator.next();
                if (key.isValid()) {
                    if (key.isAcceptable()) {
                        accept(key);
                    }
                    if (key.isReadable()) {
                        read(key);
                    }
                    if (key.isWritable()) {
                        write(key);
                    }
                }
                iterator.remove();
            }
        }
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel channel = ssc.accept();
        logger.info("Connecton accepted from {} for socket {}", key.channel(), channel);
        if (channel != null) {
            channel.configureBlocking(false);
            pendingData.put(channel, new ConcurrentLinkedDeque<>());
            channel.register(key.selector(), SelectionKey.OP_READ);
        }
    }

    private void read(SelectionKey key) throws IOException {
        logger.info("Read from channel: {}", key.channel());
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(16);

        int bytesRead = channel.read(byteBuffer);
        if (bytesRead == -1) {
            pendingData.remove(channel);
            return;
        }
        logger.info("Read {} bytes from channel {}", bytesRead, channel);
        byteBuffer.flip();
        pendingData.get(channel).add(byteBuffer);

        channel.register(key.selector(), SelectionKey.OP_WRITE);
    }

    private void write(SelectionKey key) throws IOException {
        logger.info("Write to channel: {}", key.channel());
        SocketChannel channel = (SocketChannel) key.channel();

        Queue<ByteBuffer> queue = pendingData.get(channel);
        ByteBuffer byteBuffer;
        while ((byteBuffer = queue.peek()) != null) {
            int bytesWritten = channel.write(byteBuffer);
            logger.info("Wrote {} bytes to channel {}", bytesWritten, channel);
            if(!byteBuffer.hasRemaining()) {
                queue.poll();
            } else {
                return;
            }
        }

        channel.register(key.selector(), SelectionKey.OP_READ);
    }

    private void stopServer() {
        this.interrupt();
        running = false;
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleNIOServer server = new SimpleNIOServer();
        server.start();
        Thread.sleep(5 * 60 * 1000);
        server.stopServer();
    }
}
