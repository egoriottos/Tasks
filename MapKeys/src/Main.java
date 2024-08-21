import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Object[] array = {"Bak","Kwak","Kwak","Kwak","Egor","Egor","Egor","Egor"};
        System.out.println(getMap(array));
    }

    public static Map<Object,Integer> getMap(Object[] array){
        Map<Object,Integer> map = new HashMap<>();
        Integer count = 0;
        for(Object o : array){
           if(map.containsKey(o)){
               map.put(o,map.get(o)+1);
           }
           else {
               map.put(o,1);
           }
        }
        return map;
    }
}