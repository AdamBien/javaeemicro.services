package com.airhacks.configurator.boundary;

import java.util.Map;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("configuration")
public class ConfigurationResource {

    @GET
    public Map<String, String> all() {
        return System.getenv();
    }

    @GET
    @Path("{id}")
    public String getConfiguration(@PathParam("id") String id) {
        return System.getenv(id);
    }

}
