package com.study.majinhu.Concurrent.threadlocal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName HelloWorldExample
 * @Description Tomcat的Servlet中使用ThreadLocal导致内存泄露
 *代码（1）创建一个localVariable对象，
 * 代码（2）在servlet的doGet方法内设置localVariable值
 * 代码（3）打印当前servlet的实例
 * 代码（4）打印当前线程
 * 修改tomcat的conf下sever.xml配置如下：
 *
 *     <Executor name="tomcatThreadPool" namePrefix="catalina-exec-"
 *         maxThreads="10" minSpareThreads="5"/>
 * @Author majinhu
 * @Date 2020/9/7 10:02
 * @Version 1.0
 **/
public class HelloWorldExample extends HttpServlet {

    private static final long serialVersionUID = 1L;

    static class LocalVariable {
        private Long[] a = new Long[1024 * 1024 * 100];
    }

    //(1)
    final static ThreadLocal<LocalVariable> localVariable = new ThreadLocal<LocalVariable>();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //(2)
        localVariable.set(new LocalVariable());

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");

        out.println("<title>" + "title" + "</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
        //(3)
        out.println(this.toString());
        //(4)
        out.println(Thread.currentThread().toString());

        out.println("</body>");
        out.println("</html>");
        //localVariable.remove();
    }
}