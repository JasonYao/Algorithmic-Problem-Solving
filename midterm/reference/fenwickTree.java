//Assumes indices are 1-based
static public class Fenwick {
	public int[] table;

	public Fenwick(int maxN) {
		this.table = new int[maxN + 1];
	}

	public int sumQuery(int a, int b) {
		return sumQuery(b) - sumQuery(a - 1);
	}

	public int sumQuery(int k) {
		int ret = 0;
		while (k > 0) {
			ret += table[k];
			k &= k - 1;
		}
		return ret;
	}

	public void adjust(int i, int adj) {
		while (i < table.length) {
			table[i] += adj;
			i += (i & (-i));
		}
	}

	public int getValue(int i) {
		return sumQuery(i, i);
	}

	// Assumes entries of list are non-negative (i.e., cumulative sums are
	// increasing)
	// Returns first index whose cumulative sum is >= k
	// Returns -1 if all are less
	public int findFirst(int k) {
		int L = 1, R = table.length - 1;
		while (R - L > 1) {
			int M = (R + L) / 2;
			int val = sumQuery(M);
			if (val < k)
				L = M + 1;
			else
				R = M;
		}
		int LVal = sumQuery(L);
		if (LVal >= k)
			return L;
		return R == L || sumQuery(R) < k ? -1 : R;
	}
}

