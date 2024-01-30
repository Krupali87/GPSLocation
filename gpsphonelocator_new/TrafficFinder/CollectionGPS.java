package com.app.gpsphonelocator_new.TrafficFinder;

import android.content.Context;
import android.provider.Settings;

public class CollectionGPS {
    public static boolean isLocationEnabled(Context context) {
        try {
            return Settings.Secure.getInt(context.getContentResolver(), "location_mode") != 0;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
