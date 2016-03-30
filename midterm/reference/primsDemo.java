import java.util.*;

class primdemo
{
	static int MAXV = 100;
	static int MAXDEGREE = 50;
	static int parent[] = new int [MAXV];
	static class graph
	{
		class edge
		{
			int v,weight;
		}
		edge edges[][];
		int degree[];
		int nvertices;
		int nedges;
		graph()
		{
			edges = new edge[MAXV+1][MAXDEGREE];
			degree = new int[MAXV+1];
			nvertices=nedges=0;
			for(int i=0;i<MAXV+1;i++)
				for(int j=0;j<MAXDEGREE;j++)
					edges[i][j] = new edge();
		}
		void read_graph(boolean directed)
		{
			Scanner sc = new Scanner(System.in);
			nvertices = sc.nextInt();
			for(int i=1;i<=nvertices;i++)
				degree[i] = 0;
			int m = sc.nextInt();
			for(int i=0;i<m;i++)
			{
				int x = sc.nextInt();
				int y = sc.nextInt();
				int weight = sc.nextInt();
				edges[x][degree[x]].v = y;
				edges[x][degree[x]].weight = weight;
				if(!directed) edges[y][degree[y]].v = x;
				if(!directed) edges[y][degree[y]].weight = weight;
				degree[x]++;
				if(!directed) degree[y]++;
			}
		}		
	}
	static void prim(graph g, int start)
	{
		int i,j;
		boolean intree[] = new boolean[MAXV];
		int distance[] = new int[MAXV];
		int v,w,weight,dist;
		for(i=1;i<=g.nvertices;i++)
		{
			intree[i]=false;
			distance[i]=2147483647;
			parent[i]=-1;
		}
		distance[start]=0;
		v=start;
		while(!intree[v])
		{
			intree[v]=true;
			for(i=g.degree[v]-1;i>=0;i--)
			{
				w=g.edges[v][i].v;
				weight=g.edges[v][i].weight;
				if(distance[w]>weight&&!intree[w])
				{
					distance[w]=weight;
					parent[w]=v;
				}
			}
			v=1;
			dist=2147483647;
			for(i=2;i<=g.nvertices;i++)
			{
				if(!intree[i]&&dist>distance[i])
				{
					dist=distance[i];
					v=i;
				}
			}
		}
	}
	static void find_path(int start, int end, int parents[])
	{
		if(start==end || end == -1)
			System.out.printf("\n%d",start);
		else
		{
			find_path(start, parents[end], parents);
			System.out.printf(" %d", end);
		}
	}
	static public void main(String[] args)
	{
		graph g=new graph();
		int i;
		g.read_graph(false);
		prim(g,1);
		System.out.printf("Out of Prim\n");
		for (i=1; i<=g.nvertices; i++) 
			find_path(1,i,parent);
		System.out.printf("\n");
	}
}

