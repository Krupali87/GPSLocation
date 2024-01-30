package com.app.gpsphonelocator_new.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

import com.app.gpsphonelocator_new.R;
import com.app.gpsphonelocator_new.TrafficFinder.StreetviewActivity;
import com.app.gpsphonelocator_new.TrafficFinder.TrafficFinderClass;
import com.app.gpsphonelocator_new.databinding.ActivityHomeOptionBinding;
import com.app.gpsphonelocator_new.extensions.AppExtensionKt;
import com.app.gpsphonelocator_new.phone.util.AppAuthen;
import com.app.gpsphonelocator_new.phone.util.AppUserSingleton;
import com.app.gpsphonelocator_new.phone.util.Constants;
import com.bumptech.glide.Glide;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;


public final class HomeOptionActivity extends BaseActivity<ActivityHomeOptionBinding> {

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        onWindowFocusChanged(true);
        AppAuthen.getInstance().appContext = getApplicationContext();
        AppUserSingleton.getInstance().appContext = getApplicationContext();
        bindEvent();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public ActivityHomeOptionBinding getViewBinding() {
         ActivityHomeOptionBinding inflate = ActivityHomeOptionBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        return inflate;
    }

    public void onResume() {
        super.onResume();
     setAvatar();

    }


    private  void setAvatar() {
        Glide.with((FragmentActivity) this).load(AppUserSingleton.getInstance().getAvt()).into((ImageView) ((ActivityHomeOptionBinding) Objects.requireNonNull(getMBinding())).ivAvatar);
    }
    private  void bindEvent() {
        ((ActivityHomeOptionBinding) getMBinding()).cvRealTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intrinsics.checkNotNullParameter(this, "this$0");
                AppExtensionKt.showActivity$default(HomeOptionActivity.this, MainActivity.class, (Bundle) null, 4, (Object) null);
            }
        });
        ((ActivityHomeOptionBinding) getMBinding()).cvListUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intrinsics.checkNotNullParameter(this, "this$0");
                if (!isNetworkAvailable()) {
                    new HomeOptionActivity();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("message", Constants.from_home);
                AppExtensionKt.showActivity(HomeOptionActivity.this, TrackingUserListActivity.class, bundle);
            }
        });
        ((ActivityHomeOptionBinding) getMBinding()).cvMySharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intrinsics.checkNotNullParameter(this, "this$0");
                if (!isNetworkAvailable()) {
                    new HomeOptionActivity();
                    return;
                }
               startActivity(new Intent(HomeOptionActivity.this, ShareMyLocationActivity.class));
            }
        });
        ((ActivityHomeOptionBinding) getMBinding()).cvZoneAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intrinsics.checkNotNullParameter(this, "this$0");
                if (!isNetworkAvailable()) {
                    new HomeOptionActivity();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("message", Constants.zone_alert_from_home);
                AppExtensionKt.showActivity(HomeOptionActivity.this, ZoneAlertActivity.class, bundle);
            }
        });
        ((ActivityHomeOptionBinding) getMBinding()).cvSos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intrinsics.checkNotNullParameter(this, "this$0");
                if (!isNetworkAvailable()) {
                    new HomeOptionActivity();
                    return;
                }
                AppExtensionKt.showActivity$default(HomeOptionActivity.this, SOSActivity.class, (Bundle) null, 4, (Object) null);
            }
        });
        ((ActivityHomeOptionBinding) getMBinding()).cvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intrinsics.checkNotNullParameter(this, "this$0");
                AppExtensionKt.showActivity$default(HomeOptionActivity.this, SettingActivity.class, (Bundle) null, 4, (Object) null);
            }
        });
        ((ActivityHomeOptionBinding) getMBinding()).cvstreetview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intrinsics.checkNotNullParameter(this, "this$0");
                AppExtensionKt.showActivity$default(HomeOptionActivity.this, StreetviewActivity.class, (Bundle) null, 4, (Object) null);
            }
        });
        ((ActivityHomeOptionBinding) getMBinding()).cvtraffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intrinsics.checkNotNullParameter(this, "this$0");
                AppExtensionKt.showActivity$default(HomeOptionActivity.this, TrafficFinderClass.class, (Bundle) null, 4, (Object) null);
            }
        });
        ((ActivityHomeOptionBinding) getMBinding()).cvDeviceInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intrinsics.checkNotNullParameter(this, "this$0");
                AppExtensionKt.showActivity$default(HomeOptionActivity.this, DeviceInfoActivity.class, (Bundle) null, 4, (Object) null);
            }
        });
        ((ActivityHomeOptionBinding) getMBinding()).cvGpsInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intrinsics.checkNotNullParameter(this, "this$0");
                AppExtensionKt.showActivity$default(HomeOptionActivity.this, GetLocationActivity.class, (Bundle) null, 4, (Object) null);
            }
        });


    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeOptionActivity.this);
        builder.setTitle("Comfirm Exit..!!");
        builder.setMessage("Do you want to exit this app?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}


