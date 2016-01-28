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
