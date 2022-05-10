package algorithm.algorithm_data_type;

import java.util.Iterator;

/**
 * myStack
 */
public class myStack<T> implements Iterable<T>{

    private Node<T> first=null;
    private int N=0;

    //push() pop() isEmpty() size() peek() iterator()
    public boolean isEmpty(){
        return first==null;
    }

    public int size(){
        return N;
    }

    public void push(T item){
        Node oldFirst=first;
        first=new Node<T>();
        first.item=item;
        first.next=oldFirst;
        N++;
    }

    public T pop(){
        T item=first.item;
        first=first.next;
        N--;
        return item;
    }

    public T peek(){
        return first.item;
    }

    //a inner class implementing iterator , in order to make stack iterable
    private class reverseListIterator implements Iterator<T>{
        private Node<T> pos=first;

        public boolean hasNext(){
            return pos!=null;
        }

        public T next(){
            T item=pos.item;
            pos=pos.next;
            return item;
        }

        public void remove(){
            //pass
        }
    }
    
    public Iterator iterator(){
        return new reverseListIterator();
    }

    /* test myStack*/
    public static void main(String[] args){
        myStack<Integer> test=new myStack();

        test.push(10);
        test.push(18);
        test.push(3);
        
        System.out.println(test.size());
        for(var i:test){
            System.out.println(i);
        }

        System.out.println(test.pop());

    }
}
