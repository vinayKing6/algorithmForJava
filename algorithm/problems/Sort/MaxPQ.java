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

    public MaxPQ(){
        pq=(T[]) new Comparable[2];
        N=0;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    private void resize(int max){
        T[] temp=(T []) new Comparable[max];

        for (int i = 1; i <= N; i++) {
            temp[i]=pq[i];
        }

        pq=temp;
    }

    public void insert(T value){
        if (N==pq.length-1) {
            resize(pq.length*2);
        }

        pq[++N]=value;
        swim(N);
    }

    public T delMax(){
        T temp=pq[1];
        exchange(1, N--);
        pq[N+1]=null;
        sink(1);

        if (N>1&&N==pq.length/4) {
            resize(pq.length/2);
        }
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
        /* Integer[] test={2,5,1,0,0,6,11,666,7,10}; */
        MaxPQ<Integer> pq=new MaxPQ<Integer>();
        for (int i = 0; i < test.length; i++) {
           pq.insert(test[i]); 
        }

        while (!pq.isEmpty()) {
            System.out.println(pq.delMax());
        }
    }

}
