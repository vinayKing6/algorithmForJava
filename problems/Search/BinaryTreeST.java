package algorithm.problems.Search;

import java.io.IOException;

import algorithm.algorithm_data_type.myQueue;
import algorithm.tools.inputKit;
import network.test.charpter4.networkInterface;

/**
 * BinaryTreeST
 */
public class BinaryTreeST<Key extends Comparable<Key>,Value> {

    private Node root;

    private class Node{
        public Key key;
        public Value value;
        public Node left,right;
        public int size;

        public Node(Key key,Value value,int size){
            this.key=key;
            this.value=value;
            this.size=size;
        }
    }

    public int size(){
        return size(root);
    }

    private int size(Node x){
        if (x==null) {
            return 0;
        }
        return x.size;
    }

    public Value get(Key key){
        return get(root,key);
    }

    private Value get(Node x,Key key){
        if (x==null) {
            return null;
        }
        int compare=x.key.compareTo(key);
        if (compare<0) {
            return get(x.right,key);
        }else if (compare>0) {
            return get(x.left,key);
        }else
            return x.value;
    }

    public void put(Key key,Value value){
        assert check():"error";
        root=put(root, key,value);
    }

    private Node put(Node x,Key key,Value value){
        if (x==null) {
            return new Node(key,value,1);
        }
        int compare=x.key.compareTo(key);
        if (compare<0) {
            x.right=put(x.right, key, value);
        }else if (compare>0) {
            x.left=put(x.left, key, value);
        }else
            x.value=value;
        x.size=size(x.left)+size(x.right)+1;
        return x;
    }

    public Key min(){
        return min(root).key;
    }

    private Node min(Node x){
        if (x.left==null) {
            return x;
        }
        return min(x.left);
    }

    public Key max(){
        return max(root).key;
    }

    private Node max(Node x){
        if (x.right==null) {
            return x;
        }
        return max(x.right);
    }

    public Key floor(Key key){
        Node t=floor(root, key);
        if(t==null)
            return null;
        return t.key;
    }

    private Node floor(Node x,Key key){
        if (x==null) {
            return null;
        }
        int compare=x.key.compareTo(key);
        if (compare==0) {
            return x;
        }else if (compare>0) {
            return floor(x.left,key);
        }
        Node t=floor(x.right,key);
        if (t!=null) {
            return t;
        }else
            return x;
    }

    public Key ceiling(Key key){
        Node t=ceiling(root, key);
        if(t==null)
            return null;
        else
            return t.key;
    }

    private Node ceiling(Node x,Key key){
        if (x==null) {
            return null;
        }
        int compare=x.key.compareTo(key);
        if (compare==0) {
            return x;
        }else if (compare<0) {
            return ceiling(x.right, key);
        }
        Node t=ceiling(x.left, key);
        if(t!=null)
            return t;
        else
            return x;
    }

    public Key select(int i){
        return select(root, i).key;
    }

    private Node select(Node x,int i){
        if (x==null) {
            return null;
        }
        if (size(x.left)==i) {
            return x;
        }else if (size(x.left)<i) {
            return select(x.right, i-size(x.left)-1);
        }else
            return select(x.left, i);
    }

    public int rank(Key key){
        return rank(root, key);
    }

    private int rank(Node x,Key key){
        if (x==null) {
            return 0;
        }
        int compare=x.key.compareTo(key);
        if (compare==0) {
            return size(x.left);
        }else if (compare<0) {
            return size(x.left)+rank(x.right,key)+1;
        }else
            return rank(x.left, key);
    }

    public void deleteMin(){
        assert check():"error";
        root=deleteMin(root);
    }

    private Node deleteMin(Node x){
        if (x.left==null) {
            return x.right;
        }
        x.left=deleteMin(x.left);
        x.size=size(x.left)+size(x.right)+1;
        return x;
    }

    public void deleteMax(){
        assert check():"error";
        root=deleteMax(root);
    }

    private Node deleteMax(Node x){
        if (x.right==null) {
            return x.left;
        }
        x.right=deleteMax(x.right);
        x.size=size(x.left)+size(x.right)+1;
        return x;
    }

    public void delete(Key key){
        assert check():"error";
        root=delete(root, key);
    }

    private Node delete(Node x,Key key){
        if (x==null) {
            return null;
        }
        int compare=x.key.compareTo(key);
        if (compare<0) {
            x.right=delete(x.right,key);
        }else if (compare>0) {
            x.left=delete(x.left, key);
        }else{
            if (x.left==null) {
                return x.right;
            }else if(x.right==null){
                return x.left;
            }
            Node t=x;
            x=min(t.right);
            x.left=t.left;
            x=deleteMin(x);
            x.size=size(x.left)+size(x.right)+1;
            return x;
        }
        x.size=size(x.left)+size(x.right)+1;
        return x;
    }

    public boolean check(){
        return isSorted(root)&&isRank();
    }

    private boolean isSorted(Node x){
        if (x==null) {
            return true;
        }
        if (x.left!=null&&x.right!=null) {
            if (x.left.key.compareTo(x.key)<0&&x.right.key.compareTo(x.key)>0) {
                return isSorted(x.left)&&isSorted(x.right);
            }else
                return false;
        }else if (x.left!=null) {
            if (x.left.key.compareTo(x.key)<0) {
                return isSorted(x.left);
            }else 
                return false;
        }else if(x.right!=null){
            if (x.right.key.compareTo(x.key)>0) {
                return isSorted(x.right);
            }else
                return false;
        }else
            return true;
    }

    private boolean isRank(){
        for(int i=0;i<size();i++){
            if (i!=rank(select(i))) {
               return false; 
            }
        }
        return true;
    }

    public Iterable<Key> keys(){
        myQueue<Key> queue=new myQueue<Key>();
        keys(root, queue, min(), max());
        return queue;
    }

    private void keys(Node x,myQueue<Key> queue,Key low,Key height){
        if (x==null) {
            return;
        }
        int cmplo=low.compareTo(x.key);
        int cmphi=height.compareTo(x.key);
        if(cmplo<0) keys(x.left,queue,low,height);
        if(cmplo<=0&&cmphi>=0) queue.enqueue(x.key);
        if(cmphi>0) keys(x.right, queue, low, height);
    }
    //test
    public static void main(String[] args)throws IOException{
        String[] strings=inputKit.getStrings(System.getProperty("user.dir")+"/"+args[0]);
        BinaryTreeST<String,Integer> st=new BinaryTreeST<String,Integer>();
        for (String string : strings) {
            if (st.get(string)==null) {
                st.put(string, 1);
            }else
                st.put(string, st.get(string)+1);
        }
        st.deleteMin();
        System.out.println(st.size()+" "+st.min()+" "+st.get(st.min()));
        System.out.println(st.check());
        for (var key : st.keys()) {
            System.out.println(key+" "+st.get(key));
        }
    }
}
