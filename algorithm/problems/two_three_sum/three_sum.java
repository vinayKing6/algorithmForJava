package algorithm.problems.two_three_sum;

import java.io.*;
import java.util.*;
import algorithm.problems.Search.*;
/**
 * three_sum
 */
public class three_sum {

     public static void main(String[] args) throws IOException{
        Scanner sc=new Scanner(new File(args[0]));
        Integer[] array=new Integer[1000000];
        int i=0;
        int num=0;
        while (sc.hasNextInt()) {
            array[i++]=sc.nextInt();
        }
        
        Arrays.sort(array);

         for (int j = 0,temp=-1; j < array.length; j++) {
             for (int k = j+1; k < array.length; k++) {
                 if ((temp=BinarySearch.rank(-(array[j]+array[k]), array))!=-1) {
                     num++;
                     System.out.println("("+array[j]+","+array[k]+","+array[temp]+")");
                }    
            }
        }
        System.out.println(num);
    }   
}
