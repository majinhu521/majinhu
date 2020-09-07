package com.study.majinhu.webservice.mdmWsdl;

import com.study.majinhu.webservice.TestService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * @Author majinhu
 * @Description webservice 客户端
 * @Date 16:08 2019/6/11
 * @Param
 * @return
 **/
public class MdmCxfClient {
    public static void main(String[] args) {
        cl2();
    }

//    /**
//     * 方式1.代理类工厂的方式,需要拿到对方的接口
//     */
//    public static void cl1() {
//        try {
//            // 接口地址
//            String address = "http://localhost:8083/services/TradeResultNotifyService?wsdl";
//            // 代理工厂
//            JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
//            // 设置代理地址
//            jaxWsProxyFactoryBean.setAddress(address);
//            // 设置接口类型
//            jaxWsProxyFactoryBean.setServiceClass(TestService.class);
//            // 创建一个代理接口实现
//            TestService cs = (TestService) jaxWsProxyFactoryBean.create();
//            // 数据准备
//            String username = "LemmonTree";
//            String pwd = "pwd";
//            // 调用代理接口的方法调用并返回结果
//            String result = cs.sendMessage(username,pwd);
//            System.out.println("返回结果:" + result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 动态调用方式
     */
    public static void cl2() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://10.135.16.46:8001/soa-infra/services/interface/MDMGeneralBankInterface/mdmgeneralbankinterface_client_ep?WSDL");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,
        // PASS_WORD));
        Object[] objects = new Object[0];
        //105452004038  中国建设银行股份有限公司青岛海尔路支行
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("process", "105452004038");
            System.out.println("返回数据:" + objects[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}