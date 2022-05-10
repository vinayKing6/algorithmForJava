package algorithm.problems.Graphs;

import algorithm.algorithm_data_type.myStack;

/**
 * DepthFirstPaths
 */
public class DepthFirstPaths {

    private boolean[] marked;
    private int[] edgeTo;
    private final int S;

    public DepthFirstPaths(Graph G,int S){
        this.S=S;
        marked=new boolean[G.V()];
        edgeTo=new int[G.V()];
        dfs(G, S);
    }

    private void dfs(Graph G,int v){
        marked[v]=true;

        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w]=v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public myStack<Integer> pathTo(int v){
        myStack<Integer> result=new myStack<Integer>();
        
        if(!marked[v])
            return null;

        for(int x=v;x!=this.S;x=edgeTo[x]){
            result.push(x);
        }
        result.push(this.S);

        return result;
    }

    public static void main(String[] args){
        try{
            Graph G=new Graph(args[0]);
            int S=Integer.parseInt(args[1]);
            DepthFirstPaths path=new DepthFirstPaths(G,S);
            for (int v = 0; v <G.V() ; v++) {
                if (path.hasPathTo(v)) {
                    System.out.print(S+" to "+v+": ");
                    for (int w : path.pathTo(v)) {
                        System.out.print(w+" ");
                    }
                    System.out.println();
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("usage java DepthFirstPaths filename parameter");
        }
    }
}
