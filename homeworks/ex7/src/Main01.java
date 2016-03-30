import java.util.HashMap;
import java.util.Scanner;

/**
 * UVA Question 436: Arbitrage II
 * Question link:
 * Valid solution: Yes
 */
public class Main01
{
    private static final boolean IS_DEBUG_MODE = false;
    private static final int INF = 1000000000; // use 1.10^9 to avoid overflow

    public static void main(String args[])
    {
        Scanner in  = new Scanner(System.in);
        StringBuilder out = new StringBuilder();

        for (int caseNumber = 1; in.hasNextInt(); ++caseNumber)
        {
            int n = in.nextInt(); // Number of currencies (number of nodes, V)

            // Ending condition
            if (n == 0)
                break;

            String[] currencies = new String[n];
            HashMap<String, Integer> map = new HashMap<>(); // Allows O(1) access to a specific currency index

            // Reads in the currencies
            for (int i = 0; i < n; ++i)
            {
                String currency = in.next();
                currencies[i] = currency;
                map.put(currency, i);
            }

            int m = in.nextInt(); // Number of exchange rates (number of edges, E)

            // Initialises the adjacency matrix for floyd warshall's All-pairs shortest path algorithm
            double[][] adjacencyMatrix = new double[n][];
            for (int i = 0; i < n; ++i)
            {
                adjacencyMatrix[i] = new double[m];
                for (int j = 0; j < m; j++)
                    adjacencyMatrix[i][j] = INF;
                adjacencyMatrix[i][i] = 0;
            }

            if (IS_DEBUG_MODE)
                test("Adjacency Matrix: Initial", adjacencyMatrix);

            // Reads in exchange rates (edge weights)
            for (int i = 0; i < m; ++i)
            {
                String source = in.next();
                double exchangeRate = in.nextDouble(); // Edge weight
                String destination = in.next();

                int sourceIndex = map.get(source);
                int destinationIndex = map.get(destination);
                adjacencyMatrix[sourceIndex][destinationIndex] = exchangeRate; // For directed graphs
            }

            if (IS_DEBUG_MODE)
                test("Adjacency Matrix: Before Floyd Warshall", adjacencyMatrix);

            // Runs through Floyd Warshall's algo
            for (int k = 0; k < n; ++k) // O(v^3) Floyd Warshall's code is here
                for (int i = 0; i < n; ++i)
                    for (int j = 0; j < n; ++j)
                        adjacencyMatrix[i][j] = Math.max(adjacencyMatrix[i][j],
                                adjacencyMatrix[i][k] * adjacencyMatrix[k][j]);

            // Finds the maximum to determine if arbitrage is possible
            boolean isValid = false;
            for (int i = 0; i < n; ++i)
            {
                if (adjacencyMatrix[i][i] > 1)
                {
                    isValid = true;
                    break; // Only need one solution
                }
            }

            if (IS_DEBUG_MODE)
                test("Adjacency Matrix: After Floyd Warshall", adjacencyMatrix);

            String outputCase = "Case " + caseNumber + ": ";
            out.append(outputCase);
            // Output
            if (isValid)
                out.append("Yes");
            else
                out.append("No");
            out.append("\n");
        }

        System.out.print(out);
    } // End of the main method

    private static void test(String s, double[][] adjacencyMatrix)
    {
        System.out.println("--------------------  Start " + s + " --------------------");
        for (int i = 0; i < adjacencyMatrix.length; ++i)
        {
            for (int j = 0; j < adjacencyMatrix[i].length; ++j)
                System.out.print(adjacencyMatrix[i][j] + " ");
            System.out.println();
        }
        System.out.println("--------------------  End " + s + " --------------------");
    } // End of test method
} // End of the main class
