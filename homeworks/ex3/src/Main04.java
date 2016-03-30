import java.util.Scanner;

public class Main04
{
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        StringBuilder out = new StringBuilder("");

        int n = in.nextInt();

        int[][] arr = new int[n][n];

        // Input
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                arr[i][j] = in.nextInt();

        for (int i = 0; i < arr.length; ++i)
        {
            for (int j = 0; j < arr[i].length; ++j)
            {
                System.out.print(" " + arr[i][j]);
            }
            System.out.println();

        }

//        // Summed
//        int[][] summed = new int[n][n];
//        for (int i = 0; i < n; ++i)
//        {
//            for (int j = 0; j < n; ++j)
//            {
//                summed[i][j] += arr[i][j];
//                for (int k = 0; k < i; ++k)
//                    summed[i][j] += arr[k][j];
//            }
//        }
//
//        // Collapses summed rows
//        int maxCollapsed = (n*(n+1))/(2);
//        int[][] collapsed = new int[maxCollapsed][n];
//
//        int count = 0;
//        for (int i = 0; i < n; ++i)
//        {
//            for (int j = i; j < n; ++j)
//            {
//                for (int col = 0; col < n; ++col)
//                {
//                    if (i > 0)
//                        collapsed[count][col] = summed[j][col] - summed[i - 1][col];
//                    else
//                        collapsed[count][col] = summed[j][col];
//                }
//                ++count;
//            }
//        }

        int best = Integer.MIN_VALUE;

        for (int i = 0; i < n; ++i)
        {
            int[] sum = new int[n];
            for (int j = 0; j < n; ++j)
            {
                for (int k = 0; k < n; ++k)
                {
                    sum[k] += arr[j][k];
                }
                best = Math.max(best, kadane(sum));
            }
        }



//
//        // Iterates over collapsed to generate best
//        for (int i = 0; i < n; ++i)
//        {
//            // Runs Kadane's algorithm over collapsed[i][0:length]
//            int[] current = new int[n];
//            for (int j = 0; j < n; ++j)
//                current[j] = collapsed[i][j];
//            best[i] = Math.max(kadane(current), best[i]);
//        }

//        // Finds the maximum out of the best array
//        int max = 0;
//        for (int x : best)
//            if (max < x)
//                max = x;

        // Output
        out.append(best);
        out.append("\n");
        System.out.print(out);
    } // End of the main method

    private static int kadane(int[] current)
    {
        int maxEnding = current[0];
        int maxSoFar = current[0];

        for (int i = 1; i < current.length; ++i)
        {
            maxEnding = Math.max(current[i], maxEnding + current[i]);
            maxSoFar = Math.max(maxSoFar, maxEnding);
        }
        return maxSoFar;
    } // End of the kadane's algorithm method
} // End of the main class
