package algorithm.problems.Search;

import java.io.IOException;

import algorithm.algorithm_data_type.myQueue;
import algorithm.tools.inputKit;

/**
 * ChainingHashST
 */
public class ChainingHashST<Key extends Comparable<Key>,Value> {

    private int size;
    private int capacity;
    private BinarySearchST<Key,Value>[] binarySearchST;
    private myQueue<Key> keys;

    public ChainingHashST(){
        this(997);//the capacity should be prime
    }

    public ChainingHashST(int capacity){
        size=0;
        this.capacity=capacity;
        binarySearchST=(BinarySearchST<Key,Value>[])new BinarySearchST[capacity];
        for (int i = 0; i < binarySearchST.length; i++) {
           binarySearchST[i]=new BinarySearchST<Key,Value>(2); 
        }
    }

    public int hash(Key key){
        return (key.hashCode()&0x7fffffff)%capacity;
    }

    public void put(Key key,Value value){
        binarySearchST[hash(key)].put(key, value);
    }

    public Value get(Key key){
        return binarySearchST[hash(key)].get(key);
    }

    public void delete(Key key){
        binarySearchST[hash(key)].delete(key);
    }

    public int size(){
        for (int i = 0; i < binarySearchST.length; i++) {
            size+=binarySearchST[i].size();
        }
        return size;
    }

    public boolean contains(Key key){
        return binarySearchST[hash(key)].contains(key);
    }

    public static void main(String[] args)throws IOException{
        String[] words=inputKit.getStrings(System.getProperty("user.dir")+"/"+args[0]);
        ChainingHashST<String,Integer> map=new ChainingHashST();
        for (String string : words) {
           if (map.get(string)==null) {
               map.put(string, 1);
           }else
               map.put(string, map.get(string)+1);
        }
        System.out.println(map.size());
        System.out.println(map.get("a"));
    }
}
