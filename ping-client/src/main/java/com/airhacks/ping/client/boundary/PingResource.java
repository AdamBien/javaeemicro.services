package com.airhacks.ping.client.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("ping")
public class PingResource {

    @Inject
    PingService service;

    @GET
    public String message() {
        return service.message();
    }

}
