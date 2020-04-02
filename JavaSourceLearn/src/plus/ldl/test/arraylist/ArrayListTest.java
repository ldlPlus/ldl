package plus.ldl.test.arraylist;

import java.util.ArrayList;

/**
 * @author ldl.plus
 * @date 2020年03月25日  22:04
 */
public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add(2,"2");
        list.add(4,"4");
        System.out.println("list = " + list);
    }
}
