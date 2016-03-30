import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * UVA Question 11045: My T-shirt suits me
 * Question link: https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1986
 * Valid solution: Yes
 */
public class Main02
{
    private static final String[] TYPES = {"XS", "S", "M", "L", "XL", "XXL"};
    private static HashMap<String, Integer> map = new HashMap<>();
    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        // Builds the hashmap indices for O(1) lookup
        map.put("XS", 0);
        map.put("S", 1);
        map.put("M", 2);
        map.put("L", 3);
        map.put("XL", 4);
        map.put("XXL", 5);

        try
        {
            int numberOfCases = Integer.parseInt(br.readLine());

            for (int caseNumber = 0; caseNumber < numberOfCases; ++caseNumber)
            {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken()); // Number of t-shirts (multiples of 6)
                int m = Integer.parseInt(st.nextToken()); // Number of volunteers

                int shirtPackets = n/6;
                int numberOfNodes = 1 + 1 + m + 6; // (V) = Source, sink, each volunteer, and shirt packets nodes

                // Builds the adjacency list
                ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
                for (int i = 0; i < numberOfNodes; ++i)
                    adj.add(new ArrayList<Integer>());

                // Builds the capacity array
                int[][] capacity = new int[numberOfNodes][numberOfNodes];
                int source = 0,  sink = 1, p = 2, sh = 2 + m;

                // Reads in all the volunteer possibly states
                for (int i = 0; i < m; ++i)
                {
                    // Adds the bidirectional path
                    adj.get(source).add(p + i); adj.get(p + i).add(source);
                    capacity[source][p + i] = 1;
                    st = new StringTokenizer(br.readLine());
                    for (int j = 0; j < 2; ++j)
                    {
                        int type = map.get(st.nextToken());
                        // Adds the bidirectional path
                        adj.get(p + i).add(sh + type);
                        adj.get(sh + type).add(p + i);
                        capacity[p + i][sh + type] = 1;
                    }
                }
                for (int i = 0; i < 6; ++i)
                {
                    adj.get(sh + i).add(sink);
                    adj.get(sink).add(sh + i);
                    capacity[sh + i][sink] = shirtPackets;
                }

                int maxFlowValue = maxFlow(adj, capacity, source, sink);

                // Output
                if (maxFlowValue == m)
                    out.append("YES\n");
                else
                    out.append("NO\n");

            } // End of all case iterations
            System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}
    } // End of the main method

    private static int maxFlow(ArrayList<ArrayList<Integer>> adj, int[][] capacity, int source, int sink)
    {
        int ret = 0;
        while (true)
        {
            int f = augment(adj, capacity, source, sink);
            if (f == 0)
                break;
            ret += f;
        }
        return ret;
    } // End of the find maxFlow method

    private static int augment(ArrayList<ArrayList<Integer>> adj, int[][] capacity, int source, int sink)
    {
        Queue<Integer> q = new ArrayDeque<>();
        int[] pred = new int[adj.size()];
        Arrays.fill(pred, -1);
        int[] f = new int[adj.size()];

        pred[source] = source;
        f[source] = Integer.MAX_VALUE;
        q.add(source);

        while (!q.isEmpty())
        {
            int curr = q.poll();
            int currf = f[curr];

            if (curr == sink)
            {
                update(capacity, pred, curr, f[curr]);
                return f[curr];
            }
            for (int i = 0; i < adj.get(curr).size(); ++i)
            {
                int j = adj.get(curr).get(i);
                if ((pred[j] != -1) || (capacity[curr][j] == 0))
                    continue;
                pred[j] = curr;
                f[j] = Math.min(currf, capacity[curr][j]);
                q.add(j);
            }
        } // End of overall looping
        return 0;
    } // End of the augment method

    private static void update(int[][] capacity, int[] pred, int curr, int f)
    {
        int p = pred[curr];
        if (p == curr)
            return;
        capacity[p][curr] -= f;
        capacity[curr][p] += f;
        update(capacity, pred, p, f);
    } // End of the update method
} // End of the main class
