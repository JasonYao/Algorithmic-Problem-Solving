import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C
{
    public static void main(String args[])
    {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        // Try memo?
        try
        {
            int testCases = Integer.parseInt(br.readLine());

            for (int testCase = 0; testCase < testCases; ++testCase)
            {
                int n = Integer.parseInt(br.readLine()); // Number of candidates

                long[] votes = new long[n];

                // Read in v_i
                StringTokenizer st = new StringTokenizer(br.readLine());
                int highestIndex = 0;

                for (int i = 0; i < n; ++i)
                {
                    votes[i] = Long.parseLong(st.nextToken());
                    if (votes[highestIndex] < votes[i])
                        highestIndex = i;
                }

                int secondHighestIndex = calculateSecondHighestIndex(votes, highestIndex);

                // Sanity check: john already wins
                if ((highestIndex == 0) && (votes[highestIndex] != votes[secondHighestIndex]))
                {
                    out.append("0\n");
                    continue;
                }

                boolean johnIsLosing = true;
                int voteChangeCount = 0;
                while (johnIsLosing)
                {
                    // Difference between the two highest:
//                    long diff = (votes[highestIndex] - votes[secondHighestIndex])/2;
//                    if (diff < 1)
//                        diff = 1;
                    long diff = 1L;

                    // Moves the diff value from the highest to john
                    votes[0] += diff;
                    votes[highestIndex] -= diff;
                    voteChangeCount += diff;

                    if (votes[highestIndex] < votes[secondHighestIndex])
                        highestIndex = secondHighestIndex;
                    else if (votes[highestIndex] < votes[0])
                        highestIndex = 0;

                    // Re calculates the second highest index
                    secondHighestIndex = calculateSecondHighestIndex(votes, highestIndex);

                    // Checks for john winning
                    if ((highestIndex == 0) && (votes[highestIndex] != votes[secondHighestIndex]))
                    {
                        out.append(voteChangeCount);
                        out.append("\n");
                        johnIsLosing = false;
                    }
                } // End of moving votes around
            } // End of iterating through all test cases
			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method

    private static int calculateSecondHighestIndex(long[] votes, int highestIndex){
        int secondHighest = 0;

        for (int i = 0; i < votes.length; ++i)
        {
            if (highestIndex == 0)
                secondHighest = 1;

            if (i != highestIndex)
                if (votes[secondHighest] <= votes[i])
                    secondHighest = i;
        }
        return secondHighest;
    }
} // End of the main class
