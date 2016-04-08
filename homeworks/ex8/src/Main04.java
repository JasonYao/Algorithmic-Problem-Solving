import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * UVA Question 11609: Teams
 * Question link: https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=2656
 * Valid solution: Yes
 */
public class Main04
{
    private static final BigInteger MODVALUE = BigInteger.valueOf(1000000007);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    public static void main(String args[])
    {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        try
        {
            int numberOfTestCases = Integer.parseInt(br.readLine());

            for (int currentTestCase = 1; currentTestCase <= numberOfTestCases; ++currentTestCase)
            {
                int N = Integer.parseInt(br.readLine()); // Number of players to choose from

                BigInteger bigN = BigInteger.valueOf(N).mod(MODVALUE);
                BigInteger answer = bigN.multiply(TWO.modPow(BigInteger.valueOf(N - 1), MODVALUE)).mod(MODVALUE);

                // Output
                String output = String.format("Case #%d: %s", currentTestCase, answer.toString());
                out.append(output);
                out.append("\n");
            } // End of iterating through all test cases
			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method
} // End of the main class
