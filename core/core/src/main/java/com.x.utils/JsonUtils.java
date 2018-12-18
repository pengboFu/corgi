package com.x.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by fpb on 2018/12/18.
 */
public class JsonUtils {

    /**
     * 将Json字符串信息转换成对应的Java对象
     *
     * @param json json字符串对象
     * @param c    对应的类型
     */
    public static <T> T parseJsonToObj(String json, Class<T> c) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            return JSON.toJavaObject(jsonObject, c);
        } catch (Exception e) {
            Utils.getServiceException(e.getMessage());
        }
        return null;
    }

    /**
     * 将json数组转换为对应的集合
     * @param response
     * @param object
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(String response,Class<T> object){
        List<T> modelList = JSON.parseArray(response, object);
        return modelList;
    }

}
