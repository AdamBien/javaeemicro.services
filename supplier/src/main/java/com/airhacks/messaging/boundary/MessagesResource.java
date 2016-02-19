package com.airhacks.messaging.boundary;

import javax.ejb.Stateless;
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
    MessageProvider provider;

    @GET
    public String message() {
        return provider.nextMessage();
    }

    @POST
    public void message(String message) {
        System.out.println("message = " + message);
    }

}
