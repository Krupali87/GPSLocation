package com.app.gpsphonelocator_new.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.work.WorkRequest;


import com.app.gpsphonelocator_new.databinding.DialogConfirmDeleteBinding;
import com.app.gpsphonelocator_new.phone.util.AppAuthen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;


public final class Common {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public static float MIN_DISTANCE_GPS = 200.0f;

    public static long MIN_TIME_GPS = WorkRequest.MIN_BACKOFF_MILLIS;

    public static int ZONE_RADIUS = 600;

    public static boolean checkShare;


    public static boolean isAppRunBackground;

    public static String name = "";

    public static String phone;


    public static String previousAddActivity = "";

    public static List<InforSaved> userlist1 = new ArrayList();



    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final long getMIN_TIME_GPS() {
            return Common.MIN_TIME_GPS;
        }

        public final void setMIN_TIME_GPS(long j) {
            Common.MIN_TIME_GPS = j;
        }

        public final float getMIN_DISTANCE_GPS() {
            return Common.MIN_DISTANCE_GPS;
        }

        public final void setMIN_DISTANCE_GPS(float f) {
            Common.MIN_DISTANCE_GPS = f;
        }

        public final int getZONE_RADIUS() {
            return Common.ZONE_RADIUS;
        }

        public final void setZONE_RADIUS(int i) {
            Common.ZONE_RADIUS = i;
        }

        public final void setAppRunBackground(boolean z) {
            Common.isAppRunBackground = z;
        }

        public final boolean getCheckShare() {
            return Common.checkShare;
        }

        public final void setCheckShare(boolean z) {
            Common.checkShare = z;
        }

        public final void setPreviousAddActivity(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            Common.previousAddActivity = str;
        }

    }
}
