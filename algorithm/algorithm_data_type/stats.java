package algorithm.algorithm_data_type;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class stats
{
        public static void main(String[] args )
		{
				var in=new Scanner(System.in);
				List<Double> numbers=new ArrayList<>();
				double num;

				while((num=in.nextDouble())!=-1)
				{
						System.out.println("please enter -1 to quit: ");
						numbers.add(num);
				}
				int N=numbers.size();

				double sum=0;
				for(Double d:numbers)
				{
						sum+=d;
				}
				double avg=sum/N;

				sum=0;
				for(double d:numbers)
				{
						sum+=(d-avg)*(d-avg);
				}
				double std=Math.sqrt(sum);

				System.out.printf("Mean: %.2f\n" , avg);
				System.out.printf("Std dev: %.2f\n",std);
		}
}
