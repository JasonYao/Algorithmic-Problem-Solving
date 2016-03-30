import java.util.*;

public class Main02
{
    private static double[][] weights;

    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        StringBuilder out = new StringBuilder();

        while (in.hasNextInt())
        {
            int n = in.nextInt(); // Number of buildings
            Node[] buildings = new Node[n];
            weights = new double[n][n];
            for (int i = 0; i < n; ++i)
                Arrays.fill(weights[i], Double.MAX_VALUE);

            // Reads in all building locations
            for (int i = 0; i < n; ++i)
            {
                int x = in.nextInt();
                int y = in.nextInt();
                buildings[i] = new Node(x, y, i, new ArrayList<Node>());
            }

            // Calulates distances for each node
            for (int i = 0; i < n; ++i)
            {
                for (int j = 0; j < n; ++j)
                {
                    if (weights[i][j] != Double.MAX_VALUE && weights[j][i] != Double.MAX_VALUE)
                        continue;

                    if (i != j)
                    {
                        double xSquared = Math.pow(buildings[i].x - buildings[j].x, 2);
                        double ySquared = Math.pow(buildings[i].y - buildings[j].y, 2);
                        double distance = Math.sqrt(xSquared + ySquared);

                        weights[i][j] = Math.min(weights[i][j], distance);
                        weights[j][i] = Math.min(weights[j][i], distance);
                    }
                }
            }

            // Deals with existing cables
            int m = in.nextInt(); // Number of existing cables
            for (int i = 0; i < m; ++i)
            {
                int building1 = in.nextInt() - 1;
                int building2 = in.nextInt() - 1;

                weights[building1][building2] = 0;
                weights[building2][building1] = 0;
            }

            // TODO remove after testing
//            printWeights(n);

            // Builds adjacency lists
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < n; ++j)
                    if (i != j)
                        buildings[i].adj.add(buildings[j]);

            /**
             * The general solution:
             * We generate a minimum spanning tree via prim's algorithm,
             * in which any prior cables's weights are set to 0
             */

            double totalCableRequired = prims(buildings, n);
            out.append(String.format("%.2f", totalCableRequired));
            out.append("\n");
        }

        System.out.print(out);

    } // End of the main method

    private static double prims(Node[] buildings, int n)
    {
        double minimumSpanningCost = 0;
        int sourceNode = 0; // Arbitrarily

        double[] distance = new double[n]; // shortest distance from source
        Arrays.fill(distance, Double.MAX_VALUE);
        distance[sourceNode] = 0;
        ArrayList<Node> MST = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(sourceNode);

        int[] markedNodes = new int[n];
        // Processes the nodes

        while (queue.size() != 0)
        {
            int current = queue.remove();
            markedNodes[current] = 1;
            MST.add(buildings[current]);

            // Updates the distance value for all neighbors
            for (Node neighbour : buildings[current].adj)
            {
                if (markedNodes[neighbour.index] == 0)
                {
                    //TODO remove after testing
//                    System.out.println("Altering for neighbour: " + neighbour.index);
                    distance[neighbour.index] = Math.min(distance[neighbour.index], weights[current][neighbour.index]);
                }
            }


            //TODO remove after
//            System.out.println("------------------------------------------");
//            for (int i = 0; i < n; ++i)
//            {
//                if (distance[i] == Double.MAX_VALUE)
//                    System.out.printf("INF\t");
//                else
//                    System.out.printf("%.3f\t", distance[i]);
//            }
//            System.out.println("\n------------------------------------------");


            // First does check to see if ANY valid neighbour nodes are left
            int lowestUnvisitedNeighbourIndex = -1;
            for (int i = 0; i < n; ++i)
                if (markedNodes[i] == 0)
                    lowestUnvisitedNeighbourIndex = i;

            if (lowestUnvisitedNeighbourIndex == -1)
            {
                // No valid unvisited neighbours, prims is complete
                //TODO remove after testing
//                System.out.println("No valid unvisited neighbours, prims is complete");
            }
            else
            {
                //TODO remove after testing
//                System.out.println("Unmarked neighbours found");
                // Finds the lowest
                for (Node neighbour : buildings[current].adj)
                    if ((markedNodes[neighbour.index] == 0) && (distance[neighbour.index] < distance[lowestUnvisitedNeighbourIndex]))
                        lowestUnvisitedNeighbourIndex = neighbour.index;

                //TODO remove after testing
//                System.out.println("index of lowest unvisted neighbour by weight is: " + lowestUnvisitedNeighbourIndex);

                // Adds the lowest to the MST
                minimumSpanningCost += distance[lowestUnvisitedNeighbourIndex];

                //TODO remove after testing
//                System.out.println("minimum spanning cost is: " + minimumSpanningCost);
                queue.add(lowestUnvisitedNeighbourIndex);
            }
        } // End of Prims looping
        return minimumSpanningCost;
    } // End of kruskal's algorithm

    private static void printWeights(int n)
    {
        System.out.println("-----------------------------Weights[][]-------------------------------------------");
        for (int i = 0; i < n; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (weights[i][j] == Double.MAX_VALUE)
                    System.out.print("INF\t\t");
                else
                    System.out.printf("%.3f\t\t", weights[i][j]);
            }
            System.out.println();
        }
        System.out.println("-----------------------End of Weights[][]-------------------------------------------------");

    } // End of the print weights method

    private static class Node
    {
        int x;
        int y;
        int index;
        ArrayList<Node> adj;

        public Node(int x, int y, int index, ArrayList<Node> adj) {
            this.x = x;
            this.y = y;
            this.index = index;
            this.adj = adj;
        }
    } // End of the node class
} // End of the main class
