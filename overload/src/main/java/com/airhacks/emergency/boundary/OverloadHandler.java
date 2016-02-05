package com.airhacks.emergency.boundary;

import javax.enterprise.event.Observes;

/**
 *
 * @author airhacks.com
 */
public class OverloadHandler {

    public void onOverload(@Observes String message) {
        System.err.println("!!!! " + message);
    }

}
