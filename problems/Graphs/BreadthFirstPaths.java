package algorithm.problems.Graphs;

import algorithm.algorithm_data_type.myQueue;
import algorithm.algorithm_data_type.myStack;

/**
 * BreadthFirstPaths
 */
public class BreadthFirstPaths {

    private boolean[] marked;
    private int[] edgeTo;
    private final int S;
    private myQueue<Integer> connected;

    public BreadthFirstPaths(Graph G,int S){
        this.S=S;
        marked=new boolean[G.V()];
        edgeTo=new int[G.V()];
        connected=new myQueue<Integer>();
        bfs(G, S);
    }

    private void bfs(Graph G,int S){
        myQueue<Integer> queue=new myQueue<Integer>();
        marked[S]=true;
        queue.enqueue(S);

        while (!queue.isEmpty()) {
            int v=queue.dequeue();
            for(int w:G.adj(v)){
                if (!marked[w]) {
                    connected.enqueue(w);
                    marked[w]=true;
                    edgeTo[w]=v;
                    queue.enqueue(w);
                }
            }
        }
    }

    public myQueue<Integer> connected(){
        return connected;
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public myStack<Integer> pathTo(int v){
        myStack<Integer> stack=new myStack<Integer>();

        if (!marked[v]) {
            return null;
        }

        for(int x=v;x!=S;x=edgeTo[x]){
            stack.push(x);
        }

        stack.push(S);
        return stack;
    }

    public static void main(String[] args){
        try{
            Graph G=new Graph(args[0]);
            int S=Integer.parseInt(args[1]);
            BreadthFirstPaths path=new BreadthFirstPaths(G,S);
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
            System.out.println("usage java BreadthFirstPaths filename parameter");
        }
    }

}
