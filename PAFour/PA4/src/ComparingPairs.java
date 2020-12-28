import java.util.Comparator;

public class ComparingPairs implements Comparator<Pair<String,Integer>> {
    // compare method here


    @Override
    public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
        Integer value1 =  o1.getValue();
        Integer value2 = o2.getValue();
        if(value1 > value2) {return 1;}
        else if (value1 < value2){return -1;}
        return 0;
    }
}


