import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main03
{
    public static void main(String args[])
    {
        StringBuilder out = new StringBuilder("");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = 0; // number of test cases
        try
        {
            m = Integer.parseInt(br.readLine());

            for (int testCase = 0; testCase < m; ++testCase)
            {
                ArrayList<Integer> arr = new ArrayList<>(); // Weights of each suitcase
                String sLine = br.readLine();
                StringTokenizer st = new StringTokenizer(sLine);

                // Parses the input
                while (st.hasMoreTokens())
                    arr.add(Integer.parseInt(st.nextToken()));

                // Finds the total weight
                int totalWeight = 0;
                for (int x : arr)
                    totalWeight += x;

                boolean canLoadSameWeight = checkForSameWeight(totalWeight, arr);

                if (canLoadSameWeight)
                    out.append("YES\n");
                else
                    out.append("NO\n");
            } // End of iterating through each test case

        } catch (IOException e)
        {e.printStackTrace();}

        System.out.print(out.toString());
    } // End of the main method

    private static boolean checkForSameWeight(int totalWeight, ArrayList<Integer> arr)
    {
        int MAX_TOTAL_WEIGHT = 200;
        // Quick heuristic check
        if ((totalWeight % 2 != 0) || (totalWeight == 0))
            return false;

        int targetWeight = totalWeight/2;

        int[] table = new int[MAX_TOTAL_WEIGHT];
        table[0] = 1;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < arr.size(); ++i)
            q.add(arr.get(i));

        while (q.peek() != null)
        {
            int i = q.peek();
            for (int j = targetWeight - 1; j > -1; j--)
            {
                if ((table[j] != 0) && (i + j <= targetWeight))
                    table[i + j] = table[i] + table[j];
            }
            q.remove();
        }

        return table[targetWeight] != 0;
    } // End of the check for same weight

} // End of the main class
