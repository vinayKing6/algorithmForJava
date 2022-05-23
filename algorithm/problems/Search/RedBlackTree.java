package algorithm.problems.Search;

import java.io.IOException;

import algorithm.algorithm_data_type.myQueue;
import algorithm.tools.inputKit;

/**
 * RedBlackTree
 * a self balanced search tree 
 * insert: always O(logN) ; search: always O(logN);
 */
public class RedBlackTree<Key extends Comparable<Key>,Value> {

   private static final boolean RED=true;
   private static final boolean BLACK=false;
   private Node root=null;

   private class Node{
       Key key;
       Value value;
       boolean color;
       Node left,right;
       int size;

       Node(Key key,Value value,int size,boolean color){
           this.key=key;
           this.value=value;
           this.color=color;
           this.size=size;
       }
   }

   private boolean isRed(Node x){
       if(x==null) return false;
       return x.color;
   }
   
   public int size(){
       return size(root);
   }
   
   public int size(Node x){
       if(x==null)
           return 0;
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

    public boolean contains(Key key){
        return get(key)!=null;
    }

    private Node rotateRight(Node h){
       assert h!=null&&isRed(h.left);
       Node x=h.left;
       h.left=x.right;
       x.right=h;
       x.color=h.color;
       h.color=RED;
       x.size=h.size;
       h.size=size(h.left)+size(h.right)+1;
       return x;
   }

   private Node rotateLeft(Node h){
       assert h!=null&&isRed(h.right);
       Node x=h.right;
       h.right=x.left;
       x.left=h;
       x.color=h.color;
       h.color=RED;
       x.size=h.size;
       h.size=size(h.left)+size(h.right)+1;
       return x;
   }

   private void flipColor(Node h){
       assert h.left!=null&&h.right!=null&&h!=null;
       assert (isRed(h.left)&&isRed(h.right)&&!isRed(h))||(isRed(h)&&!isRed(h.left)&&!isRed(h.right));
       h.color=!h.color;
       h.left.color=!h.left.color;
       h.right.color=!h.right.color;
   }

   public void put(Key key,Value value){
       root=put(root, key, value);
       if(isRed(root)) root.color=BLACK;
   }

   private Node put(Node x,Key key,Value value){
       if (x==null) {
           return new Node(key,value,1,RED);
       }
       int compare=x.key.compareTo(key);
       if (compare>0) {
           x.left=put(x.left, key, value);
       }else if (compare<0) {
           x.right=put(x.right, key, value);
       }else       x.value=value;

       //rotate in order to balance tree
       if(!isRed(x.left)&&isRed(x.right)) x=rotateLeft(x);
       if(isRed(x.left)&&isRed(x.left.left)) x=rotateRight(x);
       if(isRed(x.left)&&isRed(x.right)) flipColor(x);

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

    private Node MoveRedLeft(Node h){
        //suppose that h is red
        flipColor(h);

        if (isRed(h.right.left)) {
            h.right=rotateRight(h.right);
            h=rotateLeft(h);
        }
        return h;
    }

    private Node deleteMin(Node h){
        if (h.left==null) {
            return null;
        }
        if (!isRed(h.left)&&!isRed(h.left.left)) {
            h=MoveRedLeft(h);
        }
        h.left=deleteMin(h.left);
        return balance(h);
    }

    public void deleteMin(){
        assert check();
        if (!isRed(root.left)&&!isRed(root.right)) {
            root.color=RED;
        }
        root=deleteMin(root);
        if(size()!=0) root.color=BLACK;
    }

    private Node MoveRedRight(Node h){
        flipColor(h);

        if (!isRed(h.left.left)) {
            h=rotateRight(h);
        }
        return h;
    }

    private Node deleteMax(Node h){
        if (isRed(h.left)) {
            h=rotateRight(h);
        }
        if (h.right==null) {
            return null;
        }
        if (!isRed(h.right)&&!isRed(h.right.left)) {
            h=MoveRedRight(h);
        }
        h.right=deleteMax(h.right);
        return balance(h);
    }

    public void deleteMax(){
        assert check();
        if (isRed(root.left)&&isRed(root.right)) {
            root.color=RED;
        }
        root=deleteMax(root);
        if (size()!=0) {
            root.color=BLACK;
        }
    }

    private Node delete(Node h,Key key){
        if (key.compareTo(h.key)<0) {
            if (!isRed(h.left)&&!isRed(h.left.left)) {
                h=MoveRedLeft(h);
            }
            h.left=delete(h.left, key);
        }
        else{
            if (isRed(h.left)) {
                h=rotateRight(h);
            }
            if (key.compareTo(h.key)==0&&h.right==null) {
                return null;
            }
            if (!isRed(h.right)&&!isRed(h.right.left)) {
                h=MoveRedRight(h);
            }
            if (key.compareTo(h.key)==0) {
                h.value=get(h.right, min(h.right).key);
                h.key=min(h.right).key;
                h.right=deleteMin(h.right);
            }
            else
                h.right=delete(h.right, key);
        }
        return balance(h);
    }

    public void delete(Key key){
        assert check();
        if (!isRed(root.left)&&!isRed(root.right)) {
            root.color=RED;
        }
        root=delete(root, key);
        if(size()!=0) root.color=BLACK;
    }

    private Node balance(Node h){
        assert h!=null;

        if(isRed(h.right)) h=rotateLeft(h);
        if(isRed(h.left)&&isRed(h.left.left)) h=rotateRight(h);
        if(isRed(h.right)&&isRed(h.left)) flipColor(h);

        h.size=size(h.left)+size(h.right)+1;
        return h;
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
        if (root==null) {
            return queue;
        }
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

    public static void main(String[] args)throws IOException{
        RedBlackTree<String,Integer> tree=new RedBlackTree<String,Integer>();
        /* String[] strings=inputKit.getStrings(System.getProperty("user.dir")+"/"+args[0]); */
        /* for(String string:strings){ */
        /*     if(tree.get(string)==null) */
        /*         tree.put(string, 1); */
        /*     else tree.put(string, tree.get(string)+1); */
        /* } */
        /* tree.deleteMax(); */
        /* tree.delete("youunder"); */
        myQueue<String> queue=(myQueue<String>)tree.keys();
        for(String string:queue){
            System.out.println(string+" "+tree.get(string));
        }
        System.out.println(tree.size());
        System.out.println(tree.get("a"));
    }

}
