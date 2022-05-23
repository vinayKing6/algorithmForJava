package algorithm.problems.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class evaluate
{
        public static void main(String[] args)
        {
                Scanner in=new Scanner(System.in);
                Stack<String> options=new Stack<>();
                Stack<Double> values=new Stack<>();
                String charString;
                charString=in.nextLine();
                Scanner stdin=new Scanner(charString);

                while (stdin.hasNext())
                {
                        charString=stdin.next();
                        if (charString.equals("+"))
                        {
                                options.push(charString);
                        }
                        else if (charString.equals("(")) ;
                        else if(charString.equals("*"))
                        {
                                options.push(charString);
                        }
                        else if(charString.equals("/"))
                        {
                                options.push(charString);
                        }
                        else if(charString.equals("sqrt"))
                        {
                                options.push(charString);
                        }
                        else if(charString.equals("-"))
                        {
                                options.push(charString);
                        }
                        else if(charString.equals(")"))
                        {
                                String opt=options.pop();
                                double val=values.pop();

                                if(opt.equals("+"))
                                        val=values.pop()+val;
                                else if(opt.equals("-"))
                                        val=values.pop()-val;
                                else if(opt.equals("*"))
                                        val=values.pop()*val;
                                else if(opt.equals("/"))
                                        val=values.pop()/val;
                                else if(opt.equals("sqrt"))
                                        val=Math.sqrt(val);
                                values.push(val);
                        }
                        else
                                values.push(Double.parseDouble(charString));
                }


                Stack<String> PlusOrMinus=new Stack<>();
                Stack<Double> valuesForPM=new Stack<>();

                while(!options.isEmpty())
                {
                        charString=options.pop();
                        double val=values.pop();

                        if(charString.equals("*"))
                        {
                                val*=values.pop();
                                values.push(val);
                        }
                        else if(charString.equals("/"))
                        {
                                val=values.pop()/val;
                                values.push(val);
                        }
                        else if(charString.equals("+")||charString.equals("-"))
                        {
                                PlusOrMinus.push(charString);
                                valuesForPM.push(val);
                        }
                }

                valuesForPM.push(values.pop());

                while(!PlusOrMinus.isEmpty())
                {
                        charString=PlusOrMinus.pop();
                        double val=valuesForPM.pop();
                        if(charString.equals("+"))
                                val=val+valuesForPM.pop();
                        else if(charString.equals("-"))
                                val=val-valuesForPM.pop();
                        valuesForPM.push(val);
                }

                System.out.println(valuesForPM.pop());

        }
}
