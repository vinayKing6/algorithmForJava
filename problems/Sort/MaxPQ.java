package algorithm.problems.Sort;

import java.io.IOException;

import algorithm.tools.inputKit;

/**
 * MaxPQ
 */
public class MaxPQ<T extends Comparable<T>> {

    //priority queue for time-complexity(ONLogN)
    private T[] pq;
    private int N;

    public MaxPQ(int maxN){
        //index 0 is not used which means array starts from 1
        pq=(T[]) new Comparable[maxN+1];
        N=0;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    public void insert(T value){
        pq[++N]=value;
        swim(N);
    }

    public T delMax(){
        T temp=pq[1];
        exchange(1, N--);
        pq[N+1]=null;
        sink(1);
        return temp;
    }

    public T getMax(){
        return pq[1];
    }

    private void swim(int k){
        while (k/2>=1 && less(k/2, k)) {
            exchange(k/2, k);
            k=k/2;
        }
    }

    private void sink(int k){
        while (k*2<=N) {
            int j=2*k;
            if (j<N&&less(j, j+1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exchange(k, j);
            k=j;
        }
    }

    private boolean less(int i,int j){
        return pq[i].compareTo(pq[j])<0;
    }

    private void exchange(int i,int j){
        T temp=pq[i];
        pq[i]=pq[j];
        pq[j]=temp;
    }

    //test
    public static void main(String[] args)throws IOException{
        Integer[] test=inputKit.getInts(System.getProperty("user.dir")+"/"+args[0]);
        MaxPQ pq=new MaxPQ<Integer>(test.length);
        for (int i = 0; i < test.length; i++) {
           pq.insert(test[i]); 
        }
        System.out.println(pq.size()+" "+pq.getMax());
    }

}
