package com.mario_android_sdk.modules_and_widgets.modules;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;

import com.facebook.cache.common.CacheKey;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.AndroidPredicates;
import com.facebook.common.soloader.SoLoaderShim;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.modules.common.ModuleDataCleaner;
import com.facebook.react.modules.fresco.SystraceRequestListener;
import com.facebook.react.modules.network.OkHttpClientProvider;
import com.facebook.soloader.SoLoader;

import java.util.HashSet;

import okhttp3.OkHttpClient;

/**
 * Created by MeePwn on 2017/8/11.
 */

public class CustomFrescoModule extends ReactContextBaseJavaModule implements ModuleDataCleaner.Cleanable {

    @Nullable
    private ImagePipelineConfig mConfig;

    public CustomFrescoModule(ReactApplicationContext reactContext) {
        this(reactContext, getDefaultConfig(reactContext, null, null));
    }

    public CustomFrescoModule(ReactApplicationContext reactContext, RequestListener listener) {
        this(reactContext, getDefaultConfig(reactContext, listener, null));
    }

    public CustomFrescoModule(ReactApplicationContext reactContext, RequestListener listener, DiskCacheConfig diskCacheConfig) {
        this(reactContext, getDefaultConfig(reactContext, listener, diskCacheConfig));
    }

    public CustomFrescoModule(ReactApplicationContext reactContext, ImagePipelineConfig config) {
        super(reactContext);
        mConfig = config;
    }

    @Override
    public void initialize() {
        super.initialize();
        // Make sure the SoLoaderShim is configured to use our loader for native libraries.
        // This code can be removed if using Fresco from Maven rather than from source
        SoLoaderShim.setHandler(new CustomFrescoModule.FrescoHandler());

        Context context = getReactApplicationContext().getApplicationContext();
        Fresco.initialize(context, mConfig);
        mConfig = null;
    }

    @Override
    public String getName() {
        return "FrescoModule";
    }

    @Override
    public void clearSensitiveData() {
        // Clear image cache.
        ImagePipelineFactory imagePipelineFactory = Fresco.getImagePipelineFactory();
        imagePipelineFactory.getBitmapMemoryCache().removeAll(AndroidPredicates.<CacheKey>True());
        imagePipelineFactory.getEncodedMemoryCache().removeAll(AndroidPredicates.<CacheKey>True());
    }

    private static ImagePipelineConfig getDefaultConfig(
            Context context,
            @Nullable RequestListener listener,
            @Nullable DiskCacheConfig diskCacheConfig) {
        HashSet<RequestListener> requestListeners = new HashSet<>();
        requestListeners.add(new SystraceRequestListener());
        if (listener != null) {
            requestListeners.add(listener);
        }

        OkHttpClient okHttpClient = OkHttpClientProvider.getOkHttpClient();
        ImagePipelineConfig.Builder builder =
                OkHttpImagePipelineConfigFactory.newBuilder(context.getApplicationContext(), okHttpClient);

        builder
                .setDownsampleEnabled(false)
                .setRequestListeners(requestListeners);

        if (diskCacheConfig != null) {
            builder.setMainDiskCacheConfig(diskCacheConfig);
        }

        final int maxCacheSize = getMaxCacheSize(context);
        builder.setBitmapMemoryCacheParamsSupplier(() -> new MemoryCacheParams(maxCacheSize, 100, 0, Integer.MAX_VALUE, Integer.MAX_VALUE));

        return builder.build();
    }

    private static int getMaxCacheSize(Context context) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final int maxMemory = Math.min(activityManager.getMemoryClass() * ByteConstants.MB, Integer.MAX_VALUE);
        if (maxMemory < 32 * ByteConstants.MB) {
            return 4 * ByteConstants.MB;
        } else if (maxMemory < 64 * ByteConstants.MB) {
            return 6 * ByteConstants.MB;
        } else {
            // We don't want to use more ashmem on Gingerbread for now, since it doesn't respond well to
            // native memory pressure (doesn't throw exceptions, crashes app, crashes phone)
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                return 8 * ByteConstants.MB;
            } else {
                return maxMemory / 4;
            }
        }
    }

    private static class FrescoHandler implements SoLoaderShim.Handler {
        @Override
        public void loadLibrary(String libraryName) {
            SoLoader.loadLibrary(libraryName);
        }
    }

}
