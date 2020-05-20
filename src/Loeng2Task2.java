import java.util.Scanner;

public class Loeng2Task2 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        calculateArray(x);
    }

    // TODO prindi välja x paaris arvu
    // Näide:
    // Sisend 5
    // Väljund 2 4 6 8 10
    private static void calculateArray(int x) {
        for (int i=2;i<=2*x;i+=2){
            System.out.print(i+" ");
        }
        System.out.println();
    }

}