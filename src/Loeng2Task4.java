import java.util.Scanner;

public class Loeng2Task4 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        korrutustabel(x,y);
        // DONE trüki välja korrutustabel mis on x ühikut lai ja y ühikut kõrg
        // DONE näiteks x = 3 y = 3
        // DONE väljund
        // 1 2 3
        // 2 4 6
        // 3 6 9
    }

    public static void korrutustabel(int x, int y) {
        int a, b = 0;
        String tyhik = " ";
        for (a = 1; a <= x; a++) {
            for (b = 1; b <= y; b++) {
                System.out.print(a * b + tyhik + tyhik + tyhik);
                if ((a * b) / 100 < 1) {
                    System.out.print(tyhik);
                    if ((a * b) / 10 < 1) {
                        System.out.print(tyhik);
                    }
                }
            }
            System.out.println();
        }
    }
}