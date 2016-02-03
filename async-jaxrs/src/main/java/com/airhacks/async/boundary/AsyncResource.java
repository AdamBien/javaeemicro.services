package com.airhacks.async.boundary;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientProperties;

/**
 *
 * @author airhacks.com
 */
@Path("async")
public class AsyncResource {

    @Resource
    ManagedExecutorService mes;

    private Client client;
    private WebTarget tut;
    private WebTarget processor;

    @PostConstruct
    public void init() {
        this.client = ClientBuilder.newClient();
        client.property(ClientProperties.CONNECT_TIMEOUT, 1000);
        client.property(ClientProperties.READ_TIMEOUT, 5000);
        this.tut = this.client.target("http://localhost:8080/supplier/resources/messages");
        this.processor = this.client.target("http://localhost:8080/processor/resources/processors/beautification");
    }

    @GET
    @Path("orchestration")
    public void fetchMessage(@Suspended AsyncResponse response) {
        Supplier<String> messageSupplier = () -> this.tut.request().get(String.class);
        CompletableFuture.supplyAsync(messageSupplier, mes).
                thenApply(this::process).
                exceptionally(this::handle).
                thenApply(this::consume).
                thenAccept(response::resume);
    }

    String handle(Throwable t) {
        return "sorry we are overloaded! " + t.getMessage();
    }

    String process(String input) {
        Response response = this.processor.request().post(Entity.text(input));
        return response.readEntity(String.class);
    }

    String consume(String message) {
        this.tut.request().post(Entity.text(message));
        return message;
    }

    @GET
    public void get(@Suspended AsyncResponse response) {
        CompletableFuture.
                supplyAsync(this::doSomeWork, mes).
                thenAccept(response::resume);
    }

    String doSomeWork() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AsyncResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "+" + System.currentTimeMillis();
    }

}
