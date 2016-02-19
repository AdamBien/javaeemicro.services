package com.airhacks.messaging.boundary;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Interceptors(MonitoringInterceptor.class)
public class MessageProvider {

    @Inject
    Event<String> monitoringChannel;

    public String nextMessage() {
        long timestamp = System.currentTimeMillis();
        if (timestamp % 2 == 0) {
            throw new IllegalStateException("Invalid timestamp! " + timestamp);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        String message = "hey duke ";
        monitoringChannel.fire(message);
        return message + timestamp;
    }

}
