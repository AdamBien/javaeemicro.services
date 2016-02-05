package com.airhacks.overload.boundary;

import com.airhacks.porcupine.execution.boundary.Dedicated;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import java.util.concurrent.ExecutorService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

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
    public void answers(@Suspended AsyncResponse response) {
        supplyAsync(finder::find, answers).
                thenAccept(response::resume);
    }

}
