package algorithm.problems.Graphs;

import java.io.IOException;

import algorithm.algorithm_data_type.myBag;
import algorithm.tools.inputKit;

/**
 * Graph
 */
public class Graph {

    private int V;
    int E;
    myBag<Integer>[] adj;

    public Graph(int V){
        this.V=V;
        adj=(myBag<Integer>[])new myBag[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i]=new myBag<Integer>();
        }
    }

    public Graph(String path){
        try{
            Integer[] input=inputKit.getInts(path);
            this.V=input[0];
            adj=(myBag<Integer>[])new myBag[V];
            for (int i = 0; i < adj.length; i++) {
                adj[i]=new myBag<Integer>();
            }
            int E=input[1];
            this.E=0;

            int j=2;
            for (int i = 0; i < E; i++) {
                addEdge(input[j++], input[j++]);
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

    public void addEdge(int v,int w){
        
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public myBag<Integer> adj(int v){
        return adj[v];
    }

    public String toString(){
        String s="";

        s+=V+" vertices "+E+" edges\n";

        for (int v = 0; v < V; v++) {
            s+=v+" : ";
            for (int w : adj[v]) {
                s+=w+" ";
            }
            s+="\n";
        }

        return s;
    }

    public static int degree(Graph G,int v){
        return G.adj(v).size();
    }

    public static int maxDegree(Graph G){
        int max=0;

        for(int v=0;v<G.V();v++){
            int count=degree(G, v);
            max=max>count?max:count;
        }

        return max;
    }

    public static double avgDegree(Graph G){
        return G.E()/G.V();
    }

    public static int numberOfSelfLoops(Graph G){
        int count=0;

        for(int v=0;v<G.V();v++){
            for(int w:G.adj(v)){
                if (v==w) {
                    count++;
                }
            }
        }

        return count/2;
    }

    public static void main(String[] args){
        Graph test=new Graph(System.getProperty("user.dir")+"/"+args[0]);

        System.out.println(test.toString());

        for (int v=0;v<test.V();v++) {
            System.out.println(Graph.degree(test, v));
        }

        System.out.println(Graph.maxDegree(test)+" "+Graph.avgDegree(test)+" "+Graph.numberOfSelfLoops(test));
    }
}
