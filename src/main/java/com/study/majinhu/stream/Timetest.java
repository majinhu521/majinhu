package com.study.majinhu.stream;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName Timetest
 * @Description
 * @Author majinhu
 * @Date 2020/9/10 17:08
 * @Version 1.0
 **/
public class Timetest {
    public static void main(String[] args){
        Date now = new Date();
//        long time = now.getTime();
////        long endDate = 1599753600000L;
////        //1599729613776 now time
////        //1599753600000L 2020-09-11 00:00:00
////        System.out.println(time);
//////        if(endDate < time){
//////            System.out.println("小于");
//////           }else{
//////            System.out.println("小于");
//////        }
////        if(endDate < time+7*24*3600*1000){
////            System.out.println("您的合同将到期");
////        }else{
////            System.out.println("小于");
////        }


//        int year =cal.get(Calendar.YEAR)+1;
//        int month=cal.get(Calendar.MONTH)+1;
//        cal.add(Calendar.MONTH, 1);
//Date date = cal.getTime(); //结果
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        cal.set(Calendar.YEAR,cal.get(Calendar.YEAR)+1);
//        Date date=cal.getTime();
//        System.out.println(sdf.format(date));
        System.out.println(getEndDate());
    }

    private static Date getEndDate(){
        Calendar cal = Calendar.getInstance();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cal.set(Calendar.YEAR,cal.get(Calendar.YEAR)+1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date date=cal.getTime();
        //System.out.println(sdf.format(date));

//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
//                0, 0, 0);
//        Date beginOfDate = calendar1.getTime();
//        System.out.println(beginOfDate+"=========");

        return date;
//        // 时
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        // 分
//        cal.set(Calendar.MINUTE, 0);
//        // 秒
//        cal.set(Calendar.SECOND, 0);
//        // 毫秒
//        cal.set(Calendar.MILLISECOND, 0);
//
//        Date time = cal.getTime();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        String format = df.format(time);
//        System.out.println(format);


//        return date;
    }
}
