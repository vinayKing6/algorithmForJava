package algorithm.problems.Search;

import java.io.IOException;

import algorithm.tools.inputKit;

/**
 * LinearHashST
 */
public class LinearHashST<Key extends Comparable<Key>,Value> {

    private int capacity;
    private Key[] keys;
    private Value[] values;
    private int size;

    public LinearHashST(){
        this.capacity=16;
        this.size=0;
        keys=(Key[])new Comparable[capacity];
        values=(Value[])new Object[capacity];
    }

    public LinearHashST(int capacity){
        this.capacity=capacity;
        this.size=0;
        keys=(Key[])new Comparable[capacity];
        values=(Value[])new Object[capacity];
    }

    private int hash(Key key){
        return (key.hashCode()&0x7fffffff)%capacity;
    }

    private void resize(int newCapacity){
        LinearHashST<Key,Value> temp=new LinearHashST<Key,Value>(newCapacity);
        for (int i = 0; i < capacity; i++) {
            if (keys[i]!=null) {
                temp.put(keys[i], values[i]);
            }
        }
        keys=temp.keys;
        values=temp.values;
        capacity=temp.capacity;
    }

    public void put(Key key,Value value){
        if(size>=capacity/2) resize(capacity*2);
        int i;
        for(i=hash(key);keys[i]!=null;i=(i+1)%capacity){
            if (keys[i].compareTo(key)==0) {
                values[i]=value;
                return;
            }
        }
        keys[i]=key;
        values[i]=value;
        size++;
    }

    public Value get(Key key){
        for(int i=hash(key);keys[i]!=null;i=(i+1)%capacity){
            if (keys[i].compareTo(key)==0) {
                return values[i];
            }
        }
        return null;
    }

    public void delete(Key key){
        int i;
        for(i=hash(key);keys[i]!=null;i=(i+1)%capacity){
            if (keys[i].compareTo(key)==0) {
                break;
            }
        }
        keys[i]=null;
        values[i]=null;
        for(i=(i+1)%capacity;keys[i]!=null;i=(i+1)%capacity){
            size--;
            put(keys[i], values[i]);//contains size++ in put
            keys[i]=null;
            values[i]=null;
        }
        size--;
        if (size>0&&size==capacity/8) {
            resize(capacity/2);
        }
    }

    public int size(){
        return size;
    }

    public boolean contains(Key key){
        for(int i=hash(key);keys[i]!=null;i=(i+1)%capacity){
            if(keys[i].compareTo(key)==0)
                return true;
        }
        return false;
    }

    public static void main(String[] args)throws IOException{
        String[] words=inputKit.getStrings(System.getProperty("user.dir")+"/"+args[0]);
        LinearHashST<String,Integer> map=new LinearHashST();
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
