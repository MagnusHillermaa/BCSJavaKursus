import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        HashMap<Integer, Node> NodesData = new HashMap<>();
    }
    public static class Node {
        public boolean isGateway = false;
        public int riskLevel = 0;
        public List<Integer> connections = new ArrayList<>();
    }
}
