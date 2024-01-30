package com.app.gpsphonelocator_new.TrafficFinder;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.app.gpsphonelocator_new.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class TrafficFinderClass extends AppCompatActivity implements  OnMapReadyCallback {
    public Context context = this;
    public Activity mActivity;
    public GoogleMap mMap;
    public CheckBox mMyLocationCheckbox;
    public boolean mShowPermissionDeniedDialog = false;
    public Spinner mSpinner;
    public CheckBox mTrafficCheckbox;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView((int) R.layout.trafficfinderclass);
        findViewById(R.id.ivback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        this.mActivity = this;
        this.mTrafficCheckbox = (CheckBox) findViewById(R.id.traffic);
        this.mMyLocationCheckbox = (CheckBox) findViewById(R.id.my_location);
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 123 || iArr.length <= 0 || iArr[0] + iArr[1] != 0) {
            return;
        }
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.mMap.setMyLocationEnabled(true);
            this.mMyLocationCheckbox.setChecked(true);
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;

        updateTraffic();
        updateMyLocation();
        if (ContextCompat.checkSelfPermission(this.mActivity, "android.permission.ACCESS_FINE_LOCATION") + ContextCompat.checkSelfPermission(this.mActivity, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.mMap.setMyLocationEnabled(true);
            this.mMap.setOnMyLocationChangeListener(myLocationChangeListener());
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this.mActivity, "android.permission.ACCESS_FINE_LOCATION") || ActivityCompat.shouldShowRequestPermissionRationale(this.mActivity, "android.permission.ACCESS_COARSE_LOCATION")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
            builder.setMessage((CharSequence) "Location Permission are required to do the task.");
            builder.setTitle("Please grant those permissions");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(TrafficFinderClass.this.mActivity, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 123);
                }
            });
            builder.setNeutralButton("Cancel", (DialogInterface.OnClickListener) null);
            builder.create().show();
        } else {
            ActivityCompat.requestPermissions(this.mActivity, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 123);
        }

    }

    public final GoogleMap.OnMyLocationChangeListener myLocationChangeListener() {
        return new GoogleMap.OnMyLocationChangeListener() {
            public void onMyLocationChange(Location location) {
                TrafficFinderClass.this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 16.0f));
            }
        };
    }

    public final boolean checkReady() {
        if (this.mMap != null) {
            return true;
        }

        return false;
    }

    public void onTrafficToggled(View view) {
        updateTraffic();
    }

    public final void updateTraffic() {
        if (checkReady()) {
            this.mMap.setTrafficEnabled(this.mTrafficCheckbox.isChecked());
        }
    }

    public void onMyLocationToggled(View view) {
        updateMyLocation();
    }

    public final void updateMyLocation() {
        if (!checkReady()) {
            return;
        }
        if (this.mMyLocationCheckbox.isChecked()) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                this.mMap.setMyLocationEnabled(true);
            } else {
                this.mMyLocationCheckbox.setChecked(false);
            }
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.mMap.setMyLocationEnabled(false);
        }
    }

    public void onResumeFragments() {
        super.onResumeFragments();
        if (this.mShowPermissionDeniedDialog) {
            this.mShowPermissionDeniedDialog = false;
        }
    }




    public void onBackPressed() {


                TrafficFinderClass.this.finish();
            }

    }

