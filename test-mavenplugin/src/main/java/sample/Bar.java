package sample;

/**
 *
 * Created by leiko on 12/11/15.
 */
public class Bar<T> {

    private Foo<String> foo;
    private int[] intArray = new int[42];
    private boolean[] boolArray = new boolean[42];
    private foo.Foo[] fooArray;
    private Bar<T>[] bArr = new Bar[10];

    public Bar() {
        this.foo = new Foo<>();
        Lambda lambda = (str, i) -> {
            System.out.println("str = "+str);
            System.out.println("i = "+i);
        };
        lambda.run("hello", 42);
    }
}