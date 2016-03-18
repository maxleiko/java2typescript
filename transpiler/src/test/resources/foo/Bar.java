package foo;

public class Bar {

    public void test() {
        Callback<String> callback = new Callback<String>() {
            @Override
            public void on(String o) {
                System.out.println("On> "+o);
            }
        }

        callback.on("foo");
        String.valueOf(false);
    }
}