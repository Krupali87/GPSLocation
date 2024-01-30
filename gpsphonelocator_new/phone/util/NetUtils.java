package com.app.gpsphonelocator_new.phone.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;


public final class NetUtils {
    public static final NetUtils INSTANCE = new NetUtils();

    private NetUtils() {
    }

    @JvmStatic
    public static final boolean haveNetworkConnection(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        @SuppressLint("WrongConstant") Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        NetworkInfo[] allNetworkInfo = ((ConnectivityManager) systemService).getAllNetworkInfo();
        Intrinsics.checkNotNullExpressionValue(allNetworkInfo, "cm.allNetworkInfo");
        boolean z = false;
        boolean z2 = false;
        for (NetworkInfo networkInfo : allNetworkInfo) {
            if (StringsKt.equals(networkInfo.getTypeName(), "WIFI", true) && networkInfo.isConnected()) {
                z = true;
            }
            if (StringsKt.equals(networkInfo.getTypeName(), "MOBILE", true) && networkInfo.isConnected()) {
                z2 = true;
            }
        }
        if (z || z2) {
            return true;
        }
        return false;
    }
}
