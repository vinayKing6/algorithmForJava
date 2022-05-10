package algorithm.problems.Graphs;

import algorithm.algorithm_data_type.myBag;

/**
 * KosarajuSCC
 * Strongly connected for directive graph
 */
public class KosarajuSCC {

    private boolean[] marked;
    private int[] id;
    private myBag<Integer>[] components;
    private int count;

    public KosarajuSCC(Digraph G){
        marked=new boolean[G.V()];
        id=new int[G.V()];
        count=0;

        DepthFirstOrder order=new DepthFirstOrder(G.reverse());
        for(int S:order.reversePost()){
            if (!marked[S]) {
                dfs(G, S);
                count++;
            }
        }

        components=(myBag<Integer>[])new myBag[count];
        for(int i=0;i<count;i++){
            components[i]=new myBag<Integer>();
        }

        for(int v=0;v<G.V();v++){
            components[id[v]].add(v);
        }

    }

    private void dfs(Digraph G,int v){
        marked[v]=true;
        id[v]=count;

        for(int w:G.adj(v)){
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean stronglyConnected(int v,int w){
        return id[v]==id[w];
    }

    public int count(){
        return count;
    }

    public myBag<Integer>[] components(){
        return components;
    }

    public static void main(String[] args){
        try{
            Digraph G=new Digraph(args[0]);
            KosarajuSCC cc=new KosarajuSCC(G);
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
            System.out.println("usage java KosarajuSCC filename parameter");
        }
    }

}
