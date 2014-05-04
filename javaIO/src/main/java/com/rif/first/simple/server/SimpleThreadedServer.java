package com.rif.first.simple.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * User: rifcoder
 * Date: 03/05/14
 */
public class SimpleThreadedServer {
    private static final Logger logger = LoggerFactory.getLogger(SimpleThreadedServer.class);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7676);
        while (true) {
            Socket socket = serverSocket.accept(); //blocking call - never null!
            new Thread(() -> Util.process(socket)).start();
        }

    }

}
