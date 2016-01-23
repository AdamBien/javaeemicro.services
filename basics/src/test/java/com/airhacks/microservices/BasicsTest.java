package com.airhacks.microservices;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class BasicsTest {

    @Test
    public void references() {
        Runnable run = this::display;

        new Thread(run).start();
    }

    void display() {
        try {
            Thread.sleep(50000000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BasicsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void threads() throws InterruptedException {
        List<Thread> pool = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Runnable run = this::display;
            Thread t = new Thread(run);
            pool.add(t);
            t.start();
            Thread.sleep(10);
        }
    }

}
