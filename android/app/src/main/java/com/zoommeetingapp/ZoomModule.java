package com.zoommeetingapp;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

import us.zoom.sdk.ZoomSDK;
import us.zoom.sdk.ZoomSDKInitParams;
import us.zoom.sdk.ZoomSDKInitializeListener;
import us.zoom.sdk.MeetingService;
import us.zoom.sdk.ZoomSDKError;

public class ZoomModule extends ReactContextBaseJavaModule {
    public ZoomModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ZoomModule";
    }

    @ReactMethod
    public void initializeZoom(String appKey, String appSecret, Promise promise) {
        ZoomSDK sdk = ZoomSDK.getInstance();
        ZoomSDKInitParams params = new ZoomSDKInitParams();
        params.appKey = appKey;
        params.appSecret = appSecret;
        params.domain = "zoom.us";

        sdk.initialize(getReactApplicationContext(), new ZoomSDKInitializeListener() {
            @Override
            public void onZoomSDKInitializeResult(int errorCode, int internalErrorCode) {
                if (errorCode == ZoomSDKError.ZOOMSDKERR_SUCCESS) {
                    promise.resolve("Zoom SDK inicializado correctamente");
                } else {
                    promise.reject("INIT_ERROR", "Error al inicializar Zoom SDK");
                }
            }

            @Override
            public void onZoomAuthIdentityExpired() {}
        }, params);
    }
}
