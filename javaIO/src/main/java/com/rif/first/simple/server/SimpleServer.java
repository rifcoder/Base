package com.rif.first.simple.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This is simple single thread server.
 *
 * Hint: You can use telnet to send data to it!
 * Ex. telnet localhost 7676
 *
 * User: rifcoder
 * Date: 03/05/14
 */
public class SimpleServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7676);
        while (true) {
            Socket socket = serverSocket.accept(); //blocking call - never null!
            Util.process(socket);
        }
    }
}
