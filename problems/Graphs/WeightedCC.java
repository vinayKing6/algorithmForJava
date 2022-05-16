package algorithm.problems.Graphs;

import algorithm.algorithm_data_type.myBag;

/**
 * WeightedCC
 * find all components of undirective graph but with weighted edges
 */
public class WeightedCC {

    private boolean[] marked;
    private int[] id;
    private myBag<Integer>[] components;
    private int count;

    public WeightedCC(WeightedGraph G){
        marked=new boolean[G.V()];
        id=new int[G.V()];
        count=0;
        for (int v = 0; v < marked.length; v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }

        components=(myBag<Integer>[])new myBag[count];

        for (int i = 0; i < count; i++) {
            components[i]=new myBag<Integer>();
        }

        for (int v = 0; v < marked.length; v++) {
            components[id[v]].add(v);
        }
    }

    private void dfs(WeightedGraph G,int v){
        marked[v]=true;
        id[v]=count;

        for(Edge e:G.edge(v)){
            int w=e.other(v);
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public int count(){
        return count;
    }

    public boolean connected(int v,int w){
        return id[v]==id[w];
    }

    public int id(int v){
        return id[v];
    }

    public myBag<Integer>[] components(){
        return components;
    }

    public static void main(String[] args){
        try{
            WeightedGraph G=new WeightedGraph(args[0]);
            WeightedCC cc=new WeightedCC(G);
            myBag<Integer>[] components=cc.components();
            System.out.println(cc.count()+" components");
            for (int i = 0; i < cc.count(); i++) {
                for (int v: components[i]) {
                    System.out.print(v+" ");
                }
                System.out.println();
            }

        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("usage java CC filename parameter");
        }
    }   
}
