import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

public class DeluxeBFS {
    
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s->v path?
    private int[] distTo;      // distTo[v] = length of shortest s->v path

    public DeluxeBFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        validateVertices(sources);
        bfs(G, sources);
    }

    private void bfs(Digraph G, Iterable<Integer> sources) {
        Queue<Integer> q = new Queue<Integer>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }
        
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }
    
    public CommonAncestor getCommonAncestor(Digraph G, 
                                            Iterable<Integer> destinations)
    {
        int length = INFINITY;
        int ancestor = -1;
        
        validateVertices(destinations);
        for (int d : destinations)
        {
            boolean[] currentMarked = new boolean[G.V()];
            int[] currentDistTo = new int[G.V()];
            for (int v = 0; v < G.V(); v++)
                currentDistTo[v] = INFINITY;
        
            Queue<Integer> q = new Queue<Integer>();
            currentMarked[d] = true;
            currentDistTo[d] = 0;
            q.enqueue(d);
            while (!q.isEmpty()) {
                int v = q.dequeue();
                
                int distBetweenSourceAndDistination = distTo[v] + currentDistTo[v];
                if (marked[v] && length > distBetweenSourceAndDistination)
                {
                    length = distBetweenSourceAndDistination;
                    ancestor = v;
                }
                for (int w : G.adj(v)) 
                {
                    if (!currentMarked[w]) 
                    {
                        currentDistTo[w] = currentDistTo[v] + 1;
                        currentMarked[w] = true;
                        q.enqueue(w);
                    }
                }
            }
        }
        if (ancestor == -1)
        {
            length = -1;
        }
        return new CommonAncestor(ancestor, length);
    }
    
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        for (int v : vertices) {
            if (v < 0 || v >= marked.length) {
                throw new IllegalArgumentException(
                 "vertex " + v + " is not between 0 and " + (marked.length-1));
            }
        }
    }
}