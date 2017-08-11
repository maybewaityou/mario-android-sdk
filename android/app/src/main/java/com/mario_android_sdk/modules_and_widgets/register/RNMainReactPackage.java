package com.mario_android_sdk.modules_and_widgets.register;

import com.facebook.react.animated.NativeAnimatedModule;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.modules.camera.CameraRollManager;
import com.facebook.react.modules.camera.ImageEditingManager;
import com.facebook.react.modules.camera.ImageStoreManager;
import com.facebook.react.modules.clipboard.ClipboardModule;
import com.facebook.react.modules.datepicker.DatePickerDialogModule;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.modules.i18nmanager.I18nManagerModule;
import com.facebook.react.modules.image.ImageLoaderModule;
import com.facebook.react.modules.intent.IntentModule;
import com.facebook.react.modules.location.LocationModule;
import com.facebook.react.modules.netinfo.NetInfoModule;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.modules.permissions.PermissionsModule;
import com.facebook.react.modules.share.ShareModule;
import com.facebook.react.modules.statusbar.StatusBarModule;
import com.facebook.react.modules.storage.AsyncStorageModule;
import com.facebook.react.modules.timepicker.TimePickerDialogModule;
import com.facebook.react.modules.toast.ToastModule;
import com.facebook.react.modules.vibration.VibrationModule;
import com.facebook.react.modules.websocket.WebSocketModule;
import com.facebook.react.shell.MainReactPackage;
import com.mario_android_sdk.modules_and_widgets.modules.CustomFrescoModule;

import java.util.Arrays;
import java.util.List;

/**
 * Created by MeePwn on 2017/8/11.
 */

public class RNMainReactPackage extends MainReactPackage {

    @Override
    public List<ModuleSpec> getNativeModules(ReactApplicationContext context) {
        return Arrays.asList(
                new ModuleSpec(AppStateModule.class, () -> new AppStateModule(context)),
                new ModuleSpec(AsyncStorageModule.class, () -> new AsyncStorageModule(context)),
                new ModuleSpec(CameraRollManager.class, () -> new CameraRollManager(context)),
                new ModuleSpec(ClipboardModule.class, () -> new ClipboardModule(context)),
                new ModuleSpec(DatePickerDialogModule.class, () -> new DatePickerDialogModule(context)),
                new ModuleSpec(DialogModule.class, () -> new DialogModule(context)),
                new ModuleSpec(CustomFrescoModule.class, () -> new CustomFrescoModule(context)),
                new ModuleSpec(I18nManagerModule.class, () -> new I18nManagerModule(context)),
                new ModuleSpec(ImageEditingManager.class, () -> new ImageEditingManager(context)),
                new ModuleSpec(ImageLoaderModule.class, () -> new ImageLoaderModule(context)),
                new ModuleSpec(ImageStoreManager.class, () -> new ImageStoreManager(context)),
                new ModuleSpec(IntentModule.class, () -> new IntentModule(context)),
                new ModuleSpec(LocationModule.class, () -> new LocationModule(context)),
                new ModuleSpec(NativeAnimatedModule.class, () -> new NativeAnimatedModule(context)),
                new ModuleSpec(NetworkingModule.class, () -> new NetworkingModule(context)),
                new ModuleSpec(NetInfoModule.class, () -> new NetInfoModule(context)),
                new ModuleSpec(PermissionsModule.class, () -> new PermissionsModule(context)),
                new ModuleSpec(ShareModule.class, () -> new ShareModule(context)),
                new ModuleSpec(StatusBarModule.class, () -> new StatusBarModule(context)),
                new ModuleSpec(TimePickerDialogModule.class, () -> new TimePickerDialogModule(context)),
                new ModuleSpec(ToastModule.class, () -> new ToastModule(context)),
                new ModuleSpec(VibrationModule.class, () -> new VibrationModule(context)),
                new ModuleSpec(WebSocketModule.class, () -> new WebSocketModule(context)));
    }

}
