import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.SynchronousQueue;

public class Main02
{

    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        try
        {
            int numberOfCases = Integer.parseInt(br.readLine());

            for (int caseNumber = 0; caseNumber < numberOfCases; ++caseNumber)
            {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken()); // Number of t-shirts (multiples of 6)
                int m = Integer.parseInt(st.nextToken()); // Number of volunteers

                Volunteer[] volunteers = new Volunteer[m];

                /**
                 * Where:
                 * 0 == XS
                 * 1 == S
                 * 2 == M
                 * 3 == L
                 * 4 == XL
                 * 5 == XXL
                 */

                for (int i = 0; i < volunteers.length; ++i)
                {

                    volunteers[i] = new Volunteer();

                }



            } // End of all case iterations

            System.out.println(out);
        }
        catch (IOException e)
        {e.printStackTrace();}
    } // End of the main method

    public static class Volunteer
    {

        int validState1;
        int validState2;

        public Volunteer(int validState1, int validState2) {
            this.validState1 = validState1;
            this.validState2 = validState2;
        }
    } // End of the volunteer class

    private static double edmondKarp(String[] currencies, HashMap<String, Integer> map, int[][] exchange, ArrayList<ArrayList<Integer>> edgeList)
    {
        int n = exchange.length;
        int[][] rc = new int[n][n]; // Residual capacity from u to v is C[u][v] - rc[u][v]



    } // End of the edmond karp method to find the max flow
} // End of the main class
