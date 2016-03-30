/* Depth first search: Adjacency List */

/* Use for finding *a* maze solution (for shortest, use BFS) */

/* Inside main(): */
int[][] graph = new int[rows][cols];

// Read in graph
for (int i = 0; i < rows; ++i)
{
	StringTokeniser st = new StringTokeniser(br.readLine());
	for (int j = 0; j < cols; ++j)
		graph[i][j] = Integer.parseInt(st.nextToken());
}

Node source = new Node(startX, startY);
Node end = new Node(endX, endY);
int costOfTraversal = dfs(source, end, graph);

// Output
out.append(costOfTraversal);
out.append("\n");
/* End of main() */

private static dfs(Node source, Node end, Node[][] graph)
{
	int validValud = 0; // The value of any valid values in graph[][]
	Stack<Node> stack = new Stack<>();
	stack.push(source);

	boolean[][] visited = new boolean[graph.length][graph[0].length]; // Note, this will work for matrix, not array of arrays

	while (stack.size() != 0)
	{
		Node current = stack.pop();
		if (!visited[current.row][current.col])
		{
			// Current has not been visited yet
			visited[current.row][current.col] = true;

			// For each edge from current to its neighbours, push onto stack
			// Top
			int rowCheck = current.row - 1;
			int colCheck = current.col;

			if (rowCheck >= 0)
			{
				if ()
			}

			// Bot
			rowCheck = current.row + 1;
			colCheck = current.col;
			if (rowCheck < graph.length)
			{

			}

			// Left
			rowCheck = current.row;
			colCheck = current.col - 1;

			if (colCheck >= 0)
			{

			}

			// Right
			rowCheck = current.row;
			colCheck = current.col + 1;

			if (colCheck < graph[0].length)
			{

			}

		} // End of visiting check

	} // End of iterating through dfs
} // End of dfs

