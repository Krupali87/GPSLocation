package com.app.gpsphonelocator_new.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.gpsphonelocator_new.R;
import com.app.gpsphonelocator_new.databinding.ActivityDeviceInfoBinding;
import kotlin.jvm.internal.Intrinsics;

public class DeviceInfoActivity extends BaseActivity<ActivityDeviceInfoBinding> {

    ImageView imgback;
    private TextView textViewSetInformation;


    @Override
    public ActivityDeviceInfoBinding getViewBinding() {
        ActivityDeviceInfoBinding inflate = ActivityDeviceInfoBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        return inflate;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        imgback= findViewById(R.id.img_back);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        textViewSetInformation = (TextView) findViewById(R.id.textViewSetInformation);
        String information = getHardwareAndSoftwareInfo();
        textViewSetInformation.setText(information);

    }


    private String getHardwareAndSoftwareInfo() {

        return getString(R.string.serial) + " " + Build.SERIAL + "\n" +
                getString(R.string.model) + " " + Build.MODEL + "\n" +
                getString(R.string.id) + " " + Build.ID + "\n" +
                getString(R.string.manufacturer) + " " + Build.MANUFACTURER + "\n" +
                getString(R.string.brand) + " " + Build.BRAND + "\n" +
                getString(R.string.type) + " " + Build.TYPE + "\n" +
                getString(R.string.user) + " " + Build.USER + "\n" +
                getString(R.string.base) + " " + Build.VERSION_CODES.BASE + "\n" +
                getString(R.string.incremental) + " " + Build.VERSION.INCREMENTAL + "\n" +
                getString(R.string.sdk) + " " + Build.VERSION.SDK + "\n" +
                getString(R.string.board) + " " + Build.BOARD + "\n" +
                getString(R.string.host) + " " + Build.HOST + "\n" +
                getString(R.string.fingerprint) + " " + Build.FINGERPRINT + "\n" +
                getString(R.string.versioncode) + " " + Build.VERSION.RELEASE;
    }
}