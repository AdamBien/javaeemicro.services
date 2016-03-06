package com.airhacks.configurator.boundary;

import com.airhacks.marina.boundary.CacheEntry;
import java.util.function.Function;
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
    @CacheEntry(host = "headlands", port = 8080, cache = "configuration", key = "msg")
    Instance<String> message;

    @Inject
    @CacheEntry(host = "headlands", port = 8080, cache = "configuration")
    Function<String, String> configurationCache;

    @GET
    public String all() {
        return message.get();
    }

    @GET
    @Path("{id}")
    public String getConfiguration(@PathParam("id") String id) {
        return configurationCache.apply(id);
    }

}
