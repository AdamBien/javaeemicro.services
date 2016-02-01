package com.airhacks.async.boundary;

import java.util.logging.Level;
import java.util.logging.Logger;
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

    @GET
    public void get(@Suspended AsyncResponse response) {
        response.resume(this.doSomeWork());
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
