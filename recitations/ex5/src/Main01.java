import java.util.ArrayList;
import java.util.Scanner;

public class Main01
{
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        StringBuilder out = new StringBuilder("");

        int n = in.nextInt();

        while (n != 0)
        {
            ArrayList<Integer> arr = new ArrayList<>();

            for (int i = 0; i < n; ++i)
                arr.add(in.nextInt());

            int best = kadane(arr);

            if (best > 0)
            {
                out.append("The maximum winning streak is ");
                out.append(best);
                out.append(".\n");
            }
            else
                out.append("Losing streak.\n");

            // Sets up for next run
            if (in.hasNextInt())
                n = in.nextInt();
            else
                n = 0;
        } // End of iterating through all test cases

        System.out.print(out.toString());
    } // End of the main method

    private static int kadane(ArrayList<Integer> current)
    {
        int maxEnding = current.get(0);
        int maxSoFar = current.get(0);

        for (int i = 1; i < current.size(); ++i)
        {
            maxEnding = Math.max(current.get(i), maxEnding + current.get(i));
            maxSoFar = Math.max(maxSoFar, maxEnding);
        }
        return maxSoFar;
    } // End of the kadane's algorithm method
} // End of the main class
