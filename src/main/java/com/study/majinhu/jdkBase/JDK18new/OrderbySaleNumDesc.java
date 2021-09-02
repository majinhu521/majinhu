package com.study.majinhu.jdkBase.JDK18new;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName OrderbySaleNumDesc
 * @Description
 * @Author majinhu
 * @Date 2021/4/29 9:35
 * @Version 1.0
 **/
public class OrderbySaleNumDesc {
    /**
     * 销量从高到低排序
     * @param groupBuyIndexList
     * @param listproducts
     * @return
     */
    private static List<GroupBuyIndexListDTO> orderbySaleNumDesc(List<GroupBuyIndexListDTO> groupBuyIndexList, List<String> listproducts) {
        List<GroupBuyIndexListDTO> list = new ArrayList<>();
//        if (listproducts == null) {
//            return groupBuyIndexList;
//        }
//        for (GroupBuyIndexListDTO groupBuyIndexListDTO : groupBuyIndexList) {
//            for (SgProducts listproduct : listproducts) {
//                if (groupBuyIndexListDTO.getProductId() == listproduct.getProductId()) {
//                    groupBuyIndexListDTO.setSaleNum(listproduct.getSaleNum());
//                }
//            }
//            list.add(groupBuyIndexListDTO);
//        }
        list = groupBuyIndexList.stream().sorted(Comparator.comparing(GroupBuyIndexListDTO::getSaleNum).reversed().thenComparing(GroupBuyIndexListDTO::getStartTime)).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(list));
        return list;
    }

    public static void main(String[] args){
        List<GroupBuyIndexListDTO> groupBuyIndexList = new ArrayList<>();
        GroupBuyIndexListDTO d1 = new GroupBuyIndexListDTO();
        d1.setProductId(1);
        d1.setSaleNum(1);
        d1.setStartTime(new Date());

        GroupBuyIndexListDTO d2 = new GroupBuyIndexListDTO();
        d2.setProductId(2);
        d2.setSaleNum(2);
        d2.setStartTime(new Date());

        GroupBuyIndexListDTO d3 = new GroupBuyIndexListDTO();
        d3.setProductId(3);
        d3.setSaleNum(3);
        d3.setStartTime(new Date());


        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        Date startDate = calendar.getTime();

        GroupBuyIndexListDTO d4 = new GroupBuyIndexListDTO();
        d4.setProductId(3);
        d4.setSaleNum(3);
        d4.setStartTime(startDate);

        groupBuyIndexList.add(d1);
        groupBuyIndexList.add(d2);
        groupBuyIndexList.add(d3);
        groupBuyIndexList.add(d4);
        orderbySaleNumDesc(groupBuyIndexList,null);
    }
}
