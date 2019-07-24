import java.util.Collections;
import edu.princeton.cs.algs4.Digraph;

// Shortest ancestral path.
public class SAP {
    
   private final Digraph G;
   
   // constructor takes a digraph (not necessarily a DAG)
   public SAP(Digraph G)
   {
       if (G == null)
           throw new IllegalArgumentException();
       this.G = new Digraph(G);      
   }

   // length of shortest ancestral path between v and w; -1 if no such path
   public int length(int v, int w)
   {
      validateVertex(v);
      validateVertex(w);
     
      CommonAncestor ancestor = getCommonAncestor(v, w);
      return ancestor.length; 
   }

   // a common ancestor of v and w that participates 
   // in a shortest ancestral path; -1 if no such path
   public int ancestor(int v, int w)
   {   
       validateVertex(v);
       validateVertex(w);
       
       CommonAncestor ancestor = getCommonAncestor(v, w);
       return ancestor.vertex;
   }

   // length of shortest ancestral path between any vertex in v and 
   // any vertex in w; -1 if no such path
   public int length(Iterable<Integer> v, Iterable<Integer> w)
   {
       validateVertices(v);
       validateVertices(w);

       CommonAncestor ancestor = getCommonAncestor(v, w);
       return ancestor.length;
   }

   // a common ancestor that participates in shortest ancestral path;
   // -1 if no such path
   public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
   {
       validateVertices(v);
       validateVertices(w);
       
       CommonAncestor ancestor = getCommonAncestor(v, w);
       return ancestor.vertex;
   }
   
   private CommonAncestor getCommonAncestor(int v, int w)
   {   
       DeluxeBFS bfs = new DeluxeBFS(this.G, Collections.singletonList(v));
       return bfs.getCommonAncestor(G, Collections.singletonList(w));
   }
   
   private CommonAncestor getCommonAncestor(Iterable<Integer> v, Iterable<Integer> w)
   {
       DeluxeBFS bfs = new DeluxeBFS(this.G, v);
       return bfs.getCommonAncestor(G, w);
   }   
   
   private void validateVertex(int v)
   {
       if (v < 0 || v > G.V())
           throw new IllegalArgumentException();
   }
   
   private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("the argument is null!");
        }
        
        for (Integer v : vertices) {
            if (v == null) {
                throw new IllegalArgumentException("the argument is null!");
            }
            validateVertex(v);
        }
   }
}