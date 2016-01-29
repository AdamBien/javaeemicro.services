package com.airhacks.messaging.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Path("messages")
public class MessagesResource {

    @GET
    public String message() {
        return "hey duke " + System.currentTimeMillis();
    }

}
