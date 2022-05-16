package algorithm.problems.Graphs;

import algorithm.algorithm_data_type.myQueue;
import algorithm.problems.Sort.MinPQ;
import algorithm.problems.union_find.UF;

/**
 * KruskalMST
 * a k-minimum spanning tree which is different from prim algorithm
 * instead of adding edge to tree one by one, KruskalMST create separate trees ,
 * which quite like forest , at the end , it combines all forest to one tree ,which is
 * the final k-minimum spanning tree.
 *
 */
public class KruskalMST {

   private myQueue<Edge> mst;

   public KruskalMST(WeightedGraph G){
       mst=new myQueue<Edge>();
       MinPQ<Edge> pq=new MinPQ<Edge>(G.edges());
       UF uf=new UF(G.V());

       while (!pq.isEmpty()&&mst.size()<G.V()) {
           Edge e=pq.delMin();
           int v=e.either();
           int w=e.other(v);

           if (uf.connected(v, w)) {
               continue;
           }

           mst.enqueue(e);
           uf.union(v, w);
       }
   }

   public double weights(){
       double result=0.0;
       for(Edge e:mst){
           result+=e.weight();
       }
       return result;
   }

   public myQueue<Edge> edges(){
       return mst;
   }

   public static void main(String[] args){
        WeightedGraph test=new WeightedGraph(System.getProperty("user.dir")+"/"+args[0]);
        KruskalMST Kruskal=new KruskalMST(test);

        myQueue<Edge> mst=Kruskal.edges();
        WeightedGraph result=new WeightedGraph(mst.size()+1);
        for(Edge e:mst){
            System.out.println(e.toString());
            result.addEdge(e);
        }
        System.out.println(result.toString());
        System.out.println(Kruskal.weights());
   }
}
