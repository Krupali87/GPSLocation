package com.app.gpsphonelocator_new.database;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&¨\u0006\n"}, d2 = {"Lcom/phone/number/gpstracker/familylocator/phonetracker/database/UserDatabase;", "Landroidx/room/RoomDatabase;", "()V", "alertHistoryDAO", "Lcom/phone/number/gpstracker/familylocator/phonetracker/database/AlertHistoryDao;", "userDAO", "Lcom/phone/number/gpstracker/familylocator/phonetracker/database/UserTrackedDao;", "zoneDAO", "Lcom/phone/number/gpstracker/familylocator/phonetracker/database/ZoneAlertDao;", "Companion", "APP78_NumberLocator_v1.1.0(110)_Nov.27.2023_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: UserDatabase.kt */
public abstract class UserDatabase extends RoomDatabase {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String DATABASE_NAME = "User.db";
    /* access modifiers changed from: private */
    public static volatile UserDatabase instance;

    public abstract AlertHistoryDao alertHistoryDAO();

    public abstract UserTrackedDao userDAO();

    public abstract ZoneAlertDao zoneDAO();

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/phone/number/gpstracker/familylocator/phonetracker/database/UserDatabase$Companion;", "", "()V", "DATABASE_NAME", "", "instance", "Lcom/phone/number/gpstracker/familylocator/phonetracker/database/UserDatabase;", "getInstance", "context", "Landroid/content/Context;", "APP78_NumberLocator_v1.1.0(110)_Nov.27.2023_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: UserDatabase.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UserDatabase getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (UserDatabase.instance == null) {
                synchronized (this) {
                    Companion companion = UserDatabase.Companion;
                    UserDatabase.instance = Room.databaseBuilder(context, UserDatabase.class, UserDatabase.DATABASE_NAME).allowMainThreadQueries().build();
                    Unit unit = Unit.INSTANCE;
                }
            }
            return UserDatabase.instance;
        }
    }
}
