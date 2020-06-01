package Loeng4;


public class L4T3 {


     //Loo uus klass ruut
     //ruudu kontstruktor peab vastu võtma x ja y kordinaadi ning külje pikkuse.
     //objektil peab olema meetod pindala, mis tagastab ruudu pindala

        public static void main(String[] args){

            Pair a = new Pair(1, 2);

            System.out.println(a.getA());
            System.out.println(a.getB());
            System.out.println(a.getLength());

            Ruut r = new Ruut(1,2,10);
            System.out.println(r.pindala());
        }

}


