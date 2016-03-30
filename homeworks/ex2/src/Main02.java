import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main02
{
    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Reads in the number of problems
        try
        {
            int testCases = Integer.parseInt(br.readLine());

            for (int i = 0; i < testCases; ++i)
            {
                // Input parsing
                StringTokenizer guess1Line = new StringTokenizer(br.readLine());
                StringTokenizer guess2Line = new StringTokenizer(br.readLine());

                String fullSet = "RGBYOV";

                String guess1 = guess1Line.nextToken();
                String guess2 = guess2Line.nextToken();

                int guess1N1 = Integer.parseInt(guess1Line.nextToken());
                int guess1N2 = Integer.parseInt(guess1Line.nextToken());

                int guess2N1 = Integer.parseInt(guess2Line.nextToken());
                int guess2N2 = Integer.parseInt(guess2Line.nextToken());

                TreeSet<String> allPermutations1 = new TreeSet<>();
                TreeSet<String> allPermutations2 = new TreeSet<>();

                // Generates all valid permutations in 1
                permutationDriver(guess1, allPermutations1);
                checkPermutations(allPermutations1, guess1N1, guess1N2, guess1);

                // Sanity check if there's no valid permutation in 1, skips to save computation
                if (allPermutations1.size() != 0)
                {
                    // There are some valids in 1, moves to 2
                    permutationDriver(guess2, allPermutations2);
                    checkPermutations(allPermutations2, guess2N1, guess2N2, guess2);

                    // Find the intersection between the two sets
                    allPermutations1.retainAll(allPermutations2); // set 1 now contains only elements in both sets
                }

                // Output
                if (allPermutations1.size() != 0)
                    System.out.println("Possible");
                else
                    System.out.println("Cheat");

            } // End of iterating through all test cases
        } // End of try
        catch (IOException e)
        {e.printStackTrace();}
        finally {try {br.close();} catch (IOException e) {e.printStackTrace();}}
    } // End of the main method

    private static void permutationDriver(String str, TreeSet<String> set) {generateValids("", str, set);}

    private static void generateValids(String prefix, String str, TreeSet<String> set)
    {
        int n = str.length();
        if (n == 0) set.add(prefix);
        else {
            for (int i = 0; i < n; i++)
                generateValids(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), set);
        }
    } // End of the generate valids method

    private static void checkPermutations(TreeSet<String> allPermutations, int N1, int N2, String guess)
    {
        Iterator<String> setIterator = allPermutations.iterator();

        while (setIterator.hasNext())
        {
            String current = setIterator.next();
            int count1 = 0;
            int count2 = 0;

            for (int i = 0; i < current.length(); ++i)
            {
                if (current.charAt(i) == guess.charAt(i))
                    ++count1;


                if (count1 != N1){}


            }

        }

    } // End of the check permutations


} // End of the main class
