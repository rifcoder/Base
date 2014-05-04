package com.rif.first.simple.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * It could be
 * 'Exception in thread "main" java.io.IOException: Too many open files in system '
 * but it configure to 1M up to machine.
 *
 * User: rifcoder
 * Date: 03/05/14
 */
public class NonblockingSingleThreadedPollingServer {
    private static final Logger logger = LoggerFactory.getLogger(NonblockingSingleThreadedPollingServer.class);
    private static long counter;

    private static Collection<SocketChannel> sockets = new HashSet<>(512);

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 7676));
        serverSocketChannel.configureBlocking(false);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept(); //nonblocking call - usually null!
            if (socketChannel != null) {
                logger.info("{} Connection from {}", ++counter, socketChannel);
                socketChannel.configureBlocking(false);
                sockets.add(socketChannel);
            }

            for (Iterator<SocketChannel> iterator = sockets.iterator(); iterator.hasNext(); ) {
                SocketChannel socket = iterator.next();

                try {
                    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
                    int read = socket.read(byteBuffer);
                    if (read == -1) {
                        iterator.remove();
                        continue;
                    }
                    if (read != 0) {
                        byteBuffer.flip();
                        for (int i = 0; i < byteBuffer.limit(); i++) {
                            byteBuffer.put(i, (byte) Util.transmogrify(byteBuffer.get(i)));
                        }
                        socket.write(byteBuffer);
                    }
                } catch (IOException e) {
                    logger.error("Connection problem:", e);
                }
            }


        }
    }
}
