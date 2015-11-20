package org.kevoree;

import fr.braindead.MyInterface;

/**
 *
 * Created by leiko on 06/11/15.
 */
public class OtherClass extends fr.braindead.MyClass implements MyInterface {

    @Override
    public void foo() {

    }

    @Override
    public void bar() {

    }

    /**
     * @native ts
     * setTimeout(() => {
     *     cb();
     * }, 5000);
     * @param cb
     */
    public void asyncJob(Callback cb) {
        new Thread(() -> {
            try {
                // wait 5 seconds
                Thread.sleep(5000);
                // then notify parent
                cb.done();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}