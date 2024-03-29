package com.app.gpsphonelocator_new.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.app.gpsphonelocator_new.other.MyApp;
import com.app.gpsphonelocator_new.databinding.ActivitySplashBinding;
import com.app.gpsphonelocator_new.extensions.AppExtensionKt;
import com.app.gpsphonelocator_new.other.RTDB_DEFINE;
import com.app.gpsphonelocator_new.phone.service.GPSLocationService;
import com.app.gpsphonelocator_new.phone.util.AppAuthen;
import com.app.gpsphonelocator_new.phone.util.AppUserSingleton;
import com.app.gpsphonelocator_new.phone.util.SharedPref;
import com.app.gpsphonelocator_new.common.Common;

import kotlin.jvm.internal.Intrinsics;

@SuppressLint("CustomSplashScreen")
public final class SplashActivity extends BaseActivity<ActivitySplashBinding> {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        AppAuthen.getInstance().loginUser(getApplicationContext());
        initializeContext();
        AppAuthen.getInstance().loginUser(getApplicationContext());
        MyApp.Companion.increaseCountOpenApp(getApplicationContext());
        SharedPref.init(getApplicationContext());
        getAppSetting();

    }

    private void initializeContext() {
        AppAuthen.getInstance().appContext = getApplicationContext();
        AppUserSingleton.getInstance().appContext = getApplicationContext();
    }



    public ActivitySplashBinding getViewBinding() {
        ActivitySplashBinding inflate = ActivitySplashBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        return inflate;
    }


    public  void runFirst() {
        AppExtensionKt.showActivity$default(this, PermissionActivity.class, (Bundle) null, 4, (Object) null);
        finish();
    }

    private final void getAppSetting() {
        Common.Companion.setAppRunBackground(RTDB_DEFINE.APPSETTING_RUN_BACKGROUND);
        Common.Companion.setMIN_TIME_GPS((long) RTDB_DEFINE.APPSETTING_MIN_TIME_GPS);
        Common.Companion.setMIN_DISTANCE_GPS((float) RTDB_DEFINE.APPSETTING_MIN_DISTANCE_GPS);
        Common.Companion.setZONE_RADIUS(RTDB_DEFINE.APPSETTING_ZONE_RADIUS);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity splashActivity = new SplashActivity();
                runFirst();
                splashActivity.startLocationService();
            }
        }, 5000);
    }



    private final void startLocationService() {
        Context context = null;
        if (context != null) {
            boolean z = ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0;
            boolean loginUser = AppAuthen.getInstance().loginUser(context);
            if (z && loginUser) {
                if (Build.VERSION.SDK_INT >= 26) {
                    startForegroundService(new Intent(context, GPSLocationService.class));
                } else {
                    startService(new Intent(context, GPSLocationService.class));
                    Log.e("SplashActivity", "Context is null in startLocationService()");
                }
            }
        }
    }
}
