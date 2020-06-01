import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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
        Node nodeA;
        Node nodeB;

        for (int i = 0; i < links.length; i++) {

            //if nodes in hashmap, then use them, if not, then initialize new node
            if (nodes.containsKey(links[i][1])) {
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

            nodeA.connectedNodes.computeIfAbsent(nodeB.id, nodeB);
            nodeB.connectedNodes.computeIfAbsent(nodeB.id, nodeA);

            //check if connected node exists
            if (!(nodeA.connectedNodes.containsKey(nodeB.id))){
                nodeA.connectedNodes.(nodeB);
            }
            nodeA.connectedNodes.add(nodeB);
            nodeB.connectedNodes.add(nodeA);
            for (int gateway : gateways) {
                if (links[i][1] == gateway) {
                    nodeA.isGateway = true;

                }
                ;
            }
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
