package com.dali.dalishop.http;

import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseCallBack<T> {

    public Type type;

    /**
     * 将T对象转换成type类型
     * @param subclass
     * @return
     */
    static Type getSuperclassTypeParamter(Class<?> subclass){
        Type superclass = subclass.getGenericSuperclass();

        if (superclass instanceof Class){
            throw new RuntimeException("Missing type paramter.");
        }

        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public BaseCallBack() {
        type = getSuperclassTypeParamter(getClass());
    }

    public abstract void onFailure(Request request, IOException e);

    public abstract void onSuccess(Response response,T t);

    public abstract void onError(Response response,int code,Exception e);

    public abstract void onRequestBefore(Request request);
}
