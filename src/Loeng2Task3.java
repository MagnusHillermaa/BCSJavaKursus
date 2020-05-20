import java.util.Scanner;

public class Loeng2Task3 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int[] taisarvudeMassiiv= new int[10];
        for (int i=0;i<taisarvudeMassiiv.length;i++){
            taisarvudeMassiiv[i] = in.nextInt();
        }

        for (int i=taisarvudeMassiiv.length-1;i>=0;i--){
            System.out.println(taisarvudeMassiiv[i]);
        }

        // DONE loo 10 elemendile täisarvude massiv
        // DONE loe sisse konsoolist 10 täisarvu
        // DONE trüki arvud välja vastupidises järiekorras
    }
}
