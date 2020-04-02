package plus.ldl.test.hashmap;

import java.util.HashMap;

/**
 * @author ldl.plus
 * @date 2020年03月25日  21:24
 */
public class HashMapTest {
    public static void main(String[] args) {
        // write your code here
        HashMap<String, Integer> map = new HashMap<>();
        map.put("K1",1);
        map.put("K2",2);
        map.put("K3",3);
        System.out.println("map = " + map);
    }
}
