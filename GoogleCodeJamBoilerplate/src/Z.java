import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Z
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

            } // End of iterating through all test cases

			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}
    } // End of the main method
} // End of the main class
