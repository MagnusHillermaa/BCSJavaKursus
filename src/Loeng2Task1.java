import java.util.Scanner;

public class Loeng2Task1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(abs(n));

// DONE, lisa siia klassi eelmises tunnis tehtud abs() meetod
// DONE anna konsoolilt sisseloetud number funktsioonile ette ja prindi välja väljund

    }


    public static int abs(int a) {
        if (a >= 0) {
            return a;
        } else {
            return a * (-1);
        }
    }

}
