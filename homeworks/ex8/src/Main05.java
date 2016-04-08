import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * UVA Question 897: Anagrammatic Primes
 * Question link: https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=838
 * Valid solution: Yes
 */
public class Main05
{
    private static PriorityQueue<Integer> PERMUTATIONS;
    private static PriorityQueue<Integer> ANAGRAMMATIC_PRIMES = new PriorityQueue<>();
    public static void main(String args[])
    {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        try
        {
            addPrecomputedAnagrammaticPrimes(); // Generated via the generateAnagrammaticPrimes() method
            String line;
            while (!(line = br.readLine()).equals("0"))
            {
                /**
                 * Note: Sieve of Eratosthenes is too slow in this case, direct cardinality testing is probably
                 * the way to go <-- NOPE, not even that. We're depending on the fact that we can pre-compute the
                 * anagrammatic primes, and that it looks like there aren't any others past 1K for some reason
                 */
                int n = Integer.parseInt(line); // Lower bound

                // Generates the upper bound (higher than n, and the closest power of 10 (e.g. 10, 100, or 1,000))
                int upperBound = 1;
                for (int nCopy = n; nCopy > 0; nCopy /= 10)
                    upperBound *= 10;

                int lowerBound = n + 1;
                if (lowerBound % 2 == 0)
                    lowerBound = n + 1;

                int answer = 0;
                for (int a : ANAGRAMMATIC_PRIMES)
                {
                    if ((a >= lowerBound) && (a < upperBound))
                    {
                        answer = a;
                        break;
                    }
                }

                // Output
                out.append(answer);
                out.append(System.lineSeparator());
            } // End of iterating through all test cases
			System.out.print(out);
        }
        catch (IOException e)
        {e.printStackTrace();}

    } // End of the main method

    private static void addPrecomputedAnagrammaticPrimes()
    {
        int[] set = {
                2, 3, 5, 7, 11,
                13, 17, 31, 37,
                71, 73, 79, 97,
                113, 131, 199,
                311, 337, 373,
                733, 919, 991};
        for (int a : set)
            ANAGRAMMATIC_PRIMES.add(a);
    } // End of the add pre computed anagrammatic primes

    private static void generateAnagrammaticPrimes()
    {
        ANAGRAMMATIC_PRIMES.add(2);
        for (int i = 3; i < 20000; i += 2)
        {
            if (isPrime(i))
            {
                if (isAnagrammatic(i))
                    ANAGRAMMATIC_PRIMES.add(i);
            }
        }
    }

    private static boolean isPrime(int num)
    {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) return false;
        return true;
    } // End of the isPrime method

    private static boolean isAnagrammatic(Integer prime)
    {
        boolean isAnagrammatic = true;
        PERMUTATIONS = new PriorityQueue<>();
        permutation(Integer.toString(prime));

        while (PERMUTATIONS.size() != 0)
        {
            int state = PERMUTATIONS.remove();
            if (!isPrime(state))
            {
                isAnagrammatic = false;
                break;
            }
        }
        return isAnagrammatic;
    } // End of the isAnagrammatic method

    /**
     * NOTE: The following permutation methods were copied from stack overflow since I didn't want to re-implement it
     * SO link: http://stackoverflow.com/a/4240323
     */

    public static void permutation(String str) {
        permutation("", str);
    } // End of the permutation method

    private static void permutation(String prefix, String str)
    {
        int n = str.length();
        if ((n == 0) && (!PERMUTATIONS.contains(Integer.parseInt(prefix)))) PERMUTATIONS.add(Integer.parseInt(prefix));
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    } // End of the permutation method
} // End of the main class
