package algorithm.problems.two_three_sum;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

import algorithm.box.intenger;
import algorithm.problems.Search.BinarySearch;
import algorithm.tools.inputKit;

/**
 * two_sum
 */
public class two_sum {

    public static void main(String[] args) throws IOException{
        Integer[] array=inputKit.getInts(System.getProperty("user.dir")+"/"+args[0]);
        
        Arrays.sort(array);

        int num=0;
        for (int j = 0,temp=-1; j < array.length; j++) {
            if ((temp=BinarySearch.rank(-array[j], array))!=-1) {
                num++;
                System.out.println("("+array[j]+","+array[temp]+")");
            }
        }
        System.out.println(num);
    }
}
