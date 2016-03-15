package foo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Bar<T> {

//    private AtomicInteger atomicInteger;
//    private List<String> list = new ArrayList<>();
//    private boolean[] arr = new boolean[42];
//    private Bar<T>[] bArr = new Bar[10];
    private Bar<Inner>[] b2Arr = new Bar[1];


    public class Inner {}
}