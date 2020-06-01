import java.util.HashMap;
import java.util.List;

public class Node {
    public Integer id;
    public List<Node> riskiestPath;
    int pathFreedom;
    // public boolean isNextToGateway = false;
    public boolean isGateway = false;
    public boolean zeroFreedomPathNode = false;
    public int numberOfConnectedGateways = 0;
    public HashMap<Integer, Node> connectedNodes= new HashMap<>();
    public static void main (String args[]){

    }
}
