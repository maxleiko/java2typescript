package sources.generics;

public class UseGeneric {

    MyList<String> strList;
    MyMap<String, Number> map;

    public UseGeneric() {
        this.strList = new MyArrayList<>();
        this.map = new MyHashMap<>();
    }
}
