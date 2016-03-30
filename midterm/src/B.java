import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B
{
    public static void main(String args[])
    {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        try
        {
            int testCases = Integer.parseInt(br.readLine());

            for (int testCase = 0; testCase < testCases; ++testCase)
            {
                StringTokenizer input = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(input.nextToken()); // number of integers in this test case
                int k = Integer.parseInt(input.nextToken()); // key to be compared against

                int[] arr = new int [n];

                StringTokenizer st = new StringTokenizer(br.readLine());
                if (n == 1)
                {
                    out.append(1);
                    out.append("\n");
                    continue;
                }

                // File readin
                for (int i = 0; i < n; ++i)
                    arr[i] = Integer.parseInt(st.nextToken());

                int[] memo = new int[n];

                // brute force
                int best = 0;
                for (int i = 1; i < n; ++i)
                {
                    int current = 1;

                    int j = i + 1;
                    for (; j < n; ++j)
                    {
                        if (arr[j] - arr[i] >= k)
                            ++current;
                    }
                    memo[i] = current;
                    best = Math.max(best, memo[i]);
                }

                // output
                out.append(best);
                out.append("\n");
            } // End of iterating through all test cases

			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}
    } // End of the main method
} // End of the main class
