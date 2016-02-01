package com.airhacks.messaging.boundary;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Path("messages")
public class MessagesResource {

    @GET
    public String message() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MessagesResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "hey duke " + System.currentTimeMillis();
    }

    @POST
    public void message(String message) {
        System.out.println("message = " + message);
    }

}
