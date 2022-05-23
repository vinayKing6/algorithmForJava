package algorithm.problems.Graphs;

import algorithm.algorithm_data_type.myQueue;
import algorithm.problems.Graphs.undirectGraph.Graph;

/**
 * BreadthFirstSearch
 */
public class BreadthFirstSearch {

    private boolean[] marked;
    private int count;

    public BreadthFirstSearch(Graph G,int S){
        marked=new boolean[G.V()];
        count=0;
        bfs(G, S);
    }

    private void bfs(Graph G,int S){
        myQueue<Integer> queue=new myQueue<Integer>();
        queue.enqueue(S);
        marked[S]=true;
        count++;

        while (!queue.isEmpty()) {
            int v=queue.dequeue();
            for (int w:G.adj(v) ) {
                if (!marked[w]) {
                    count++;
                    marked[w]=true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public int count(){
        return count;
    }

    public boolean isConnected(){
        return count==marked.length;
    }

    public boolean marked(int v){
        return marked[v];
    }

    public myQueue<Integer> connected(){
        myQueue<Integer> result=new myQueue<Integer>();

        for (int v = 0; v < marked.length; v++) {
            if (marked(v)) {
                result.enqueue(v);
            }
        }

        return result;
    }

    public static void main(String[] args){
        try{
            Graph G=new Graph(System.getProperty("user.dir")+"/"+args[0]);
            int S=Integer.parseInt(args[1]);
            BreadthFirstSearch search=new BreadthFirstSearch(G,S);
            myQueue<Integer> connected=search.connected();
            System.out.println(search.count());
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
            System.out.println("usage: java BreadthFirstSearch filename parameter");
        }
    }
}
