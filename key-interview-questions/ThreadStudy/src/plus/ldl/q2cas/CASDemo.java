package plus.ldl.q2cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ldl.plus
 * @date 2020年05月12日  21:48
 * CAS是什么：compareAndSet
 */
public class CASDemo {
    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t current data: " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024) + "\t current data: " + atomicInteger.get());
    }
}
