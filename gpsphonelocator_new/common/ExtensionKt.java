package com.app.gpsphonelocator_new.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.PowerManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;

import com.app.gpsphonelocator_new.R;
import com.app.gpsphonelocator_new.phone.util.SafeOnClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.google.android.gms.maps.model.LatLng;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007\u001a\u0014\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0004\u001a\n\u0010\f\u001a\u00020\u0001*\u00020\r\u001a\u001c\u0010\u000e\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u001a\n\u0010\u0013\u001a\u00020\u0014*\u00020\u0002\u001a\u0014\u0010\u0015\u001a\u00020\u0001*\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004\u001a(\u0010\u0018\u001a\u00020\u0001*\u00020\u00192\u001a\b\u0002\u0010\u001a\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u001bH\u0007\u001a\u0014\u0010\u0018\u001a\u00020\u0001*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001cH\u0007\u001a$\u0010\u001d\u001a\u00020\u0001*\u00020\u00192\u0018\b\u0002\u0010\u001a\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001b\u001a\u001e\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00010\u001b\u001a.\u0010!\u001a\u00020\u0001*\u00020\u00022\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010#2\u0010\b\u0002\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010#\u001a\u0012\u0010%\u001a\u00020\u0001*\u00020\r2\u0006\u0010&\u001a\u00020\u0019\u001a\u0012\u0010'\u001a\u00020\u0001*\u00020\u00022\u0006\u0010(\u001a\u00020\u0004\u001a\n\u0010)\u001a\u00020\u0004*\u00020*\u001a\n\u0010+\u001a\u00020\u0004*\u00020*\u001a\n\u0010,\u001a\u00020\u0006*\u00020\u0004¨\u0006-"}, d2 = {"copyText", "", "Landroid/content/Context;", "text", "", "distance", "", "Lcom/google/android/gms/maps/model/LatLng;", "endP", "getAvatarDrawable", "Landroid/graphics/drawable/Drawable;", "name", "hideKeyboard", "Landroid/app/Activity;", "initLayout", "Landroid/app/Dialog;", "context", "percentWidth", "", "isIgnoringBatteryOptimizations", "", "loadImage", "Landroid/widget/ImageView;", "url", "setOnClickAffect", "Landroid/view/View;", "onClick", "Lkotlin/Function1;", "Landroid/view/View$OnClickListener;", "setOnSingleClickListener", "showDatePicker", "onDatePicked", "Ljava/util/Calendar;", "showDeleteDialog", "onDeleted", "Lkotlin/Function0;", "onCanceled", "showKeyboard", "editText", "showToast", "message", "toDateFormat", "", "toDateTimeFormat", "toDouble", "APP78_NumberLocator_v1.1.0(110)_Nov.27.2023_productionRelease"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Extension.kt */
public final class ExtensionKt {
    public static final void showToast(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, "message");
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

    public static final void copyText(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, "text");
        Object systemService = context.getSystemService(Context.CLIPBOARD_SERVICE);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText("share_code", str));
    }

    public static final boolean isIgnoringBatteryOptimizations(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getApplicationContext().getSystemService(Context.POWER_SERVICE);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        return ((PowerManager) systemService).isIgnoringBatteryOptimizations(context.getApplicationContext().getPackageName());
    }

    public static final void loadImage(ImageView imageView, String str) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        ((RequestBuilder) Glide.with(imageView.getContext()).load(str).placeholder((int) R.drawable.logo)).into(imageView);
    }

    public static /* synthetic */ void setOnClickAffect$default(View view, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = null;
        }
        setOnClickAffect(view, (Function1<? super View, Unit>) function1);
    }

    public static final void setOnClickAffect(View view, Function1<? super View, Unit> function1) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setOnTouchListener(new ExtensionKt$$ExternalSyntheticLambda2());
        view.setOnClickListener(new ExtensionKt$$ExternalSyntheticLambda3(function1));
    }

    /* access modifiers changed from: private */
    public static final boolean setOnClickAffect$lambda$0(View view, MotionEvent motionEvent) {
        Integer valueOf = motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null;
        if (valueOf == null || valueOf.intValue() != 0) {
            boolean z = true;
            if ((valueOf == null || valueOf.intValue() != 3) && (valueOf == null || valueOf.intValue() != 1)) {
                z = false;
            }
            if (z && view != null) {
                view.setAlpha(1.0f);
            }
        } else if (view != null) {
            view.setAlpha(0.5f);
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static final void setOnClickAffect$lambda$1(Function1 function1, View view) {
        if (function1 != null) {
            Unit unit = (Unit) function1.invoke(view);
        }
    }

    public static final void setOnClickAffect(View view, View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(onClickListener, "onClick");
        view.setOnTouchListener(new ExtensionKt$$ExternalSyntheticLambda1());
        view.setOnClickListener(onClickListener);
    }

    /* access modifiers changed from: private */
    public static final boolean setOnClickAffect$lambda$2(View view, MotionEvent motionEvent) {
        Integer valueOf = motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null;
        if (valueOf == null || valueOf.intValue() != 0) {
            boolean z = true;
            if ((valueOf == null || valueOf.intValue() != 3) && (valueOf == null || valueOf.intValue() != 1)) {
                z = false;
            }
            if (z && view != null) {
                view.setAlpha(1.0f);
            }
        } else if (view != null) {
            view.setAlpha(0.5f);
        }
        return false;
    }

    public static /* synthetic */ void initLayout$default(Dialog dialog, Context context, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            f = 0.8f;
        }
        initLayout(dialog, context, f);
    }

    @SuppressLint("ResourceType")
    public static final void initLayout(Dialog dialog, Context context, float f) {
        Intrinsics.checkNotNullParameter(dialog, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(17170445);
        }
        Window window2 = dialog.getWindow();
        Intrinsics.checkNotNull(window2);
        window2.setLayout((int) (((float) context.getResources().getDisplayMetrics().widthPixels) * f), -2);
        dialog.setCancelable(false);
    }

    public static /* synthetic */ void showDeleteDialog$default(Context context, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        if ((i & 2) != 0) {
            function02 = null;
        }
        showDeleteDialog(context, function0, function02);
    }

    public static final void showDeleteDialog(Context context, Function0<Unit> function0, Function0<Unit> function02) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_confirm_delete);
        initLayout$default(dialog, context, 0.0f, 2, (Object) null);
        TextView textView = (TextView) dialog.findViewById(R.id.txtDelete);
        TextView textView2 = (TextView) dialog.findViewById(R.id.txtCancel);
        Intrinsics.checkNotNullExpressionValue(textView, "tvDelete");
        setOnClickAffect((View) textView, (Function1<? super View, Unit>) new ExtensionKt$showDeleteDialog$1(function0, dialog));
        Intrinsics.checkNotNullExpressionValue(textView2, "tvCancel");
        setOnClickAffect((View) textView2, (Function1<? super View, Unit>) new ExtensionKt$showDeleteDialog$2(function02, dialog));
        dialog.show();
    }

    public static final double distance(LatLng latLng, LatLng latLng2) {
        Intrinsics.checkNotNullParameter(latLng, "<this>");
        Intrinsics.checkNotNullParameter(latLng2, "endP");
        double d = latLng.latitude;
        double d2 = latLng2.latitude;
        double d3 = latLng.longitude;
        double d4 = latLng2.longitude;
        double radians = Math.toRadians(d2 - d);
        double radians2 = Math.toRadians(d4 - d3);
        double d5 = (double) 2;
        double d6 = radians / d5;
        double d7 = radians2 / d5;
        double asin = ((double) 6371) * d5 * Math.asin(Math.sqrt((Math.sin(d6) * Math.sin(d6)) + (Math.cos(Math.toRadians(d)) * Math.cos(Math.toRadians(d2)) * Math.sin(d7) * Math.sin(d7))));
        DecimalFormat decimalFormat = new DecimalFormat("####");
        Integer valueOf = Integer.valueOf(decimalFormat.format(asin / ((double) 1)));
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(newFormat.format(km))");
        valueOf.intValue();
        Integer valueOf2 = Integer.valueOf(decimalFormat.format(asin % ((double) 1000)));
        Intrinsics.checkNotNullExpressionValue(valueOf2, "valueOf(newFormat.format(meter))");
        valueOf2.intValue();
        return asin;
    }

    public static final double toDouble(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            return Double.parseDouble(str);
        } catch (Exception unused) {
            return 0.0d;
        }
    }

    public static final String toDateTimeFormat(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm dd/MM/yyyy");
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        String format = simpleDateFormat.format(Long.valueOf(instance.getTimeInMillis()));
        Intrinsics.checkNotNullExpressionValue(format, "formatter.format(calendar.timeInMillis)");
        return format;
    }

    public static final String toDateFormat(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        String format = simpleDateFormat.format(Long.valueOf(instance.getTimeInMillis()));
        Intrinsics.checkNotNullExpressionValue(format, "formatter.format(calendar.timeInMillis)");
        return format;
    }

    public static final void showDatePicker(Context context, Function1<? super Calendar, Unit> function1) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(function1, "onDatePicked");
        Calendar instance = Calendar.getInstance();
        new DatePickerDialog(context, new ExtensionKt$$ExternalSyntheticLambda0(instance, function1), instance.get(1), instance.get(2), instance.get(5)).show();
    }

    /* access modifiers changed from: private */
    public static final void showDatePicker$lambda$3(Calendar calendar, Function1 function1, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(function1, "$onDatePicked");
        calendar.set(1, i);
        calendar.set(2, i2);
        calendar.set(5, i3);
        Intrinsics.checkNotNullExpressionValue(calendar, "myCalendar");
        function1.invoke(calendar);
    }

    public static final Drawable getAvatarDrawable(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        try {
            return ContextCompat.getDrawable(context, context.getResources().getIdentifier(str, "drawable", context.getPackageName()));
        } catch (Exception unused) {
            return context.getDrawable(R.drawable.avatar_1);
        }
    }

    public static /* synthetic */ void setOnSingleClickListener$default(View view, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = null;
        }
        setOnSingleClickListener(view, function1);
    }

    public static final void setOnSingleClickListener(View view, Function1<? super View, Unit> function1) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        SafeOnClickListener safeOnClickListener = new SafeOnClickListener();
        safeOnClickListener.setOnSafeClick(function1);
        setOnClickAffect(view, (View.OnClickListener) safeOnClickListener);
    }

    public static final void hideKeyboard(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Object systemService = activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        InputMethodManager inputMethodManager = (InputMethodManager) systemService;
        View currentFocus = activity.getCurrentFocus();
        inputMethodManager.hideSoftInputFromWindow(currentFocus != null ? currentFocus.getWindowToken() : null, 0);
    }

    public static final void showKeyboard(Activity activity, View view) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(view, "editText");
        Object systemService = activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        InputMethodManager inputMethodManager = systemService instanceof InputMethodManager ? (InputMethodManager) systemService : null;
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(view, 1);
        }
    }
}
