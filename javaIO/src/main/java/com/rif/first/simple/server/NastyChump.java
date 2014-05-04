package com.rif.first.simple.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;

/**
 * User: rifcoder
 * Date: 03/05/14
 */
public class NastyChump {
    private static final Logger logger = LoggerFactory.getLogger(NastyChump.class);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5000; i++) {
            try {
                new Socket("localhost", 7676);
                logger.info("Connection number [{}]", i);
            } catch (IOException e) {
                logger.error("Could not connect: {}", e);
            }
        }

        Thread.sleep(1000000000); //looong sleep
    }
}
