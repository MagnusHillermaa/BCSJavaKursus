import java.math.BigInteger;

public class Fibionacci {
    public static void main(String args[]) {

        System.out.println("vastus: " + fibionacciLoop(20 ));
    }

    public static int fibionacci(int mitmesArv) {
        return fibionacci(0, 1, mitmesArv);
    }
    public static BigInteger fibionacciLoop(int mitmesArv) {
        return BfibionacciLoop(0,1,mitmesArv);
    }

    public static int fibionacci(int arv, int jargmineArv, int mitmesArv) {
        int tulemus = arv;
        mitmesArv--;
        System.out.println(mitmesArv);
        if (mitmesArv > 0) {
            tulemus = fibionacci(jargmineArv, arv + jargmineArv, mitmesArv);
        }
        return tulemus;
    }
    public static int fibionacciLoop(int arv, int jargmineArv, int mitmesArv){
        int tulemus;
        for (int i=0; i<mitmesArv;i++){
            tulemus = arv + jargmineArv;
            arv = jargmineArv;
            jargmineArv = tulemus;
            System.out.println(i + " - " + tulemus);
            if(tulemus<0){
                return i;
            }

        }
        return mitmesArv;
    }
    public static BigInteger BfibionacciLoop(int arv, int jargmineArv, int mitmesArv){
        BigInteger tulemus = BigInteger.valueOf(arv);
        BigInteger Barv=  BigInteger.valueOf(arv);
        BigInteger BjargmineArv=  BigInteger.valueOf(jargmineArv);
        try {
            for (int i = 0; i < mitmesArv; i++) {
                tulemus = Barv.add(BjargmineArv);
                Barv = BjargmineArv;
                BjargmineArv = tulemus;
                System.out.println(i);

            }
        }catch (Exception e){
            System.out.println(e);
            System.out.println(tulemus);

        }
        return tulemus;
    }
}
