

    Make a 2D array boolean[][] visited designating the points that you have visited; set all elements to false
    Go through each point in two nested loops
    For each point where visited[r][c] is false, go into a DFS which you can implement recursively
    Inside the recursive DFS call check if the point is your destination; if yes, return true
    If the point has the correct color, explore its neighbors (up to four) in four directions
    If a neighbor has the right color, mark it as visited, and make a recursive call
    If a recursive call returns true, return true
    Otherwise, continue exploring other neighbors
    Once you are done exploring neighbors, return false.


///// DFS in general

1  procedure DFS-iterative(G,v):
2      let S be a stack
3      S.push(v)
4      while S is not empty
5            v = S.pop()
6            if v is not labeled as discovered:
7                label v as discovered
8                for all edges from v to w in G.adjacentEdges(v) do
9                    S.push(w)

