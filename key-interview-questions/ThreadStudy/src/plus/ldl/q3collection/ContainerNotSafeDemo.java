package plus.ldl.q3collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author ldl.plus
 * @date 2020年05月14日  21:48
 * 集合类不安全问题
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        // Map<String, String> map = new HashMap<>();
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        // Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }

        /*
         * 1、 故障现象
         *          java.util.ConcurrentModificationException
         * 2、导致原因
         *          并发争抢修改导致，参考我们的花名册签名情况,
         *          一个人正在写入，另一个同学过来抢夺，导致数据不一致异常，并发修改异常
         * 3、解决方案
         *      1、List<String> list = new Vector<>();
         *      2、Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
         *      3、Map<String, String> map = new ConcurrentHashMap<>();
         * 4、优化建议
         *
         */
    }

    public static void setNotSafe() {
        // Set<String> set = new HashSet<>();
        // Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set.size() + "\t" + set);
            }, String.valueOf(i)).start();
        }

        new HashSet<>().add("a");

        /*
         * 1、 故障现象
         *          java.util.ConcurrentModificationException
         * 2、导致原因
         *          并发争抢修改导致，参考我们的花名册签名情况,
         *          一个人正在写入，另一个同学过来抢夺，导致数据不一致异常，并发修改异常
         * 3、解决方案
         *      1、List<String> list = new Vector<>();
         *      2、Set<String> set = Collections.synchronizedSet(new HashSet<>());
         *      3、Set<String> set = new CopyOnWriteArraySet<>();
         * 4、优化建议
         *
         */
    }

    public static void listNotSafe() {
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

        /*
         * 1、 故障现象
         *          java.util.ConcurrentModificationException
         * 2、导致原因
         *          并发争抢修改导致，参考我们的花名册签名情况,
         *          一个人正在写入，另一个同学过来抢夺，导致数据不一致异常，并发修改异常
         * 3、解决方案
         *      1、List<String> list = new Vector<>();
         *      2、List<String> list = Collections.synchronizedList(new ArrayList<>());
         *      3、List<String> list = new CopyOnWriteArrayList<>();
         * 4、优化建议
         *
         */
    }
}
