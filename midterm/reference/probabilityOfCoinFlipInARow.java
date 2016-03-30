import java.util.*;
import java.io.*;

public class CoinProb
{
/**
 * A coin is flipped n times, where n is given on a single line. You win if there is
 * ever a run of 7 heads or 7 tails in a row. What is the probability that you win?
 * Here 1 ≤ n ≤ 100. Output the result with 6 digits after the decimal.
**/
    static double[][] cache;
    static double solve(int runLength, int tossesLeft)
    {
	if (runLength == 7) return 1;
	if (tossesLeft + runLength < 7) return 0;
	if (cache[runLength][tossesLeft] > -1) 
	    return cache[runLength][tossesLeft];
	return cache[runLength][tossesLeft] = (solve(runLength+1,tossesLeft-1)+solve(1,tossesLeft-1))/2.0;
    }
    public static void main(String[] args) throws Exception
    {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	int n = Integer.parseInt(in.readLine());
	cache = new double[7][n+1];
	for (int i = 0; i < 7; ++i) Arrays.fill(cache[i],-1.0);
	System.out.printf("%.06f\n",solve(0,n));
    }
}
