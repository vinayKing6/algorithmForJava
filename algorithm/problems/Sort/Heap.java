package algorithm.problems.Sort;

import java.io.IOException;

import algorithm.tools.inputKit;

/**
 * Heap
 */
public class Heap {

    public static <T extends Comparable<T>> void sort(T[] array){
        int N=array.length-1;

        for (int k=(N-1)/2;k>=0;k--) {
            sink(array, k, N);
        }
        while (N>0) {
            exchange(array, 0, N--);
            sink(array, 0, N);
        }
    }

    private static <T extends Comparable<T>> void sink(T[] array,int k,int N){
        while (k*2+1<=N) {
            int j=k*2+1;
            if (j<N&&less(array[j], array[j+1])) {
                j++;
            }
            if (!less(array[k], array[j])) {
                break;
            }
            exchange(array, k, j);
            k=j;
        }
    }

    private static <T extends Comparable<T>> boolean less(T v, T w){
        return v.compareTo(w)<0;
    }

    private static <T> void exchange(T[] array,int i,int j){
        T temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] array){
        for (int i = 1; i < array.length; i++) {
            if (less(array[i], array[i-1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args)throws IOException{
        Integer[] array=inputKit.getInts(System.getProperty("user.dir")+"/"+args[0]);
        System.out.println(Heap.isSorted(array));
        SortCompare.time("Heap", array);
        System.out.println(Heap.isSorted(array));
    }
}
