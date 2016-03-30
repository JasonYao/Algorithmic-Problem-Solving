import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class A
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
                StringTokenizer st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken()); // size of L_A
                int m = Integer.parseInt(st.nextToken()); // size of L_B

                HashSet<String> a = new HashSet<>();
                HashSet<String> b = new HashSet<>();

                // Reads in all input into each respective hashset
                for (int i = 0; i < 2; ++i)
                {
                    st = new StringTokenizer(br.readLine());
                    if (i == 0)
                    {
                        for (int j = 0; j < n; ++j)
                            a.add(st.nextToken());
                    }
                    else
                    {
                        for (int j = 0; j < m; ++j)
                            b.add(st.nextToken());
                    }
                }

                // Output
                double jacard = jacard(a, b);
                System.out.printf("%6f\n", jacard);
            } // End of iterating through all test cases

			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}
    } // End of the main method

    private static double jacard(HashSet<String> a, HashSet<String> b)
    {
        Iterator<String> it = a.iterator();

        int intersect = 0; // The number of intersections between the two sets
        int subtractUnion = 0; // We take this from the sum of a.size and b.size
        while (it.hasNext())
        {
            String current = it.next();
            if (b.contains(current))
            {
                ++intersect;
                ++subtractUnion;
            }
        }

        int union = a.size() + b.size() - subtractUnion;
        return (double) intersect/ (double) union;
    } // End of jacard
} // End of the main class
