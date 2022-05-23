package algorithm.problems.Graphs;

import algorithm.algorithm_data_type.myQueue;
import algorithm.algorithm_data_type.myStack;
import algorithm.problems.Graphs.undirectGraph.Graph;

/**
 * DepthFirstOrder
 */
public class DepthFirstOrder {

    private myQueue<Integer> pre;
    private myQueue<Integer> post;
    private myStack<Integer> reversePost;
    private boolean[] marked;

    public DepthFirstOrder(Graph G){
        pre=new myQueue<Integer>();
        post=new myQueue<Integer>();
        reversePost=new myStack<Integer>();
        marked=new boolean[G.V()];

        for(int v=0;v<G.V();v++){
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Graph G,int v){
        marked[v]=true;
        pre.enqueue(v);

        for(int w:G.adj(v)){
            if (!marked[w]) {
                dfs(G, w);
            }
        }

        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public Iterable<Integer> reversePost(){
        return reversePost;
    }
}
