package com.mario_android_sdk.vendors.http.network;

import java.io.Serializable;

/**
 * Created by MeePwn on 2017/8/11.
 */

public interface NetworkSuccessResult extends Serializable {

    public void onSuccess(String responseString);

}
