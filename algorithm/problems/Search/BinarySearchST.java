package algorithm.problems.Search;

import java.io.IOException;

import algorithm.algorithm_data_type.myQueue;
import algorithm.tools.inputKit;

/**
 * BinarySearchST
 */
public class BinarySearchST<Key extends Comparable<Key>,Value> {

    private Key[] keys;
    private Value[] values;
    private int N=0;

    public BinarySearchST(int Capacity){
        keys=(Key[]) new Comparable[Capacity];
        values=(Value[]) new Object[Capacity];
    }

    public void resize(int max){
        Key[] newKeys=(Key[]) new Comparable[max];
        Value[] newValues=(Value[]) new Object[max];

        for (int i = 0; i < N; i++) {
            newKeys[i]=keys[i];
            newValues[i]=values[i];
        }

        keys=newKeys;
        values=newValues;
    }

    public void put(Key key,Value value){
        if (N==keys.length) {
            resize(2*keys.length);
        }

        if (value==null) {
            delete(key);
        }

        int i=rank(key);
        if (i<N&&keys[i].compareTo(key)==0) {
            values[i]=value;
            return;
        }

        for(int j=N;j>i;j--){
            keys[j]=keys[j-1];
            values[j]=values[j-1];
        }
        keys[i]=key;
        values[i]=value;
        N++;
        assert check();
    }

    public Value get(Key key){
        assert check();
        if(isEmpty())
            return null;
        int i=rank(key);
        if (i<N&&keys[i].compareTo(key)==0) {
            return values[i];
        }
        return null;
    }

    public void delete(Key key){
        assert check();
        if (isEmpty()) {
            return;
        }
        int i=rank(key);
        if (i<N&&keys[i].compareTo(key)==0) {
            for (int j = i; j <N-1; j++) {
                keys[j]=keys[j+1];
                values[j]=values[j+1];
            }
            N--;
            keys[N]=null;
            values[N]=null;
            if (N>0&&N==keys.length/4) {
                resize(keys.length/2);
            }
            return;
        }
        return;
    }

    public boolean contains(Key key){
        assert check();
        int i=rank(key);
        if (i<N&&keys[i].compareTo(key)==0) {
            return true;
        }
        return false;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    public Key min(){
        return keys[0];
    }

    public Key max(){
        return keys[N-1];
    }

    public Key floor(Key key){
        int i=rank(key, 0, N-1);
        if (i<N&&keys[i].compareTo(key)==0) {
            return keys[i];
        }else if (i==0) {
            return null;
        }else
            return keys[i-1];
    }

    public Key ceiling(Key key){
        int i=rank(key, 0, N-1);
        if (i==N) {
            return null;
        }else
            return keys[i];
    }

    public int rank(Key key,int low,int height){
        if (low>height) {
            return low;
        }

        int mid=height+(low-height)/2;
        int compare=keys[mid].compareTo(key);

        if (compare>0) {
            return rank(key, low, mid-1);
        }else if (compare<0) {
            return rank(key, mid+1, height);
        }else
            return mid;
    }

    public int rank(Key key){
        return rank(key, 0, size()-1);
    }

    public Key select(int k){
        return keys[k];
    }

    public void deleteMin(){
        delete(min());
    }

    public void deleteMax(){
        delete(max());
    }

    public Iterable<Key> keys(){
        myQueue<Key> queue=new myQueue<Key>();
        for (int i = 0; i < N; i++) {
            queue.enqueue(keys[i]);
        }
        return queue;
    }

    public boolean isSorted(){
        for (int i = 1; i < keys.length; i++) {
            if (keys[i].compareTo(keys[i-1])<0) {
                return false;
            }
        }
        return true;
    }

    public boolean isRank(){
        for (int i = 0; i < keys.length; i++) {
           if (i!=rank(keys[i])) {
               return false;
           } 
        }
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].compareTo(keys[rank(keys[i])])!=0) {
                return false;
            }
        }
        return true;
    }

    public boolean check(){
        return isSorted()&&isRank();
    }

    //test
    public static void main(String[] args)throws IOException{
        BinarySearchST<String,Integer> test=new BinarySearchST<String,Integer>(2);
        String[] strings=inputKit.getStrings(System.getProperty("user.dir")+"/"+args[0]);
        System.out.println(strings.length);
        for (int i = 0; i < strings.length; i++) {
            if (test.get(strings[i])==null) {
                test.put(strings[i], 1);
            }else
                test.put(strings[i], test.get(strings[i])+1);
        }
        System.out.println(test.max()+" "+test.get(test.max()));
        myQueue<String> queue=(myQueue)test.keys();
        for(var key:queue){
            System.out.println(key+" "+test.get(key));
        }
    }
}
