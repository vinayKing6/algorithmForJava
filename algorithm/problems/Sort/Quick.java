package algorithm.problems.Sort;

import java.io.IOException;

import algorithm.tools.ShuffleArray;
import algorithm.tools.inputKit;

/**
 * Quick
 */
public class Quick {

    private static <T extends Comparable<T>> int partion(T[] array,int low,int height){
        T key=array[low];
        int i=low;
        int j=height+1;

        while (true) {
            while (less(array[++i], key)) {
                if (i>=height) {
                    break
                        ;
                }
            }

            while (less(key, array[--j])) {
                if (j<=low) {
                    break;
                }
            }

            if (i>=j) {
                break;
            }
            exchange(array, i, j);
        }
        exchange(array,low,j);
        return j;
    }

    private static <T extends Comparable<T>> void sort(T[] array,int low,int height){
        if (low>=height-10) {
            Insertion.sort(array,low,height);
            return;
        }

        int j=partion(array, low, height);
        sort(array, low, j-1);
        sort(array,j+1,height);
    }

    public static <T extends Comparable<T>> void sort(T[] array){
        ShuffleArray.shuffle(array);
        sort(array,0,array.length-1);
    }

    private static <T extends Comparable<T>> boolean less(T v,T w){
        return v.compareTo(w)<0;
    }

    private static <T> void exchange(T[] array,int i ,int j){
        T temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }

    public static <T extends Comparable<T>> void three_partion_sort(T[] array,int low,int height){
        if (low>=height) {
            return;
        }
        int left=low;
        int i=low+1;
        int right=height;
        T key=array[low];

        while (i<=right) {
            int compare=key.compareTo(array[i]);

            if (compare<0) {
                exchange(array, i, right--);
            }else if (compare>0) {
                exchange(array, i++, left++);
            }else
                i++;
        }
        three_partion_sort(array, low, left-1);
        three_partion_sort(array, right+1, height);
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] array){
        for (int i = 1; i < array.length; i++) {
           if (array[i].compareTo(array[i-1])<0) {
               return false;
           } 
        }
        return true;
    }

    private static <T> void show(T[] array){
        for (int i = 0; i < array.length; i++) {
           System.out.print(array[i]+" "); 
        }
        System.out.println();
    }

    //test
    public static void main(String[] args)throws IOException{
        Integer[] test=inputKit.getInts(System.getProperty("user.dir")+"/"+args[0]);
        System.out.println(Quick.isSorted(test));
        SortCompare.time("three_partion_sort", test);
        System.out.println(Quick.isSorted(test));
    }
}
