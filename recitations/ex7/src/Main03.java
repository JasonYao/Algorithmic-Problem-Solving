import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main03
{
    public static int cycleSteps = Integer.MIN_VALUE;
    public static void main(String args[])
    {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        try
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int rows = Integer.parseInt(st.nextToken()); // Number of rows
            int cols = Integer.parseInt(st.nextToken()); // number of columns
            int entryColumn = Integer.parseInt(st.nextToken()); // where the robot comes in at (1,entryCol)

            while ((rows != 0) || (cols != 0) || (entryColumn != 0))
            {
                boolean[][] status = new boolean[rows + 2][cols + 2];
                //int steps = dfs(0, status, 1, entryColumn);

                // Output
                //String stepCount = steps + " step(s) ";
                //out.append(stepCount);

                String output;
                if (cycleSteps == Integer.MIN_VALUE)
                {
                    // No cycle
                    output = "to exit\n";
                }
                else
                {
                    // Cycle
                    output = "before a loop of " + cycleSteps + " step(s)\n";
                }
                out.append(output);

                // Resets for next round
                st = new StringTokenizer(br.readLine());
                rows = Integer.parseInt(st.nextToken());
                cols = Integer.parseInt(st.nextToken());
                entryColumn = Integer.parseInt(st.nextToken());
                cycleSteps = Integer.MIN_VALUE;
            } // End of iteraing through a test case


			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method
//
//    private static int dfs(int currentStepCount, boolean[][] status, int currentRow, int currentColumn, int offRow, int offCol)
//    {
//        // Break conditions: exit
//        if ((currentColumn == 0) || (currentColumn == offCol) || (currentRow == 0) || (currentRow == offRow))
//            return currentStepCount;
//
//        // Cycle detection
//        if (status[currentRow][currentColumn])
//        {
//
//        }
//
//
//    } // End of dfs

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
//    private static LinkedList<Integer> kahn(ArrayList<ArrayList<Integer>> adj, int[] indegree)
//    {
//        PriorityQueue<Integer> queue = new PriorityQueue<>();
//        LinkedList<Integer> finalList = new LinkedList<>();
//        for (int i = 0; i < indegree.length; ++i)
//            if (indegree[i] == 0)
//                queue.add(i);
//
//        int count = 0;
//        while (queue.size() != 0)
//        {
//            int u = queue.remove();
//            finalList.addLast(u);
//            ++count;
//
//            for (int i = 0; i < adj.get(u).size(); ++i)
//            {
//                indegree[adj.get(u).get(i)] -= 1;
//
//                if (indegree[adj.get(u).get(i)] == 0)
//                    queue.add(adj.get(u).get(i));
//            }
//        }
//        if (count != adj.size())
//            System.out.println("There is a cycle");
//        return finalList;
//    } // End of kahn's algorithm
} // End of the main class
