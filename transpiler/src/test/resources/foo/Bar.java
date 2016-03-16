package foo;

public class Bar {

    /**
     * @native ts
     * console.log('I dont know');
     * function test() {
     *   console.log('');
     * }
     * this.test(null);     // hum
     * @param cb
     * @apiNote
     */
    public void test(Callback<int> cb) {
        cb.on(42);
    }

}