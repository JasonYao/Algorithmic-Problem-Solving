import java.util.Scanner;

public class Main01
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        int numberOfTestCases = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numberOfTestCases; ++i)
        {
            int n = Integer.parseInt(sc.nextLine());
            String field = sc.nextLine();

            int numberOfScarecrows = 0;

            boolean isDone = false;

            int count = 0;
            while (!isDone)
            {
                boolean wasFound = false;
                for (; count < field.length(); ++count)
                {
                    if (field.charAt(count) == '.')
                    {
                        wasFound = true;
                        break;
                    }
                }

                if (wasFound)
                {

                    if (count + 1 < field.length())
                    {
                        if (field.charAt(count + 1) == '.')
                        {
                            if (count + 2 < field.length())
                            {
                                // Adds to the middle
                                ++numberOfScarecrows;
                                count += 1;
                            }
                            else if ()
                            {
                                // Adds to the left (initial count)
                                ++numberOfScarecrows;
                            } // End of dealing with right one
                        }
                        else if (count + 2 < field.length())
                        {
                            if (field.charAt(count + 2) == '.')
                            {
                                // Adds to the middle
                                ++numberOfScarecrows;
                                count += 1;
                            }
                            else
                                ++count;
                        }
                    }
                    else if (count + 2 < field.length())
                    {
                        if (field.charAt(count + 2) == '.')
                        {
                            // Adds to the middle
                            ++numberOfScarecrows;
                            count += 1;
                        }
                        else
                            ++count;
                    }
                    else
                        ++count;
                }// End of wasfound
                else
                    ++count;

                System.out.println(count);
                if (count >= field.length())
                {
                    isDone = true;
                    System.out.println("Case " + i + ": " + numberOfScarecrows);
                }
            }

        } // End of iterating through all test cases

    } // End of the main method
} // End of the main class
