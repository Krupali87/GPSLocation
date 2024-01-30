package com.app.gpsphonelocator_new.extensions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;


public final class AppExtensionKt {
    private static long lastClick;

    public static final void setColorText(TextView textView, int i) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setTextColor(ContextCompat.getColor(textView.getContext(), i));
    }

    public static final void setStyleText(TextView textView, int i) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.getTypeface();
        ResourcesCompat.getFont(textView.getContext(), i);
    }

    public static void showActivity$default(Context context, Class cls, Bundle bundle, int i, Object obj) {
        if ((i & 4) != 0) {
            bundle = null;
        }
        showActivity(context, cls, bundle);
    }

    public static final void showActivity(Context context, Class<?> cls, Bundle bundle) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cls, "activity");
        Intent intent = new Intent(context, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static final void hide(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        try {
            view.setVisibility(View.GONE);
        } catch (Exception unused) {
            view.setVisibility(View.GONE);
        }
    }

    public static final void show(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        try {
            view.setVisibility(View.VISIBLE);
        } catch (Exception unused) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static final void setPref(Context context, String str, String str2) {
        Intrinsics.checkNotNullParameter(context, "c");
        Intrinsics.checkNotNullParameter(str, "pref");
        Intrinsics.checkNotNullParameter(str2, "value");
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static final String getPref(Context context, String str, String str2) {
        Intrinsics.checkNotNullParameter(context, "c");
        Intrinsics.checkNotNullParameter(str, "pref");
        Intrinsics.checkNotNullParameter(str2, "value");
        return PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
    }

    public static final void setPref(Context context, String str, boolean z) {
        Intrinsics.checkNotNullParameter(context, "c");
        Intrinsics.checkNotNullParameter(str, "pref");
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static final boolean getPref(Context context, String str, boolean z) {
        Intrinsics.checkNotNullParameter(context, "c");
        Intrinsics.checkNotNullParameter(str, "pref");
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(str, z);
    }

    public static final void setPref(Context context, String str, int i) {
        Intrinsics.checkNotNullParameter(context, "c");
        Intrinsics.checkNotNullParameter(str, "pref");
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public static final int getPref(Context context, String str, int i) {
        Intrinsics.checkNotNullParameter(context, "c");
        Intrinsics.checkNotNullParameter(str, "pref");
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(str, i);
    }

    public static final <T extends View> void onClickDelay(T t, Function1<? super T, Unit> function1) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        onClick(t, 200, function1);
    }


    public static final <T extends View> void onClick(T t, long j, Function1<? super T, Unit> function1) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intrinsics.checkNotNullParameter(function1, "$block");
                Intrinsics.checkNotNullParameter(view, "$this_onClick");
                if (j <= 0) {
                    //function1.invoke(view);
                } else if (System.currentTimeMillis() - lastClick > j) {
                    lastClick = System.currentTimeMillis();
                    // function1.invoke(view);
                }
            }

        });
    }


}