package algorithm.problems.Graphs;

import algorithm.problems.Graphs.directGraph.Digraph;

/**
 * TransitiveClosure
 * a Closure graph: for example ,in the original graph where v ---> w (not absolutely in the edge),
 * which means w can be reached from v, in that case, in the Closure graph there is an edge link w and v ,which means w.add(v)
 */
public class TransitiveClosure {

    private DepthFirstSearch[] dfsAll;

    public TransitiveClosure(Digraph G){
        dfsAll=new DepthFirstSearch[G.V()];

        for(int v=0;v<G.V();v++){
            dfsAll[v]=new DepthFirstSearch(G,v);
        }
    }

    public boolean reachable(int v,int w){
        return dfsAll[v].marked(w);
    }


}
