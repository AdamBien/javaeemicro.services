package com.airhacks.async.boundary;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 *
 * @author airhacks.com
 */
@Path("async")
public class AsyncResource {

    @Resource
    ManagedExecutorService mes;

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
