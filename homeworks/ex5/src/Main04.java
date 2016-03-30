import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main04
{
    public static void main(String args[])
    {
        StringBuilder out = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            int setNumber = 1;
            while (true)
            {
                String line = br.readLine();

                if (line == null)
                    break;

                StringTokenizer st = new StringTokenizer(line);
                int n = Integer.parseInt(st.nextToken()); // Number of junctinos
                int r = Integer.parseInt(st.nextToken()); // Number of roads

                int shortestLength;
                boolean cannotReachEnd = false;

                int[][][] distance = new int[n][n][2];

                // Reads in the edges
                for (int i = 0; i < r; ++i)
                {
                    StringTokenizer road = new StringTokenizer(br.readLine());
                    int interconnect1 = Integer.parseInt(road.nextToken());
                    int interconnect2 = Integer.parseInt(road.nextToken());
                    int weight = Integer.parseInt(road.nextToken());


                    if ((interconnect1 == 0) || (interconnect2 == 0))
                    {
                        // Deal with start constraints
                        if (interconnect1 == 0)
                        {
                            distance[0][interconnect2][0] = weight;
                            distance[0][interconnect2][1] = Integer.MAX_VALUE;

                            distance[interconnect2][0][0] = weight;
                            distance[interconnect2][0][1] = Integer.MAX_VALUE;
                        }
                        else
                        {

                        }


                        int source;
                        if (interconnect1 == 0)
                            source = 0;
                        else source = 1;
                        distance[interconnect1][interconnect2][0] = weight;
                        distance[interconnect1][interconnect2][1] = weight;
                    }
                    else if ((interconnect1 == n - 1) || (interconnect2 == n - 1))
                    {
                        // Deal with end constraints
                        if (interconnect1 == n - 1)
                        {

                        }
                        else
                        {

                        }
                    }
                    else
                    {
                        // Normal road, normal weights
                        distance[interconnect1][interconnect2][0] = weight;
                        distance[interconnect1][interconnect2][1] = weight;

                        distance[interconnect2][interconnect1][0] = weight;
                        distance[interconnect2][interconnect1][1] = weight;
                    }
                } // End of reading in edges



                // Output
                String output;
                if (cannotReachEnd)
                    output = "?\n";
                else
                    output = "Set #" + setNumber + "\n" + shortestLength + "\n";
                out.append(output);
                // Sets up for next round
                ++setNumber;
            } // End of iterating through all test cases
			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method

} // End of the main class
