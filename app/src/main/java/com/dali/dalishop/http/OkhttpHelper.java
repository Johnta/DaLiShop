package com.dali.dalishop.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkhttpHelper {

    private static OkHttpClient okHttpClient;

    private Handler handler;

    private Gson gson;

    private OkhttpHelper(){
        okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(10,TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(10, TimeUnit.SECONDS);

        gson = new Gson();
        handler = new Handler(Looper.myLooper());
    }

    public static OkhttpHelper getInstanse(){
        return new OkhttpHelper();
    }


    public void doGet(String url,BaseCallBack callBack){
        Request request = buildRequest(url,HttpMethodType.GET,null);
        doRequest(request,callBack);
    }

    public void doPost(String url,Map<String,String> params,BaseCallBack callBack){
        Request request = buildRequest(url,HttpMethodType.GET,params);
        doRequest(request,callBack);
    }

    private Request buildRequest(String url,HttpMethodType type,Map<String,String> params) {

        Request.Builder builder = new Request.Builder().url(url);

        if (type == HttpMethodType.GET){
            builder.get();
        }else if (type == HttpMethodType.POST){
            RequestBody body = buildFormData(params);
            builder.post(body);
        }
        return builder.build();
    }

    private RequestBody buildFormData(Map<String, String> params) {

        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (Map.Entry<String,String> entry:params.entrySet()){
            builder.add(entry.getKey(),entry.getValue());
        }
        return builder.build();
    }

    public void doPost(){

    }

    public void doRequest(Request request,BaseCallBack callBack){

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()){

                }else {

                }
            }
        });
    }

    enum HttpMethodType{
        POST,
        GET
    }
}