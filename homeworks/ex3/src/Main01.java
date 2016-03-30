import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main01
{
    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try
        {
            int n = 0; // nominal Fuel consumption in Litres/ 100 km
            int leaks = 0; // Number of leaks currently

            float currentFuelUsed = 0;
            int oldPosition = 0;
            int actualFuelConsumption = n + (100 * leaks); // Litres/ 100 km
            float largestSingleStretchUsed = 0; // 1 Litre/km

            while (!(line = br.readLine()).equals("0 Fuel consumption 0"))
            {
                StringTokenizer st = new StringTokenizer(line);
                String[] arr = new String[4];
                for (int i = 0; st.hasMoreTokens(); ++i)
                    arr[i] = st.nextToken();

                int distanceTraveled = Integer.parseInt(arr[0]) - oldPosition;
                currentFuelUsed += ((float) distanceTraveled/ 100.0) * (float) actualFuelConsumption;

                if (arr[1].equals("Fuel"))
                {
                    n = Integer.parseInt(arr[3]);
                    actualFuelConsumption = n + (100 * leaks);
                    oldPosition = Integer.parseInt(arr[0]);
                }
                else if (arr[1].equals("Goal"))
                {
                    // Output
                    if (largestSingleStretchUsed < currentFuelUsed)
                        System.out.printf("%.3f\n", currentFuelUsed);
                    else
                        System.out.printf("%.3f\n", largestSingleStretchUsed);

                    // Resets
                    n = 0;
                    leaks = 0;
                    oldPosition = 0;
                    actualFuelConsumption = 0;
                    currentFuelUsed = 0;
                    largestSingleStretchUsed = 0;
                }
                else if (arr[1].equals("Leak"))
                {
                    ++leaks;
                    actualFuelConsumption = n + (100 * leaks);
                    oldPosition = Integer.parseInt(arr[0]);
                }

                else if (arr[1].equals("Gas"))
                {
                    if (largestSingleStretchUsed < currentFuelUsed)
                        largestSingleStretchUsed = currentFuelUsed;

                    oldPosition = Integer.parseInt(arr[0]);
                    currentFuelUsed = 0;
                }
                else if (arr[1].equals("Mechanic"))
                {
                    leaks = 0;
                    actualFuelConsumption = n + (100 * leaks);
                    oldPosition = Integer.parseInt(arr[0]);
                }
            } // End of input
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method
} // End of the main class
