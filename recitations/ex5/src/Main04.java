import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main04
{
    public static void main(String args[])
    {
        StringBuilder out = new StringBuilder("");

        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            int MAX_CUBIC_VALUE_BASES = 21;
            int HOW_DEEP_DO_YOU_WANNA_GO = 10000;

            int[] cubicValues = new int [MAX_CUBIC_VALUE_BASES];

            for (int i = 1; i <= MAX_CUBIC_VALUE_BASES; ++i)
                cubicValues[i - 1] = i * i * i;

            String line;
            while ((line = br.readLine()) != null)
            {
                int value = Integer.parseInt(line);

                int sum[] = new int[n];




//                long[][] memo = new long[MAX_CUBIC_VALUE_BASES][HOW_DEEP_DO_YOU_WANNA_GO];
//
//                memo[0][0] = 1;
//





            } // End of iterating through each test case
        } catch (IOException e)
        {e.printStackTrace();}

        System.out.print(out.toString());
    } // End of the main method

} // End of the main class
