package com.mario_android_sdk.base;

import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.mario_android_sdk.initialize.UIInitialize;

/**
 * Created by MeePwn on 2017/8/11.
 */

public abstract class UIActivity extends ReactActivity implements UIInitialize {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView();
        super.onCreate(savedInstanceState);

        initialize();
        initData();
        initViews();
        setupViews();
    }

    @Override
    public void initialize() {

//        /* 注意!! 此做法会导致Activity内存泄漏 start */
//        // 初始化 Dialog 管理类
//        InfoDialogManager.init(this);
//        // 初始化 ProgressBarDialog 管理类
//        ProgressBarDialogManager.init(this);
//        // 初始化 FunctionDialog 管理类
//        FunctionalDialogManager.init(this);
//        // 初始化 CAUtility 工具类
//        CAUtility.init(this);
//        /* 注意!! 此做法会导致Activity内存泄漏 end */

    }

}
