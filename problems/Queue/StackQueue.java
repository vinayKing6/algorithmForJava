package algorithm.problems.Queue;

import java.io.IOException;

import algorithm.algorithm_data_type.myStack;
import algorithm.tools.inputKit;

/**
 * StackQueue
 */
public class StackQueue<T> {
    private int N=0;
    private myStack<T> stackPush;
    private myStack<T> stackPop;

    public StackQueue(){
        stackPush=new myStack<T>();
        stackPop=new myStack<T>();
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public void enqueue(T item){
        stackPush.push(item);
        N++;
    }

    public T dequeue(){
        if (stackPop.isEmpty()&&stackPush.isEmpty()) {
            throw new RuntimeException("Empty Queue!");
        }else if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        N--;
        return stackPop.pop();
    }

    public T peek(){
        if (stackPop.isEmpty()&&stackPush.isEmpty()) {
            throw new RuntimeException("Empty Queue!");
        }else if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();
    }

    //test
    public static void main(String[] args)throws IOException{
        Integer[] array=inputKit.getInts(System.getProperty("user.dir")+"/"+args[0]);
        StackQueue<Integer> queue=new StackQueue<Integer>();
        for (int i = 0; i < array.length; i++) {
           queue.enqueue(array[i]); 
        }

        while (!queue.isEmpty()) {
            System.out.print(queue.dequeue()+" ");
        }
        System.out.println();
    }
}
