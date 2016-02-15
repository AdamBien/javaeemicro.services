package com.airhacks.workshops.boundary;

import com.airhacks.workshops.entity.Workshop;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Stateless
@Path("workshops")
public class WorkshopsResource {

    @Inject
    EventManager manager;

    @POST
    public void save(String name) {
        this.manager.save(name);
    }

    @GET
    public List<Workshop> all() {
        return this.manager.all();
    }

}
