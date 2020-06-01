package Loeng4;

import java.math.BigDecimal;

public class L4T2 {
        public static void main(String[] args) {
            float a  = (float) 1.89;
            System.out.println("float: " + a*11);
            BigDecimal b = new BigDecimal("1.89");

            System.out.println("BigDecimal: " + b.multiply(BigDecimal.valueOf(11)));

            // TODO loo float muutuja ja väärtusta see 1.89
            // TODO korruta see läbi täisarvuga 11
            // TODO trüki tulemus välja
            // TODO nüüd tee seda kasutades klassi BigDecimal ja võrdle tulemust
        }


}
