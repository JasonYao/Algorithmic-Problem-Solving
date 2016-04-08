/* Topological sort via Adjacency List (recommended efficient version) (Kahn's algorithm) */

/* Inside Main(): */
// Adjacency list
HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();

// Parses edges & generates indegrees list
int[] indegrees = new int[n + 1];
for (int i = 0; i < m; ++i)
{
	StringTokenizer dependency = new StringTokenizer(br.readLine());
	int source = Integer.parseInt(dependency.nextToken());
	int destination = Integer.parseInt(dependency.nextToken());

	if (!adjList.containsKey(source))
		adjList.put(source, new ArrayList<Integer>());

	indegrees[destination] += 1;
	adjList.get(source).add(destination);
} // End of edge parsing

LinkedList<Integer> sorted = kahn(adjList, indegrees);

if (sorted == null)
{
	// Cycle was detected, do whatever spec implementation asks for
}
else
{
	// No Cycles detected, output
	for (int curr : sorted)
	{
		out.append(curr);
		out.append("\n");
	}
}

/* End of Main(): */

private static LinkedList<Integer> kahn(HashMap<Integer, ArrayList<Integer>> adj, int[] indegree)
{
	PriorityQueue<Integer> queue = new PriorityQueue<>();
	LinkedList<Integer> finalList = new LinkedList<>();
	for (int i = 1; i < indegree.length; ++i)
		if (indegree[i] == 0)
			queue.add(i);


	int count = 0;
	while (queue.size() != 0)
	{
		int u = queue.remove();
		finalList.addLast(u);
		++count;

		if (adj.containsKey(u))
		{
			for (int i = 0; i < adj.get(u).size(); ++i)
			{
				indegree[adj.get(u).get(i)] -= 1;
				if (indegree[adj.get(u).get(i)] == 0)
					queue.add(adj.get(u).get(i));
			}
		}
	} // End of kahn's iteration

	//Check for cycle
	if (count != indegree.length - 1)
		return null;
	else
		return finalList;
} // End of kahn's algorithm

/* Topological sort via Adjacency Matrix (not efficient) (Kahn's algorithm) */
