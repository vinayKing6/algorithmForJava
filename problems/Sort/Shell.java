package algorithm.problems.Sort;

import java.io.IOException;

import algorithm.tools.inputKit;

/**
 * Shell
 */
public class Shell {

    public static <T> void exchange(T[] array,int v,int w){
        T temp=array[v];
        array[v]=array[w];
        array[w]=temp;
    }

    public static <T extends Comparable<T>> boolean less(T v, T w){
        return v.compareTo(w)<0;
    }

    public static <T extends Comparable<T>> void sort(T[] array){
        int N=array.length;
        //step equals 4
        int h=1;

        while(h<(N/3))
            h=h*3+1;
        while (h>=1) {
            for (int i = h; i < N; i++) {
                for (int j = i;j>=h && less(array[j], array[j-h]) ; j=j-h) {
                    exchange(array, j,j-h);
                }
            }
            h/=3;
        }
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] array){
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(array[i-1])<0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException{
        Integer[] test=inputKit.getInts(System.getProperty("user.dir")+"/"+args[0]);
        System.out.println(Shell.isSorted(test));
        SortCompare.time("Shell", test);
        /* Merge.show(test); */
        System.out.println(Shell.isSorted(test));
    }
}
