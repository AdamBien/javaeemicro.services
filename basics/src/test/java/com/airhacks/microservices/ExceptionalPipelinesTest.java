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
                handle(this::handle).
                thenAccept(this::consume).
                get();
    }

    String handle(String valid, Throwable ex) {
        return valid + " -- " + ex;
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
