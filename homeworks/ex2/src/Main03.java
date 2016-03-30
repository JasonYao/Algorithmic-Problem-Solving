import java.util.Scanner;

public class Main03
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        int testCases = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < testCases; ++i)
        {
            String mn = sc.nextLine();
            Scanner mnsc = new Scanner(mn);
            int length = mnsc.nextInt(); // m
            int width = mnsc.nextInt(); // n

            int currentPosition = 0;
            int finalPosition = 0;

            String[] arr = new String[length];
            for (int j = 0; j < length; ++j)
            {
                arr[j] = sc.nextLine();
            }


            // Finds the starting position
            for (int j = 0; j < arr[length - 1].length(); ++j)
                if (arr[length - 1].charAt(j) == '@')
                    currentPosition = j;

            // Finds the end position
            for (int j = 0; j < arr[0].length(); ++j)
                if (arr[0].charAt(j) == '#')
                    finalPosition = j;

            int currentLine = length - 1;

            int priorPosition = currentPosition;
            int priorLine = length - 1;

            boolean isFirstTime = true;
            int count = 0;

            while ((currentLine != 0) && (currentPosition != finalPosition))
            {
                System.out.println("Current line is: " + currentLine);
                System.out.println("Current Position is: " + currentPosition);



                // Left
                if (currentPosition - 1 < 0)
                {

                }
                else if (priorPosition != currentPosition - 1)
                {
                    char leftChar = arr[currentLine].charAt(currentPosition - 1);
                    if (matches(leftChar))
                    {
                        // Goes left
                        if (isFirstTime)
                        {
                            isFirstTime = false;
                            System.out.print("left");
                        }
                        else
                            System.out.print(" left");

                        priorPosition = currentPosition;
                        currentPosition -= 1;
                        continue;
                    }
                }

                // Right
                if (currentPosition + 1 > width - 1)
                {

                }
                else if (priorPosition != currentPosition + 1)
                {
                    char rightChar = arr[currentLine].charAt(currentPosition + 1);
                    if (matches(rightChar))
                    {
                        // Goes right
                        if (isFirstTime)
                        {
                            isFirstTime = false;
                            System.out.print("right");
                        }
                        else
                            System.out.print(" right");
                        priorPosition = currentPosition;
                        currentPosition += 1;
                        continue;
                    }
                }

                // Forth
                if (currentLine - 1 < 0)
                {

                }
                else if (priorLine != currentLine - 1)
                {
                    char forthChar = arr[currentLine - 1].charAt(currentPosition);
                    if (matches(forthChar))
                    {
                        // Goes forth
                        if (isFirstTime)
                        {
                            isFirstTime = false;
                            System.out.print("forth");
                        }
                        else
                            System.out.print(" forth");
                        priorLine = currentLine;
                        currentLine -= 1;

                    }
                }

            } // End of moving through the maze
            System.out.println();
        } // End of iterating through all test cases
    } // End of the main method

    private static boolean matches(char comparison)
    {
        String safeLetters = "IEHOVA";

        for (int i = 0; i < safeLetters.length(); ++i)
        {
            if (comparison == safeLetters.charAt(i))
                return true;
        }
        return false;
    } // End of the matches method


} // End of the main class
