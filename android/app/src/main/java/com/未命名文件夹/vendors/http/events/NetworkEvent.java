package com.mario_android_sdk.vendors.http.events;

import com.android.volley.VolleyError;

/**
 * Created by MeePwn on 2017/8/11.
 */

public class NetworkEvent {

    private String url;
    private String responseType;
    private String response;
    private VolleyError error;

    public NetworkEvent(String url, String responseType, String response) {
        this.url = url;
        this.responseType = responseType;
        this.response = response;
    }

    public NetworkEvent(String url, String responseType, VolleyError error) {
        this.url = url;
        this.responseType = responseType;
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public VolleyError getError() {
        return error;
    }

    public void setError(VolleyError error) {
        this.error = error;
    }
}
