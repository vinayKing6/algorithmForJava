package algorithm.problems.Sort;

import java.io.IOException;

import algorithm.tools.inputKit;

/**
 * Merge
 */
public class Merge {

    private static Comparable[] temp;

    private static void merge(Comparable[] array,int low,int mid,int high){
        int i=low;
        int j=mid+1;

        for(int k=low;k<=high;k++){
            temp[k]=array[k];
        }

        for(int k=low;k<=high;k++){
            if (i>mid) {
                array[k]=temp[j++];
            }else if (j>high) {
                array[k]=temp[i++];
            }else if (less(temp[i],temp[j])) {
                array[k]=temp[i++];
            }else{
                array[k]=temp[j++];
            }
        }
    }

    private static void sort(Comparable[] array,int low,int high){
        if (low>=high) {
            return;
        }

        int mid=low+(high-low)/2;
        sort(array, low, mid);
        sort(array, mid+1, high);
        merge(array, low, mid, high);
    }

    public static void sort(Comparable[] array){
        temp=new Comparable[array.length];
        sort(array,0,array.length-1);
    }

    private static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }

    public static boolean isSorted(Comparable[] array){
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(array[i-1])<0) {
                return false;
            }
        }
        return true;
    }

    public static void show(Comparable[] array){
        System.out.println(array.length);
        for (int i = 0; i < array.length; i++) {
           System.out.print(array[i]+" "); 
        }
    }

    public static void main(String[] args)throws IOException{
        Integer[] test=inputKit.getInts(System.getProperty("user.dir")+"/"+args[0]);
        /* Integer[] test={1,5,3,0}; */
        /* Merge.show(test); */
        System.out.println(Merge.isSorted(test));
        SortCompare.time("Merge",test);
        System.out.println(Merge.isSorted(test));
        /* Merge.show(test); */
    }
}
