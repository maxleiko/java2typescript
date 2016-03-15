package sample;

import fr.braindead.MyClass;

import java.util.*;

public class App {
    public App() {
        fr.braindead.SomeClass o = new fr.braindead.SomeClass();
        org.kevoree.SomeClass o2 = new org.kevoree.SomeClass();
        List<String> l = new ArrayList<>();
        int[] array = new int[1];
        long[] longArray = new long[1];
        boolean[] boolArray = new boolean[1];
        String[] strArray = new String[2];
        MyClass[] myClassArray = new MyClass[5];
        Foo<Integer>[] fooArray = new Foo[1];
        Foo<App>[] fooAppArray = new Foo[1];

        // private fooAppArray: sample.Foo<sample.App>[] = new Array<sample.Foo<sample.App>>(1)

        int[] array2 = new int[] { 42, 1664 };
        long[] longArray2 = new long[] { 42, 1664 };
        boolean[] boolArray2 = new boolean[] { false, true};
    }
}