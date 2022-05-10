package algorithm.problems.Search;

import java.util.function.Function;

/**
 * BinarySearch
 */
public class BinarySearch {

    //the input array must be sequential
    public static <T> int rank(Comparable<T> key,T[] array){
        int left=0;
        int right=array.length-1;

        while (left<=right) {
            int mid=left+(right-left)/2;
            if (key.compareTo(array[mid])<0) {
                right=mid-1;
            }else if (key.compareTo(array[mid])>0) {
                left=mid+1;
            }else
                return mid;
        }

        return -1;
    }
}
