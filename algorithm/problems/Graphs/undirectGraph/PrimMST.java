package algorithm.problems.Graphs.undirectGraph;

import algorithm.algorithm_data_type.myQueue;
import algorithm.problems.Sort.IndexMinPQ;

/**
 * PrimMST
 * a k-minimum spanning tree algorithm which is more efficient than lazyPrimMST
 */
public class PrimMST {

   private Edge[] edgeTo;
   private boolean[] marked;
   private IndexMinPQ pq;
   private double[] distTo;

   public PrimMST(WeightedGraph G){
       edgeTo=new Edge[G.V()];
       marked=new boolean[G.V()];
       distTo=new double[G.V()];
       pq=new IndexMinPQ<Double>(G.V());

       for(int v=0;v<G.V();v++){
           distTo[v]=Double.POSITIVE_INFINITY;
       }

       distTo[0]=0.0;
       pq.insert(0, 0.0);

       while (!pq.isEmpty()) {
           visit(G, pq.delMin());
       }
   }

   private void visit(WeightedGraph G,int v){
       marked[v]=true;

       for(Edge e:G.edge(v)){
           int w=e.other(v);
           if (marked[w]==true) {
               continue;
           }

           if (e.weight()<distTo[w]) {
               edgeTo[w]=e;
               distTo[w]=e.weight();
               pq.insert(w, distTo[w]);
           }
       }
   }

   public myQueue<Edge> edges(){
       myQueue<Edge> result=new myQueue<Edge>();

       for(int i=1;i<edgeTo.length;i++){
           result.enqueue(edgeTo[i]);
       }

       return result;
   }

   public double weights(){
       double weights=0;
       for(double weight:distTo){
           weights+=weight;
       }

       return weights;
   }

   public static void main(String[] args){
        WeightedGraph test=new WeightedGraph(System.getProperty("user.dir")+"/"+args[0]);
        PrimMST prim=new PrimMST(test);

        myQueue<Edge> mst=prim.edges();
        WeightedGraph result=new WeightedGraph(mst.size()+1);
        for(Edge e:mst){
            System.out.println(e.toString());
            result.addEdge(e);
        }
        System.out.println(result.toString());
        System.out.println(prim.weights());
   }
}
