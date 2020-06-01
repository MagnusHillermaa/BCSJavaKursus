package Loeng4;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class L4T1 {
    public static void main(String[] args) {
        //TODO arvuta kasutades BigDecimali 1.89 * ((394486820345 / 15 ) + 4 )
        Calc();

    }

    public static void Calc(){
        BigDecimal a;
        BigDecimal x = new BigDecimal("394486820345");
        BigDecimal y = new BigDecimal("15");
        BigDecimal z = new BigDecimal("1.89");
        BigDecimal zz = new BigDecimal("4");

        a= x.divide(y, RoundingMode.HALF_UP).add(zz).multiply(z);
        System.out.println(a);
    }

}