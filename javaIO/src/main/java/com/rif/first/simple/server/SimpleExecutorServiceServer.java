package com.rif.first.simple.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: rifcoder
 * Date: 03/05/14
 */
public class SimpleExecutorServiceServer {
    private static final Logger logger = LoggerFactory.getLogger(SimpleExecutorServiceServer.class);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7676);
        //Discard all request that we can't process, so we will skip request if there is no thread avalable in the pool
//      ExecutorService pool = new ThreadPoolExecutor(0,
//                1000,
//                60L, TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>(),
//                new ThreadPoolExecutor.DiscardPolicy());
//
        // Rejected tasks that runs the rejected task directly in the calling thread, it serves better,
        // but we will get connection time over if throughput is quite high
//      ExecutorService pool = new ThreadPoolExecutor(0,
//                1000,
//                60L, TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>(),
//                new ThreadPoolExecutor.CallerRunsPolicy());

        ExecutorService pool = Executors.newFixedThreadPool(1000);
        while (true) {
            Socket socket = serverSocket.accept(); //blocking call - never null!
            pool.submit(() -> Util.process(socket));
        }

    }

}
