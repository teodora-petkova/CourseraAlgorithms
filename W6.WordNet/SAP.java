import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
//Shortest ancestral path.
public class SAP {
    
   private Digraph G;
   private Integer[] distv;
   private Integer[] distw;
       
   // constructor takes a digraph (not necessarily a DAG)
   public SAP(Digraph G)
   {
       if (G == null)
           throw new java.lang.NullPointerException();
       this.G = G;         
       this.distv = new Integer[G.V()];
       this.distw = new Integer[G.V()];
       for (int i = 0; i < G.V(); i++)
       {
           this.distv[i] = 0;
           this.distw[i] = 0;
       }        
   }

   private int getCommonAncestor(int v, int w)
   {   
       boolean[] markedv = new boolean[G.V()];
       boolean[] markedw = new boolean[G.V()];

       Queue<Integer> qv = new Queue<Integer>();
       Queue<Integer> qw = new Queue<Integer>();
       qv.enqueue(v);
       qw.enqueue(w);
       distv[v] = 0;
       distw[w] = 0;
       int bestAncestralLength = Integer.MAX_VALUE;
       int ancestor = -1;
       int currentv = v;
       int currentw = w;
       while ((!qv.isEmpty() || !qw.isEmpty())
                  && (bestAncestralLength > distv[currentv] 
                          && bestAncestralLength > distw[currentw]))
       {
           if (!qv.isEmpty() && bestAncestralLength > distv[currentv])
           {
               currentv = qv.dequeue(); 
               markedv[currentv] = true;
               if (markedw[currentv])
               {
                   bestAncestralLength = distw[currentv] + distv[currentv];
                   ancestor = currentv;
               }
               for (int adjacentv : G.adj(currentv))
               {
                   if (!markedv[adjacentv]) 
                   {
                       distv[adjacentv] = distv[currentv] + 1;
                       qv.enqueue(adjacentv);
                   }
               }
           }
           
           if (!qw.isEmpty() && bestAncestralLength > distw[currentw])
           {
               currentw = qw.dequeue(); 
               markedw[currentw] = true;
               if (markedv[currentw])
               {
                   bestAncestralLength = distw[currentw] + distv[currentw];
                   ancestor = currentw;               
               } 
               
               for (int adjacentw : G.adj(currentw))
               {
                   if (!markedw[adjacentw]) 
                   {
                       distw[adjacentw] = distw[currentw] + 1;
                       qw.enqueue(adjacentw);
                   }
               }
           }
       }
       
       return ancestor;       
   }
   
   // length of shortest ancestral path between v and w; -1 if no such path
   public int length(int v, int w)
   {
      if (v < 0 || v > G.V() | w < 0 || w > G.V())
           throw new java.lang.IndexOutOfBoundsException();
     
      int r = getCommonAncestor(v, w);
      if (r == -1)
          return -1;
      return distv[r] + distw[r]; 
   }

   // a common ancestor of v and w that participates 
   // in a shortest ancestral path; -1 if no such path
   public int ancestor(int v, int w)
   {   
       if (v < 0 || v > G.V() | w < 0 || w > G.V())
           throw new java.lang.IndexOutOfBoundsException();
    
       return getCommonAncestor(v, w);
   }

   // length of shortest ancestral path between any vertex in v and 
   // any vertex in w; -1 if no such path
   public int length(Iterable<Integer> v, Iterable<Integer> w)
   {
       if (v == null || w == null)
           throw new java.lang.NullPointerException();
       return -1;
   }

   // a common ancestor that participates in shortest ancestral path;
   // -1 if no such path
   public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
   {
       if (v == null || w == null)
           throw new java.lang.NullPointerException();
       return -1;
   }

   // do unit testing of this class
   public static void main(String[] args)
   {
   
   }
}