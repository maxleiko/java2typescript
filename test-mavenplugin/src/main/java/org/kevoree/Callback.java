package org.kevoree;

/**
 * Created by leiko on 06/11/15.
 * @native ts_callback
 */
public interface Callback {
    /**
     * @optional e
     * @param e
     */
    void done(Exception e);
}
