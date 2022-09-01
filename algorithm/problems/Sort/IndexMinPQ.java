package algorithm.problems.Sort;

import java.io.IOException;

/**
 * IndexMinPQ
 */
public class IndexMinPQ<T extends Comparable<T>> {

    //priority queue for time-complexity(ONLogN)
    private T[] pq;
    private int[] index;
    private int N;

    public IndexMinPQ(int maxN){
        //index 0 is not used which means array starts from 1
        pq=(T[]) new Comparable[maxN+1];
        index=new int[maxN+1];
        N=0;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    public void insert(int i,T value){
        if (pq[i]!=null) {
            change(i, value);
            return;
        }

        pq[i]=value;
        index[++N]=i;
        swim(N);
    }

    public void change(int i,T value){
        if (pq[i]==null) {
            insert(i, value);
            return;
        }

        pq[i]=value;
        for(int j=1;j<=N;j++){
            if (index[j]==i) {
                sink(j);
                swim(j);
                break;
            }
        }
    }

    public int delMin(){
        int temp=index[1];
        exchange(1, N--);
        pq[temp]=null;
        sink(1);

        return temp;
    }

    public int getMin(){
        return index[1];
    }

    public T getValue(int index){
        return pq[index];
    }

    private void swim(int k){
        while (k/2>=1 && larger(k/2, k)) {
            exchange(k/2, k);
            k=k/2;
        }
    }

    private void sink(int k){
        while (k*2<=N) {
            int j=2*k;
            if (j<N&&larger(j, j+1)) {
                j++;
            }
            if (!larger(k, j)) {
                break;
            }
            exchange(k, j);
            k=j;
        }
    }

    private boolean larger(int i,int j){
        return pq[index[i]].compareTo(pq[index[j]])>0;
    }

    private void exchange(int i,int j){
        int temp=index[i];
        index[i]=index[j];
        index[j]=temp;
    }

    //test
    public static void main(String[] args)throws IOException{
        // Integer[] test=inputKit.getInts(System.getProperty("user.dir")+"/"+args[0]);
        Integer[] test={2,5,1,0,0,6,11,666,7,10};
        IndexMinPQ<Integer> pq=new IndexMinPQ<Integer>(test.length);
        for (int i = 0; i < test.length; i++) {
           pq.insert(i,test[i]); 
        }

        pq.change(9, 10000);
        pq.change(7, -1);

        while (!pq.isEmpty()) {
            System.out.println(pq.getValue(pq.getMin())+" "+pq.delMin());
        }
    }

}
