package algorithm.problems.Graphs;
import java.io.IOException;

import algorithm.algorithm_data_type.myBag;
import algorithm.tools.inputKit;


/**
 * WeightedGraph
 * an undirective graph with weighted edges
 */
public class WeightedGraph {
    private int V;
    private int E;
    private myBag<Edge>[] adj;

    public WeightedGraph(int V){
        this.V=V;
        this.E=0;
        this.adj=(myBag<Edge>[])new myBag[V];

        for (int i = 0; i < adj.length; i++) {
            adj[i]=new myBag<Edge>();
        }
    }

    public WeightedGraph(String path){
        try{
            Double[] input=inputKit.getDoubles(path);
            this.V=input[0].intValue();
            adj=(myBag<Edge>[])new myBag[V];
            for (int i = 0; i < adj.length; i++) {
                adj[i]=new myBag<Edge>();
            }
            int E=input[1].intValue();
            this.E=0;

            int j=2;
            for (int i = 0; i < E; i++) {
                addEdge(new Edge(input[j++].intValue(), input[j++].intValue(),input[j++]));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(Edge e){
        int v=e.either();
        int w=e.other(v);

        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    private void addEdge(int v,int w,double weight){
        Edge e=new Edge(v,w,weight);
        addEdge(e);
    }

    public myBag<Edge> edge(int v){
        return adj[v];
    }

    public myBag<Edge> edges(){
        myBag<Edge> results=new myBag<Edge>();

        //only add one edge once
        for(int v=0;v<this.V;v++){
            for(Edge e:adj[v]){
                if (v>e.other(v)) {
                    results.add(e);
                }
            }
        }

        return results;
    }

    public String toString(){
        String s="";

        s+=V+" vertices "+E+" edges\n";

        for (int v = 0; v < V; v++) {
            s+=v+" : ";
            for (Edge e : adj[v]) {
                int w=e.other(v);
                double weight=e.weight();
                s+=w+"--"+weight+" ";
            }
            s+="\n";
        }

        return s;
    }

    //test
    public static void main(String[] args){
        WeightedGraph test=new WeightedGraph(System.getProperty("user.dir")+"/"+args[0]);

        System.out.println(test.toString());

        /* for (int v=0;v<test.V();v++) { */
        /*     System.out.println(Graph.degree(test, v)); */
        /* } */
        /*  */
        /* System.out.println(Graph.maxDegree(test)+" "+Graph.avgDegree(test)+" "+Graph.numberOfSelfLoops(test)); */
    }
}
