package algorithm.problems.Graphs;

import algorithm.algorithm_data_type.myQueue;
import algorithm.problems.Graphs.directGraph.Digraph;
import algorithm.problems.Graphs.directGraph.*;
import algorithm.problems.Graphs.undirectGraph.*;

/**
 * LinkMap
 */
public class LinkMap {

    private myQueue<String> prefix;
    private Digraph G;
    private int S;
    private String symbol;
    private SymbolDigraph sg;
    private boolean[] marked;

    public LinkMap(String filename,String splitor,String symbol){
        prefix=new myQueue<String>();
        this.symbol=symbol;
        sg=new SymbolDigraph(filename,splitor);
        this.G=sg.G();
        marked=new boolean[G.V()];
    }

    public void printLink(){
        /* dfsVertical(G, v); */
        for(int v=0;v<G.V();v++){
            if (!marked[v]) {
                dfsHorizon(G, v, -1);
            }
        }
    }

    public void printLink(String name){
        int v=sg.index(name);
        dfsHorizon(G, v, -1);
    }

    private void dfsVertical(Graph G,int v){
        prefix.enqueue(symbol);
        print(sg.name(v));
        marked[v]=true;

        for(int w:G.adj(v)){
            dfsVertical(G, w);
        }

        prefix.dequeue();
    }

    private void dfsHorizon(Graph G,int v,int position){
        prefix.enqueue(symbol);
        marked[v]=true;

        int mid=G.adj(v).size()/2;
        int i=0;

        if (G.adj(v).size()==0) {
            print(sg.name(v),position);
        }

        for(int w:G.adj(v)){
            if (i<mid) {
                dfsHorizon(G, w,0);
                i++;
            }else if (i==mid) {
                print(sg.name(v), position);
                dfsHorizon(G, w, 1);
                i++;
            }else{
                dfsHorizon(G, w, 1);
                i++;
            }
        }

        print("",-1);

        prefix.dequeue();
    }

    private void print(String name,int position){
        //position is not used yet
        String line="";
        int i=0;

        for(String sym:prefix){
            line=line+sym+"|";
        }

        if(position==0){
            /* System.out.println(line); */
            System.out.println(line+"|---"+name+"--");
            /* System.out.println(line+prefix.peek()+"|"); */
        }else if (position==1) {
            /* System.out.println(line+prefix.peek()+"|"); */
            System.out.println(line+"|---"+name+"--");
            /* System.out.println(line); */
        }else
            System.out.println(line+"|---"+name+"--");


    }

    private void print(String name){
        String line="";

        for(String sym:prefix){
            line+=sym;
        }

        line+=name;

        System.out.println(line);
        System.out.println();
    }
    
    public static void main(String[] args){
        String filename=args[0];
        String splitor=args[1];
        /* String name=args[2]; */
        String symbol=args[2];
        LinkMap lm=new LinkMap(filename,splitor,symbol);
        lm.printLink();
    }

}
