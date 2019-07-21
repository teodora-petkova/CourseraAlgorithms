import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
// import edu.princeton.cs.algs4.StdOut;
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

   /* private void print(Integer[] arr)
   {
       for (int i = 0; i < arr.length; i++)
       {
           StdOut.print(arr[i] + ", ");
       }   
       StdOut.println();
   } */
   
   private class CommonAncestor
   {
       int vertex;
       int length;
       
       public CommonAncestor(int v, int len)
       {
           this.vertex = v;
           this.length = len;
       }
   };
   
   private void bfsOneStep(CommonAncestor commonAncestor, int currentVertex, 
                           Queue<Integer> q, Integer[] dist, boolean[] marked, 
                           int distFromOtherVertex, boolean markedInOtherBFS)
   {
       marked[currentVertex] = true;
       int currentDistance = distFromOtherVertex + dist[currentVertex];
       if (markedInOtherBFS && currentDistance < commonAncestor.length)
       {
           commonAncestor.length = currentDistance;
           commonAncestor.vertex = currentVertex;
       }
            
       for (int adjacent : G.adj(currentVertex))
       {
           if (!marked[adjacent]) 
           {
               dist[adjacent] = dist[currentVertex] + 1;
               q.enqueue(adjacent);
           }
       }
   }
   
   private void initialiseDistancesAndMarkedVertices(Integer[] distv, Integer[] distw,
                                                     boolean[] markedv, boolean[] markedw, int size)
   {
       for (int i = 0; i < size; i++)
       {
           distv[i] = 0;
           distw[i] = 0;
           markedv[i] = false;
           markedw[i] = false;
       }  
   }
   
   private CommonAncestor getCommonAncestor(int v, int w)
   {   
       int size = G.V();
       boolean[] markedv = new boolean[size];
       boolean[] markedw = new boolean[size];

       Integer[] distv = new Integer[size];
       Integer[] distw = new Integer[size];
       
       initialiseDistancesAndMarkedVertices(distv, distw, markedv, markedw, size);
       
       int currentv = v;
       int currentw = w;
       
       Queue<Integer> qv = new Queue<Integer>();
       Queue<Integer> qw = new Queue<Integer>();
       qv.enqueue(currentv);
       qw.enqueue(currentw);       
       
       CommonAncestor commonAncestor = new CommonAncestor(-1, Integer.MAX_VALUE);
       
       while ((!qv.isEmpty() || !qw.isEmpty())
                  && (commonAncestor.length >= distv[currentv] &&
                      commonAncestor.length >= distw[currentw]))
       {   
           if (!qv.isEmpty())
           {
               currentv = qv.dequeue(); 
               bfsOneStep(commonAncestor, currentv, qv, distv, markedv, distw[currentv], markedw[currentv]);
           }
           if (!qw.isEmpty())
           {
               currentw = qw.dequeue(); 
               bfsOneStep(commonAncestor, currentw, qw,  distw, markedw, distv[currentw], markedv[currentw]);
           }
       }
       return commonAncestor;
   }
   
   // length of shortest ancestral path between v and w; -1 if no such path
   public int length(int v, int w)
   {
      if (v < 0 || v > G.V() | w < 0 || w > G.V())
           throw new IllegalArgumentException();
     
      CommonAncestor r = getCommonAncestor(v, w);
      if (r.vertex == -1)
          return -1;
      return r.length; 
   }

   // a common ancestor of v and w that participates 
   // in a shortest ancestral path; -1 if no such path
   public int ancestor(int v, int w)
   {   
       if (v < 0 || v > G.V() | w < 0 || w > G.V())
           throw new IllegalArgumentException();
    
       CommonAncestor ancestor = getCommonAncestor(v, w);
       return ancestor.vertex;
   }

   // length of shortest ancestral path between any vertex in v and 
   // any vertex in w; -1 if no such path
   public int length(Iterable<Integer> v, Iterable<Integer> w)
   {
       if (v == null || w == null)
           throw new IllegalArgumentException();
       return -1;
   }

   // a common ancestor that participates in shortest ancestral path;
   // -1 if no such path
   public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
   {
       if (v == null || w == null)
           throw new IllegalArgumentException();
       return -1;
   }

   // do unit testing of this class
   public static void main(String[] args)
   {
   
   }
}