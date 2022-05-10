package algorithm.algorithm_data_type;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * myQueue
 */
public class myQueue<T> implements Iterable<T> {

    private Node<T> first=null;
    private Node<T> last=null;
    private int N=0;

    //isEmpty() size() enqueue() dequeue() iterator()
    public boolean isEmpty(){
        return first==null;
    }

    public int size(){
        return N;
    }

    public void enqueue(T item){
        Node<T> oldLast=last;
        last=new Node<T>();
        last.item=item;
        last.next=null;
        if (isEmpty()) {
            first=last;
        }
        else{
            oldLast.next=last;
        }
        N++;
    }

    public T dequeue(){
        T item=first.item;
        first=first.next;
        if (first==null) {
            last=null;
        }
        N--;
        return item;
    }

    public T peek(){
        return first.item;
    }

    private class QueueIterator implements Iterator<T>{
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


   public Iterator<T> iterator(){
        return new QueueIterator();
   }

   public static void main(String[] args) throws IOException {
       //test
       myQueue<String> queue=new myQueue<String>();
       String filename="/media/vinay/Data/myFile/sharing_files/from_ubuntu/Java/src/algorithm/algorithm_data_type/data.txt";
       

       try(Scanner stdin=new Scanner(new FileReader(filename))){
           stdin.useDelimiter(" ");
           while (stdin.hasNext()) {
               String item=stdin.next();
               if (!item.equals("-")) {
                   queue.enqueue(item);
               }
               else if (!queue.isEmpty()) {
                   System.out.print(queue.dequeue()+" ");
               }
           }
           /* for (String string : queue) { */
           /*       System.out.print(string); */
           /* } */
       }
   }
}
