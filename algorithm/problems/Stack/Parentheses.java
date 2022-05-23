package algorithm.problems.Stack;

import java.io.*;

import algorithm.algorithm_data_type.myStack;

/**
 * Parentheses
 */
public class Parentheses {

    public static void main(String[] args) throws IOException{
        //judge if ( { [ is matched
        File file=new File(args[0]);
        try(FileInputStream in=new FileInputStream(file) ){
            int temp;
            String current;
            myStack<String> parent=new myStack<String>();
            while ((temp=in.read())!=-1) {
                current=String.valueOf((char)temp);
                if (current.equals("}")) {
                    if (parent.isEmpty()||!parent.pop().equals("{")) {
                        System.out.println("missing {");
                        break;
                    }
                }
                else if (current.equals(")")) {
                    if (parent.isEmpty()||!parent.pop().equals("(")) {
                        System.out.println("missing (");
                        break;
                    }
                }
                else if (current.equals("]")) {
                    if (parent.isEmpty()||!parent.pop().equals("[")) {
                        System.out.println("missing [");
                        break;
                    }
                }
                else if (current.equals("(")||current.equals("{")||current.equals("[")) {
                    parent.push(current);
                }
            }
            if (!parent.isEmpty()) {
                while (!parent.isEmpty()) {
                    String tempString=parent.pop();
                    if (tempString.equals("(")) {
                        System.out.println("missing )");
                    }else if (tempString.equals("{")) {
                        System.out.println("missing }");
                    }else 
                        System.out.println("missing ]");
                }
            }
        }
        
    }
}
