package com.airhacks.microservices;

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
        System.out.println("hey custom duke");
    }

}
