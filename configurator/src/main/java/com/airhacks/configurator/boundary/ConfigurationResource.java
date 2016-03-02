package com.airhacks.configurator.boundary;

import com.airhacks.jc2.configuration.boundary.Configurable;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author airhacks.com
 */
@ApplicationScoped
@Path("configuration")
public class ConfigurationResource {

    @Inject
    @Configurable("msg")
    Instance<String> message;

    @GET
    public String all() {
        return message.get();
    }

    @GET
    @Path("{id}")
    public String getConfiguration(@PathParam("id") String id) {
        return System.getenv(id);
    }

}
