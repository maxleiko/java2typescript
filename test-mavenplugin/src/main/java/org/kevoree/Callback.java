package org.kevoree;

/**
 * @ts_callback
 */
public interface Callback {
    /**
     * @optional e
     * @param e
     */
    void done(Exception e);
}
