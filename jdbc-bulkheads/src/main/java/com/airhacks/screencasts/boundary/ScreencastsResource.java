package com.airhacks.screencasts.boundary;

import com.airhacks.screencasts.entity.Video;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Stateless
@Path("screencasts")
public class ScreencastsResource {

    @Inject
    VideoManager manager;

    @POST
    public void save(String name) {
        this.manager.save(name);
    }

    @GET
    public List<Video> all() {
        return this.manager.all();
    }
}
