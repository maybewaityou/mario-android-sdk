package com.mario_android_sdk.modules_and_widgets.modules;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.mario_android_sdk.vendors.log.JLog;

/**
 * Created by MeePwn on 2017/8/14.
 */

public class LogModule extends ReactContextBaseJavaModule {

    public LogModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "LogModule";
    }

    @ReactMethod
    public void d(String info) {
        JLog.INSTANCE.d(info);
    }

    @ReactMethod
    public void i(String info) {
        JLog.INSTANCE.i(info);
    }

    @ReactMethod
    public void v(String info) {
        JLog.INSTANCE.v(info);
    }

    @ReactMethod
    public void w(String info) {
        JLog.INSTANCE.w(info);
    }

    @ReactMethod
    public void wtf(String info) {
        JLog.INSTANCE.wtf(info);
    }

    @ReactMethod
    public void e(String error) {
        JLog.INSTANCE.e(error);
    }

    @ReactMethod
    public void json(String json) {
        JLog.INSTANCE.json(json);
    }

    @ReactMethod
    public void xml(String xml) {
        JLog.INSTANCE.xml(xml);
    }

}
