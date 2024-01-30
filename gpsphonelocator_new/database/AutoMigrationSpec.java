package com.app.gpsphonelocator_new.database;

import androidx.sqlite.db.SupportSQLiteDatabase;

public interface AutoMigrationSpec {
    void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase);
}
