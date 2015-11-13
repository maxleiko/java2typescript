/**
 * Created by gnain on 04/09/15.
 */
public class Foo<T> {}

public class Bar {
    private Foo<String> foo;

    public Bar() {
        this.foo = new Foo<>();
    }
}