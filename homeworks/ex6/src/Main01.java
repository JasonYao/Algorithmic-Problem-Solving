import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main01
{
    private static int blackCount;
    private static int whiteCount;
    private static char[][] board;
    private static boolean[][] visited;
    private static Node[][] graph;

    public static void main(String args[])
    {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        try
        {
            // Why was this not included inside the sample input z.z, also, the wording is terrible
            int numberOfTestCases = Integer.parseInt(br.readLine());

            for (int currentTestCase = 0; currentTestCase < numberOfTestCases; ++currentTestCase)
            {
                blackCount = 0;
                whiteCount = 0;

                int board_size = 11; // 9 + 2, where 9 is original board size, and 2 is the padding for top/bot/left/right
                board = new char[board_size][board_size];
                visited = new boolean[board_size][board_size];
                graph = new Node[board_size][board_size];

                // Initialises node graph
                for (int i = 0; i < board_size; ++i)
                {
                    for (int j = 0; j < board_size; ++j)
                        graph[i][j] = new Node(i, j, new ArrayList<Node>());
                }

                // Pads top and bottom (inefficient due to caching, but simple)
                for (int i = 0; i < board_size; ++i)
                    board[0][i] = board[board_size - 1][i] = '#';

                // Reads in middle
                for (int i = 1; i < board_size - 1; ++i)
                {
                    board[i][0] = '#'; // Pads left
                    String line = br.readLine();
                    for (int j = 1; j < board_size - 1; ++j)
                    {
                        board[i][j] = line.charAt(j - 1);
                        if (board[i][j] == 'X')
                            ++blackCount;
                        if (board[i][j] == 'O')
                            ++whiteCount;
                    } // End of column iteration
                    board[i][board_size - 1] = '#'; // Pads right
                } // End of reading in graph

                // Builds adj list
                for (int i = 1; i < board_size - 1; ++i)
                {
                    for (int j = 1; j < board_size - 1; ++j)
                    {
                        if (board[i][j] == '.')
                        {
                            Node current = graph[i][j];
                            if (board[i - 1][j] == '.')
                            {current.adj.add(graph[i - 1][j]);} // End of top
                            if (board[i + 1][j] == '.')
                            {current.adj.add(graph[i + 1][j]);} // End of bottom
                            if (board[i][j - 1] == '.')
                            {current.adj.add(graph[i][j - 1]);} // End of left
                            if (board[i][j + 1] == '.')
                            {current.adj.add(graph[i][j + 1]);} // End of right
                        } // End of adding other empties
                    }
                }

                // Identifies a source node for each empty space via BFS & iteration, marking prior visited nodes.
                // NOTE: not really O(n^2 * (V*E)), since visted nodes are just continued
                for (int i = 1; i < board_size - 1; ++i)
                {
                    for (int j = 1; j < board_size - 1; ++j)
                    {
                        if ((board[i][j] == '.') && (!visited[i][j]))
                            bfs(i, j);
                    }
                }

                // Output
                String output = "Black " + blackCount + " White " + whiteCount + "\n";
                out.append(output);

                // Reset for next round
                blackCount = 0;
                whiteCount = 0;

            } // End of iterating through each test case
			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method

    private static void bfs(int i, int j)
    {
        Node source = graph[i][j];

        Queue<Node> queue = new LinkedList<>();

        int setWhiteCount = 0;
        int setBlackCount = 0;

        source.distance = 0;
        visited[i][j] = true;
        queue.add(source);

        int setCount = 0;
        for (; queue.size() != 0; ++setCount)
        {
            Node current = queue.remove();
            visited[current.y][current.x] = true;

            // Checks top
            if (board[current.y - 1][current.x] == 'X')
                ++setBlackCount;
            else if (board[current.y - 1][current.x] == 'O')
                ++setWhiteCount;

            // Checks bot
            if (board[current.y + 1][current.x] == 'X')
                ++setBlackCount;
            else if (board[current.y + 1][current.x] == 'O')
                ++setWhiteCount;

            // Checks left
            if (board[current.y][current.x - 1] == 'X')
                ++setBlackCount;
            else if (board[current.y][current.x - 1] == 'O')
                ++setWhiteCount;

            // Checks right
            if (board[current.y][current.x + 1] == 'X')
                ++setBlackCount;
            else if (board[current.y][current.x + 1] == 'O')
                ++setWhiteCount;

            for (Node neighbor : current.adj)
            {
                if (neighbor.distance == Integer.MAX_VALUE)
                {
                    neighbor.distance = current.distance + 1;
                    neighbor.parent = current;
                    queue.add(neighbor);
                }
            }
        } // End of bfs run

        if ((setBlackCount != 0) && (setWhiteCount == 0))
            blackCount += setCount;
        else if ((setBlackCount == 0) && (setWhiteCount != 0))
            whiteCount += setCount;
    } // End of bfs

    private static class Node
    {
        int y;
        int x;
        ArrayList<Node> adj;
        int distance;
        Node parent;

        public Node(int y, int x, ArrayList<Node> adj) {
            this.y = y;
            this.x = x;
            this.adj = adj;
            distance = Integer.MAX_VALUE;
            parent = null;
        }
    } // End of the node class
} // End of the main class
