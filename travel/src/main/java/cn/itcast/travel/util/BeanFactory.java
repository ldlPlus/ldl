/*
 * 版权所有(C)，ldl公司，2020，所有权利保留。
 *
 * 项目名： travel
 * 文件名： BeanFactory.java
 * 模块说明：
 * 修改历史:
 * 2020-3-2 - lidonglin - 创建。
 */

package cn.itcast.travel.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.lang.reflect.Proxy;

/**
 * @author ldl.plus
 * @date 2020年03月02日  16:45
 * 工厂类
 */
public class BeanFactory {

    private static Document document;

    static {
        try {
            String path = BeanFactory.class.getClassLoader().getResource("applicationContext.xml").getPath();
            document = Jsoup.parse(new File(path), "UTF-8");
        } catch (Exception e) {
            System.err.println("FileNotFindException --> \"applicationContext.xml\"");
        }
    }


    public static Object getBean(String name) {
        String className = document.getElementById(name).attr("class");
        try {
            Class<?> cls = Class.forName(className);
            Object instance = cls.getConstructor().newInstance();
            //增强对象，如果是DaoImpl里的findXxx方法
            String targetClassName = "DaoImpl";
            String targetMethodName = "find";
            if (className.endsWith(targetClassName)) {
                //判断是否是findXxx方法
                return Proxy.newProxyInstance(
                        instance.getClass().getClassLoader(),
                        instance.getClass().getInterfaces(),
                        (proxy, method, args) -> {
                            if (method.getName().startsWith(targetMethodName)) {
                                long start = System.currentTimeMillis();
                                Object invoke = method.invoke(instance, args);
                                long end = System.currentTimeMillis();
                                System.err.println(name + "的" + method.getName() + "方法执行消耗了：" + (end - start) + "毫秒");
                                return invoke;
                            }
                            return method.invoke(instance, args);
                        });
            }
            return instance;

        } catch (Exception e) {
            System.err.println("ClassNotFoundException --> \"applicationContext.xml\"");
            return null;
        }
    }

    private BeanFactory() {
    }
}
