package Loeng4;

public class Pair {
    private int a, b;

    public Pair(int a, int b) {
    this.setA(a);
    this.setB(b);
    }

    public int getA() {
        return this.a;
    }

    public void setA(int numA) {
        this.a = numA;
    }

    public int getB() {
        return this.b;
    }

    public void setB(int numB) {
        this.b = numB;
    }

    public int getLength() {
        return (a + b) * 2;
    }
}
