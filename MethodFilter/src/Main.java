import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Double [] numbers = new Double[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Math.random()+1;
        }
        Filter filter = new Filter() {
            @Override
            public Object apply(Object o) {
                Double d=(Double) o;
                return d<4;
            }
        };
        System.out.println(filter(numbers,filter));
    }
    public static <T> List<T> filter(T[] array, Filter filter){
        List<T> result = new ArrayList<T>();
        for(T t : array){
            if((Boolean) filter.apply(t)){
                result.add(t);
            }
        }
        return result;
    }
}