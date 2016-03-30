import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main01
{
    // Global vars
    private static int best = Integer.MIN_VALUE;
    private static boolean hasPassedAllCourses = true;
    private static int[][] memo;
    private static int[][] input;

    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        try
        {
            int numTestCases = Integer.parseInt(br.readLine());
            for (int testCases = 0; testCases < numTestCases; ++testCases)
            {
                // Reads in input
                StringTokenizer st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken()); // n number of courses
                int m = Integer.parseInt(st.nextToken()); // m number of hours left to study

                // Sanity checks
                if (m == 0)
                {
                    out.append("Peter, you shouldn’t have played billiard that much.\n");
                    continue;
                }

                int matrixN = n + 1;
                int matrixM = m + 1;
                input = new int[matrixN][matrixM];
                memo = new int[matrixN][matrixM];

                for (int i = 1; i < matrixN; ++i)
                {
                    StringTokenizer arrIn = new StringTokenizer(br.readLine());
                    for (int j = 1; j < matrixM; ++j)
                        input[i][j] = Integer.parseInt(arrIn.nextToken());
                }

                // Initialises memo table to invalid values
                for (int[] row : memo)
                    Arrays.fill(row, -1);

                // Resets best value for next test case
                best = Integer.MIN_VALUE;
                hasPassedAllCourses = true;

                // Runs dfs to find maximum course score (or invalid score)
                int best = dfs(n, m, 1, 1, 0, m);

                // Output:
                String output;
                if (hasPassedAllCourses)
                {
                    double bestMark = (double) best/ (double) n;
                    output =  String.format("Maximal possible average mark - %.2f.\n", bestMark);
                }
                else
                    output = "Peter, you shouldn’t have played billiard that much.\n";
                out.append(output);
            } // End of iterating through all test cases
        }
        catch (IOException e)
        {e.printStackTrace();}

        System.out.print(out);
    } // End of the main method

    private static int dfs(int n, int m, int currentN, int currentM, int currentTotal, int timeRemaining)
    {
        if ((currentM > m) && (best == Integer.MIN_VALUE))
        {
            hasPassedAllCourses = false;
            return -100;
        }

        // Break check
        if ((!hasPassedAllCourses) || (currentN > n) || (currentM > m))
            return -100;

        // Time check
        if (timeRemaining < currentM)
            return -100;


        // Valid End path
        if ((input[currentN][currentM] >= 5) && (currentN == n))
        {
            // Success and at end of row, return
            best = Math.max(best, currentTotal + input[currentN][currentM]);
            return memo[currentN][currentM];
        }

        // Invalid end path
        if ((currentN == n) && (currentM == m) && (input[currentN][currentM] < 5) && (best != Integer.MIN_VALUE))
        {
            hasPassedAllCourses = false;
            return -100;
        }

        // Invalid path
        if ((currentN == n) && (input[currentN][currentM] < 5))
            return -100;

        // Valid end path
        if ((currentN == n) && (currentM == m) && (input[currentN][currentM] >= 5))
        {
            return memo[currentN][currentM] = Math.max(best, currentTotal + input[currentN][currentM]);
        }

        if (input[currentN][currentM] >= 5)
        {
            return memo[currentN][currentM] = Math.max(
                    dfs(n, m, currentN + 1, 1, currentTotal + input[currentN][currentM], timeRemaining - currentM),
                    dfs(n, m, currentN, currentM + 1, currentTotal, timeRemaining)
            );
        }
        else
        {
            return memo[currentN][currentM] = dfs(n, m, currentN, currentM + 1, currentTotal, timeRemaining);
        }
    } // End of the dfs method
} // End of the main class
