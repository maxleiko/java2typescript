package fr.braindead;


/**
 *
 * Created by leiko on 06/11/15.
 */
public abstract class MyClass implements MyInterface {

    private Foo<String> swag;

    public MyClass() {
        this.swag = new Foo<>();
        this.swag.setBar("yolo");
    }

    @Override
    public void foo() {
        System.out.println("foo :)");
        System.out.println(this.swag.getBar());
    }

    public abstract void bar();
}
