export namespace lang {
  export class System {
    static gc() {
    }

    static arraycopy(src: any[]| Float64Array | Int32Array, srcPos: number, dest: any[]| Float64Array | Int32Array, destPos: number, numElements: number): void {
      for (var i = 0; i < numElements; i++) {
        dest[destPos + i] = src[srcPos + i];
      }
    }
  }

  export class StringBuilder {
    private _buffer: string = '';
    public length = 0;

    append(val: any): StringBuilder {
      this._buffer = this._buffer + val;
      this.length = this._buffer.length;
      return this;
    }

    insert(position: number, val: any): StringBuilder {
      this._buffer = this._buffer.slice(0, position) + val + this._buffer.slice(position);
      return this;
    }

    toString(): string {
      return this._buffer;
    }
  }

  export class String {
    static valueOf(data: any, offset?: number, count?: number): string {
      if (typeof offset === 'undefined' && typeof count === 'undefined') {
        return data+'';
      } else {
        return data.slice(offset, offset + count);
      }
    }

    static hashCode(str: string): number {
      var h: number = str['_hashCode'] ? str['_hashCode'] : 0;
      if (h === 0 && str.length > 0) {
          var val: string = str;

          for (var i: number = 0; i < str.length; i++) {
              h = 31 * h + str.charCodeAt(i);
          }
          str['_hashCode'] = h;
      }
      return h;
    }

    static isEmpty(str: string): boolean {
      return str.length === 0;
    }

    static join(delimiter: string, ...elements: string[]): string {
      return elements.join(delimiter);
    }
  }
}
export namespace util {
  export namespace concurrent {
    export namespace atomic {
      export class AtomicIntegerArray {
        _internal: Int32Array;

        constructor(p: Int32Array) {
          this._internal = p;
        }

        set(index: number, newVal: number) {
          this._internal[index] = newVal;
        }

        get(index: number) {
          return this._internal[index];
        }

        getAndSet(index: number, newVal: number) {
          var temp = this._internal[index];
          this._internal[index] = newVal;
          return temp;
        }

        compareAndSet(index: number, expect: number, update: number): boolean {
          if (this._internal[index] == expect) {
            this._internal[index] = update;
            return true;
          } else {
            return false;
          }
        }

      }

      export class AtomicReference<A> {
        _internal: A = null;

        compareAndSet(expect: A, update: A): boolean {
          if (this._internal == expect) {
            this._internal = update;
            return true;
          } else {
            return false;
          }
        }

        get(): A {
          return this._internal
        }

        set(newRef: A) {
          this._internal = newRef;
        }

        getAndSet(newVal: A): A {
          var temp = this._internal;
          this._internal = newVal;
          return temp;
        }
      }

      export class AtomicLong {
        _internal = 0;

        constructor(init: number) {
          this._internal = init;
        }

        compareAndSet(expect: number, update: number): boolean {
          if (this._internal == expect) {
            this._internal = update;
            return true;
          } else {
            return false;
          }
        }

        get(): number {
          return this._internal;
        }

        incrementAndGet(): number {
          this._internal++;
          return this._internal;
        }

        decrementAndGet(): number {
          this._internal--;
          return this._internal;
        }
      }

      export class AtomicInteger {
        _internal = 0;

        constructor(init: number) {
          this._internal = init;
        }

        compareAndSet(expect: number, update: number): boolean {
          if (this._internal == expect) {
            this._internal = update;
            return true;
          } else {
            return false;
          }
        }

        get(): number {
          return this._internal;
        }

        set(newVal: number) {
          this._internal = newVal
        }

        getAndSet(newVal: number): number {
          var temp = this._internal;
          this._internal = newVal;
          return temp;
        }

        incrementAndGet(): number {
          this._internal++;
          return this._internal;
        }

        decrementAndGet(): number {
          this._internal--;
          return this._internal;
        }

        getAndIncrement(): number {
          var temp = this._internal;
          this._internal++;
          return temp;
        }

        getAndDecrement(): number {
          var temp = this._internal;
          this._internal--;
          return temp;
        }
      }
    }
  }

  export class Random {
    public nextInt(max?: number): number {
      if (typeof max === 'undefined') {
        max = Math.pow(2, 32);
      }
      return Math.floor(Math.random() * max);
    }

    public nextDouble(): number {
      return Math.random();
    }

    public nextBoolean(): boolean {
      return Math.random() >= 0.5;
    }
  }

  export interface Iterator<E> {
    hasNext(): boolean;
    next(): E;
  }

  export interface ListIterator<E> extends Iterator<E> {
    hasPrevious(): boolean;
    previous(): E;
    set(e: E);
    add(e: E);
    nextIndex(): number;
    previousIndex(): number;
  }

  export class Arrays {
    public static fill(data: any, begin: number, nbElem: number, param: number): void {
      var max = begin + nbElem;
      for (var i = begin; i < max; i++) {
        data[i] = param;
      }
    }

    public static copyOf<T>(original: any[], newLength: number, ignore?: any): T[] {
      var copy = new Array<T>(newLength);
      lang.System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
      return copy;
    }
  }

  export class Collections {

    public static reverse(list: List<any>) {
      var size: number = list.size();
      if (size < 5000) {
        for (var i: number = 0, mid: number = size >> 1, j: number = size - 1; i < mid; i++, j--) {
          Collections.swap(list, i, j);
        }
      } else {
        var fwd = list.listIterator();
        var rev = list.listIterator(size);
        for (var i: number = 0, mid: number = list.size() >> 1; i < mid; i++) {
          var tmp: any = fwd.next();
          fwd.set(rev.previous());
          rev.set(tmp);
        }
      }
    }

    public static swap(list: List<any>, i: number, j: number) {
      const l = list;
      l.set(i, l.set(j, l.get(i)));
    }

    public static sort<A>(p: List<A>): void {
      p.sort();
    }
  }

  export interface Collection<E> {
    add(val: E);
    addAll(vals: Collection<E>);
    remove(o: any): any;
    clear();
    isEmpty(): boolean;
    size(): number;
    contains(o: E): boolean;
    toArray<E>(a: Array<E>): E[];
    iterator(): Iterator<E>;
    containsAll(c: Collection<any>): boolean;
    addAll(c: Collection<any>): boolean;
    removeAll(c: Collection<any>): boolean;
  }

  export abstract class AbstractCollection<E> implements Collection<E> {
    private static MAX_ARRAY_SIZE = Number.MAX_VALUE - 8;

    abstract add(val: E);
    abstract remove(o: any): boolean;
    abstract clear();
    abstract size(): number;
    abstract iterator(): Iterator<E>;
    abstract containsAll(c: Collection<any>): boolean;
    abstract addAll(c: Collection<any>): boolean;
    abstract removeAll(c: Collection<any>): boolean;

    toArray<T>(a?: Array<T>): T[] {
      var r = new Array<T>(this.size());
      var it = this.iterator();
      for (var i = 0; i < r.length; i++) {
        if (!it.hasNext()) {
          return Arrays.copyOf<T>(r, i);
        }
        r[i] = <any> it.next();
      }
      return it.hasNext() ? AbstractCollection.finishToArray(r, it) : r;
    }

    private static finishToArray<E>(r: E[], it: any): E[] {
      var i: number = r.length;
      while (it.hasNext()){
        var cap: number = r.length;
        if (i == cap) {
          var newCap: number = cap + (cap >> 1) + 1;
          if (newCap - AbstractCollection.MAX_ARRAY_SIZE > 0) {
            newCap = AbstractCollection.hugeCapacity(cap + 1);
          }
          r = Arrays.copyOf<E>(r, newCap);
        }
        r[i++] = <E>it.next();
      }
      return (i == r.length) ? r : Arrays.copyOf<E>(r, i);
    }

    private static hugeCapacity(minCapacity: number): number {
      if (minCapacity < 0) { // overflow
          throw new Error("Required array size too large");
      }
      return (minCapacity > AbstractCollection.MAX_ARRAY_SIZE) ? Number.MAX_VALUE : AbstractCollection.MAX_ARRAY_SIZE;
    }

    contains(o: any): boolean {
      var it = this.iterator();
      if (o === null) {
        while (it.hasNext()) {
          if (it.next()==null) {
            return true;
          }
        }
      } else {
        while (it.hasNext()) {
          if (o.equals(it.next())) {
            return true;
          }
        }
      }
      return false;
    }

    isEmpty(): boolean {
      return this.size() === 0;
    }
  }

  export interface List<E> extends Array<E>, Collection<E> {
    add(elem: E);
    add(index: number, elem: E);
    poll(): E;
    addAll(c: Collection<E>): boolean;
    addAll(index: number, c: Collection<E>): boolean;
    get(index: number): E;
    set(index: number, element: E): E;
    indexOf(o: E): number;
    lastIndexOf(o: E): number;
    listIterator(): ListIterator<E>;
    listIterator(index: number): ListIterator<E>;
    remove(index: number): E;
  }

  export interface Set<E> extends Collection<E> {}

  export class HashSet<E> implements Set<E> {
    add(val: E) {
      this[<any>val] = val;
    }

    clear() {
      for (var p in this) {
        if (this.hasOwnProperty(p)) {
          delete this[p];
        }
      }
    }

    contains(val: E): boolean {
      return this.hasOwnProperty(<any>val);
    }

    containsAll(elems: Collection<E>): boolean {
      return false;
    }

    addAll(vals: Collection<E>): boolean {
      var tempArray = vals.toArray(null);
      for (var i = 0; i < tempArray.length; i++) {
        this[<any>tempArray[i]] = tempArray[i];
      }
      return true;
    }

    remove(val: E): boolean {
      var b = false;
      if (this[<any>val]) {
        b = true;
      }
      delete this[<any>val];
      return b;
    }

    removeAll(): boolean {
      return false;
    }

    size(): number {
      return Object.keys(this).length;
    }

    isEmpty(): boolean {
      return this.size() == 0;
    }

    toArray<E>(a: Array<E>): E[] {
      for (var ik in this) {
        a.push(this[ik]);
      }
      return a;
    }

    iterator(): Iterator<E> {
      return null;
    }
  }

  export class AbstractList<E> extends Array<E> implements List<E> {
    public length: number = 0;

    addAll(index: any, vals?: any): boolean {
      var tempArray = vals.toArray(null);
      for (var i = 0; i < tempArray.length; i++) {
        this.push(tempArray[i]);
      }
      return false;
    }

    clear() {
      this.length = 0;
    }

    poll(): E {
      return this.shift();
    }

    remove(indexOrElem: any): any {

      return false;
    }

    removeAll(): boolean {
      return false;
    }

    toArray(a: Array<E>): E[] {
      return <E[]><any>this;
    }

    size(): number {
      return this.length;
    }

    add(index: any, elem?: E) {
      if (typeof elem !== 'undefined') {
        this.splice(index, 0, elem);
      } else {
        this.push(index);
      }
    }

    get(index: number): E {
      return this[index];
    }

    contains(val: E): boolean {
      return this.indexOf(val) != -1;
    }

    containsAll(elems: Collection<E>): boolean {
      return false;
    }

    isEmpty(): boolean {
      return this.length == 0;
    }

    set(index: number, element: E): E {
      return null;
    }

    indexOf(element: E): number {
      return 0;
    }

    lastIndexOf(element: E): number {
      return 0;
    }

    iterator(): Iterator<E> {
      return new AbstractList.Itr(this);
    }

    listIterator(index?: number): ListIterator<E> {
      if (typeof index !== 'undefined') {
        return new AbstractList.ListItr(this, index);
      } else {
        return this.listIterator(0);
      }
    }
  }

  export class LinkedList<E> extends AbstractList<E> {}
  export class ArrayList<E> extends AbstractList<E> {}

  export module AbstractList {
    export class Itr<E> implements Iterator<E> {
      public cursor: number = 0;
      public lastRet: number = -1;
      protected list: AbstractList<E>;

      constructor(list: AbstractList<E>) {
        this.list = list;
      }

      public hasNext(): boolean {
        return this.cursor != this.list.size();
      }
      public next(): E {
        try {
          var i: number = this.cursor;
          var next: E = this.list.get(i);
          this.lastRet = i;
          this.cursor = i + 1;
          return next;
        } catch ($ex$) {
          if ($ex$ instanceof Error) {
            var e: Error = <Error>$ex$;
            throw new Error("no such element exception");
          } else {
            throw $ex$;
          }
        }
      }
      public remove(): void {
        if (this.lastRet < 0) {
          throw new Error("illegal state exception");
        }
        try {
          this.list.remove(this.lastRet);
          if (this.lastRet < this.cursor) {
            this.cursor--;
          }
          this.lastRet = -1;
        } catch ($ex$) {
          throw $ex$;
        }
      }
    }
    export class ListItr<E> extends Itr<E> implements ListIterator<E> {
      constructor(list: AbstractList<E>, index: number) {
        super(list);
        this.cursor = index;
      }
      public hasPrevious(): boolean {
        return this.cursor !== 0;
      }
      public previous(): E {
        try {
          var i: number = this.cursor - 1;
          var previous: E = this.list.get(i);
          this.lastRet = this.cursor = i;
          return previous;
        } catch ($ex$) {
          if ($ex$ instanceof Error) {
            var e: Error = <Error>$ex$;
            throw new Error("no such element exception");
          } else {
            throw $ex$;
          }
        }
      }
      public nextIndex(): number {
        return this.cursor;
      }
      public previousIndex(): number {
        return this.cursor - 1;
      }
      public set(e: E): void {
        if (this.lastRet < 0) {
          throw new Error("illegal state exception");
        }
        try {
          this.list.set(this.lastRet, e);
        } catch ($ex$) {
          throw $ex$;
        }
      }
      public add(e: E): void {
        try {
          var i: number = this.cursor;
          this.list.add(i, e);
          this.lastRet = -1;
          this.cursor = i + 1;
        } catch ($ex$) {
          throw $ex$;
        }
      }
    }
  }

  export class Stack<E> {
    content = new Array();

    pop(): E {
      return this.content.pop();
    }

    push(t: E): void {
      this.content.push(t);
    }

    isEmpty(): boolean {
      return this.content.length == 0;
    }

    peek(): E {
      return this.content.slice(-1)[0];
    }
  }

  export interface Map<K, V> {
    get(key: K): V;
    put(key: K, value: V): V;
    containsKey(key: K): boolean;
    remove(key: K): V;
    keySet(): Set<K>;
    isEmpty(): boolean;
    values(): Set<V>;
    clear();
  }

  export class HashMap<K, V> implements Map<K, V> {
    get(key: K): V {
      return this[<any>key];
    }

    put(key: K, value: V): V {
      var previous_val = this[<any>key];
      this[<any>key] = value;
      return previous_val;
    }

    containsKey(key: K): boolean {
      return this.hasOwnProperty(<any>key);
    }

    remove(key: K): V {
      var tmp = this[<any>key];
      delete this[<any>key];
      return tmp;
    }

    keySet(): Set<K> {
      var result = new HashSet<K>();
      for (var p in this) {
        if (this.hasOwnProperty(p)) {
          result.add(<any> p);
        }
      }
      return <Set<K>> result;
    }

    isEmpty(): boolean {
      return Object.keys(this).length == 0;
    }

    values(): Set<V> {
      var result = new HashSet<V>();
      for (var p in this) {
        if (this.hasOwnProperty(p)) {
          result.add(this[p]);
        }
      }
      return <Set<V>> result;
    }

    clear(): void {
      for (var p in this) {
        if (this.hasOwnProperty(p)) {
          delete this[p];
        }
      }
    }
  }
}
