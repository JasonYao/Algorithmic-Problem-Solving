import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTestCases;
        try
        {
            int numberOfFemale = 0;
            int numberOfMale = 0;
            numberOfTestCases = Integer.parseInt(br.readLine());
            for (int i = 0; i < numberOfTestCases; ++i)
            {
                String currentTestCase = br.readLine();

                StringTokenizer st = new StringTokenizer(currentTestCase);
                int count = 0;
                // Iterates through the test case
                while (st.hasMoreTokens())
                {
                    String token = st.nextToken();
                    switch (token)
                    {
                        case "MF":
                        case "FM":
                            ++numberOfFemale;
                            ++numberOfMale;
                            break;
                        case "FF":
                            numberOfFemale = numberOfFemale + 2;
                            break;
                        case "MM":
                            numberOfMale = numberOfMale + 2;
                            break;
                    }
                    ++count;
                } // End of iterating through test case

                // Special case
                if ((count == 1) || (numberOfFemale != numberOfMale))
                    System.out.println("NO LOOP");
                else
                    System.out.println("LOOP");
            }
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method
} // End of the main class
