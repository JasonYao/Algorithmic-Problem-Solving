import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main02
{
    public static int numberCounter;
    public static int[] dfs_num;
    public static int[] dfs_low;
    public static int[] dfs_parent;
    public static ArrayList<Integer> articulationPoints;
    public static void main(String args[])
    {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        try
        {
            int n = Integer.parseInt(br.readLine());

            int cityMapNumber = 1;
            while (n != 0)
            {
                String[] cities = new String[n];
                HashMap<String, Integer> map = new HashMap<>();
                ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
                numberCounter = 0;
                dfs_num = new int[n];
                dfs_low = new int[n];
                dfs_parent = new int[n];
                articulationPoints = new ArrayList<>();

                // Reads in city information
                for (int i = 0; i < n; ++i)
                {
                    cities[i] = br.readLine();
                    map.put(cities[i], i);
                    adj.add(new ArrayList<Integer>());
                }

                // Reads in route information (adjacency list)
                int r = Integer.parseInt(br.readLine()); // number of routes
                for (int i = 0; i < r; ++i)
                {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    // Note: there is no source/destination since routes are bidirectional
                    String city1 = st.nextToken();
                    String city2 = st.nextToken();

                    int city1Index = map.get(city1);
                    int city2Index = map.get(city2);

                    // Adds the directional routes to adjacency list
                    adj.get(city1Index).add(city2Index);
                    adj.get(city2Index).add(city1Index);
                }

                findArticulationPoints(adj);

                // Output
                String output = "City map #" + cityMapNumber + ": " + articulationPoints.size() + " cameras(s) found\n";
                out.append(output);

                for (int current : articulationPoints)
                {
                    out.append(cities[current]);
                    out.append("\n");
                }
                // Resets for next round
                n = Integer.parseInt(br.readLine());
                ++cityMapNumber;
            }
			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method

    private static void findArticulationPoints(ArrayList<ArrayList<Integer>> adj)
    {
        //findArticulationPointsRecursive(adj, 0, 0);

        for (int i = 0; i < adj.size(); ++i)
            if (dfs_num[i] == 0)
                articulationPointAndBridge(adj, i);
    } // End of the find articulation points driver method


    private static void articulationPointAndBridge(ArrayList<ArrayList<Integer>> adj, int u)
    {
        if (dfs_low[u] <= dfs_num[u])
            dfs_low[u] = dfs_num[u] = numberCounter++; // dfs_low[u] <= dfs_num[u]

        for (int i = 0; i < adj.get(u).size(); i++)
        {

            ii v = AdjList[u][j];
            if (dfs_num[v.first] == UNVISITED) {  // a tree edge
                dfs_parent[v.first] = u;
                if (u == dfsRoot)
                    rootChildren++;  // special case
                articulationPointAndBridge(v.first);
                if (dfs_low[v.first] >= dfs_num[u])  // for articulation point
                    articulation_vertex[u] = true;  // store this information first
                if (dfs_low[v.first] > dfs_num[u])  // for bridge
                    printf(" Edge (%d, %d) is a bridge\n", u, v.first);
                dfs_low[u] = min(dfs_low[u], dfs_low[v.first]); // update dfs_low[u]
            }
            else if (v.first != dfs_parent[u]) // a back edge and not direct cycle
                dfs_low[u] = min(dfs_low[u], dfs_num[v.first]); // update dfs_low[u]
        }
    }

//    private static void findArticulationPointsRecursive(ArrayList<ArrayList<Integer>> adj, int index, int currentDepth)
//    {
//        System.out.println("Got to start");
//        visited[index] = true;
//        depth[index] = currentDepth;
//        low[index] = currentDepth;
//        int childCount = 0;
//
//        boolean isArticulation = false;
//
//        for (int i = 0; i < adj.get(i).size(); ++i)
//        {
//            if (!visited[i])
//            {
//                parent[i] = index;
//                findArticulationPointsRecursive(adj, i, currentDepth + 1);
//                childCount += 1;
//                if (low[i] >= depth[index])
//                    isArticulation = true;
//                low[i] = Math.min(low[index], low[i]);
//            }
//            else if (i != parent[i])
//                low[i] = Math.min(low[index], depth[i]);
//        }
//
//        if (((parent[index] != -1) && isArticulation) || (parent[index] == -1 && childCount > 1))
//            articulationPoints.add(index);
//        System.out.println("Got to end");
//    }

} // End of the main class
