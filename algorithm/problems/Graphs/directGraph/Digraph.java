package algorithm.problems.Graphs.directGraph;

import algorithm.algorithm_data_type.myQueue;
import algorithm.problems.Graphs.BreadthFirstPaths;
import algorithm.problems.Graphs.DepthFirstPaths;
import algorithm.problems.Graphs.undirectGraph.Graph;

/**
 * Digraph
 * a directive graph
 * an edge v->w has direction which does not equal w->v
 */
public class Digraph extends Graph{

    public Digraph(String path){
        super(path);
    }

    public Digraph(int V){
        super(V);
    }


    @Override
    public void addEdge(int v,int w){
        super.adj[v].add(w);
        super.E++;
    }

    public Digraph reverse(){
        Digraph g=new Digraph(this.V());

        for (int v = 0; v <this.V(); v++) {
            for (int w:this.adj(v)) {
                g.addEdge(w, v);
            }
        }

        return g;
    }

    //test unit
    public static void main(String[] args){
        //test the correction of Digraph extended from Graph
        Digraph test=new Digraph(System.getProperty("user.dir")+"/"+args[0]);

        System.out.println(test.toString());

        for (int v=0;v<test.V();v++) {
            System.out.println(Graph.degree(test, v));
        }

        System.out.println(Graph.maxDegree(test)+" "+Graph.avgDegree(test)+" "+Graph.numberOfSelfLoops(test));

        int S=Integer.parseInt(args[1]);
        DepthFirstPaths path=new DepthFirstPaths(test,S);
        for (int v = 0; v <test.V() ; v++) {
            if (path.hasPathTo(v)) {
                System.out.print(S+" to "+v+": ");
                for (int w : path.pathTo(v)) {
                    System.out.print(w+" ");
                }
                System.out.println();
            }
        }
        System.out.println();
        BreadthFirstPaths Bpath=new BreadthFirstPaths(test,S);
        for (int v = 0; v <test.V() ; v++) {
            if (Bpath.hasPathTo(v)) {
                System.out.print(S+" to "+v+": ");
                for (int w : Bpath.pathTo(v)) {
                    System.out.print(w+" ");
                }
                System.out.println();
            }
        }

    }

}
