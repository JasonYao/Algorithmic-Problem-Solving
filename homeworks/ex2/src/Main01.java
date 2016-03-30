import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class Main01
{
    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Reads in the number of problems
        try
        {
            int numberOfProblems = Integer.parseInt(br.readLine());

            for (int i = 0; i < numberOfProblems; ++i)
            {
                // Input read-in
                int numberOfLEDs = Integer.parseInt(br.readLine()); // P
                int numberOfSymbols = Integer.parseInt(br.readLine()); // N
                int[] map = new int[numberOfSymbols];

                // Parses all input into up to 15-bit integers
                for (int j = 0; j < numberOfSymbols; ++j)
                {
                    StringTokenizer st = new StringTokenizer(br.readLine());
                    StringBuilder digit = new StringBuilder("");

                    while (st.hasMoreTokens())
                        digit.append(st.nextToken());

                    map[j] = Integer.parseInt(digit.toString(), 2);
                }

                int finalAnswer = Integer.MAX_VALUE;

                for (int mask = 0; mask < (1 << numberOfLEDs); ++mask)
                {
                    // Creates a pool for each level of masking
                    TreeSet<Integer> pool = new TreeSet<>();

                    // Appends to the set the new masked number
                    for (int j = 0; j < numberOfSymbols; ++j)
                        pool.add(mask & map[j]);

                    // No duplicates were found
                    if (pool.size() == numberOfSymbols)
                        finalAnswer = Math.min(finalAnswer, Integer.bitCount(mask));
                }

                // Output
                System.out.println(finalAnswer);
            } // End of iterating through all problems
        } // End of try
        catch (IOException e)
        {e.printStackTrace();}
        finally {try {br.close();} catch (IOException e) {e.printStackTrace();}}
    } // End of the main method
} // End of the main class
