package com.app.gpsphonelocator_new.activity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.app.gpsphonelocator_new.R;
import com.app.gpsphonelocator_new.databinding.ActivityGetLocationBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;

public class GetLocationActivity extends BaseActivity<ActivityGetLocationBinding> {
    FusedLocationProviderClient fusedLocationProviderClient;
    TextView lattitude,longitude,address,city,country;
    ImageView imgback;
    private final static int REQUEST_CODE = 100;

    @Override
    public ActivityGetLocationBinding getViewBinding() {
        ActivityGetLocationBinding inflate = ActivityGetLocationBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        return inflate;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        lattitude = findViewById(R.id.lattitude);
        longitude = findViewById(R.id.longitude);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);
        imgback = findViewById(R.id.img_back);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        imgback.setOnClickListener(view -> onBackPressed());
        getLastLocation();
    }

    private void getLastLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(location -> {

                        if (location != null){
                            try {
                                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                lattitude.setText("Lattitude: "+addresses.get(0).getLatitude());
                                longitude.setText("Longitude: "+addresses.get(0).getLongitude());
                                address.setText("Address: "+addresses.get(0).getAddressLine(0));
                                city.setText("City: "+addresses.get(0).getLocality());
                                country.setText("Country: "+addresses.get(0).getCountryName());
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

        ActivityCompat.requestPermissions(GetLocationActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {

        if (requestCode == REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else {
                Toast.makeText(GetLocationActivity.this,"Please provide the required permission",Toast.LENGTH_SHORT).show();

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

