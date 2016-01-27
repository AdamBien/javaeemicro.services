package com.airhacks.microservices;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class BasicsTest {

    @Test
    public void references() {
        Runnable run = this::display;

        new Thread(run).start();
    }

    void display() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BasicsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void threads() throws InterruptedException {
        List<Thread> pool = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Runnable run = this::display;
            Thread t = new Thread(run);
            pool.add(t);
            t.start();
            Thread.sleep(10);
        }
    }

    public String message() {
        return "hey duke " + System.currentTimeMillis();
    }

    @Test
    public void callable() throws InterruptedException, ExecutionException {
        Callable<String> messageProvider = this::message;
        ExecutorService tp = Executors.newFixedThreadPool(10);
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> futureResult = tp.submit(messageProvider);
            futures.add(futureResult);
        }
        for (Future<String> future : futures) {
            String result = future.get();
            System.out.println("result = " + result);
        }
    }

    @Test
    public void backpressure() {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(2);
        ThreadPoolExecutor tp = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, queue,
                new ThreadPoolExecutor.DiscardPolicy());
        long start = System.currentTimeMillis();
        tp.submit(this::display);
        duration(start);
        tp.submit(this::display);
        duration(start);
        tp.submit(this::display);
        duration(start);
        tp.submit(this::display);
        duration(start);

    }

    public void duration(long start) {
        System.out.println("-- took: " + (System.currentTimeMillis() - start));
    }

    @Test
    public void threadPool() throws InterruptedException {
        ExecutorService tp = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10000; i++) {
            Runnable run = this::display;
            tp.submit(run);
            Thread.sleep(10);
        }
    }

}
