package com.rif.server.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * User: rifcoder
 * Date: 06/12/14
 */
public class SimpleIOServer {
    private String port;
    private volatile boolean running;

    public SimpleIOServer(String port) {
        this.port = port;
    }

    public void startServer() {
        running = true;
        try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(port))) {
            while (running) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public void stopServer() {
        running = false;
    }

    private class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                out.write("Simple echo server");
                out.newLine();
                out.write("Piu Piu Piu");
                out.newLine();
                out.flush();

                String line = in.readLine();
                while (line != null) {
                    System.out.println("Read line:" + line);
                    out.write("Echo:" + line);
                    out.newLine();
                    out.flush();
                    line = in.readLine();
                }
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    public static void main(String[] args) {
        SimpleIOServer server = new SimpleIOServer("9999");
        server.startServer();
    }
}
