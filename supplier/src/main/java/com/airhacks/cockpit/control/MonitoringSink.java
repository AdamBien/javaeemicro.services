package com.airhacks.cockpit.control;

import java.util.concurrent.atomic.AtomicLong;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;

/**
 *
 * @author airhacks.com
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class MonitoringSink {

    private final AtomicLong COUNTER = new AtomicLong();
    private final AtomicLong EXCEPTION_COUNTER = new AtomicLong();
    private String lastException = "-";

    public void onNewMonitoringData(@Observes String message) {
        COUNTER.incrementAndGet();
    }

    public void onNewException(@Observes Exception ex) {
        EXCEPTION_COUNTER.incrementAndGet();
        this.lastException = ex.getMessage();
    }

    public long getMessageCount() {
        return COUNTER.longValue();
    }

    public long getExceptionCount() {
        return EXCEPTION_COUNTER.longValue();
    }

    public String getLastException() {
        return lastException;
    }

}
