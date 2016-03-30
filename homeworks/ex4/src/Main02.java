import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main02
{
    // Global vars
    private static int MAX = 352; // sum(1:26) + 1
    private static int[][][] memo = new int[27][27][MAX];

    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        // Initialises the memo array to an invalid value
        for (int[][] row : memo)
            for (int[] col : row)
                Arrays.fill(col, -1);

        try
        {
            for (int testCases = 1; true; ++testCases)
            {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken()); // length
                int s = Integer.parseInt(st.nextToken()); // target sum

                // End condition check
                if ((l == 0) && (s == 0))
                    break;

                // Sanity checks
                if ((s > MAX - 1) || (l > 26) || (s < l))
                {
                    String output = "Case " + testCases + ": " + 0 + "\n";
                    out.append(output);
                    continue;
                }

                // Output:
                String output = "Case " + testCases + ": " + dfs(0, l, s, s) + "\n";
                out.append(output);
            } // End of iterating through all test cases
        }
        catch (IOException e)
        {e.printStackTrace();}

        System.out.print(out);
    } // End of the main method

    private static int dfs(int index, int lettersRemaining, int sum, int targetSum)
    {
        // Sanity checks
        if ((sum < 0) || (index > 26) || (index > targetSum) || (lettersRemaining < 0))
            return 0;
        else if ((sum == 0) && (lettersRemaining != 0))
            return 0;
        else if ((lettersRemaining == 0) && (sum != 0))
            return 0;

        // Valid path, returns with 1
        if ((sum == 0) && (lettersRemaining == 0))
        {
            memo[index][0][0] = 1;
            return memo[index][0][0] = 1;
        }

        // Checks if answer already exists in memo table
        if (memo[index][lettersRemaining][sum] != -1)
            return memo[index][lettersRemaining][sum];

        memo[index][lettersRemaining][sum] = dfs(index + 1, lettersRemaining - 1, sum - index - 1, targetSum)
                + dfs(index + 1, lettersRemaining, sum, targetSum);

        // Goes down both available paths: letter was used added with when letter was not used
        return memo[index][lettersRemaining][sum];
    } // End of the dfs method
} // End of the main class
