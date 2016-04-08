import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * UVA Question 11247: Income Tax
 * Question link: https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=2204
 * Valid solution: Yes
 */
public class Main02
{
    public static void main(String args[])
    {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        try
        {
            String line;
            while (!(line = br.readLine()).equals("0 0"))
            {
                StringTokenizer st = new StringTokenizer(line);
                long m = Long.parseLong(st.nextToken()); // Nominal income of a person
                long x = Long.parseLong(st.nextToken()); // Tax percentage

                // Sanity check for tax percentage (can't be 100% otherwise you get a divide by 0 error later)
                if ((x == 100) || (x == 0) || (m == 1))
                {
                    out.append(String.format("Not found%s", System.lineSeparator()));
                    continue;
                }

                /**
                 * To begin with, we find the find the highest (integer) value below the taxation value (m). --> m - 1,
                 * which we define as A.
                 * As A is not taxed, it'll probably be the highest income you can get.
                 * We define the percentageOfIncomeKept as 100 - x
                 * and thus the income kept is (A * 100) * percentageOfIncomeKept, where since percentageOfIncomeKept
                 * isn't divided by 100, we multiply A by 100 instead (saves from having to deal with floating point)
                 */

                long percentOfIncomeKept = 100 - x;
                long effectiveIncome = ((m - 1) * 100)/ percentOfIncomeKept;

                if (((m - 1) * 100) % percentOfIncomeKept == 0)
                    --effectiveIncome;
                if (effectiveIncome >= m)
                    out.append(String.format("%d%s", effectiveIncome, System.lineSeparator()));
                else
                    out.append(String.format("Not found%s", System.lineSeparator()));
            } // End of iterating through all test cases
			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}
    } // End of the main method
} // End of the main class
