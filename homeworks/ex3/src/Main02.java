import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main02
{
    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder("");

        try
        {
            int numberOfTestCases = Integer.parseInt(br.readLine());
            for (int i = 1; i <= numberOfTestCases; ++i)
            {

                int n = Integer.parseInt(br.readLine()); // number of buildings

                // Initialises the memo and data arrs
                int[] amountIncreasing = new int[n];
                int[] amountDecreasing = new int[n];
                int[] heights = new int[n];
                int [] widths = new int[n];

                String sHeights = br.readLine();
                String sWidths = br.readLine();

                // Parses height
                StringTokenizer stHeight = new StringTokenizer(sHeights);
                for (int j = 0; stHeight.hasMoreTokens(); ++j)
                    heights[j] = Integer.parseInt(stHeight.nextToken());

                // Parses width
                StringTokenizer stWidth = new StringTokenizer(sWidths);
                for (int j = 0; stWidth.hasMoreTokens(); ++j)
                    widths[j] = Integer.parseInt(stWidth.nextToken());

                for (int j = n - 1; j >= 0; --j)
                {
                    amountIncreasing[j] = amountDecreasing[j] = widths[j];
                    for (int k = j+1; k<n; ++k)
                    {
                        if ((heights[j] < heights[k]) && (amountIncreasing[j] < widths[j] + amountIncreasing[k]))
                            amountIncreasing[j] = widths[j] + amountIncreasing[k];
                        else if ((heights[j] > heights[k]) && (amountDecreasing[j] < widths[j] + amountDecreasing[k]))
                            amountDecreasing[j] = widths[j] + amountDecreasing[k];
                    }
                }

                // Finds the max increasing
                int maxInc = 0;
                for (int j = 0; j < amountIncreasing.length; ++j)
                    if (maxInc < amountIncreasing[j])
                        maxInc = amountIncreasing[j];

                // Finds the max decreasing
                int maxDec = 0;
                for (int j = 0; j < amountDecreasing.length; ++j)
                    if (maxDec < amountDecreasing[j])
                        maxDec = amountDecreasing[j];

                // Output
                out.append("Case ");
                out.append(i);
                if (maxInc < maxDec)
                {
                    out.append(". Decreasing (");
                    out.append(maxDec);
                    out.append("). Increasing (");
                    out.append(maxInc);
                    out.append(").\n");
                }
                else
                {
                    out.append(". Increasing (");
                    out.append(maxInc);
                    out.append("). Decreasing (");
                    out.append(maxDec);
                    out.append(").\n");
                }
            } // End of iterating through each test case
            System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method
} // End of the main class
