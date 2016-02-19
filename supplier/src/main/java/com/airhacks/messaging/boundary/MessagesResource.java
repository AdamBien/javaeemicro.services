package com.airhacks.messaging.boundary;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("messages")
public class MessagesResource {

    @Inject
    Event<String> monitoringChannel;

    @GET
    public String message() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MessagesResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        String message = "hey duke ";
        monitoringChannel.fire(message);
        return message + System.currentTimeMillis();
    }

    @POST
    public void message(String message) {
        System.out.println("message = " + message);
    }

}
