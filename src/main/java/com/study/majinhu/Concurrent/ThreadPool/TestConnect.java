package com.study.majinhu.Concurrent.ThreadPool;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * 测试应用连接
 */

public class TestConnect {
	
    public static Boolean testWsdlConnection(String address) throws Exception {
        boolean flag = false;
        try {
            URL urlObj = new URL(address);
            HttpURLConnection oc = (HttpURLConnection) urlObj.openConnection();
            oc.setUseCaches(false);
            oc.setConnectTimeout(5000); // 设置超时时间5s
            int status = oc.getResponseCode();// 请求状态
            if (200 == status) {
                // 200是请求地址顺利连通。。
                return true;
            }
        } catch (Exception e) {
            return flag;
        }
        return flag;
    }
   
    public static void main(String[]   args) {
    	
    	try {
    		
    		//TestConnect testConnect=new TestConnect();

//           int stat=TestConnect.testWsdlConnection("https://www.baidu.com/");
//            System.out.println("zhuangtai================"+stat);

//            Boolean stat2=TestConnect.testWsdlConnection("https://www.hahahc.com/");
//            //int stat2=TestConnect.testWsdlConnection("www.baidu.com/");
//            System.out.println("zhuangtai2================"+stat2);//连接超时

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<String> mobileList = new ArrayList<>();
        mobileList.add("17721111111");
        mobileList.add("15223333333");
        mobileList.add("15223234444");

        System.out.println( TestConnect.listToString(mobileList,','));
    }



    public static String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
            }
        return sb.toString().substring(0,sb.toString().length()-1);
    }

    public static String listToString2(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
        sb.append(list.get(i));
        if (i < list.size() - 1) {
            sb.append(separator);
        }
        }
        return sb.toString();}


//    public static boolean setduanxinphone(String phonenumber)
//    {
//        if (!jedis.exists(phonenumber))
//        {
//            jedis.set(phonenumber, "1");
//
//            jedis.expire(phonenumber, 3600);
//            return true;
//        }
//        int num = Integer.valueOf(jedis.get(phonenumber));
//        if (num < 4)
//        {
//            jedis.set(phonenumber, String.valueOf(num + 1));
//
//            jedis.expire(phonenumber, 3600);
//            return true;
//        }
//        return false;
//    }


  
}
