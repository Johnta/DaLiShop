package com.dali.dalishop;

import android.os.Bundle;

import com.zhy.autolayout.AutoLayoutActivity;

public abstract class BaseActivity extends AutoLayoutActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        init();
    }

    protected abstract void init();

    protected abstract int getLayoutId();
}
