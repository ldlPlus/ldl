package plus.ldl.q3collection;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ldl.plus
 * @date 2020年05月14日  21:48
 * 集合类不安全问题
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        // List<String> list = Arrays.asList("a", "b", "c");
        // list.forEach(System.out::println);

        // List<String> list = new ArrayList<>();
        // List<String> list = new Vector<>();
        // List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName() + "\t" + list.size() + "\t" + list);
            }, String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t" + list.size() + "\t" + list);


        /**
         * 1、 故障现象
         *          java.util.ConcurrentModificationException
         * 2、导致原因
         *
         * 3、解决方案
         *      1、List<String> list = new Vector<>();
         *      2、List<String> list = Collections.synchronizedList(new ArrayList<>());
         *      3、List<String> list = new CopyOnWriteArrayList<>();
         * 4、优化建议
         *
         */
    }
}
