package com.dali.dalishop.http;

import android.content.Context;

import dmax.dialog.SpotsDialog;

/**
 * Created by dali on 2017/3/2. 10:33
 */
public abstract class SpotCallback<T> extends BaseCallBack{

    private SpotsDialog mDialog;
    private Context mContext;

    public SpotCallback(Context context){
        this.mContext = context;
        mDialog = new SpotsDialog(context,"正在拼命加载...");
    }

    public void showDialog(){
        mDialog.show();
    }

    public void dismissDialog(){
        if (mDialog!=null && mDialog.isShowing())
            mDialog.dismiss();
    }



}
