import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ref
{
    public static void main(String args[])
    {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        try
        {
            int rows = 0;
            int cols = 0;
            int startX = 0;
            int startY = 0;
            int endX = 9;
            int endY = 9;

            Node[][] graph = new Node[rows][cols];

            // Read in graph
            for (int i = 0; i < rows; ++i)
            {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < cols; ++j)
                    graph[i][j] = new Node(i, j, Integer.parseInt(st.nextToken()));
            }

            Node source = graph[startX][startY];
            Node end = graph[endX][endY];
            int costOfTraversal = dfs(source, end, graph);

            // Output
            out.append(costOfTraversal);
            out.append("\n");
			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method

    private static int dfs(Node source, Node end, Node[][] graph)
    {
        int validValue = 0; // The value of any valid values in graph[][]
        Stack<Node> stack = new Stack<>();
        stack.push(source);

        boolean[][] visited = new boolean[graph.length][graph[0].length]; // Note, this will work for matrix, not array of arrays

        while (stack.size() != 0)
        {
            Node current = stack.pop();
            if (!visited[current.row][current.col])
            {
                // Current has not been visited yet
                visited[current.row][current.col] = true;

                // For each edge from current to its neighbours, push onto stack
                // Top
                int rowCheck = current.row - 1;
                int colCheck = current.col;

                if (rowCheck >= 0)
                {
                    if (graph[rowCheck][colCheck].val == validValue)
                        stack.push(graph[rowCheck][colCheck]);
                }

                // Bot
                rowCheck = current.row + 1;
                colCheck = current.col;
                if (rowCheck < graph.length)
                {
                    if (graph[rowCheck][colCheck].val == validValue)
                        stack.push(graph[rowCheck][colCheck]);
                }

                // Left
                rowCheck = current.row;
                colCheck = current.col - 1;
                if (colCheck >= 0)
                {
                    if (graph[rowCheck][colCheck].val == validValue)
                        stack.push(graph[rowCheck][colCheck]);
                }

                // Right
                rowCheck = current.row;
                colCheck = current.col + 1;
                if (colCheck < graph[0].length)
                {
                    if (graph[rowCheck][colCheck].val == validValue)
                        stack.push(graph[rowCheck][colCheck]);
                }
            } // End of visiting check

        } // End of iterating through dfs
    } // End of dfs


    private static class Node {
        int row;
        int col;
        int val;

        public Node(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    } // End of the node class
} // End of the main class
