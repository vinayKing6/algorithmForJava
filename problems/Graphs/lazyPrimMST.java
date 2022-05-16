package algorithm.problems.Graphs;

import algorithm.algorithm_data_type.myQueue;
import algorithm.problems.Sort.MinPQ;

/**
 * lazyPrimMST
 * the algorithm for the weighted graph to find out the 
 * k-mininum spanning tree (a tree which includes all vertexs only once 
 * but with the smallest weights of all edges)
 */
public class lazyPrimMST {
    private MinPQ<Edge> pq;
    private myQueue<Edge> mst;
    private boolean[] marked;
    private double weights;

    public lazyPrimMST(WeightedGraph G){
        pq=new MinPQ<Edge>();
        mst=new myQueue<Edge>();
        marked=new boolean[G.V()];
        weights=0;

        visit(G, 0);//start from 0
        while (!pq.isEmpty()) {
            Edge minE=pq.delMin();
            int v=minE.either();
            int w=minE.other(v);
            if (marked[v]&&marked[w]) {
                continue;
            }
            mst.enqueue(minE);
            weights+=minE.weight();

            if (mst.size()==G.V()) {
                break;
            }

            if(!marked[v])
                visit(G, v);
            if(!marked[w])
                visit(G, w);
        }

    }

    private void visit(WeightedGraph G,int v){
        marked[v]=true;
        for(Edge e:G.edge(v)){
            int w=e.other(v);
            if (!marked[w]) {
                pq.insert(e);
            }
        }
    }

    public double weights(){
        return weights;
    }

    public myQueue<Edge> edges(){
        return mst;
    }

    public static void main(String[] args){
        WeightedGraph test=new WeightedGraph(System.getProperty("user.dir")+"/"+args[0]);
        lazyPrimMST Prim=new lazyPrimMST(test);

        myQueue<Edge> mst=Prim.edges();
        WeightedGraph result=new WeightedGraph(mst.size()+1);
        for(Edge e:mst){
            System.out.println(e.toString());
            result.addEdge(e);
        }
        System.out.println(result.toString());
        System.out.println(Prim.weights());
    }
}
