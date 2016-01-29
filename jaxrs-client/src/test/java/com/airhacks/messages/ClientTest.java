package com.airhacks.messages;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class ClientTest {

    private Client client;
    private WebTarget tut;
    private WebTarget processor;

    @Before
    public void init() {
        this.client = ClientBuilder.newClient();
        this.tut = this.client.target("http://localhost:8080/supplier/resources/messages");
        this.processor = this.client.target("http://localhost:8080/processor/resources/processors/beautification");
    }

    @Test
    public void fetchMessage() throws InterruptedException, ExecutionException {
        Supplier<String> messageSupplier = () -> this.tut.request().get(String.class);
        CompletableFuture.supplyAsync(messageSupplier).
                thenApply(this::process).
                thenAccept(this::consume).
                get();
    }

    String process(String input) {
        Response response = this.processor.request().post(Entity.text(input));
        return response.readEntity(String.class);
    }

    void consume(String message) {
        this.tut.request().post(Entity.text(message));
    }

}
