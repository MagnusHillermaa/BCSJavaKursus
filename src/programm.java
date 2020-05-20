public class programm {
    public static void main(String[] args) {
        /*
        System.out.println("Hello!");
        System.out.println("kirjutame veel midagi");


        korrutustabel();


        System.out.println(
                "MIN: "+
                min (5,6)
                );
        System.out.println(
                "ABS: " +
                abs(5) +" "+
                abs(-4)+" "+
                abs(0) +" "
                );

        System.out.println(fibionacci(1));
        System.out.println(fibionacci(2));
        System.out.println(fibionacci(3));
        System.out.println(fibionacci(4));
        System.out.println(fibionacci(5));
        System.out.println(fibionacci(6));
        System.out.println(fibionacci(500));

        neg(int)
        tagastab negatiivse arvu
        0->0
        1->-1
        -8->-8

        funk(int a, int b, int c)
            tagastab kõige nullile lähema  neg arvu, kui ei ole, siis nullile lähima pos arvu
         */
/*
        // System.out.println(olympiaad_cyclic_lenth(1));


        int olympiaadi_sisend[][] = {
                {1, 10},
                {100, 200},
                {201, 210},
                {900, 1000},
                {1,1},
                {10, 1},
                {1, 10000}
        };
        olympiaad(olympiaadi_sisend);

*/
/*
        System.out.println(negatiivne(5));
        System.out.println(negatiivne(-8));
        System.out.println(negatiivne(0));
*/
        System.out.println(closest_neg_to_zero(0, 0, 0));
        System.out.println(closest_neg_to_zero(0, 1, 10));
        System.out.println(closest_neg_to_zero(10, -11, 12));
        System.out.println(closest_neg_to_zero(-9, -11, 8));
        System.out.println(closest_neg_to_zero(-9, -11, -1));
        System.out.println(closest_neg_to_zero(12, 11, 7));
    }

    public static int closest_neg_to_zero(int a, int b, int c) {
        int closest_to_zero;

        if (a == 0 || b == 0 || c == 0) {
            return 0;
        }
        if (a > 0 && b > 0 && c > 0) {
            closest_to_zero = (a < b) && (a < c) ? a : b;
            closest_to_zero = (closest_to_zero < c) ? closest_to_zero : c;
        }else{
            closest_to_zero = ((1 / (float) a * -1) > (1 / (float) b * -1) && (1 / (float) a * -1) > (1 / (float) c * -1)) ? a : b;
            closest_to_zero = ((1 / (float) closest_to_zero * -1) > (1 / (float) c * -1)) ? closest_to_zero : c;
        }

        return closest_to_zero;
    }

    public static int negatiivne(int a) {
        return (a > 0) ? a * -1 : a;
    }

    public static void olympiaad(int[][] twoIntArray) {
        int i, smallerInt, biggerInt, counter, maxCounter = 0, maxInt0 = 0, maxInt1 = 0;

        for (i = 0; i < twoIntArray.length; i++) {
            if (twoIntArray[i][0] < twoIntArray[i][1]) {
                smallerInt = twoIntArray[i][0];
                biggerInt = twoIntArray[i][1];
            } else {
                smallerInt = twoIntArray[i][1];
                biggerInt = twoIntArray[i][0];
            }
            while (smallerInt <= biggerInt) {
                counter = olympiaad_cyclic_lenth(smallerInt);
                smallerInt++;
                if (maxCounter < counter) {
                    maxCounter = counter;
                    maxInt0 = twoIntArray[i][0];
                    maxInt1 = twoIntArray[i][1];
                }
            }
            System.out.println(maxInt0 + " " + maxInt1 + " " + maxCounter);
            maxCounter = 0;
        }
    }

    public static int olympiaad_cyclic_lenth(int a) {
        return olympiaad_cyclic_lenth(a, 1);
    }

    public static int olympiaad_cyclic_lenth(int a, int counter) {
        //System.out.println("counter: " + counter + " | a: " + a);
        if (a != 1) {
            if (a % 2 == 1) {
                a = 3 * a + 1;
            } else {
                a = a / 2;
            }
            counter++;
            counter = olympiaad_cyclic_lenth(a, counter);
        }

        return counter;
    }

    public static void korrutustabel() {
        int a, b = 0;
        String tyhik = " ";
        for (a = 1; a <= 10; a++) {
            for (b = 1; b <= 10; b++) {
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

    public static int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    public static int abs(int a) {
        if (a >= 0) {
            return a;
        } else {
            return a * (-1);
        }
    }

    public static int fibionacci(int mitmesArv) {
        return fibionacci(0, 1, mitmesArv);
    }

    public static int fibionacci(int arv, int jargmineArv, int mitmesArv) {
        int tulemus = arv;
        mitmesArv--;
        if (mitmesArv > 0) {
            tulemus = fibionacci(jargmineArv, arv + jargmineArv, mitmesArv);
        }
        return tulemus;
    }
}
