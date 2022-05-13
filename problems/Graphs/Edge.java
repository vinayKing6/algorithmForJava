package algorithm.problems.Graphs;

/**
 * Edge
 * an undirective graph which include weighted edges
 * this is the container edge of weights
 */
public class Edge implements Comparable<Edge>{
    private int v;
    private int w;
    private double weight;

    public Edge(int v,int w,double weight){
        this.v=v;
        this.w=w;
        this.weight=weight;
    }

    public double weight(){
        return this.weight;
    }

    public int either(){
        //get one of the end of the edge
        return v;
    }

    public int other(int s){
        //get the ohter end of this edge
        if (s==this.v) {
            return w;
        }else
            return v;
    }

    public int compareTo(Edge that){
        if (this.weight>that.weight) {
            return 1;
        }else if (this.weight==that.weight) {
            return 0;
        }else
            return -1;
    }

    public String toString(){
        return String.format("%d-%d : %f", v,w,weight);
    }

    //test
    public static void main(String[] args){
        Edge edge=new Edge(1,2,0.4);
        System.out.println(edge.other(1)+" "+edge.toString());
    }

}
