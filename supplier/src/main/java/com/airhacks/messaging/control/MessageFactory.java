package com.airhacks.messaging.control;

import com.airhacks.breakr.Breakr;
import com.airhacks.breakr.IgnoreCallsWhen;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

/**
 *
 * @author airhacks.com
 */
@ApplicationScoped
@Interceptors(Breakr.class)
public class MessageFactory {

    @Inject
    Event<String> monitoringChannel;

    @IgnoreCallsWhen(failures = 2)
    public String nextMessage() {
        long timestamp = System.currentTimeMillis();
        if (timestamp % 2 == 0) {
            throw new IllegalStateException("Invalid timestamp! " + timestamp);
        }
        String message = "hey duke ";
        monitoringChannel.fire(message);
        return message + timestamp;
    }

}
