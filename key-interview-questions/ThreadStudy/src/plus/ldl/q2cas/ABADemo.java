package plus.ldl.q2cas;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author ldl.plus
 * @date 2020年05月14日  15:52
 * ABA问题的解决
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        System.out.println("===================以下是ABA问题的产生================");

        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());
        }, "t2").start();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("===================以下是ABA问题的解决================");
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("t3---stamp = " + stamp);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, stamp, atomicStampedReference.getStamp() + 1);
            System.out.println("t3---stamp = " + stamp);
            atomicStampedReference.compareAndSet(101, 100, stamp, atomicStampedReference.getStamp() + 1);
            System.out.println("t3---stamp = " + stamp);
        }, "t3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("t4---stamp = " + stamp);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(100, 2019, stamp,
                    atomicStampedReference.getStamp() + 1) + "\t" + atomicReference.get());
        }, "t4").start();
    }
}
