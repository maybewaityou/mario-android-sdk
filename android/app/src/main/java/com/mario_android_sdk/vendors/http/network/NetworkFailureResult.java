package com.mario_android_sdk.vendors.http.network;

import com.android.volley.VolleyError;

import java.io.Serializable;

/**
 * Created by MeePwn on 2017/8/11.
 */

public interface NetworkFailureResult extends Serializable {

    public void onFailure(VolleyError error);

}
