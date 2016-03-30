import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main01
{
    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            String number;
            while (!(number = br.readLine()).equals("0"))
            {
                int length = number.length();
                int finalNum = sumNum(number, length);
                System.out.println(finalNum);
            } // End of iterating through all test cases

        }
        catch (IOException e)
        {e.printStackTrace();}
    } // End of the main method


    private static int sumNum(String number, int length)
    {
        // Base case
        if (length == 1)
            return Integer.parseInt(number);

        int finalNum = 0;
        for (int i = 0; i < length; ++i)
            finalNum += Character.getNumericValue(number.charAt(i));
        return sumNum(Integer.toString(finalNum), Integer.toString(finalNum).length());
    }
} // End of the main class
