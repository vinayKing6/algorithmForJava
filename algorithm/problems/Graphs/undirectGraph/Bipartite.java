package algorithm.problems.Graphs.undirectGraph;

/**
 * Bipartite
 */
public class Bipartite {

    private boolean[] marked;
    private boolean[] colors;
    private boolean isBipartite;

    public Bipartite(Graph G){
        marked=new boolean[G.V()];
        colors=new boolean[G.V()];
        //default color
        for (int i = 0; i < colors.length; i++) {
            colors[i]=true;
        }
        isBipartite=true;
        for(int S=0;S<G.V();S++){
            if (!marked[S]) {
                dfs(G,S);
            }
        }
    }

    private void dfs(Graph G,int v){
        marked[v]=true;
        for(int w:G.adj(v)){
            if (!marked[w]) {
                colors[w]=!colors[v];
                dfs(G,w);
            }else if (colors[w]==colors[v]) {
                isBipartite=false;
            }
        }
    }

    public boolean isBipartite(){
        return isBipartite;
    }

    public static void main(String[] args){
        try{
            Graph G=new Graph(args[0]);
            Bipartite bipartite=new Bipartite(G);
            System.out.println(bipartite.isBipartite());
            
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("usage java Bipartite filename parameter");
        }
    }
}
