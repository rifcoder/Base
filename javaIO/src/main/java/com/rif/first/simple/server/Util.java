package com.rif.first.simple.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * User: rifcoder
 * Date: 03/05/14
 */
public class Util {
    private static final Logger logger = LoggerFactory.getLogger(Util.class);
    private static long counter;

    public static int transmogrify(int data) {
        if (Character.isLetter(data)) return data ^ ' ';
        return data;
    }

    public static void process(Socket socket) {
        logger.info("Connection from {}", socket);
        try (
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream()
        ) {
            int data;
            while ((data = inputStream.read()) != -1) {
                data = transmogrify(data);
                outputStream.write(data);
            }
        } catch (IOException e) {
            logger.error("Connection problem:", e);
        }
    }

    public static void process(SocketChannel socketChannel) {
        logger.info("{} Connection from {}", ++counter, socketChannel);
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            // position = 0
            // limit = capacity = 1024
            while (socketChannel.read(byteBuffer) != -1) {
                // byte array with some additional information
                // position
                // limit
                // capacity
                //Ex. "hello world" - set postion to 11 and limit still 1024
//              byteBuffer.limit(byteBuffer.position()).position(0); // Set position to 0 and limit to 11
                byteBuffer.flip();  //Does the same thing as prev line
                for (int i = 0; i < byteBuffer.limit(); i++) {
                    byteBuffer.put(i, (byte) transmogrify(byteBuffer.get(i)));
                }
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            logger.error("Connection problem:", e);
        }

    }
}
