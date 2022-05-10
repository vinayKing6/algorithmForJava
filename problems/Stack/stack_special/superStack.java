package algorithm.problems.Stack.stack_special;

import java.io.IOException;
import java.util.function.Function;

import algorithm.algorithm_data_type.myStack;
import algorithm.tools.inputKit;

/**
 * superStack
 */
public class superStack<T extends Comparable<T>> {

    private myStack<T> stackData;
    private myStack<T> stackMin;
    private myStack<T> stackMax;

    public superStack(){
        this.stackData=new myStack<T>();
        this.stackMin=new myStack<T>();
        this.stackMax=new myStack<T>();
    }

    public void push(T item){
        stackData.push(item);

        if (stackMin.isEmpty()) {
            stackMin.push(item);
        }else if (item.compareTo(stackMin.peek())<=0) {
            stackMin.push(item);
        }else 
            stackMin.push(stackMin.peek());

        if (stackMax.isEmpty()) {
            stackMax.push(item);
        }else if (item.compareTo(stackMax.peek())>=0) {
            stackMax.push(item);
        }else
            stackMax.push(stackMax.peek());
    }

    public T pop(){
        T item=stackData.pop();
        stackMax.pop();
        stackMin.pop();
        return item;
    }

    public T peek(){
        return stackData.peek();
    }

    public T getMin(){
        return stackMin.peek();
    }

    public T getMax(){
        return stackMax.peek();
    }

    //test
    public static void main(String[] args) throws IOException{
        Integer[] test=inputKit.getInts(System.getProperty("user.dir")+"/"+args[0]);
        superStack<Integer> stack=new superStack<Integer>();
        for(int i=0;i<test.length;i++){
            stack.push(test[i]);
        }

        System.out.println("peek: "+stack.peek()+" max: "+stack.getMax()+" min: "+stack.getMin());
    }
}
