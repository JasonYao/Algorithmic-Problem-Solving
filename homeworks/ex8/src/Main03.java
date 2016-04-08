import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * UVA Question 10970: Big Chocolate
 * Question link: https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1911
 * Valid solution: Yes
 */
public class Main03
{
    public static void main(String args[])
    {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        try
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(line);
                int M = Integer.parseInt(st.nextToken());
                int N = Integer.parseInt(st.nextToken());
                // Since constant cuts are required regardless of what happens, it'd be M*N - 1 cuts required
                out.append((M * N) - 1);
                out.append("\n");
            } // End of iterating through all test cases
			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method
} // End of the main class
