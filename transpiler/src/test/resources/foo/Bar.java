package foo;

public class Bar {

    private char localC = 'r';

    public void test() {
        byte b = '|';
        byte b1 = getChar();
        byte b2 = 42;
        byte b3 = localC;
        byte b4;
        b4 = 'b';
        byte[] a = new byte[] { 'a', 42, getChar() };
    }

    public char getChar() {
        return "foo".charAt(0);
    }
}