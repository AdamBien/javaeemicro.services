package com.airhacks.messaging.boundary;

import java.lang.reflect.Method;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author airhacks.com
 */
public class MonitoringInterceptor {

    @Inject
    Event<Exception> exceptionChannel;

    @AroundInvoke
    public Object handle(InvocationContext ic) throws Exception {
        Method method = ic.getMethod();
        System.out.println("method = " + method);
        try {
            return ic.proceed();
        } catch (Exception ex) {
            exceptionChannel.fire(ex);
            throw ex;
        }
    }

}
