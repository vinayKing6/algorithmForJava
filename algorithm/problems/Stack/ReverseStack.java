package algorithm.problems.Stack;

import algorithm.algorithm_data_type.myStack;

/**
 * ReverseStack
 */
public class ReverseStack {

    private static <T> T getAndRemoveLast(myStack<T> stack){
        T result=stack.pop();

        if (stack.isEmpty()) {
            return result;
        }else{
            T last=getAndRemoveLast(stack);
            stack.push(result);
            return last;
        }
    }

    public static <T> void Reverse(myStack<T> stack){
        if (stack.isEmpty()) {
            return;
        }
        T temp=getAndRemoveLast(stack);
        Reverse(stack);
        stack.push(temp);
    }

    //test
    public static void main(String[] args){
        int[] array={1,5,6,2,0};
        myStack<Integer> stack=new myStack<Integer>();
        for (int i = 0; i < array.length; i++) {
            stack.push(array[i]);
        }
        for (var i:stack) {
            System.out.println(i);
        }
        System.out.println();
        ReverseStack.Reverse(stack);
        for (var i:stack) {
            System.out.println(i);
        }
    }
}
