package plus.ldl.q0thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ldl.plus
 * @date 2020年05月13日  10:27
 */
public class ThreadPool {
    public static void main(String[] args) {

    }

    public static void getThreadPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        threadPool.execute(() -> {
            for (int i = 0; i <= 100; i++) {
                if (i % 2 == 0) {
                    System.out.println("i = " + i);
                }
            }
        });
        threadPool.shutdown();
        // threadPool.submit()
    }
}
