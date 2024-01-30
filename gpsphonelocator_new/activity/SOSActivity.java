package com.app.gpsphonelocator_new.activity;


import static com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.app.gpsphonelocator_new.R;

import com.app.gpsphonelocator_new.databinding.ActivitySosBinding;
import com.app.gpsphonelocator_new.phone.util.AppAuthen;
import com.app.gpsphonelocator_new.common.ExtensionKt;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;


public final class SOSActivity extends BaseActivity<ActivitySosBinding> {

    public String address = "";
    private boolean isCurrentSOS;

   public int LOCATION_PERMISSION_REQUEST_CODE=101;

    private FusedLocationProviderClient fusedLocationClient;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initView();


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

    }


    public void onResume() {
        super.onResume();
        ((ActivitySosBinding) getMBinding()).layoutAddFriend.setVisibility(View.VISIBLE);
        getLastLocation();
    }


    private final void initView() {
        ImageView imageView = ((ActivitySosBinding) getMBinding()).imgBack;
        Intrinsics.checkNotNullExpressionValue(imageView, "imgBack");
        ExtensionKt.setOnSingleClickListener(imageView, new Function1<View, Unit>() {
            @Override
            public Unit invoke(View view) {
                onBackPressed();
                return Unit.INSTANCE;
            }
        });
        ((ActivitySosBinding) getMBinding()).btnSos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intrinsics.checkNotNullParameter(this, "this$0");
                getLastLocation();
            }
        });
    }

    private void getLastLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(location -> {

                        if (location != null){
                            try {
                                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                ((ActivitySosBinding) getMBinding()).txtAddress.setText("Address: "+addresses.get(0).getAddressLine(0));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    });
        }else {
            askPermission();
        }
    }

    private void askPermission() {

        ActivityCompat.requestPermissions(SOSActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {

        if (requestCode == RESULT_OK){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else {
                Toast.makeText(SOSActivity.this,"Please provide the required permission",Toast.LENGTH_SHORT).show();

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public ActivitySosBinding getViewBinding() {
        ActivitySosBinding inflate = ActivitySosBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        return inflate;
    }

    public void showLoading() {
        super.showLoading();
        FrameLayout frameLayout = ((ActivitySosBinding) getMBinding()).frLoading;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "mBinding.frLoading");
        frameLayout.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        super.hideLoading();
        FrameLayout frameLayout = ((ActivitySosBinding) getMBinding()).frLoading;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "mBinding.frLoading");
        frameLayout.setVisibility(View.GONE);
    }

}