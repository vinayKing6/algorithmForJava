package algorithm.problems.Graphs;

import java.io.IOException;

import algorithm.algorithm_data_type.myBag;
import algorithm.algorithm_data_type.myQueue;

/**
 * DepthFirstSearch
 */
public class DepthFirstSearch {

    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G,int S){
        marked=new boolean[G.V()];
        this.count=0;
        dfs(G, S);
    }

    public boolean marked(int w){
        return marked[w];
    }

    private void dfs(Graph G,int w){
        marked[w]=true;
        count++;
        for(int v:G.adj(w)){
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    public int count(){
        return count;
    }

    public boolean isConnected(){
        return count==marked.length;
    }

    public myQueue<Integer> connected(){
        myQueue<Integer> result=new myQueue<Integer>();

        for (int i = 0; i < marked.length; i++) {
            if (marked[i]) {
                result.enqueue(i);
            }
        }

        return result;
    }

    //test
    public static void main(String[] args){
        try{
            Graph G=new Graph(System.getProperty("user.dir")+"/"+args[0]);
            int S=Integer.parseInt(args[1]);
            DepthFirstSearch search=new DepthFirstSearch(G,S);
            myQueue<Integer> connected=search.connected();
            for (Integer integer : connected) {
                System.out.print(" "+integer);
            }
            System.out.println();
            if (search.isConnected()) {
                System.out.println("Connected!");
            }else
                System.out.println("Not Connected");
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("usage: java DepthFirstSearch filename parameter");
        }
    }
}
