package foo;

/**
 * @ts_callback
 */
public interface Callback<T> {

    void on(T o);
}