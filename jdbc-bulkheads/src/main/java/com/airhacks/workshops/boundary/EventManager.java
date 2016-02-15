package com.airhacks.workshops.boundary;

import com.airhacks.workshops.entity.Workshop;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EventManager {

    @PersistenceContext(unitName = "workshops")
    EntityManager em;

    public List<Workshop> all() {
        return this.em.
                createNamedQuery(Workshop.all, Workshop.class).
                getResultList();
    }

    public void save(String name) {
        this.em.merge(new Workshop(name));
    }

}
