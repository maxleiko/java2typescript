import * as java from 'java2ts-java';

export class Primitive {
  public s: string = "a" + "b" + "c";
}
export class ClassFields {
  public str: string = "foo";
  public str2: string = java.lang.String.join(", ", ...["a", "b", "c"]);
}
export class InFunction {
  private str: string;
  private str2: string;
  public foo(): void {
    var str: string = "foo";
    var str2: string = java.lang.String.join(", ", ...["a", "b", "c"]);
  }
  public bar(): void {
    this.str = this.str2 + this.str;
    var s: string = this.getString();
    var s2: string = java.lang.String.replace(this.getString(), 'o'.charCodeAt(0), 'a'.charCodeAt(0));
    var s3: string = java.lang.String.getString(java.lang.String.getF(this.getF()));
  }
  public getString(): string {
    return "potato";
  }
  public getF(): InFunction {
    return this;
  }
}
