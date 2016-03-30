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

            int currentTestCase = 1;
            while (true)
            {
                String line = br.readLine();
                if (line == null)
                    break;

                int n = Integer.parseInt(line); // Number of available beverages

                String[] graph = new String[n];
                HashMap<String, Integer> map = new HashMap<>();
                ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

                // Reads in node information
                for (int i = 0; i < n; ++i)
                {
                    String current = br.readLine();
                    graph[i] = current;
                    map.put(current, i);
                    adj.add(new ArrayList<Integer>());
                }

                int m = Integer.parseInt(br.readLine()); // Number of edges

                // Reads in edge information
                int[] indegrees = new int[n];

                for (int i = 0; i < m; ++i)
                {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    String source = st.nextToken();
                    String destination = st.nextToken();

                    int sourceIndex = map.get(source);
                    int destinationIndex = map.get(destination);

                    indegrees[destinationIndex] += 1;

                    adj.get(sourceIndex).add(destinationIndex);
                }

                //ArrayList<Integer> sorted = topSort(adj);
                LinkedList<Integer> sorted = kahn(adj, indegrees);

                // Output
                String caseNum = "Case #" + currentTestCase + ": Dilbert should drink beverages in this order: ";
                out.append(caseNum);
                boolean hasPrintedFirstTime = false;
                for (int index : sorted)
                {
                    String current = graph[index];
                    if (!hasPrintedFirstTime)
                    {
                        hasPrintedFirstTime = true;
                        out.append(current);
                    }
                    else
                    {
                        String output = " " + current;
                        out.append(output);
                    }
                }
                out.append(".\n\n");

                // Resets for next run
                if ((line = br.readLine()) == null)
                    break;

                ++currentTestCase;
            }
			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method

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
