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
        System.out.println(olympiaad(1, 97));
    }
    public static int olympiaad(int a, int b){
        return olympiaad(a, b,0);
    }

    public static int olympiaad(int a, int b, int counter){
        System.out.println("counter: " + counter + " | a: " + a + " |b: " + b);
        if (a != 1) {
            if (a % 2 == 1) {
                a = 3 * a + 1;
            } else {
                a = a / 2;
            }
            counter++;
        }
        if (b != 1) {
            if (b % 2 == 1) {
                b = 3 * b + 1;
            } else {
                b = b / 2;
            }
            counter--;
        }
        if (a != b) {
            counter = olympiaad(a, b, counter);
        }
        return abs(counter);
    }

    public static void korrutustabel(){
        int a,b=0;
        String tyhik=" ";
        for (a=1;a<=10;a++){
            for(b=1;b<=10;b++) {
                System.out.print(a * b + tyhik + tyhik + tyhik);
                if((a*b)/100<1){
                    System.out.print(tyhik);
                    if((a*b)/10<1){
                        System.out.print(tyhik);
                    }
                }
            }
            System.out.println();
        }
    }

    public static int min (int a, int b){
        if(a<b){
            return a;
        } else{
            return b;
        }
    }

    public static int abs(int a){
        if(a>=0){
            return a;
        } else{
            return a*(-1);
        }
    }
    public static int fibionacci(int mitmesArv){
        return fibionacci(0,1,mitmesArv);
    }
    public static int fibionacci(int arv, int jargmineArv, int mitmesArv){
        int tulemus = arv;
        mitmesArv--;
        if (mitmesArv>0) {
            tulemus = fibionacci(jargmineArv, arv+jargmineArv,mitmesArv);
        }
        return tulemus;
    }
}
