package com.app.gpsphonelocator_new.TrafficFinder;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.app.gpsphonelocator_new.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import java.io.IOException;
import java.util.List;

public class StreetviewActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, OnMapReadyCallback {
    public Context context = this;
    public Location currentLocation;
    public FusedLocationProviderClient fusedLocationProviderClient;
    public double global_latitude_streetView = 0.0d;
    public double global_longitude_StreetView = 0.0d;
    public Activity mActivity;
    public GoogleMap mMap;
    public boolean mShowPermissionDeniedDialog = false;
    public Spinner mSpinner;
    public ImageView speakBtnSearchStreetViewPractice;
    public EditText tvSearchStreetViewPractice;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView((int) R.layout.activity_streetview);
        findViewById(R.id.ivback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        this.mActivity = this;
        this.speakBtnSearchStreetViewPractice = (ImageView) findViewById(R.id.speakBtnSearchStreetViewPractice);
        this.tvSearchStreetViewPractice = (EditText) findViewById(R.id.tvSearchStreetViewPractice);
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getCurrentLoacation();
    }


    public void getCurrentLoacation() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                public void onSuccess(Location location) {
                    if (location != null) {
                        StreetviewActivity streetviewActivity = StreetviewActivity.this;
                        streetviewActivity.currentLocation = location;
                        ((SupportMapFragment) streetviewActivity.getSupportFragmentManager().findFragmentById(R.id.mapsss)).getMapAsync(StreetviewActivity.this);
                    }
                }
            });
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 101);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 123 || iArr.length <= 0 || iArr[0] + iArr[1] != 0) {
            return;
        }
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            getCurrentLoacation();
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        updateMapType();
        if (ContextCompat.checkSelfPermission(this.mActivity, "android.permission.ACCESS_FINE_LOCATION") + ContextCompat.checkSelfPermission(this.mActivity, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            LatLng latLng = new LatLng(this.currentLocation.getLatitude(), this.currentLocation.getLongitude());
            this.mMap.addMarker(new MarkerOptions().position(latLng).title("You are here!!"));
            this.mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this.mActivity, "android.permission.ACCESS_FINE_LOCATION") || ActivityCompat.shouldShowRequestPermissionRationale(this.mActivity, "android.permission.ACCESS_COARSE_LOCATION")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
            builder.setMessage((CharSequence) "Location Permission are required to do the task.");
            builder.setTitle("Please grant those permissions");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(StreetviewActivity.this.mActivity, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 123);
                }
            });
            builder.setNeutralButton("Cancel", (DialogInterface.OnClickListener) null);
            builder.create().show();
        } else {
            ActivityCompat.requestPermissions(this.mActivity, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 123);
        }

    }

    public void onResumeFragments() {
        super.onResumeFragments();
        if (this.mShowPermissionDeniedDialog) {
            this.mShowPermissionDeniedDialog = false;
        }
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        updateMapType();
    }

    public final void updateMapType() {
        this.speakBtnSearchStreetViewPractice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String place = StreetviewActivity.this.tvSearchStreetViewPractice.getText().toString();
                if (TextUtils.isEmpty(place)) {
                    StreetviewActivity.this.tvSearchStreetViewPractice.setError("Enter Source point");
                } else {
                    StreetviewActivity.this.AddressToLatLongComplete(place);
                }
            }
        });
    }

    public Comparable AddressToLatLongComplete(String str) {
        try {
            List<Address> fromLocationName = new Geocoder(this).getFromLocationName(str, 5);
            if (fromLocationName == null) {
                return null;
            }
            Address address = fromLocationName.get(0);
            double latitude = address.getLatitude();
            double longitude = address.getLongitude();
            this.global_latitude_streetView = latitude;
            this.global_longitude_StreetView = longitude;
            openStreetView(latitude, longitude);
            return null;
        } catch (IOException | IndexOutOfBoundsException e) {
            Toast.makeText(this, "Enter Valid Place name", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return null;
        }
    }

    public final void openStreetView(double d, double d2) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("google.streetview:cbll=" + d + "," + d2));
        intent.setPackage("com.google.android.apps.maps");
        try {
            startActivity(intent);
        } catch (Exception e) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://maps.google.co.in/maps?q=")));
        }
    }

    public void onBackPressed() {


                StreetviewActivity.this.finish();
            }


}
