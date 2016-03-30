import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A
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
                String s = br.readLine();
                int q = Integer.parseInt(br.readLine());
                String[] queries = new String[q];

                // Fills query table
                for (int i = 0; i < q; ++i)
                    queries[i] = br.readLine();

                // Iterates through each query
                for (int i = 0; i < q; ++i)
                {
                    int answer = 0;

                    for (int j = 0; j < s.length(); ++j)
                    {
                        if (s.charAt(j) == queries[i].charAt(0))
                        {
                            if (j + queries[i].length() > s.length())
                                break;

                            if (s.substring(j, j + queries[i].length()).equals(queries[i]))
                            {
                                // Success
                                ++answer;
                            }
                            else
                            {
                                // Fail
                            }
                        }

                    } // End of iterating through the string

                    out.append(answer);
                    out.append("\n");
                } // End of iterating through each query
            } // End of iterating through all test cases

			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method
} // End of the main class
