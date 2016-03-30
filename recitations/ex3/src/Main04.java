import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main04
{
    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            String line = br.readLine();
            int numberOfStudents;
            int m;

            if (line.equals("0 0"))
                return;
            else
            {
                Scanner sc = new Scanner(line);
                numberOfStudents = sc.nextInt();
                m = sc.nextInt();
            }

            while (!(line = br.readLine()).equals("0 0"))
            {
                for (int k = 0; k < m; ++k)
                {
                    StringTokenizer tok =new StringTokenizer(line);
                    int i = Integer.parseInt(tok.nextToken());
                    int j = Integer.parseInt(tok.nextToken());


                    // Calculates the final

                } // End of the individual test case

            } // End of all test cases
        }
        catch (IOException e)
        {e.printStackTrace();}
    } // End of the main method

    private class Node {
        public ArrayList<Node> adjacency;
        public int number;
        public Node(int number)
        {
            adjacency = new ArrayList<>();
            this.number = number;
        }
    }
} // End of the main class
