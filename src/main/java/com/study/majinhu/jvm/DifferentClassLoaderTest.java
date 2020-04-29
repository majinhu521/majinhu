package com.study.majinhu.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName DifferentClassLoaderTest
 * @Description
 * 断一个类是否相同，通常用equals()方法，isInstance()方法和isAssignableFrom()方法。
 * 来判断，对于同一个类，如果没有采用相同的类加载器来加载，在调用的时候，会产生意想不到的结果：
 * @Author majinhu
 * @Date 2019/8/20 9:05
 * @Version 1.0
 **/
public class DifferentClassLoaderTest {

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream stream = getClass().getResourceAsStream(fileName);
                if (stream == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[stream.available()];
                    // 将流写入字节数组b中
                    stream.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return super.loadClass(name);
            }
        };
        Object obj = classLoader.loadClass("jvm.DifferentClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof DifferentClassLoaderTest);

        DifferentClassLoaderTest obj2 = (DifferentClassLoaderTest) classLoader.loadClass("jvm.DifferentClassLoaderTest").newInstance();


    }
}
