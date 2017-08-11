package com.mario_android_sdk.vendors.http.request;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mario_android_sdk.application.FrameworkApplication;
import com.mario_android_sdk.constant.Constant;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by MeePwn on 2017/8/11.
 */

public class JSONRequest extends JsonObjectRequest {

    public JSONRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
        int maxNumRetries = -1;
        if (Constant.INSTANCE.getRETRYABLE()) {
            maxNumRetries = Constant.INSTANCE.getMAX_NUM_RETRIES();
        }
        setRetryPolicy(new DefaultRetryPolicy(Constant.INSTANCE.getDEFAULT_NETWORK_TIMEOUT(), maxNumRetries, 1.0f));
    }

    public JSONRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
        int maxNumRetries = -1;
        if (Constant.INSTANCE.getRETRYABLE()) {
            maxNumRetries = Constant.INSTANCE.getMAX_NUM_RETRIES();
        }
        setRetryPolicy(new DefaultRetryPolicy(Constant.INSTANCE.getDEFAULT_NETWORK_TIMEOUT(), maxNumRetries, 1.0f));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        FrameworkApplication.getRequestHeader().put("Content-Type", "application/json");
//        JLog.d("== header ===>>>> " + new JSONObject(FrameworkApplication.getRequestHeader()));
        return FrameworkApplication.getRequestHeader();
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        return super.parseNetworkResponse(response);
    }

}
