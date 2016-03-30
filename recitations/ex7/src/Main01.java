import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main01
{
    public static void main(String args[])
    {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        try
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // Number of nodes
            int m = Integer.parseInt(st.nextToken()); // number of edges

            while ((n != 0) && (m != 0))
            {
                ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
                for (int i = 0; i <= n; ++i)
                    adj.add(new ArrayList<Integer>());

                // Parses edges
                int[] indegrees = new int[n + 1];
                for (int i = 0; i < m; ++i)
                {
                    StringTokenizer line = new StringTokenizer(br.readLine());
                    int source = Integer.parseInt(line.nextToken());
                    int destination = Integer.parseInt(line.nextToken());
                    indegrees[source] += 1;
                    adj.get(destination).add(source);
                }

                //TODO remove after for testing
//                System.out.println("-----------------------------");
//                for (int i = 0; i < indegrees.length; ++i)
//                    System.out.print(indegrees[i] + " ");
//                System.out.println("\n-----------------------------");

                // Topsorts
                //ArrayList<Integer> sorted = topSort(adj);
                LinkedList<Integer> sorted = kahn(adj, indegrees);
                Collections.reverse(sorted);

                // Output
                boolean isFirst = true;
                for (Integer current : sorted)
                {
                    if (current == 0);
                    else if (isFirst)
                    {
                        isFirst = false;
                        out.append(current);
                    }
                    else
                    {
                        String currentLine = " " + current;
                        out.append(currentLine);
                    }
                }
                out.append("\n");

                // Resets for next round
                st = new StringTokenizer(br.readLine());
                n = Integer.parseInt(st.nextToken());
                m = Integer.parseInt(st.nextToken());
            } // End of iteraing through a test case


			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method

//    private static void dfs(ArrayList<ArrayList<Integer>> adj, int vert, int[] state, ArrayList<Integer> list)
//    {
//        if (state[vert] != 0) return;
//        state[vert] = 1;
//        for (int i = 0; i < adj.get(vert).size(); ++i)
//            dfs(adj, adj.get(vert).get(i), state, list);
//        state[vert] = 2;
//        list.add(vert);
//    }
//
//    private static ArrayList<Integer> topSort(ArrayList<ArrayList<Integer>> adj)
//    {
//        ArrayList<Integer> list = new ArrayList<>();
//        int[] state = new int[adj.size()];
//        for (int i = 0; i < state.length; ++i)
//            if (state[i] == 0)
//                dfs(adj, i, state, list);
//        Collections.reverse(list);
//        return list;
//    } // End of dfs
    private static LinkedList<Integer> kahn(ArrayList<ArrayList<Integer>> adj, int[] indegree)
    {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        LinkedList<Integer> finalList = new LinkedList<>();
        for (int i = 0; i < indegree.length; ++i)
            if (indegree[i] == 0)
                queue.add(i);

        int count = 0;
        while (queue.size() != 0)
        {
            int u = queue.remove();
            finalList.addLast(u);
            ++count;

            for (int i = 0; i < adj.get(u).size(); ++i)
            {
                indegree[adj.get(u).get(i)] -= 1;

                if (indegree[adj.get(u).get(i)] == 0)
                    queue.add(adj.get(u).get(i));
            }
        }
        if (count != adj.size())
            System.out.println("There is a cycle");
        return finalList;
    } // End of kahn's algorithm
} // End of the main class
