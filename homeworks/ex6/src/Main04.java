import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main04
{
    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        try
        {
            String line;
            while (!(line = br.readLine()).equals("END"))
            {
                // If it's an integer, we'll rewrite it to be close (i.e. 1 == 0.999999, 2 = 1.999999, etc.)
                boolean isInt = false;
                try
                {
                    Integer.parseInt(line);
                    isInt = true;
                }
                catch (NumberFormatException e) {}

                double x; // floating point number in decimal notation

                // Check for 0
                if (isInt && Integer.parseInt(line) == 0)
                    x = Double.parseDouble(line);
                else if (isInt && Integer.parseInt(line) != 0)
                {x = Double.parseDouble(line) - 1 + 0.9999999999;} // Check for other ints
                else
                {x = Double.parseDouble(line);} // Leave as float

                int [] digit = new int[8];
                double currentX = x;
                for (int i = 0; i < digit.length; ++i)
                {
                    while (currentX > 1)
                        --currentX;

                    currentX *= 3;
                    digit[i] = (int) currentX;
                }

//                //TODO remove after
//                for (int i = 0; i < digit.length; ++i)
//                    System.out.print(digit[i] + " ");
//                System.out.println();

                boolean isCantor = true;
                for (int i = 0; i < digit.length; ++i)
                    if (digit[i] == 1)
                        isCantor = false;

                if (isCantor)
                    out.append("MEMBER\n");
                else
                    out.append("NON-MEMBER\n");
            } // End of iterating through all test cases

            // Output
            System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method
} // End of the main class
