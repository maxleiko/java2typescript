export class UseGeneric {
  public strList: MyList<string>;
  public map: MyMap<string, number>;
  constructor() {
    this.strList = new MyArrayList<string>();
    this.map = new MyHashMap<string, number>();
  }
}
export interface MyMap<K, V> {
  put(key: K, value: V): void;
  get(key: K): V;
  remove(key: K): void;
}
export class MyArrayList<E> implements MyList<E> {
  public add(elem: E): void {}
  public add(index: number, elem: E): void {}
  public remove(elem: E): void {}
  public remove(index: number): void {}
}
export class MyHashMap<K, V> implements MyMap<K, V> {
  public put(key: K, value: V): void {}
  public get(key: K): V {
    return null;
  }
  public remove(key: K): void {}
}
export interface MyList<E> {
  add(elem: E): void;
  add(index: number, elem: E): void;
  remove(elem: E): void;
  remove(index: number): void;
}