package org.kevoree;

import fr.braindead.MyInterface;

/**
 *
 * Created by leiko on 06/11/15.
 */
public class MyClass implements MyInterface {

    private MyClass me;

    public MyClass() {
        this.me = this;
    }

    @Override
    public void foo() {

    }
}
