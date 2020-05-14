package plus.ldl.q1volatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ldl.plus
 * @date 2020年05月12日  18:14
 * 验证volatile可见性
 */
public class VolatileDemo {
    public static void main(String[] args) {
        new MyData().addAtomic();
    }

    public static void seeAtomic() {
        MyData myData = new MyData();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        // 需要等待上面20个线程全部执行完毕再执行main线程
        // 2--后台有2个线程gc线程与main线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally addPlusPlus value : " + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t finally addAtomic value : " + myData.atomicInteger);
    }

    /**
     * volatile保证消息可见性
     */
    public static void seeOkVolatile() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println("myData1 = " + myData.number);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + " myData2 = " + myData.number);
        }, "AAA").start();

        while (myData.number == 0) {
            // 等于0一直循环等待，直至60
        }

        System.out.println(Thread.currentThread().getName() + "\t 线程结束  " + myData.number);
    }
}

class MyData {
    volatile int number = 0;
    // int number = 0;

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }

    public void addTo60() {
        number = 60;
    }

    /**
     * 此时number前加了volatile，保证了可见性
     */
    public void addPlusPlus() {
        number = number + 1;
    }

}
