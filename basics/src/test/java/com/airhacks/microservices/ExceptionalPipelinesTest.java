package com.airhacks.microservices;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class ExceptionalPipelinesTest {

    @Test
    public void handle() throws InterruptedException, ExecutionException {
        CompletableFuture.supplyAsync(this::exceptional).
                exceptionally(this::transform).
                thenAccept(this::consume).
                get();
    }

    void consume(String message) {
        System.out.println("message = " + message);
    }

    public String exceptional() {
        throw new IllegalStateException("happens");
    }

    String transform(Throwable t) {
        return t.toString();
    }

}
