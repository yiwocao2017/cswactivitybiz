package com.cdkj.ride.proxy;

import com.cdkj.ride.exception.ParaException;
import com.cdkj.ride.http.JsonValidator;
import com.google.gson.Gson;

public class JsonUtil {

    public static <T> T json2Bean(String json, Class<T> clazz) {
        boolean isJson = new JsonValidator().validate(json);
        if (!isJson) {
            throw new ParaException("830xxx", "json格式不正确");
        }
        T t = null;
        try {
            Gson gson = new Gson();
            t = (T) gson.fromJson(json, clazz);
        } catch (Exception e) {
            throw new ParaException("830xxx", "json2Bean不正确:" + e.getMessage());
        }
        return t;
    }

    public static String Object2Json(Object bean) {
        Gson gson = new Gson();
        return gson.toJson(bean);

    }

}
