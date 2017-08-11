package com.mario_android_sdk.vendors.http.jobs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.mario_android_sdk.constant.Constant;
import com.mario_android_sdk.utilities.NetworkUtility;
import com.mario_android_sdk.vendors.http.events.NetworkEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by MeePwn on 2017/8/11.
 */

@SuppressWarnings("unchecked")
public class NetworkJob extends Job {

    private String mUrl;
    private String mParamsString;

    public NetworkJob(int priority, String url, String paramsString) {
        super(new Params(priority).requireNetwork().persist().groupBy("network"));
        mUrl = url;
        mParamsString = paramsString;
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        NetworkUtility
                .asyncFlow(mUrl, mParamsString)
                .subscribe(response -> EventBus.getDefault().post(new NetworkEvent(mUrl, Constant.INSTANCE.getRESPONSE_SUCCESS(), (String) response)),
                        error -> EventBus.getDefault().post(new NetworkEvent(mUrl, Constant.INSTANCE.getRESPONSE_FAILURE(), (String) error)));
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        // An error occurred in onRun.
        // Return value determines whether this job should retry or cancel. You can further
        // specify a backoff strategy or change the job's priority. You can also apply the
        // delay to the whole group to preserve jobs' running order.
        return RetryConstraint.createExponentialBackoff(runCount, 1000);
    }

}
