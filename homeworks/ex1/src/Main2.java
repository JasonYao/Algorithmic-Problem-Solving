import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2
{
    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                TreeMap<Integer, Integer> tm = new TreeMap<>();
                StringTokenizer st = new StringTokenizer(line);
                int previousValue = Integer.parseInt(st.nextToken());
                int difference;
                while (st.hasMoreTokens())
                {
                    int currentValue = Integer.parseInt(st.nextToken());
                    difference = Math.abs(previousValue - currentValue);
                    previousValue = currentValue;

                    if (!tm.containsKey(difference))
                        tm.put(difference, 1);
                } // End of iterating through the test case

                Set set = tm.entrySet();
                Iterator i = set.iterator();
                int current = 1;
                boolean isJolly = true;

                while (i.hasNext())
                {
                    Map.Entry me = (Map.Entry)i.next();
                    int key = (Integer) me.getKey();
                    if (key == current)
                        isJolly = true;
                    else
                    {
                        isJolly = false;
                        break;
                    }
                    ++current;
                }

                if (isJolly)
                    System.out.println("Jolly");
                else
                    System.out.println("Not jolly");
            } // End of iterating through all test cases
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method
} // End of the main class
