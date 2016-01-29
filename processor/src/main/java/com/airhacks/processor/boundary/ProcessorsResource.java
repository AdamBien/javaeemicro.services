package com.airhacks.processor.boundary;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Path("processors")
public class ProcessorsResource {

    @POST
    @Path("beautification")
    public String process(String input) {
        return "+" + input + "+";
    }

}
