package com.airhacks.overload.boundary;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class AnswerFinder {

    public String find() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AnswerFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "42-" + System.currentTimeMillis();
    }

}
