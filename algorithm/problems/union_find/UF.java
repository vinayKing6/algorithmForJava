package algorithm.problems.union_find;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * UF
 */
public class UF {
    //weighted union_find algorithm
    private int[] id;
    private int[] size;//notice that size is not depth
    private int count;

    public UF(int N){
        count=N;
        id=new int[N];
        size=new int[N];
        for (int i = 0; i < N; i++) {
            id[i]=i;
            size[i]=1;
        }
    }

    public int count(){ return count;}

    public boolean connected(int q,int p){
        return find(q)==find(p);
    }

    public int find(int p){
        while (p!=id[p]) {
            p=id[p];
        }
        return p;
    }

    public void union(int p,int q){
        int qroot=find(q);
        int proot=find(p);

        if (qroot==proot) {
            return;
        }

        if (size[qroot]<size[proot]) {
            id[qroot]=proot;
            size[proot]+=size[qroot];
        }else{
            id[proot]=qroot;
            size[qroot]+=size[proot];
        }
        count--;
    }

    public static void main(String[] args) throws IOException{
        try(Scanner sc=new Scanner(new FileReader(args[0]))){
            int N=sc.nextInt();
            UF uf=new UF(N);
            while (sc.hasNextInt()) {
                int p=sc.nextInt();
                int q=sc.nextInt();
                if (uf.connected(q, p)) {
                   continue; 
                }
                uf.union(p, q);
                System.out.println(p+" "+q);
            }
            System.out.println("size: "+uf.count());
        }
    }

}
