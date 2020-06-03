import java.util.HashMap;
import java.util.function.BiFunction;

public class Test {
    public static void main (String args[]){
        HashMap<Integer,Integer> choicesUntilGateway = new HashMap<>();
        BiFunction<Integer, Integer, Integer> replaceBigger = (x1, x2) -> (x1>x2?x2:x1);
        choicesUntilGateway.put(1,2);
        Integer j = 0;
        for (int i=0;i<10;i++) {
            j++;
            System.out.println(choicesUntilGateway.get(1));
        }
    }
}
