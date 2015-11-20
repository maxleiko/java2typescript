package fr.braindead;

import fr.braindead.foo.Bar;

/**
 *
 * Created by leiko on 12/11/15.
 */
public class Foo<T> {

    private T bar;

    public Foo() {
        Bar.bar();
        Foo.yolo();
    }

    public T getBar() {
        return this.bar;
    }

    public void setBar(T bar) {
        this.bar = bar;
    }

    private static void yolo() {}
}
