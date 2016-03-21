package org.kevoree;

import foo.MyInterface;
import org.kevoree.modeling.KCallback;

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
        KCallback<String> callback = new KCallback<String>() {
            @Override
            public void on(String s) {
                System.out.println("Callback called with: "+s);
            }
        };
        callback.on("foo");
    }
}
