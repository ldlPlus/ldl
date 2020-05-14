package plus.ldl.q1volatile;

/**
 * @author ldl.plus
 * @date 2020年05月12日  21:10
 */
public class SingletonDemo {
    private static SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\tSingletonDemo.SingletonDemo");
    }

    /**
     * DCI (Double Check Lock双端检锁机制)
     *
     * @return
     */
    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        // 单线程
        // System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        // System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        // System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        // 并发多线程
        for (int i = 0; i < 20; i++) {
            new Thread(() -> SingletonDemo.getInstance(), String.valueOf(i + 1)).start();
        }
    }
}
