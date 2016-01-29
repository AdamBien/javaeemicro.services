package com.airhacks.microservices;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class PipelineTest {

    @Before
    public void init() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "0");
    }

    @Test
    public void forkJoinConfiguration() throws InterruptedException {
        ExecutorService custom = Executors.newCachedThreadPool();
        for (int i = 0; i < 200; i++) {
            CompletableFuture.runAsync(this::slow, custom);
        }
        Thread.sleep(20000);
    }

    void slow() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException ex) {
            Logger.getLogger(PipelineTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void pipeline() {
        CompletableFuture.supplyAsync(this::message).
                thenApply(this::beautify).
                thenAccept(this::consumeMessage).
                thenRun(this::finalAction);
    }

    @Test
    public void combiningPipelines() throws InterruptedException, ExecutionException {
        CompletableFuture<String> first = CompletableFuture.supplyAsync(this::message).thenApplyAsync(this::beautify);
        CompletableFuture<String> second = CompletableFuture.supplyAsync(this::greetings).thenApplyAsync(this::beautify);
        first.thenCombine(second, this::combinator).
                thenAccept(this::consumeMessage).get();

    }

    @Test
    public void composingPipelines() {
        CompletableFuture.supplyAsync(this::message).
                thenCompose(this::compose).
                thenAccept(this::consumeMessage);
    }

    CompletionStage<String> compose(String input) {
        return CompletableFuture.supplyAsync(() -> input).
                thenApply(this::beautify);
    }

    String greetings() {
        return "good morning";
    }

    String combinator(String first, String second) {
        return first + " -- " + second;
    }

    String message() {
        return "hey duke: " + System.currentTimeMillis();
    }

    String beautify(String input) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(PipelineTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "+ " + input + "+";
    }

    void consumeMessage(String message) {
        System.out.println("message = " + message);
    }

    void finalAction() {
        System.out.println("Clean up!");
    }
}
