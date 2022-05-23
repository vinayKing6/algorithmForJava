package algorithm.problems.Graphs.directGraph;
import java.io.IOException;

import algorithm.algorithm_data_type.myBag;
import algorithm.tools.inputKit;


/**
 * WeightedDigraph
 * an directive graph with weighted edges
 */
public class WeightedDigraph {
    private int V;
    private int E;
    private myBag<DirectedEdge>[] adj;

    public WeightedDigraph(int V){
        this.V=V;
        this.E=0;
        this.adj=(myBag<DirectedEdge>[])new myBag[V];

        for (int i = 0; i < adj.length; i++) {
            adj[i]=new myBag<DirectedEdge>();
        }
    }

    public WeightedDigraph(String path){
        try{
            Double[] input=inputKit.getDoubles(path);
            this.V=input[0].intValue();
            adj=(myBag<DirectedEdge>[])new myBag[V];
            for (int i = 0; i < adj.length; i++) {
                adj[i]=new myBag<DirectedEdge>();
            }
            int E=input[1].intValue();
            this.E=0;

            int j=2;
            for (int i = 0; i < E; i++) {
                addEdge(new DirectedEdge(input[j++].intValue(), input[j++].intValue(),input[j++]));
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

    public void addEdge(DirectedEdge e){
        int v=e.from();

        adj[v].add(e);
        E++;
    }

    private void addEdge(int v,int w,double weight){
        DirectedEdge e=new DirectedEdge(v,w,weight);
        addEdge(e);
    }

    public myBag<DirectedEdge> edge(int v){
        return adj[v];
    }

    public myBag<DirectedEdge> edges(){
        myBag<DirectedEdge> results=new myBag<DirectedEdge>();

        for(int v=0;v<this.V;v++){
            for(DirectedEdge e:adj[v]){
                results.add(e);
            }
        }

        return results;
    }

    public String toString(){
        String s="";

        s+=V+" vertices "+E+" edges\n";

        for (int v = 0; v < V; v++) {
            s+=v+" : ";
            for (DirectedEdge e : adj[v]) {
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
        WeightedDigraph test=new WeightedDigraph(System.getProperty("user.dir")+"/"+args[0]);

        System.out.println(test.toString());

        /* for (int v=0;v<test.V();v++) { */
        /*     System.out.println(Graph.degree(test, v)); */
        /* } */
        /*  */
        /* System.out.println(Graph.maxDegree(test)+" "+Graph.avgDegree(test)+" "+Graph.numberOfSelfLoops(test)); */
    }
}
