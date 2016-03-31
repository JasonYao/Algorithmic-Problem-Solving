import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * UVA Question 10480: Sabotage
 * Question link: https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1421
 * Valid solution: Yes
 */
public class Main04
{
    private static final boolean IS_DEBUG_MODE = false;
    private static final int INF = 100000000; // use 1.10^9 to avoid overflow
    private static int[][] capacity;
    private static int mf, f, source, sink;
    private static Vector<Integer> p = new Vector <> ();

    public static void main(String args[])
    {
        /**
         * Thought process: This is a simple min-cut application problem, where min-cut is defined as:
         * The maximum flow from s to t is the size of the minimum s−t cut.
         * Thus, simply calculate the maxflow, and that will be the cost of sabotaging those communication lines
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        String line;
        try
        {
            boolean isFirstTime = true;
            while (!(line = br.readLine()).equals("0 0"))
            {
                if (isFirstTime)
                {isFirstTime = false;}
                else
                    out.append("\n"); // Empty line between each set

                StringTokenizer st = new StringTokenizer(line);
                int n = Integer.parseInt(st.nextToken()); // Number of cities (nodes)
                int m = Integer.parseInt(st.nextToken()); // Number of connections (edges)
                int numberOfNodes = n + 1; // (V) = Each regular node, as source = capital, sink = largest city, leave 0 empty

                // Initialises the capacity array
                capacity = new int[numberOfNodes][numberOfNodes];

                // Reads in edges
                for (int i = 0; i < m; ++i)
                {
                    StringTokenizer edgeSt = new StringTokenizer(br.readLine());
                    int city1 = Integer.parseInt(edgeSt.nextToken());
                    int city2 = Integer.parseInt(edgeSt.nextToken());
                    int cost = Integer.parseInt(edgeSt.nextToken());

                    capacity[city1][city2] = cost;
                    capacity[city2][city1] = cost;
                }

                if (IS_DEBUG_MODE)
                    test("Capacity matrix internal edges");

                // Copies the capacity array for later comparison
                int[][] originalCapacities = new int[numberOfNodes][numberOfNodes];
                for (int i = 0; i < numberOfNodes; ++i)
                    System.arraycopy(capacity[i], 0, originalCapacities[i], 0, numberOfNodes);

                mf = 0;
                source = 1;
                sink = 2; // From the project specs

                // Runs through Edward Karp's algorithm

                while (true)
                {
                    // run O(VE^2) Edmonds Karp to solve the Max Flow problem
                    f = 0;

                    // run BFS, please examine parts of the BFS code that is different than in Section 4.3
                    Queue< Integer > q = new LinkedList <> ();
                    Vector < Integer > dist = new Vector <> ();
                    dist.addAll(Collections.nCopies(numberOfNodes, INF));
                    q.offer(source);
                    dist.set(source, 0);
                    p.clear();
                    p.addAll(Collections.nCopies(numberOfNodes, -1)); // (we have to record the BFS spanning tree)
                    while (!q.isEmpty())
                    {
                        // (we need the shortest path from s to t!)
                        int u = q.poll();
                        if (u == sink) break; // immediately stop BFS if we already reach sink t
                        for (int v = 1; v < numberOfNodes; v++)
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
                    test("Residual Graph");

                ArrayList<Pair> cityCutSet = new ArrayList<>();
                HashSet<Integer>  validCities = new HashSet<>();

                // Runs DFS through the residual graph to find the set of vertices reachable from source
                Stack<Integer> s = new Stack<>();
                int[] visited = new int[numberOfNodes];
                s.push(source);
                while (s.size() != 0)
                {
                    int v = s.pop();
                    if (visited[v] == 0)
                    {
                        visited[v] = 1;
                        if (!validCities.contains(v))
                            validCities.add(v);
                        for (int i = 1; i < numberOfNodes; ++i)
                        {
                            if (capacity[v][i] != 0)
                                s.add(i);
                        }
                    }
                } // End of dfs

                // We define a cut edge as those from a reachable vertex to a non-reachable vertex as cut edges
                for (int i = 1; i < numberOfNodes; ++i)
                {
                    for (int j = 1; j < numberOfNodes; ++j)
                    {
                        if ((validCities.contains(i)) && (!(validCities.contains(j)) && (i != j) && (originalCapacities[i][j] != 0)))
                        {
                            // Source node is a valid city, non-reachable-node is destination, so i -> j is cut edge
                            cityCutSet.add(new Pair(i, j));
                        }
                    }
                }

                // Output
                for (Pair curr : cityCutSet)
                {
                    out.append(curr.source);
                    out.append(" ");
                    out.append(curr.destination);
                    out.append(" ");
                    out.append("\n");
                }

            } // End of iterating through each set
        }
        catch (IOException e)
        {e.printStackTrace();}
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

    private static class Pair
    {
        int source;
        int destination;

        public Pair(int source, int destination) {
            this.source = source;
            this.destination = destination;
        }
    } // End of the pair class
} // End of the main class
