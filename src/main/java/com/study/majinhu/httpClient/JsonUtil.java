package com.study.majinhu.httpClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class JsonUtil {

    public static <T> List<T> json2BeanList(String json, Class<T> cls) {
        return JSON.parseArray(json, cls);
    }

    public static <T> T json2Bean(String json, Class<T> cls) {
        return JSON.parseObject(json, cls);
    }

    public static String obj2Json(Object obj) {
        String json = JSON.toJSONString(obj);
        return json;
    }

    /**
     * map 类型 不忽略 null
     * @param map
     * @return
     */
    public static String map2JsonWriteMapNullValue(Map map) {
        String json = JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
        return json;
    }

    /**
     * 不忽略 null
     * @param o
     * @return
     */
    public static String obj2JsonWriteNullValue(Object o){
        return JSON.toJSONString(o,SerializerFeature.WriteMapNullValue);
    }


    @SuppressWarnings("unchecked")
	public static <T> Map<String,T> json2Map(String json) {
        return (Map<String,T>)JSON.parse(json);
    }


    public static Object jsonObj2Obj(JSONObject jsonObject){
        if (jsonObject!=null){
            return JSONObject.parse(jsonObject.toJSONString());
        }else {
            return null;
        }
    }
    
}
