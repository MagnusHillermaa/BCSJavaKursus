import java.util.HashMap;
import java.util.List;

public class Node_Dep {
    public Integer id;
    // Freedom means number of choices(value) you can do before virus reaches specific gateway(key)
    public HashMap<Integer,Integer> freedom = new HashMap<>();
    public boolean isGateway = false;
    public int numberOfConnectedGateways = 0;
    public HashMap<Integer, Node_Dep> connectedNodes= new HashMap<>();
}
