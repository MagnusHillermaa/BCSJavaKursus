import java.lang.invoke.SwitchPoint;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;


public class Loeng2VeebiYlesanne3 {

    public static class Node {
        public Integer id;
        // Freedom means number of choices(value) you can do before virus reaches specific gateway(key)
        public HashMap<Integer, Integer> freedom = new HashMap<>();
        public boolean isGateway = false;
        public int numberOfConnectedGateways = 0;
        public HashMap<Integer, Node> connectedNodes = new HashMap<>();
    }


    public static void main(String args[]) {

        int[][] links;
        int[] gateways;
        int Set = 1;
        Integer virusNode = 1;

        links = GenLinks(Set);
        gateways = GenGateways(Set);


        HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
        HashMap<Integer, Node> twoGatewayNodes = new HashMap<Integer, Node>();
        Node nodeA, nodeB;

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


        //For each g2Nodes populate nodes with freedom
        for (Map.Entry<Integer, Node> g2Node : twoGatewayNodes.entrySet()
        ) {
            unSettled.clear();
            settled.clear();
            g2Node.getValue().freedom.put(g2Node.getKey(), -1);
            //For each node in g2Nodes connected Nodes
            for (Node node : g2Node.getValue().connectedNodes.values()) {
                //Set first leafs freedom g2Node freedom (-1)+(1-nr of leaf connected Gateways)
                if (node.freedom.containsKey(g2Node.getKey())) {
                    if (node.freedom.get(g2Node.getKey()) > -node.numberOfConnectedGateways) {
                        node.freedom.replace(g2Node.getKey(), -node.numberOfConnectedGateways);
                    }
                } else {
                    node.freedom.put(g2Node.getKey(), -node.numberOfConnectedGateways);
                }
                //If node is not Gateway then add to unSettled
                if (!node.isGateway) {
                    unSettled.add(node);
                }
            }
            settled.add(g2Node.getValue());

            //Iterate through all leafs from the g2Node
            while (unSettled.listIterator().hasNext()) {
                nodeCurr = unSettled.listIterator().next();
                settled.add(nodeCurr);

                //Loop through nodeCurr leafs
                for (Node nodeCurrLeaf : nodeCurr.connectedNodes.values()
                ) {
                    //If not settled and not Gateway
                    if (!settled.contains(nodeCurrLeaf) && !nodeCurrLeaf.isGateway) {
                        freedomForNodeNext = (1 - nodeCurrLeaf.numberOfConnectedGateways) + nodeCurr.freedom.get(g2Node.getKey());
                        //Set freedom related to two gateway node (g2Node)
                        if (nodeCurrLeaf.freedom.containsKey(g2Node.getKey())) {
                            if (nodeCurrLeaf.freedom.get(g2Node.getKey()) > freedomForNodeNext) {
                                nodeCurrLeaf.freedom.replace(g2Node.getKey(), freedomForNodeNext);
                            }
                        } else {
                            nodeCurrLeaf.freedom.put(g2Node.getKey(), freedomForNodeNext);
                        }
                        unSettled.add(nodeCurrLeaf);
                    }
                }
                unSettled.remove(nodeCurr);
            }
        }

        //Play loop
        //TODO Algorith flaw - if g1Nodes are removed, then connectednodes lengths are not updated
        int minFreedomG2Node = 1; //nasty hack, this is gateway connected node that we will work with
        int minFreedom = 100; //impossibly high number
        int minFreedomGatewayNode = 1; //gateway node connected to the focus node

        //If next to gateway, then remove this link
        if ((nodes.get(virusNode).numberOfConnectedGateways == 1)) {
            minFreedomG2Node = virusNode;

        } else if (nodes.get(virusNode).freedom.size() == 0) {
            for (Node node : nodes.values()
            ) {
                if (!node.isGateway && node.numberOfConnectedGateways == 1) {
                    minFreedomG2Node = node.id;
                }
                ;
            }
        } else {
            //find lowest freedom gateway
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
        PrintAllNodes(nodes);

        //model clean up
        nodes.get(minFreedomG2Node).connectedNodes.remove(minFreedomGatewayNode);
        nodes.get(minFreedomG2Node).numberOfConnectedGateways--;
        for (Node node : nodes.values()
        ) {
            node.freedom.remove(minFreedomG2Node);
        }
    }

    public static void PrintAllNodes(HashMap<Integer, Node> nodes) {
        for (Node node : nodes.values()
        ) {
            System.out.println("id:          " + node.id);
            System.out.println("freedom:     ");

            for (Map.Entry<Integer, Integer> entry : node.freedom.entrySet()
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
                        {1, 6, 1},
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
