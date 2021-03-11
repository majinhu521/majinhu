package com.study.majinhu.jdkBase.collection;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName listtomap
 * @Description
 *  集合操作 https://blog.csdn.net/feiyanaffection/article/details/81394745
 * @Author majinhu
 * @Date 2021/3/11 14:34
 * @Version 1.0
 **/
public class Listtomap {

    public static void main(String[] args) {
        List<Regions> regionsList = Lists.newArrayList();
        Regions Region1 = new Regions();
        Region1.setId(1);
        Region1.setRegionname("区域1");
        Regions Region2 = new Regions();
        Region2.setId(2);
        Region2.setRegionname("区域2");
        regionsList.add(Region1);
        regionsList.add(Region2);

        List<Map> retList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(regionsList)) {
            retList = regionsList.stream().map(e -> {
                Map map = new HashMap();
                map.put("id", e.getId());
                map.put("name", e.getRegionname());
                return map;
            }).collect(Collectors.toList());
        }
        //[{"name":"区域1","id":1},{"name":"区域2","id":2}]
        System.out.println(JSONObject.toJSON(retList));

        //{"1":{"regionname":"区域1","id":1},"2":{"regionname":"区域2","id":2}}
        Map<Integer, Regions> maps1 = regionsList.stream().collect(Collectors.toMap(Regions::getId, Function.identity(), (key1, key2) -> key2));
        System.out.println(JSONObject.toJSON(maps1));


        //希望得到的map的值不是对象，而是对象的某个属性，那么可以用下面的方式.
        // 转换成map的时候，可能出现key一样的情况，如果不指定一个覆盖规则，代码是会报错的(key1, key2) -> key2
        //{"1":"区域1","2":"区域2"}
        Map<Integer, String> maps = regionsList.stream()
                .collect(Collectors.toMap(Regions::getId, Regions::getRegionname, (key1, key2) -> key2));
        System.out.println(JSONObject.toJSON(maps));

        //遍历map
        //        key:value = 1:区域1
        //        key:value = 2:区域2
        maps.forEach((k, v) -> System.out.println("key:value = " + k + ":" + v));

        //map转list
        //[{"regionname":"区域1","id":1},{"regionname":"区域2","id":2}]
        List list = maps.entrySet().stream().map(e -> new Regions(Integer.valueOf(e.getKey()),e.getValue())).collect(Collectors.toList());
        System.out.println(JSONObject.toJSON(list));


        /**
         * https://www.cnblogs.com/linjiqin/p/13906136.html
         *
         * //List里面的对象元素，以某个属性来分组，例如，以id分组，将id相同的放在一起
         * //List 以ID分组 Map<Long,List<Sku>>
         * Map<Long, List<Sku>> mapList = skuList.stream().collect(Collectors.groupingBy(Sku::getProductSpecificationId));
         * System.out.println(mapList);
         *
         * //List转Map
         * Map<Long, Sku> appleMap = skuList.stream().collect(Collectors.toMap(Sku::getProductSpecificationId, item -> item));
         * System.out.println(appleMap);
         *
         * //获取 List 集合获取指定的字段数组
         * List<Vip> vipList = new ArrayList<Vip>();
         * vipList.add(new Vip().setId(1L));
         * vipList.add(new Vip().setId(2L));
         * List<Long> idList = vipList.stream().map(Vip::getId).collect(Collectors.toList());
         *
         * List转换逗号分隔的字符串
         * StringUtils.join(productSpecificationIdList.toArray(), ",")
         *
         * //Set转List
         * Set<String> idSet = buyerCartMap.keySet();
         * List<Long> productSpecificationIds = Arrays.asList(idSet.toArray(new Long[idSet.size()]));
         */

    }

    static class Regions {
        private int id;
        private String regionname;

        public Regions() {
        }

        public Regions(int id, String regionname) {
            this.id = id;
            this.regionname = regionname;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRegionname() {
            return regionname;
        }

        public void setRegionname(String regionname) {
            this.regionname = regionname;
        }
    }
}
