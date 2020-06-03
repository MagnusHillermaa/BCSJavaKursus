//import java.util.Scanner;
//
//class Player {
//
//    public static void main(String args[]) {
//        int N = 8; // the total number of nodes in the level, including the gateways
//        int L = 13; // the number of links
//        int E = 2; // the number of exit gateways
//        int[][] in = {
//            {1,2},
//            {1,3},
//            {1,6},
//            {2,4},
//            {2,6},
//            {3,6},
//            {3,5},
//            {4,6},
//            {4,7},
//            {5,6},
//            {5,8},
//            {7,6},
//            {8,6}
//        };
//
//        int[][] links= new int[L][3];
//        int[] gateway=new int[E];
//        int bestChoice, j, k, l;
//        for (int i = 0; i < L; i++) {
//            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
//            int N2 = in.nextInt();
//            // salvesta ühendused
//            links[i][0]=N1;
//            links[i][1]=N2;
//            links[i][2]=1;
//        }
//        for (int i = 0; i < E; i++) {
//            int EI = in.nextInt(); // the index of a gateway node
//            gateway[i] = EI;
//        }
//        int[][] nodes
//
//        // game loop
//        while (true) {
//            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
//            bestChoice = -1;
//            for (l=0;l<L;l++){
//                for(j=0;j<2;j++){
//                    if(links[l][j]==SI){
//                        for(k=0;k<E;k++){
//                            if(links[l][2]==1&&(links[l][0]==gateway[k] || links[l][1]==gateway[k])){
//                                bestChoice = l;
//                            }
//                        }
//
//                    }
//                }
//            }
//            if (bestChoice == -1){
//                for (l=0;l<L;l++){
//                    for(j=0;j<2;j++){
//                        for(k=0;k<E;k++){
//                            if(links[l][2]==1&&(links[l][j]==gateway[k] ||links[l][1]==gateway[k])){
//                                bestChoice = l;
//                            }
//                        }
//                    }
//                }
//            }
//            if (bestChoice == -1){
//                for (l=0;l<L;l++){
//                    if(links[l][2]==1){
//                        bestChoice=l;
//                    }
//                }
//            }
//            System.out.println(links[bestChoice][0]+" "+links[bestChoice][1]);
//            links[bestChoice][2]=0;
//
//            // Write an action using System.out.println()
//            // To debug: System.err.println("Debug messages...");
//
//
//            // Example: 0 1 are the indices of the nodes you wish to sever the link between
//            // System.out.println("0 1");
//        }
//    }
//}