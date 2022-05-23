package algorithm.problems.Graphs;

import algorithm.problems.Graphs.directGraph.DiCycle;
import algorithm.problems.Graphs.directGraph.SymbolDigraph;
import algorithm.problems.Graphs.undirectGraph.Graph;

/**
 * Topological
 */
public class Topological {

    private Iterable<Integer> order=null;

    public Topological(Graph G){
        DiCycle cycle=new DiCycle(G);
        if (!cycle.hasCycle()) {
            DepthFirstOrder dfs=new DepthFirstOrder(G);
            order=dfs.reversePost();
        }else
            System.out.println("cycle");
    }

    public boolean isDAG(){
        return order!=null;
    }

    public Iterable<Integer> order(){
        return order;
    }

    public static void main(String[] args){
        String filename=args[0];
        String splitor=args[1];
        SymbolDigraph sg=new SymbolDigraph(filename,splitor);

        Topological top=new Topological(sg.G());

        for(int v:top.order()){
            System.out.println(sg.name(v));
        }
    }
}
