package foo;

public class Bar {

    public void test(Callback<int> cb) {
        cb.on(42);
    }

    public void other(Callback<String> callback) {
        this.test((int number) -> {
            callback.on(String.valueOf(number));
        });
    }
}