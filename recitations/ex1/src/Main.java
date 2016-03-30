import java.util.Scanner;

public class Main
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int lineIndicator = sc.nextInt();

        boolean isFirst = true;
        for (int i = 0; i < lineIndicator; ++i)
        {
            if (isFirst)
                isFirst = false;
            else
                System.out.println();
            int indicator = sc.nextInt();
            int sum = 0;
            for (int j = 0; j < indicator; ++j)
                sum += sc.nextInt();
            System.out.println();
        }

        // Does first
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(a + b);

        // Does rest
        while (sc.hasNextInt())
        {
            System.out.println();
            int a1 = sc.nextInt();
            int b1 = sc.nextInt();
            int sum = a1 + b1;
            System.out.println(sum);
        }
    } // End of the main method
} // End of the Main class
