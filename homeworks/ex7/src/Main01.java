import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main01
{

    public static void main(String args[])
    {
        Scanner in  = new Scanner(System.in);
        StringBuilder out = new StringBuilder();

        for (int caseNumber = 0; in.hasNextInt(); ++caseNumber)
        {
            int n = in.nextInt(); // Number of currencies

            if (n == 0)
                break;

            String[] currencies = new String[n];
            HashMap<String, Integer> map = new HashMap<>(); // Allows O(1) access to a specific currency index
            ArrayList<ArrayList<Integer>> edgeList = new ArrayList<>();
            for (int i = 0; i < n; ++i)
                edgeList.add(new ArrayList<Integer>());

            // Reads in the currencies
            int[][] exchange = new int[n][n]; // Adjacency matrix (can we convert to adj list?)

            for (int i = 0; i < n; ++i)
            {
                String currency = in.next();
                currencies[i] = currency;
                map.put(currency, i);
            }

            // Reads in exchange rates
            int m = in.nextInt(); // Number of exchange rates
            for (int i = 0; i < m; ++i)
            {
                String source = in.next();
                int exchangeRate = in.nextInt();
                String destination = in.next();

                int sourceIndex = map.get(source);
                int destinationIndex = map.get(destination);
                exchange[sourceIndex][destinationIndex] = exchangeRate;
                edgeList.get(sourceIndex).add(destinationIndex);
            }


            /**
             * Thoughts: We can run floyd-warshal's algorithm to generate all-pairs shortest
             *
             * This is a classical max-flow problem. We designate the the source node to lead into each of
             * the currencies with 1 unit of each. We then run through Edmonds–Karp's algorithm to find the maximum
             * flow-rate of the network; If this value is > 1, then arbitrage is possible. Else, it is not.
             */

            double shortestPath = flyodWarshal(currencies, map, exchange, edgeList);

            String outputCase = "Case " + caseNumber + ": ";
            out.append(outputCase);
            // Output
            if (shortestPath > 1)
                out.append("Yes");
            else
                out.append("No");
            out.append("\n");
        }

        System.out.print(out);
    } // End of the main method

    private static double edmondKarp(String[] currencies, HashMap<String, Integer> map, int[][] exchange, ArrayList<ArrayList<Integer>> edgeList)
    {
        int n = exchange.length;
        int[][] rc = new int[n][n]; // Residual capacity from u to v is C[u][v] - rc[u][v]



    } // End of the edmond karp method to find the max flow
} // End of the main class
