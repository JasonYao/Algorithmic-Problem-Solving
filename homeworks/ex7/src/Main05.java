import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * UVA Question 10129: Play on Words
 * Question link: https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1070
 * Valid solution: TODO
 */
public class Main05
{
    private static int[] indegrees = new int[26]; // Where the indices mark a letter (a == 0, z == 26. etc)
    private static int[] outdegrees = new int[26];

    private static final boolean IS_DEBUG_MODE = false;

    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        try
        {
            int numberOfTestCases = Integer.parseInt(br.readLine());
            for (int currentTestCase = 0; currentTestCase < numberOfTestCases; ++currentTestCase)
            {
                // Resets for future round
                Arrays.fill(indegrees, 0);
                Arrays.fill(outdegrees, 0);

                int n = Integer.parseInt(br.readLine()); // Number of plates

                HashSet<Integer> sources = new HashSet<>();
                HashSet<Integer> sinks = new HashSet<>();
                // Generates indegrees and outdegrees
                for (int i = 0; i < n; ++i)
                {
                    String plate = br.readLine();
                    int source = getCharIndex(plate.charAt(0));
                    int sink = getCharIndex(plate.charAt(plate.length() - 1));
                    indegrees[source] += 1;
                    outdegrees[sink] += 1;

                    if (!sources.contains(source))
                        sources.add(source);
                    if (!sinks.contains(sink))
                        sinks.add(sink);
                }

                if (IS_DEBUG_MODE)
                {
                    System.out.println("--------------------- In degrees --------------------------");
                    for (int indegree : indegrees) System.out.print(indegree + " ");
                    System.out.println();
                    System.out.println("--------------------- In degrees --------------------------");

                    System.out.println("--------------------- Out degrees --------------------------");
                    for (int outdegree : outdegrees) System.out.print(outdegree + " ");
                    System.out.println();
                    System.out.println("--------------------- Out degrees --------------------------");
                }

                boolean isEulerianPath = true;

                ArrayList<Integer> positiveDifferences = new ArrayList<>(); // Sources
                ArrayList<Integer> negativeDifferences = new ArrayList<>(); // Sinks
                // Now iterates through to find whether the values are equal, or +/- 1
                for (int i = 0; i < indegrees.length; ++i)
                {
                    if (outdegrees[i] - indegrees[i] == 0);
                    else if (outdegrees[i] - indegrees[i] == 1)
                        positiveDifferences.add(i);
                    else if (outdegrees[i] - indegrees[i] == -1)
                        negativeDifferences.add(i);
                    else
                    {
                        isEulerianPath = false;
                        break;
                    }
                }

                // Specical case: az -> za, or varients thereof
                // za -> az is valid, 
                if ()

                if (IS_DEBUG_MODE)
                    System.out.printf("Set #%d: Status of isEulerianPath after check #1: %b\n", currentTestCase, isEulerianPath);

                // We are guaranteed by the above check that elements in posDiff do not exist in negDiff

                // Size check: if multiple source or sink nodes, sets to false
                if (positiveDifferences.size() != 1)
                    isEulerianPath = false;

                if (IS_DEBUG_MODE)
                    System.out.printf("Set #%d: Status of isEulerianPath after check #2: %b, " +
                            "with size: %d\n", currentTestCase, isEulerianPath, positiveDifferences.size());

                if (negativeDifferences.size() != 1)
                    isEulerianPath = false;

                if (IS_DEBUG_MODE)
                    System.out.printf("Set #%d: Status of isEulerianPath after check #3: %b, " +
                            "with size: %d\n", currentTestCase, isEulerianPath, negativeDifferences.size());

                if (isEulerianPath)
                    out.append("Ordering is possible.\n");
                else
                    out.append("The door cannot be opened.\n");
            } // End of iterating through all test cases
        }
        catch (IOException e)
        {e.printStackTrace();}
            System.out.print(out);
    } // End of the main method

    private static int getCharIndex(char c)
    {
        switch (c)
        {
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 4;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 7;
            case 'i': return 8;
            case 'j': return 9;
            case 'k': return 10;
            case 'l': return 11;
            case 'm': return 12;
            case 'n': return 13;
            case 'o': return 14;
            case 'p': return 15;
            case 'q': return 16;
            case 'r': return 17;
            case 's': return 18;
            case 't': return 19;
            case 'u': return 20;
            case 'v': return 21;
            case 'w': return 22;
            case 'x': return 23;
            case 'y': return 24;
            case 'z': return 25;
            default:
                return -1;
        }
    } // End of the get char index value
} // End of the main class
