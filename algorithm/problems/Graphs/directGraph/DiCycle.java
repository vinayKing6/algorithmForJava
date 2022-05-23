package algorithm.problems.Graphs.directGraph;

import algorithm.algorithm_data_type.myBag;
import algorithm.algorithm_data_type.myQueue;
import algorithm.algorithm_data_type.myStack;
import algorithm.problems.Graphs.undirectGraph.Graph;

/**
 * DiCycle
 * detect the cycles for directive graphs
 */
public class DiCycle {

    private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onStack;
    private myBag<myStack<Integer>> cycles;

    public DiCycle(Graph G){
        marked=new boolean[G.V()];
        edgeTo=new int[G.V()];
        onStack=new boolean[G.V()];
        cycles=new myBag<myStack<Integer>>();

        for (int v=0;v<G.V();v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }

    }

    private void dfs(Graph G,int v){
        onStack[v]=true;
        marked[v]=true;

        for(int w:G.adj(v)){
            if (!marked[w]) {
                edgeTo[w]=v;
                dfs(G, w);
            }else if (onStack[w]) {
                myStack<Integer> cycle=new myStack<Integer>();

                for(int x=v;x!=w;x=edgeTo[x]){
                    cycle.push(x);
                }

                cycle.push(w);
                cycle.push(v);
                cycles.add(cycle);
            }
        }

        onStack[v]=false;
    }

    public boolean hasCycle(){
        return cycles.size()!=0;
    }

    public myQueue<Integer>[] cycles(){
        myQueue<Integer>[] cyclesQueue=(myQueue<Integer>[]) new myQueue[this.cycles.size()];

        int i=0;
        for(myStack<Integer> stack:this.cycles){
            cyclesQueue[i]=new myQueue<Integer>();
            for (int x : stack) {
                cyclesQueue[i].enqueue(x);
            }
            i++;
        }

        return cyclesQueue;
    }

    //test unit
    public static void main(String[] args){
        try{
            Graph G=new Digraph(args[0]);
            DiCycle dicycle=new DiCycle(G);
            System.out.println(dicycle.hasCycle()+" "+dicycle.cycles().length);

            for (myQueue<Integer> queue : dicycle.cycles()) {
                for (int v:queue ) {
                    System.out.print(v+" ");
                }
                System.out.println();
            }

        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("usage java DiCycle filename parameter");
        }
    }

}
