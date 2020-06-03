import java.lang.invoke.SwitchPoint;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;


public class Loeng2VeebiYlesanne3 {
    // generate test data
    // + is virus next to gateway?
    // + mark multi node gateways
    // + distance from multi GNode (node before gateWay)
    //   + measure from node before gateway to virus to score risk
    //
    // + Possibility to trap virus (double link + 2 links in a row)
    //
    // node strukture
    //gatewayConnections - number of gateways connected to node
    //shortest way player int
    //priority shortest way to player -gatewayConnections
    //number of connected links
    public static void main(String args[]) {


        int[][] links;
        int[] gateways;
        int Set = 1;

        links = GenLinks(Set);
        gateways = GenGateways(Set);

        HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
        HashMap<Integer, Node> twoGatewayNodes = new HashMap<Integer, Node>();
        Node nodeA, nodeB;
        Integer virusNode = 1;

        //fill nodes info based on links
        for (int i = 0; i < links.length; i++) {

            //if nodes is in hashmap, then use existing, if not, then initialize new node
            if (nodes.containsKey(links[i][0])) {
                nodeA = nodes.get(links[i][0]);
            } else {
                nodeA = new Node();
                nodeA.id = links[i][0];
                nodeA.isGateway = IsGateway(nodeA.id, gateways);
            }
            if (nodes.containsKey(links[i][1])) {
                nodeB = nodes.get(links[i][1]);
            } else {
                nodeB = new Node();
                nodeB.id = links[i][1];
                nodeB.isGateway = IsGateway(nodeB.id, gateways);
            }

            //add connection of link to both nodes if needed
            nodeA.connectedNodes.putIfAbsent(nodeB.id, nodeB);
            nodeB.connectedNodes.putIfAbsent(nodeA.id, nodeA);
            nodes.putIfAbsent(nodeA.id, nodeA);
            nodes.putIfAbsent(nodeB.id, nodeB);
        }


        //Count connected Gateways for each node
        for (Map.Entry<Integer, Node> node : nodes.entrySet()) {
            if (!node.getValue().isGateway) {
                for (Map.Entry<Integer, Node> connectedNode : node.getValue().connectedNodes.entrySet()) {
                    if (connectedNode.getValue().isGateway) {
                        node.getValue().numberOfConnectedGateways++;
                        //Add 2 gateway nodes to separate HashMap
                        if (node.getValue().numberOfConnectedGateways == 2) {
                            twoGatewayNodes.putIfAbsent(node.getKey(), node.getValue());
                        }
                    }
                }
            }
        }


        //find smallest freedom to each two gateway node from every node
        Node nodeCurr;
        List<Node> unSettled = new ArrayList<>();
        List<Node> settled = new ArrayList<>();
        int freedomForNodeNext;


        for (Map.Entry<Integer, Node> g2Node : twoGatewayNodes.entrySet()
        ) {
            unSettled.clear();
            settled.clear();
            for (Node node : g2Node.getValue().connectedNodes.values()) {
                //Set first leafs freedom -1+(1-nr of leaf connected Gateways)
                if (node.freedom.containsKey(g2Node.getKey())) {
                    if (node.freedom.get(g2Node.getKey()) > 1 - node.numberOfConnectedGateways) {
                        node.freedom.replace(g2Node.getKey(), 1 - node.numberOfConnectedGateways);
                    }
                } else {
                    node.freedom.put(g2Node.getKey(), 1 - node.numberOfConnectedGateways);
                }
                //If node is not Gateway then add to unSettled
                if (!node.isGateway) {
                    unSettled.add(node);
                }
            }
            //Iterate through all leafs from the g2Node
            while (unSettled.listIterator().hasNext()) {
                nodeCurr = unSettled.listIterator().next();
                settled.add(nodeCurr);

                //Loop through nodeCurr leafs
                for (Node nodeLeaf : nodeCurr.connectedNodes.values()
                ) {
                    //If not settled and not Gateway
                    if (!settled.contains(nodeLeaf) && !nodeLeaf.isGateway) {
                        freedomForNodeNext = (1 - nodeLeaf.numberOfConnectedGateways) + nodeCurr.freedom.get(g2Node.getKey());
                        //Set freedom related to two gateway node (g2Node)
                        if (nodeLeaf.freedom.containsKey(g2Node.getKey())) {
                            if (nodeLeaf.freedom.get(g2Node.getKey()) > freedomForNodeNext) {
                                nodeLeaf.freedom.replace(g2Node.getKey(), freedomForNodeNext);
                            }
                        } else {
                            nodeLeaf.freedom.put(g2Node.getKey(), freedomForNodeNext);
                        }
                        unSettled.add(nodeLeaf);
                    }
                }
                unSettled.remove(nodeCurr);
            }
        }

        //Play loop
        //TODO Algorith flaw - if g1Nodes are removed, then connectednodes lengths are not updated
        int minFreedomG2Node = 1; //nasty hack, this is gateway connected node that we will work with
        int minFreedom = 100;
        int minFreedomGatewayNode = 1; //gateway node connected to the focus node

        if ((nodes.get(virusNode).numberOfConnectedGateways == 1)) {
            minFreedomG2Node = virusNode;

        }else{

            for (Map.Entry<Integer, Integer> entry : nodes.get(virusNode).freedom.entrySet()
            ) {
                if (minFreedom > entry.getValue()) {
                    minFreedom = entry.getValue();
                    minFreedomG2Node = entry.getKey();
                }
            }
        }

        //remove identify gateway link for g2Node
        for (Map.Entry<Integer, Node> node : nodes.get(minFreedomG2Node).connectedNodes.entrySet()
        ) {
            if (node.getValue().isGateway) {
                minFreedomGatewayNode = node.getKey();
            }
        }

        //remove link
        System.out.println(minFreedomGatewayNode + " " + minFreedomG2Node);

        //model clean up
        nodes.get(minFreedomG2Node).connectedNodes.remove(minFreedomGatewayNode);
        nodes.get(minFreedomG2Node).numberOfConnectedGateways--;
        for (Node node : nodes.values()
        ) {
            node.freedom.remove(minFreedomG2Node);
        }

        PrintAllNodes(nodes);
    }

    public static void PrintAllNodes(HashMap<Integer, Node> nodes) {
        for (Node node : nodes.values()
        ) {
            System.out.println("id:          " + node.id);
            System.out.println("freedom:     ");

            for (Map.Entry<Integer,Integer> entry : node.freedom.entrySet()
            ) {
                System.out.println("         id: " + entry.getKey() + " - " + entry.getValue());
            }

            System.out.println("isGateway:   " + node.isGateway);
            System.out.println("conGateways: " + node.numberOfConnectedGateways);

            for (Map.Entry entry : node.connectedNodes.entrySet()
            ) {
                System.out.println("         id: " + entry.getKey());

            }

            System.out.println("--------------------------------");

//            public Integer id;
//            // Freedom means number of choices(value) you can do before virus reaches specific gateway(key)
//            public HashMap<Integer,Integer> freedom = new HashMap<>();
//            public boolean isGateway = false;
//            public int numberOfConnectedGateways = 0;
//            public HashMap<Integer, Node> connectedNodes= new HashMap<>();

        }
    }

    public static boolean IsGateway(int node, int[] gateways) {
        for (int gateway : gateways) {
            if (node == gateway) {
                return true;
            }
        }
        return false;
    }

    public static int[][] GenLinks(int Set) {
        int[][] links;

        switch (Set) {
            case 1: {
                links = new int[][]{
                        {1, 2, 1},
                        {1, 3, 1},
                        {2, 4, 1},
                        {2, 6, 1},
                        {3, 5, 1},
                        {3, 6, 1},
                        {4, 6, 1},
                        {4, 7, 1},
                        {5, 6, 1},
                        {5, 8, 1},
                        {6, 7, 1},
                        {6, 8, 1},
                };
                break;
            }
            default: {
                links = new int[][]{};
            }
        }
        return links;
    }

    public static int[] GenGateways(int Set) {
        int[] gateways;
        switch (Set) {
            case 1: {
                gateways = new int[]{7, 8};
                break;
            }
            default: {
                gateways = new int[]{};
            }
        }
        return gateways;
    }
}
