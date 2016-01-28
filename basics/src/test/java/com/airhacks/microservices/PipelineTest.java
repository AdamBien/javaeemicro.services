package com.airhacks.microservices;

import java.util.concurrent.CompletableFuture;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class PipelineTest {

    @Test
    public void pipeline() {
        CompletableFuture.supplyAsync(this::message).
                thenApply(this::beautify).
                thenAccept(this::consumeMessage).
                thenRun(this::finalAction);
    }

    @Test
    public void combiningPipelines() {
        CompletableFuture<String> first = CompletableFuture.supplyAsync(this::message);
        CompletableFuture<String> second = CompletableFuture.supplyAsync(this::greetings);
        first.thenCombine(second, this::combinator).
                thenApply(this::beautify).
                thenAccept(this::consumeMessage);

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
        return "+ " + input + "+";
    }

    void consumeMessage(String message) {
        System.out.println("message = " + message);
    }

    void finalAction() {
        System.out.println("Clean up!");
    }
}
