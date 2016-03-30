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
            String line;
            while (!(line = br.readLine()).equals("0 0"))
            {
                StringTokenizer st = new StringTokenizer(line);
                int n = Integer.parseInt(st.nextToken()); //[1, 1M]
                int m = Integer.parseInt(st.nextToken()); //[1, 1M]

                // Adjacency list
                HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();

                // Parses edges
                int[] indegrees = new int[n + 1];
                for (int i = 0; i < m; ++i)
                {
                    StringTokenizer dependency = new StringTokenizer(br.readLine());
                    int source = Integer.parseInt(dependency.nextToken());
                    int destination = Integer.parseInt(dependency.nextToken());

                    if (!adjList.containsKey(source))
                        adjList.put(source, new ArrayList<Integer>());

                    indegrees[destination] += 1;
                    adjList.get(source).add(destination);
                }

                LinkedList<Integer> sorted = kahn(adjList, indegrees);

                if (sorted == null)
                {
                    // Cycle was detected
                    out.append("IMPOSSIBLE\n");
                }
                else
                {
                    // Topsort was completed successfully
                    for (int curr : sorted)
                    {
                        if (curr == 0);
                        else
                        {
                            out.append(curr);
                            out.append("\n");
                        }
                    }
                }
            }
			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method

    private static LinkedList<Integer> kahn(HashMap<Integer, ArrayList<Integer>> adj, int[] indegree)
    {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        LinkedList<Integer> finalList = new LinkedList<>();
        for (int i = 1; i < indegree.length; ++i)
            if (indegree[i] == 0)
                queue.add(i);

        int count = 0;
        while (queue.size() != 0)
        {
            int u = queue.remove();
            finalList.addLast(u);
            ++count;

            if (adj.containsKey(u))
            {
                for (int i = 0; i < adj.get(u).size(); ++i)
                {
                    indegree[adj.get(u).get(i)] -= 1;
                    if (indegree[adj.get(u).get(i)] == 0)
                        queue.add(adj.get(u).get(i));
                }
            }
        }

        //Check for cycle
        if (count != indegree.length - 1)
            return null;
        else
            return finalList;
    } // End of kahn's algorithm
} // End of the main class
