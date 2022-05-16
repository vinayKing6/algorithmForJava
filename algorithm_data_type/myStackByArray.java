package algorithm.algorithm_data_type;

import java.util.Iterator;


/* auto resizing stack */
public class myStackByArray<T> implements Iterable<T> {

    private T[] array=(T []) new Object[1];
    private int pos=0;

    public boolean isEmpty(){
        return pos==0;
    }

    public int size(){
        return pos;
    }

    private void resize(int max){
        T[] temp=(T []) new Object[max];

        for (int i = 0; i < pos; i++) {
            temp[i]=array[i];
        }

        array=temp;
    }

    public void push(T element){
        if (pos==array.length) {
            resize(2*pos);
        }

        array[pos++]=element;
    }

    public T pop(){
        T temp=array[--pos];
        array[pos]=null;

        if (pos>0 && pos==array.length/4) {
            resize(array.length/2);
        }

        return temp;
    }

    public T peek(){
        return array[pos-1];
    }

    //a inner class implementing iterator , in order to make stack iterable
    private class reverseArrayIterator implements Iterator<T>{
        private int i=pos;
        public boolean hasNext(){
            return i>0;
        }

        public T next(){
            return array[--i];
        }

        public void remove(){
            //pass
        }
    }

    public Iterator<T> iterator(){
        return new reverseArrayIterator();
    }

    /* test myStackByArray*/
    public static void main(String[] args){
        myStackByArray<Integer> test=new myStackByArray<>();
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
