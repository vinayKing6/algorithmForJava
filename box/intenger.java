package algorithm.box;

/**
 * intenger
 */
public class intenger {

    private Integer value;

    public intenger(int i){
        value=i;
    }
    public void set(int v){
        value=v;
    }

    public Integer getValue(){
        return value;
    }

    @Override
    public String toString(){
        return value.toString();
    }
}
