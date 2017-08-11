package com.mario_android_sdk.initialize;

/**
 * Created by MeePwn on 2017/8/11.
 */

public interface UIInitialize extends Initialize {

    /**
     * 设置 activity 布局
     */
    public void setContentView();

    /**
     * 初始化数据
     */
    public void initData();

    /**
     * 初始化 View
     */
    public void initViews();

    /**
     * 设置 View 相关信息
     */
    public void setupViews();

}
