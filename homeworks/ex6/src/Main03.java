import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main03
{
    private static int aCapacity;
    private static int bCapacity;
    private static int cCapacity;
    private static int d; // target amount
    private static ArrayList<State> finalState;
    private static State source;
    private static HashSet<State> set;

    public static void main(String args[])
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        try
        {
            int numberOfTestCases = Integer.parseInt(br.readLine());

            for (int currentTestCase = 0; currentTestCase < numberOfTestCases; ++currentTestCase)
            {
                StringTokenizer st = new StringTokenizer(br.readLine());
                aCapacity = Integer.parseInt(st.nextToken());
                bCapacity = Integer.parseInt(st.nextToken());
                cCapacity = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());

                /**
                 * The new way:
                 * 1.) We begin with a source state.
                 * 2.) From this state, we generate all possible "children state", except for
                 * the reverse of the prior move
                 * 3.) Run BFS (will give us shortest path to completion), and keep checking current state to see if
                 * ending condition is met.
                 * 4.) If ending condition is met, add the state to the list of valid paths. If not, generate children,
                 * and continue.
                 */

                /**
                 * The thinking: We begin with a source state. From this state, we generate all possible "children"
                 * states possible in one move. We then run BFS (will give us the shortest path to completion), and
                 * keep checking our current state to see if the ending condition is met. If so, adds that state to
                 * the list of valid paths. If not, generate children, and continue.
                 *
                 * Once bfs is complete, we compare each endstate's cost, and print the minimum, along with the final
                 * "d" value
                 */

                int totalSteps = Integer.MAX_VALUE;

                source = new State(0, 0, cCapacity, new ArrayList<State>(), 0);
                finalState = new ArrayList<>();
                set = new HashSet<>();

                // We continue to do this until we find one valid path
                for (; finalState.size() == 0; --d)
                {
                    newDfs(source);

                    // Since we're decrementing d at the end of the loop, we wanna kick out of it before that
                    if (finalState.size() != 0)
                        break;
                }

                // We have now built our finalState tree
                System.out.println("Finalstate size: " + finalState.size());

                for (State endState : finalState)
                {
//                    System.out.println("The final distance covered by the endstate was: " + endState.distance);
//                    System.out.println("The final depth the endstate is at: " + endState.cost);
                    totalSteps = Math.min(totalSteps, endState.cost);
                }


                // Output
                String output = totalSteps + " " + d + "\n";
                out.append(output);

            } // End of iterating through all test cases
            System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}
    } // End of the main method

    private static void dfs(State source)
    {
        Stack<State> stack = new Stack<>();
        source.distance = 0;
        stack.push(source);

        int foundDepth = Integer.MAX_VALUE;

        //TODO remove after
        while (stack.size() != 0)
        {
//            System.out.println("Stack START size is: " + stack.size());
            State current = stack.pop();
            System.out.printf("Found depth is: %d, distance is: %d\n", foundDepth, current.distance);

            // Checks for ending condition
            if ((current.a == d) || (current.b == d) || (current.c == d))
            {
                System.out.println("Got to ending condition");
                finalState.add(current);
                foundDepth = Math.min(foundDepth, current.distance);
                continue;
            }
            System.out.println("Got to 1");

            if ((foundDepth != Integer.MAX_VALUE) && (current.distance > foundDepth))
                continue;

            System.out.println("Got to 2");
            // Sanity check
            if (current.distance >= 2)
                continue;

            System.out.println("Got to 3");
            if (current.distance == Integer.MAX_VALUE || current.equals(source))
            {
                System.out.println("current dist is: " + current.distance);
                // Current has not been discovered until now
                if (current.equals(source))
                    source.distance = 0;
                else
                    current.distance = current.parent.distance + 1;

                generateChildrenStates(current);

                for (State child : current.children)
                {
                    child.parent = current;
                    stack.add(child);
                }
            }
        } // End of iterating through dfs
    } // End of the dfs method

    private static void newDfs(State source)
    {
        Stack<State> stack = new Stack<>();

        source.distance = 0;
        stack.add(source);

        int foundDepth = Integer.MAX_VALUE;

        while (stack.size() != 0)
        {
            State current = stack.pop();

            // Checks for ending condition
            if ((current.a == d) || (current.b == d) || (current.c == d))
            {
//                System.out.println("Got to ending condition");
                finalState.add(current);
                foundDepth = Math.min(foundDepth, current.distance);
                continue;
            }

            if ((foundDepth != Integer.MAX_VALUE) && (current.distance > foundDepth))
                continue;

            // Sanity check
            if (current.distance >= 19)
                continue;

            generateChildrenStates(current);

            /* Done generating children states */

            // Checks each children
            for (State child : current.children)
            {
                if (child.distance == Integer.MAX_VALUE)
                {
                    child.distance = current.distance + 1;
                    child.parent = current;
                    stack.add(child);
                }
            }
        } // End of bfs loop
    } // End of the bfs method

    private static void bfs(State source)
    {
        Queue<State> queue = new LinkedList<>();

        source.distance = 0;
        queue.add(source);

        int foundDepth = Integer.MAX_VALUE;

        while (queue.size() != 0)
        {
            State current = queue.remove();

            // Checks for ending condition
            if ((current.a == d) || (current.b == d) || (current.c == d))
            {
//                System.out.println("Got to ending condition");
                finalState.add(current);
                foundDepth = Math.min(foundDepth, current.distance);
                continue;
            }

            if ((foundDepth != Integer.MAX_VALUE) && (current.distance > foundDepth))
                continue;

            // Sanity check
            if (current.distance >= 17)
                continue;

            generateChildrenStates(current);

            /* Done generating children states */

            // Checks each children
            for (State child : current.children)
            {
                if (child.distance == Integer.MAX_VALUE)
                {
                    child.distance = current.distance + 1;
                    child.parent = current;
                    queue.add(child);
                }
            }
        } // End of bfs loop
    } // End of the bfs method

    private static void generateChildrenStates(State current)
    {
        /**
         * Generates all possible children states
         * for each bucket with water in it, check to see if it can pour into either of the other two. For each
         * valid state, create a new state with those variables, and add it to the children array.
         */

        int aDiff;
        int bDiff;
        int cDiff;
        if (current.equals(source))
        {
            aDiff = 0;
            bDiff = 0;
            cDiff = 0;
        }
        else
        {
            aDiff = current.parent.a - current.a;
            bDiff = current.parent.b - current.b;
            cDiff = current.parent.c - current.c;
        }

        // Starts generating children
        if (current.a > 0)
        {
            // Checks to see if it can pour into B or C
            if ((current.b < bCapacity) && !(bDiff > 0 && aDiff < 0))
            {
                int difference;
                if (current.a > (bCapacity - current.b))
                {
                    // Fills B to the top
                    difference = bCapacity - current.b;
                }
                else
                {
                    // Moves all from A to B, B still has capacity
                    difference = current.a;
                }

                current.children.add(new State(current.a - difference, current.b + difference, current.c,
                        new ArrayList<State>(), current.cost + difference));
            } // End of A -> B
            if ((current.c < cCapacity) && !(cDiff > 0 && aDiff < 0))
            {
                int difference;
                if (current.a > (cCapacity - current.c))
                {
                    // Fills C to the top
                    difference = cCapacity - current.c;
                }
                else
                {
                    // Moves all from A to C, C still has capacity
                    difference = current.a;
                }
                current.children.add(new State(current.a - difference, current.b, current.c + difference,
                        new ArrayList<State>(), current.cost + difference));
            } // End of A -> C
        }
        if (current.b > 0)
        {
            // Checks to see if it can pour into A or C
            if ((current.a < aCapacity) && !(aDiff > 0 && bDiff < 0))
            {
                int difference;
                if (current.b > (aCapacity - current.a))
                {
                    // Fills A to the top
                    difference = aCapacity - current.a;
                }
                else
                {
                    // Moves all from B to A, A still has capacity
                    difference = current.b;
                }
                current.children.add(new State(current.a + difference, current.b - difference, current.c,
                        new ArrayList<State>(), current.cost + difference));
            } // End of B -> A
            if ((current.c < cCapacity) && !(cDiff > 0 && bDiff < 0))
            {
                int difference;
                if (current.b > (cCapacity - current.c))
                {
                    // Fills C to the top
                    difference = cCapacity - current.c;
                }
                else
                {
                    // Moves all from B to C, C still has capacity
                    difference = current.b;
                }
                current.children.add(new State(current.a, current.b - difference, current.c + difference,
                        new ArrayList<State>(), current.cost + difference));
            } // End of B -> C
        }
        if ((current.c > 0))
        {
            // Checks to see if it can pour into A or B
            if ((current.a < aCapacity) && !(aDiff > 0 && cDiff < 0))
            {
                int difference;
                if (current.c > (aCapacity - current.a))
                {
                    // Fills A to the top
                    difference = aCapacity - current.a;
                }
                else
                {
                    // Moves all from C to A, A still has capacity
                    difference = current.c;
                }
                current.children.add(new State(current.a + difference, current.b, current.c - difference,
                        new ArrayList<State>(), current.cost + difference));
            } // End of C -> A
            if ((current.b < bCapacity) && !(bDiff > 0 && cDiff < 0))
            {
                int difference;
                if (current.c > (bCapacity - current.b))
                {
                    // Fills B to the top
                    difference = bCapacity - current.b;
                }
                else
                {
                    // Moves all from C to B, B still has capacity
                    difference = current.c;
                }
                current.children.add(new State(current.a, current.b + difference, current.c - difference,
                        new ArrayList<State>(), current.cost + difference));
            } // End of C -> B
        }
    } // End of the generate children states method

    private static class State
    {
        int a;
        int b;
        int c;
        ArrayList<State> children;
        int distance;
        State parent;
        int cost;

        public State(int a, int b, int c, ArrayList<State> children, int cost) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.children = children;
            this.parent = null;
            this.distance = Integer.MAX_VALUE;
            this.cost = cost;
        }
    } // End of the node class
} // End of the main class
