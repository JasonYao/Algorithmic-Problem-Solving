import java.util.*;

/**
 * UVA Question 10330: Power Transmission
 * Question link: https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=15&page=show_problem&problem=1271
 * Valid solution: Yes
 */
public class Main03
{
    private static final boolean IS_DEBUG_MODE = false;
    private static final int INF = 100000000; // use 1.10^9 to avoid overflow
    private static int[][] capacity;
    private static int mf, f, source, sink;
    private static Vector<Integer> p = new Vector <> ();

    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        StringBuilder out = new StringBuilder();

        while (in.hasNextInt())
        {
            int n = in.nextInt(); // Number of regulators
            int numberOfNodes = 1 + 1 + (2 * n); // (V) = Source, sink, each regulator node, and each internal node

            // Initialises the capacity array
            capacity = new int[numberOfNodes][numberOfNodes];

            // Reads in node capacities
            for (int i = 1; i <= n; i++)
                capacity[i][i + n] = in.nextInt(); // Creates an edge from source to internal node with weight capacity

            if (IS_DEBUG_MODE)
                test("Initial node capacities");

            /**
             * Note: when reasoning about node capacities, we have to just add another node, then add an edge to that node.
             * Thus our graph's index looks like:
             * Source | normal edges | internal edges | sink
             */

            int m = in.nextInt(); // Number of links (edges) among capacitors (note, not including connecting source/sink)
            // Reads in edges
            for (int i = 0; i < m; ++i)
            {
                int source = in.nextInt();
                int destination = in.nextInt();
                int weight = in.nextInt();
                capacity[source + n][destination] = weight;
            }

            if (IS_DEBUG_MODE)
                test("Capacity matrix internal edges");

            int b = in.nextInt(); // Number of regulators that are entry nodes (connected to barisal)
            int d = in.nextInt(); // Number of regulators that are exit nodes (connected to dhaka)

            mf = 0;
            source = 0;
            sink = (2 * n) + 1; // We define n + 1 + node capacities as the sink index

            // Sets the incoming edges from the source
            for (int i = 0; i < b; ++i)
            {
                int entryNodeIndex = in.nextInt();
                capacity[source][entryNodeIndex] = INF;
            }

            // Sets the outgoing edges to the sink
            for (int i = 0; i < d; ++i)
            {
                int exitNodeIndex = in.nextInt();
                capacity[exitNodeIndex + n][sink] = INF;
            }

            if (IS_DEBUG_MODE)
                test("Capacity matrix connecting edges");

            // Runs through Edward Karp's algorithm

            while (true)
            {
                // run O(VE^2) Edmonds Karp to solve the Max Flow problem
                f = 0;

                // run BFS, please examine parts of the BFS code that is different than in Section 4.3
                Queue< Integer > q = new LinkedList <> ();
                Vector < Integer > dist = new Vector <> ();
                dist.addAll(Collections.nCopies(numberOfNodes, INF)); // #define INF 2000000000
                q.offer(source);
                dist.set(source, 0);
                p.clear();
                p.addAll(Collections.nCopies(numberOfNodes, -1)); // (we have to record the BFS spanning tree)
                while (!q.isEmpty())
                {
                    // (we need the shortest path from s to t!)
                    int u = q.poll();
                    if (u == sink) break; // immediately stop BFS if we already reach sink t
                    for (int v = 0; v < numberOfNodes; v++)
                    {
                        // note: enumerating neighbors with AdjMatrix is `slow'
                        if (capacity[u][v] > 0 && dist.get(v) == INF) {
                            // res[u][v] can change!
                            dist.set(v, dist.get(u) + 1);
                            q.offer(v);
                            p.set(v, u); // parent of vertex v is vertex u
                        }
                    }
                }

                augment(sink, INF); // find the min edge weight `f' along this path, if any
                if (f == 0) break; // if we cannot send any more flow (`f' = 0), terminate the loop
                mf += f; // we can still send a flow, increase the max flow!
            }

            if (IS_DEBUG_MODE)
                test("Final capacity matrix");

            // Output
            out.append(mf);
            out.append("\n");
        } // End of iterating through all test cases

            System.out.print(out);
    } // End of the main method

    private static void augment(int v, int minEdge)
    {
        // traverse the BFS spanning tree as in print_path (section 4.3)
        if (v == source) { f = minEdge;} // reach the source, record minEdge in a global variable `f'
        else if (p.get(v) != -1) { augment(p.get(v), Math.min(minEdge, capacity[p.get(v)][v])); // recursive call
            capacity[p.get(v)][v] -= f; capacity[v][p.get(v)] += f; } // alter residual capacities
    } // End of the augment method

    private static void test(String s)
    {
        System.out.println("--------------------  Start " + s + " --------------------");
        for (int[] aCapacity : capacity) {
            for (int anACapacity : aCapacity) {
                if (anACapacity == INF)
                    System.out.print("INF\t");
                else
                    System.out.print(anACapacity + "\t");
            }
            System.out.println();
        }
        System.out.println("--------------------  End " + s + " --------------------");
    } // End of test method
} // End of the main class
