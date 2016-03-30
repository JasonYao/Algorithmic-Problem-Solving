import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main02
{
    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            // Reading the input
            String input = br.readLine();
            Scanner sc = new Scanner(input);

            long n = sc.nextLong();
            long m = sc.nextLong();
            long a = sc.nextLong();

            long firstNum = (n + a - 1) / a;
            long secondNum = (m + a - 1) / a;

            System.out.println(firstNum * secondNum);
        }
        catch (IOException e)
        {e.printStackTrace();}
    } // End of the main method
} // End of the main class
