package com.rif.first.simple.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: rifcoder
 * Date: 03/05/14
 */
public class NewIOServer {
    private static final Logger logger = LoggerFactory.getLogger(NewIOServer.class);

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 7676));

        ExecutorService pool = Executors.newFixedThreadPool(1000);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept(); //blocking call - never null!
            pool.submit(() -> Util.process(socketChannel));
        }

    }

}
