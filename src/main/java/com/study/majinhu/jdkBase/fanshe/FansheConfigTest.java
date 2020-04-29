package com.study.majinhu.jdkBase.fanshe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @ClassName FansheConfigTest
 * @Description
 *  我们利用反射和配置文件，可以使：应用程序更新时，对源码无需进行任何修改
 *  * 我们只需要将新类发送给客户端，并修改配置文件即可
 *  原文：https://blog.csdn.net/sinat_38259539/article/details/71799078
 *  https://www.jianshu.com/p/9be58ee20dee
 *
 *  当我们升级这个系统时，不要Student类，而需要新写一个Student2的类时，这时只需要更改pro.txt的文件内容就可以了。代码就一点不用改动
 *
 * 要替换的student2类：
 * public class Student2 {
 * 	public void show2(){
 * 		System.out.println("is show2()");
 * 	}
 * }
 *
 * 配置文件更改为：
 * className = cn.fanshe.Student2
 * methodName = show2
 * @Author majinhu
 * @Date 2019/7/26 10:11
 * @Version 1.0
 **/
public class FansheConfigTest {
    public static void main(String[] args) throws Exception {
        //通过反射获取Class对象
        Class stuClass = Class.forName(getValue("className"));//"cn.fanshe.Student"
        //2获取show()方法
        Method m = stuClass.getMethod(getValue("methodName"));//show
        System.out.println("方法名："+m.getName());
        //2获取show1()方法
        Method m2 = stuClass.getMethod(getValue("methodName1"), String.class);
        System.out.println("方法名："+m2.getName());
        //3.调用show()方法
        Object obj = stuClass.getConstructor().newInstance();
        m.invoke(obj);
        m2.invoke(obj,"输入参数字符串hello");

    }

    //此方法接收一个key，在配置文件中获取相应的value
    public static String getValue(String key) throws IOException {
        Properties pro = new Properties();//获取配置文件的对象
       // File file = new File("E:\\pro.txt");
        FileReader in = new FileReader("E:\\pro.txt");//获取输入流
        pro.load(in);//将流加载到配置文件对象中
        in.close();
        return pro.getProperty(key);//返回根据key获取的value值
    }


}
