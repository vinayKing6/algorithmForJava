package algorithm.problems.Graphs.undirectGraph;

/**
 * Cycle
 */
public class Cycle {

    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G){
        marked=new boolean[G.V()];
        hasCycle=false;
        for(int S=0;S<G.V();S++){
            if (!marked[S]) {
                dfs(G, S, S);
            }
        }
    }

    private void dfs(Graph G,int v,int u){
        marked[v]=true;

        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w, v);
            }else if (w!=u) {
                hasCycle=true;
            }
        }
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args){
        try{
            Graph G=new Graph(args[0]);
            Cycle cycle=new Cycle(G);
            System.out.println(cycle.hasCycle());
            
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("usage java Cycle filename parameter");
        }
    }
}
