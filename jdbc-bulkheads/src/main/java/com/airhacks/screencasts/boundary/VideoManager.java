package com.airhacks.screencasts.boundary;

import com.airhacks.screencasts.entity.Video;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class VideoManager {

    @PersistenceContext(unitName = "screencasts")
    EntityManager em;

    public List<Video> all() {
        return this.em.
                createNamedQuery(Video.all, Video.class).
                getResultList();
    }

    public void save(String name) {
        this.em.merge(new Video(name));
    }

}
