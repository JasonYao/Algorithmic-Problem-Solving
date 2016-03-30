import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main03
{
    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            String line;

            while (!(line = br.readLine()).equals("0"))
            {
                int num = Integer.parseInt(line);
                StringBuilder binary = new StringBuilder(Integer.toBinaryString(num)).reverse();

                boolean isOdd = true;
                StringBuilder a = new StringBuilder();
                StringBuilder b = new StringBuilder();

                for (int i = 0; i < binary.length(); ++i)
                {
                    if (binary.charAt(i) == '1')
                    {
                        if (isOdd)
                        {
                            // deals with a
                            isOdd = false;
                            a.append("1");
                            b.append("0");
                        }
                        else
                        {
                            // deals with b
                            isOdd = true;
                            a.append("0");
                            b.append("1");
                        }
                    }
                    else
                    {
                        // Pads with 0
                        a.append("0");
                        b.append("0");
                    }
                } // End of building a and b

                // Reverses back a and b
                String aFinal = a.reverse().toString();
                String bFinal = b.reverse().toString();

                int aInt = Integer.parseInt(aFinal, 2);
                int bInt = Integer.parseInt(bFinal, 2);

                // Outputs a and b
                System.out.println(aInt + " " + bInt);
            } // End of all test cases
        }
        catch (IOException e)
        {e.printStackTrace();}
    } // End of the main method
} // End of the main class
