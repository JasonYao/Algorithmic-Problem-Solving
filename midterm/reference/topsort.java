class topsort
{
	static int MAXV = 100;
	static void topsort(graph g, int sorted[])
	{
		int indegree[] = new int [MAXV];
		queue zeroin = new queue();
		int x,y;
		compute_indegrees(g,indegree);
		for(int i=1;i<=g.nvertices;i++)
			if(indegree[i]==0)
				zeroin.enqueue(i);
		int j=0;
		while(!zeroin.empty())
		{
			j++;
			x=zeroin.dequeue();
			sorted[j]=x;
			for(int i=g.degree[x]-1;i>=0;i--)
			{
				y=g.edges[x][i];
				indegree[y]--;
				if(indegree[y]==0)
					zeroin.enqueue(y);
			}
		}
		if(j!=g.nvertices)
			System.out.printf("Not a DAG -- only %d vertices found\n",j);
	}
	static void compute_indegrees(graph g, int in[])
	{
		for(int i=1;i<=g.nvertices;i++)
			in[i] = 0;
		for(int i=1;i<=g.nvertices;i++)
			for(int j=0;j<g.degree[i];j++)
				in[g.edges[i][j]]++;
	}
	
	static public void main(String[] args)
	{
		graph g = new graph();
		int out[] = new int [MAXV+1];
		g.read_graph(true);
		g.print_graph();
		topsort(g,out);
		for(int i=1;i<=g.nvertices;i++)
			System.out.printf(" %d",out[i]);
		System.out.printf("\n");
	}
}
