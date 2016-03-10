package org.kevoree.modeling;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        KCallback<String> callback = new KCallback<String>() {
            private String foo;

            @Override
            public void on(String s) {
                System.out.println("woot "+s);
                this.setFoo(s);
            }

            public void setFoo(String hmm) {
                this.foo = hmm;
            }
        };
        callback.on("test");
    }
}
