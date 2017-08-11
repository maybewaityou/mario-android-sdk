package com.mario_android_sdk.utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import com.mario_android_sdk.application.FrameworkApplication;
import com.mario_android_sdk.utilities.network.connectionclass.ConnectionClassManager;
import com.mario_android_sdk.utilities.network.connectionclass.ConnectionQuality;
import com.mario_android_sdk.utilities.network.connectionclass.DeviceBandwidthSampler;
import com.mario_android_sdk.vendors.http.network.NetworkFailureResult;
import com.mario_android_sdk.vendors.http.network.NetworkSuccessResult;
import com.mario_android_sdk.vendors.http.request.JSONRequest;
import com.mario_android_sdk.vendors.log.JLog;
import com.squareup.phrase.Phrase;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import io.reactivex.Observable;

/**
 * Created by MeePwn on 2017/8/10.
 */

public class NetworkUtility {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private static ConnectionClassManager mConnectionClassManager;
    private static DeviceBandwidthSampler mDeviceBandwidthSampler;
    private static ConnectionChangedListener mListener;
    private static ConnectionQuality mConnectionClass;

    /**
     * Listener to update the UI upon connectionclass change.
     */
    private static class ConnectionChangedListener implements ConnectionClassManager.ConnectionClassStateChangeListener {

        @Override
        public void onBandwidthStateChange(ConnectionQuality bandwidthState) {
            mConnectionClass = bandwidthState;
            JLog.INSTANCE.d("=== mConnectionClass ===>>>>> " + mConnectionClass);
//            Toast.makeText(mContext, "" + mConnectionClass, Toast.LENGTH_SHORT).show();
        }
    }

    public static void init(Context context) {
        mContext = context;

        mConnectionClassManager = ConnectionClassManager.getInstance();
        mDeviceBandwidthSampler = DeviceBandwidthSampler.getInstance();
        mConnectionClass = ConnectionClassManager.getInstance().getCurrentBandwidthQuality();
        mListener = new ConnectionChangedListener();

        mConnectionClassManager.register(mListener);

    }

    /**
     * 网络请求: Rx形式
     */
    public static Observable asyncObserve(String url, String paramsString) {
        return Observable.create(emitter -> asyncRequest(url, paramsString, emitter::onNext, emitter::onError));
    }

    /**
     * 网络请求: Callback形式
     */
    private static void asyncRequest(String url, String paramsString, NetworkSuccessResult successResult, NetworkFailureResult failureResult) {
        mDeviceBandwidthSampler.startSampling();
        try {
            JSONObject params = new JSONObject(paramsString);
            JLog.INSTANCE.d(Phrase.from("=== {url} ====>>>>> {params}").put("url", url).put("params", params.toString()).format().toString());
            FrameworkApplication.getInstance()
                    .getRequestQueue()
                    .add(new JSONRequest(url, "{}".equals(params.toString()) ? null : params,
                            response -> {
                                mDeviceBandwidthSampler.stopSampling();
                                // 组装成功返回数据
                                JLog.INSTANCE.d(Phrase.from("=== success === url ====>>>>> {url}").put("url", url).format().toString());
                                JLog.INSTANCE.json(Phrase.from("{response}").put("response", response.toString()).format().toString());
                                successResult.onSuccess(response.toString());
                            },
                            error -> {
                                mDeviceBandwidthSampler.stopSampling();
                                JLog.INSTANCE.d(Phrase.from("=== error === {url} ====>>>>> {error}").put("url", url).put("error", error.toString()).format().toString());
                                failureResult.onFailure(error);
                            }));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String networkStatus() {
        return ConnectionClassManager.getInstance().getCurrentBandwidthQuality().toString();
    }

    public static boolean isNetworkConnected() {
        if (mContext != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isWifiEnabled() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        return ((connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED)
                || telephonyManager.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

    public static boolean isWifi() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        return networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static boolean is3G() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        return networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    /**
     * 获取 IP 地址
     */
    public static String fetchIPAddress() {
        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return hostIp;
    }

    /**
     * 获取 Mac 地址
     */
    public static String fetchMacAddress() {
        WifiManager wifi = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

}
