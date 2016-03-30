import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main02
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
                int r = Integer.parseInt(st.nextToken()); // rows
                int c = Integer.parseInt(st.nextToken()); // cols

                int[][] graph = new int[r][c];

                int numberOfBombRows = Integer.parseInt(br.readLine());
                for (int i = 0; i < numberOfBombRows; ++i)
                {
                    StringTokenizer lineSt = new StringTokenizer(br.readLine());
                    int row = Integer.parseInt(lineSt.nextToken());
                    int numberOfBombs = Integer.parseInt(lineSt.nextToken()); // At least 1
                    for (int j = 0; j < numberOfBombs; ++j)
                    {
                        int bombIndex = Integer.parseInt(lineSt.nextToken());
                        graph[row][bombIndex] = 1;
                    }
                } // End of reading in bomb locations

                st = new StringTokenizer(br.readLine());
                int startX = Integer.parseInt(st.nextToken());
                int startY = Integer.parseInt(st.nextToken());

                st = new StringTokenizer(br.readLine());
                int endX = Integer.parseInt(st.nextToken());
                int endY = Integer.parseInt(st.nextToken());

                // Could use dijkstra's, but bfs would work as well
                int minNumberOfSteps = bfs(startX, startY, endX, endY, r, c, graph);

                // Output
                out.append(minNumberOfSteps);
                out.append("\n");
            }
			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method

    private static int bfs(int startX, int startY, int endX, int endY, int r, int c, int[][] graph)
    {
        int distance[][] = new int[r][c];
        for (int i = 0; i < r; ++i)
            Arrays.fill(distance[i], Integer.MAX_VALUE);

        Stack<Integer> stack = new Stack<>();

        Queue<Pair> queue = new LinkedList<>();

        distance[startX][startY] = 0;
        queue.add(new Pair(startX, startY));

        while (queue.size() != 0)
        {
            Pair current = queue.remove();

            // Checks each node adjacent to current
            int rowCheck = current.getKey() - 1;
            int colCheck = current.getValue();
            if (rowCheck >= 0)
            {
                // Top
                if ((distance[rowCheck][colCheck] == Integer.MAX_VALUE) && (graph[rowCheck][colCheck] == 0))
                {
                    queue.add(new Pair(rowCheck, colCheck));
                    distance[rowCheck][colCheck] = Math.min(distance[rowCheck][colCheck], distance[current.getKey()][current.getValue()] + 1);
                }

            }

            // Bot
            rowCheck = current.getKey() + 1;
            colCheck = current.getValue();
            if (rowCheck < r)
            {
                // Bot
                if ((distance[rowCheck][colCheck] == Integer.MAX_VALUE) && (graph[rowCheck][colCheck] == 0))
                {
                    queue.add(new Pair(rowCheck, colCheck));
                    distance[rowCheck][colCheck] = Math.min(distance[rowCheck][colCheck], distance[current.getKey()][current.getValue()] + 1);
                }
            }

            // Left
            rowCheck = current.getKey();
            colCheck = current.getValue() - 1;
            if (colCheck >= 0)
            {
                // Left
                if ((distance[rowCheck][colCheck] == Integer.MAX_VALUE) && (graph[rowCheck][colCheck] == 0))
                {
                    queue.add(new Pair(rowCheck, colCheck));
                    distance[rowCheck][colCheck] = Math.min(distance[rowCheck][colCheck], distance[current.getKey()][current.getValue()] + 1);
                }

            }

            // Right
            rowCheck = current.getKey();
            colCheck = current.getValue() + 1;
            if (colCheck < c)
            {
                // Bot
                if ((distance[rowCheck][colCheck] == Integer.MAX_VALUE) && (graph[rowCheck][colCheck] == 0))
                {
                    queue.add(new Pair(rowCheck, colCheck));
                    distance[rowCheck][colCheck] = Math.min(distance[rowCheck][colCheck], distance[current.getKey()][current.getValue()] + 1);
                }
            }
        } // End of iterating through bfs

        return distance[endX][endY];
    } // End of the bfs method

    private static class Pair
    {
        int row;
        int col;

        public Pair(int row, int col)
        {
            this.row = row;
            this.col = col;
        }

        int getKey()
        {return row;}

        int getValue()
        {return col;}
    } // End of the pair class
} // End of the main class
