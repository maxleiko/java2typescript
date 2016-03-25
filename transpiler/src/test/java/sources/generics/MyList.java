package sources.generics;

public interface MyList<E> {

    void add(E elem);
    void add(int index, E elem);
    void remove(E elem);
    void remove(int index);
}