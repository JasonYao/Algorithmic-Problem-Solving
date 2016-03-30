import java.util.Scanner;

public class Main02
{
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        StringBuilder out = new StringBuilder("");

        while (in.hasNextInt())
        {
            int n = in.nextInt();
            if (n == 0)
                break;
            out.append(fib(n));
            out.append("\n");
        } // End of iterating through all test cases
        System.out.print(out.toString());
    } // End of the main method

    private static int fib(int n)
    {
        if (n == 1)
            return 1;

        int firstValue = 1;
        int secondValue = 1;
        int currentValue = firstValue + secondValue;
        for (long i = 2; i < n; ++i)
        {
            firstValue = secondValue;
            secondValue = currentValue;
            currentValue = firstValue + secondValue;
        }
        return currentValue;
    } // End of the fibonacci number generator
} // End of the main class
