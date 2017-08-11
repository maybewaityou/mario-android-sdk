package com.mario_android_sdk.vendors.http;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.DiskBasedCache;
import com.mario_android_sdk.vendors.http.network.BasicNetwork;
import com.mario_android_sdk.vendors.http.network.OkHttpStack;
import com.mario_android_sdk.vendors.http.network.OkHttpStackImpl;

import java.io.File;

import okhttp3.OkHttpClient;

/**
 * Created by MeePwn on 2017/8/11.
 */

public class Volley {

    public Volley() {
    }

    /**
     * 创建一个客户端的请求队列
     * @param context Context
     * @param stack OkHttpStack
     * @return
     */
    public static RequestQueue newRequestQueue(Context context, OkHttpStack stack) {
        File cacheDir = new File(context.getCacheDir(), "volley"); // 缓存地址：/data/data/com.meepwn.demo/cache/volley
        if(stack == null) { // 如果请求的stack为空就建一个默认的
            stack = new OkHttpStackImpl(new OkHttpClient.Builder().build());
        }
        // 一个自定义的类，实现NetWork接口
        BasicNetwork network = new BasicNetwork(stack);
        RequestQueue queue = new RequestQueue(new DiskBasedCache(cacheDir), network);
        queue.start();
        return queue;
    }

    /**
     * 创建一个指定请求客户端的请求队列
     * @param context 上下文
     * @param client OkHttpClient
     * @return 请求队列
     */
    public static RequestQueue newRequestQueueByClient(Context context, OkHttpClient client) {
        return newRequestQueue(context, new OkHttpStackImpl(client));
    }
    /**
     * 创建一个默认请求客户端的请求队列
     * @param context 上下文
     * @return
     */
    public static RequestQueue newRequestQueue(Context context) {
        return newRequestQueue(context, null);
    }

}
