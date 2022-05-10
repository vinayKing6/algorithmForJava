package algorithm.algorithm_data_type;

import java.util.Iterator;

/**
 * myBag
 */
public class myBag<T> implements Iterable<T> {

    private Node<T> first=null;
    private int N=0;

    //isEmpty() size() add() iterator()
    public boolean isEmpty(){
        return first==null;
    }

    public int size(){
        return N;
    }

    public void add(T item){
        Node<T> oldFirst=first;
        first=new Node<T>();
        first.item=item;
        first.next=oldFirst;
        N++;
    }

    private class BagIterator implements Iterator<T>{
        private Node<T> pos=first;

        public boolean hasNext(){
            return pos!=null;
        }

        public T next(){
            T item=pos.item;
            pos=pos.next;
            return item;
        }
    }

    public Iterator<T> iterator(){
        return new BagIterator();
    }
}
