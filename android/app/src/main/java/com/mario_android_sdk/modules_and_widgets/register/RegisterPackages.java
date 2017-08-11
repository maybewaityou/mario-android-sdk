package com.mario_android_sdk.modules_and_widgets.register;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.mario_android_sdk.application.FrameworkApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MeePwn on 2017/8/11.
 */

public class RegisterPackages implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        FrameworkApplication.setReactApplicationContext(reactContext);
        List<NativeModule> modules = new ArrayList<>();

        return modules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList();
    }

}
