package algorithm.problems.Sort;

import java.io.IOException;

import algorithm.tools.inputKit;

/**
 * Insertion
 */
public class Insertion {

    //sort api
    public static <T extends Comparable<T>> void sort(T[] array){
        for (int i = 1; i < array.length; i++) {
            for(int j=i;j>0&&less(array[j], array[j-1]);j--){
                exchange(array, j, j-1);
            }
        }
    }

    public static <T extends Comparable<T>> void sort(T[] array,int low,int height){
        for (int i = low; i <=height; i++) {
            for (int j = i; j >0&&less(array[j], array[j-1]); j--) {
                exchange(array, j, j-1);
            }
        }
    }

    public static <T extends Comparable<T>> boolean less(T v,T w){
        return v.compareTo(w)<0;
    }

    private static <T> void exchange(T[] array,int i,int j){
        T temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }

    private static <T> void show(T[] array){
        for (int i = 0; i < array.length; i++) {
           System.out.print(array[i]+" "); 
        }
        System.out.println();
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
        Integer[] test=inputKit.getInts(System.getProperty("user.dir")+"/"+args[1]);
        /* Insertion.show(test); */
        /* System.out.println(); */
        System.out.println(Insertion.isSorted(test));
        SortCompare.time(args[0],test);
        System.out.println(Insertion.isSorted(test));
        /* Insertion.show(test); */
    }
}
