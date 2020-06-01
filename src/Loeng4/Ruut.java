package Loeng4;
import java.lang.Math;

    public class Ruut {
        private int a, b, SideLength;

        public Ruut(int x, int y, int SideLength) {
            this.setA(a);
            this.setB(b);
            this.setSideLength(SideLength);
        }
        public int getSideLength() {
            return this.SideLength;
        }

        public void setSideLength(int numSideLength) {
            this.SideLength = numSideLength;
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

        public int pindala() {
            return (SideLength*SideLength);
        }
    }


