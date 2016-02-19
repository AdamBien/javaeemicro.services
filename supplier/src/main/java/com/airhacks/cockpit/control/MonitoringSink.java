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

    public void onNewMonitoringData(@Observes String message) {
        COUNTER.incrementAndGet();
    }

    public long getMessageCount() {
        return COUNTER.longValue();
    }

}
