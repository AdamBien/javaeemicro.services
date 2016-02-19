package com.airhacks.messaging.boundary;

import com.airhacks.messaging.control.MessageFactory;
import javax.ejb.Stateless;
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
    MessageFactory factory;

    public String nextMessage() {
        return factory.nextMessage();
    }

}
