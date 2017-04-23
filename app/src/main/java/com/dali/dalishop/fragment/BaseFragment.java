package com.dali.dalishop.fragment;


import android.content.Context;
import android.util.AttributeSet;

import com.zhy.autolayout.AutoFrameLayout;


public class BaseFragment extends AutoFrameLayout {

    public BaseFragment(Context context) {
        super(context);
    }

    public BaseFragment(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseFragment(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BaseFragment(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
