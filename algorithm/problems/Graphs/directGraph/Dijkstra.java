package algorithm.problems.Graphs.directGraph;

import algorithm.algorithm_data_type.myStack;
import algorithm.problems.Sort.IndexMinPQ;

/**
 * Dijkstra
 * the algorithm for finding the shortest path from s to v 
 * which includes smallest weights summed from all edges from s to v
 */
public class Dijkstra {

    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private int S;
    private IndexMinPQ<Double> pq;

    public Dijkstra(WeightedDigraph G,int S){
        edgeTo=new DirectedEdge[G.V()];
        distTo=new double[G.V()];
        this.S=S;
        pq=new IndexMinPQ<Double>(G.V());

        for(int v=0;v<G.V();v++){
            distTo[v]=Double.POSITIVE_INFINITY;
        }

        distTo[S]=0.0;
        pq.insert(S, 0.0);
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
    }

    private void relax(WeightedDigraph G,int v){
        for(DirectedEdge e:G.edge(v)){
            int w=e.to();
            if (distTo[w]>(distTo[v]+e.weight())) {
                distTo[w]=distTo[v]+e.weight();
                edgeTo[w]=e;
                pq.insert(w, distTo[w]);
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v]<Double.POSITIVE_INFINITY;
    }

    public myStack<DirectedEdge> pathTo(int v){
        if (!hasPathTo(v)) {
            return null;
        }
        myStack<DirectedEdge> results=new myStack<DirectedEdge>();
        for(DirectedEdge e=edgeTo[v];e!=null;e=edgeTo[e.from()]){
            results.push(e);
        }
        return results;
    }

    //test
    public static void main(String[] args){
        WeightedDigraph test=new WeightedDigraph(System.getProperty("user.dir")+"/"+args[0]);
        int s=0;
        Dijkstra dijkstra=new Dijkstra(test,s);
        for(int v=0;v<test.V();v++){
            if (dijkstra.hasPathTo(v)) {
                System.out.print(s+" to "+v+" : ");
                for(DirectedEdge e:dijkstra.pathTo(v)){
                    System.out.print(e.toString()+" ");
                }
                System.out.println("total:"+dijkstra.distTo(v));
            }
        }

   }
}
