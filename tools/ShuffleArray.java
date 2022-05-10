package algorithm.tools;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ShuffleArray
 */
public class ShuffleArray {

    public static <T> void shuffle(T[] array){
        List<T> list=Arrays.asList(array);
        Collections.shuffle(list);
        list.toArray(array);
    }
}
