package algorithm.problems.Graphs;

/**
 * DirectedEdge
 * an Edge extended from Edge which declare two different ends 
 * used for directive weighted graph
 */
public class DirectedEdge extends Edge{

    public DirectedEdge(int v,int w,double weight){
        super(v, w, weight);
    }

    public int from(){
        return either();
    }

    public int to(){
        return other(either());
    }

    public String toString(){
        return String.format("%d->%d : %f ", from(),to(),weight());
    }
}
