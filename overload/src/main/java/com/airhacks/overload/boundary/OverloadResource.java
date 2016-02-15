package com.airhacks.overload.boundary;

import com.airhacks.porcupine.execution.boundary.Dedicated;
import java.util.concurrent.ExecutorService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("overload")
public class OverloadResource {

    @Inject
    AnswerFinder finder;

    @Inject
    @Dedicated
    ExecutorService answers;

    @GET
    public String answers() {
        return finder.find();
    }

    public void onTimeout(AsyncResponse asyncResponse) {
        Response response = Response.status(Status.SERVICE_UNAVAILABLE).
                header("reason", "overloaded").build();
        asyncResponse.resume(response);
    }

}
