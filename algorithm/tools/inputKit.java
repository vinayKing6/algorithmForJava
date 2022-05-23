package algorithm.tools;
import java.io.*;
import java.util.Scanner;
import algorithm.box.*;
import algorithm.algorithm_data_type.myQueue;

/**
 * inputKit
 */
public class inputKit {

    public static Integer[] getInts(String filename) throws IOException{
        myQueue<Integer> queue=new myQueue<Integer>();
        try(Scanner sc=new Scanner(new File(filename))){
            while (sc.hasNextInt()) {
                queue.enqueue(sc.nextInt());
            }
        }
        Integer[] results=new Integer[queue.size()];
        int i=0;
        while (!queue.isEmpty()) {
            results[i++]=queue.dequeue();
        }
        return results;
    }

    public static Double[] getDoubles(String filename)throws IOException{
        myQueue<Double> queue=new myQueue<Double>();
        try(Scanner sc=new Scanner(new File(filename))){
            while (sc.hasNextDouble()) {
                queue.enqueue(sc.nextDouble());
            }
        }
        Double[] results=new Double[queue.size()];
        int i=0;
        while (!queue.isEmpty()) {
            results[i++]=queue.dequeue();
        }
        return results;
    }

    public static String[] getStrings(String filename) throws IOException{
        StringBuilder stringBuilder=new StringBuilder();
        try(Scanner sc=new Scanner(new File(filename))){
            while (sc.hasNext()) {
                stringBuilder.append(sc.next());
                stringBuilder.append(" ");
            }
        }
        String[] results=stringBuilder.toString().split(" ");
        return results;
    }

    public static String[] getLines(String filename) throws IOException{
        StringBuilder stringBuilder=new StringBuilder();
        try(Scanner sc=new Scanner(new File(filename))){
            while (sc.hasNextLine()) {
                stringBuilder.append(sc.nextLine());
                stringBuilder.append("\n");
            }
        }
        String[] results=stringBuilder.toString().split("\n");
        return results;
    }

    public static String[] getLines(String filename,String splitor) throws IOException{
        StringBuilder stringBuilder=new StringBuilder();
        try(Scanner sc=new Scanner(new File(filename))){
            while (sc.hasNextLine()) {
                stringBuilder.append(sc.nextLine());
                stringBuilder.append("\n");
            }
        }
        String[] results=stringBuilder.toString().split(splitor);
        return results;
    }

    public static void main(String[] args){
        try{
            Double[] test=getDoubles(args[0]);
            for (int i = 0; i < test.length; i++) {
                System.out.println(test[i].intValue());
                
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
