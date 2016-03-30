import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main
{
    //ex1
//    public static void main(String args[])
//    {
//        Scanner sc = new Scanner(System.in);
//
//        int numberOfTestCases = sc.nextInt();
//
//        for (int i = 0; i < numberOfTestCases; ++i)
//        {
//            int numberOfStoresInterestedIn = sc.nextInt();
//
//
//
//        } // End of iterating through test cases
//
//    } // End of the main method

//    public static void main(String args[])
//    {
//        String currentWord;
//        try{
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//            // Start of reading through
//            int currentCaseNumber = 1;
//            while (!(currentWord = br.readLine()).equals("#"))
//            {
//                String language;
//
//                switch (currentWord)
//                {
//                    case "HELLO":
//                        language = "ENGLISH";
//                        break;
//                    case "HALLO":
//                        language = "GERMAN";
//                        break;
//                    case "BONJOUR":
//                        language = "FRENCH";
//                        break;
//                    case "HOLA":
//                        language = "SPANISH";
//                        break;
//                    case "CIAO":
//                        language = "ITALIAN";
//                        break;
//                    case "ZDRAVSTVUJTE":
//                        language = "RUSSIAN";
//                        break;
//                    default:
//                        language = "UNKNOWN";
//                        break;
//                }
//
//                // Outputs answer
//                System.out.println("Case " + currentCaseNumber + ": " + language);
//                ++currentCaseNumber;
//            }
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//    } // End of the ex2 main method


//    public static void main(String args[])
//    {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        // Used because insertion order is kept
//        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
//        String inputLine;
//        try {
//            while ((inputLine = br.readLine()) != null)
//            {
//                StringTokenizer st = new StringTokenizer(inputLine);
//                while (st.hasMoreTokens())
//                {
//                    int currentToken = Integer.parseInt(st.nextToken());
//                    if (map.containsKey(currentToken))
//                    {
//                        int newValue = map.get(currentToken) + 1;
//                        map.replace(currentToken, newValue);
//                    }
//                    else
//                        map.put(currentToken, 1);
//                }
//            }
//
//            for (Map.Entry<Integer,Integer> entry : map.entrySet())
//            {
//                System.out.println(entry.getKey() + " " + entry.getValue());
//            }
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    } // End of the ex3 main method


    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTestCases;
        String potentialPal;
        int squareSize = 0;
        boolean isPal = true;
        Stack<Character> stack = new Stack<>();
        try {
            numberOfTestCases = Integer.parseInt(br.readLine());

            for (int i = 0; i < numberOfTestCases; ++i)
            {
                potentialPal = br.readLine();

                for (int j = 0; j < potentialPal.length()/2; ++j)
                    stack.push(potentialPal.charAt(j));


                int j = potentialPal.length()/2;
                if (potentialPal.length() % 2 != 0)
                    ++j;

                for (; j < potentialPal.length(); ++j)
                {
                    char fromStack = stack.pop();
                    if (fromStack == potentialPal.charAt(j))
                    {
                        // Matches, continues on
                        isPal = true;
                    }
                    else
                    {
                        // No match, sets to false and moves on
                        isPal = false;
                        break;
                    }
                }

                if (Math.sqrt((double) potentialPal.length()))



                // output
                System.out.println("Case #" + i + ":");
                if (isPal)
                    System.out.println(squareSize);
                else
                    System.out.println("No magic :(");
            } // End of reading through input
        } catch (IOException e) {
            e.printStackTrace();
        }

    } // End of the ex4 main method

} // End of the Main class
