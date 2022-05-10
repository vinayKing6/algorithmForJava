package algorithm.problems.Sort;

/**
 * SortCompare
 */
public class SortCompare {

    public static <T extends Comparable<T>> double time(String mode,T[] array){
        long startTime=System.currentTimeMillis();

        if(mode.equals("Insertion"))
            Insertion.sort(array);
        if (mode.equals("Shell")) {
            Shell.sort(array);
        }
        if (mode.equals("Merge")) {
            Merge.sort(array);
        }
        if (mode.equals("Quick")) {
            Quick.sort(array);
        }
        if (mode.equals("three_partion_sort")) {
            Quick.three_partion_sort(array, 0, array.length-1);
        }
        if (mode.equals("Heap")) {
            Heap.sort(array);
        }
        
        long endTime=System.currentTimeMillis();
        System.out.println("running time: "+(endTime-startTime)+" ms");
        return (endTime-startTime);
    }
}
