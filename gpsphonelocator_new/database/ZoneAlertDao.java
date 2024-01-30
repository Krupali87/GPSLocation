package com.app.gpsphonelocator_new.database;

import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H'¢\u0006\u0002\u0010\u0007J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\b\u001a\u00020\t\"\u00020\nH'J\u0010\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\fH'J!\u0010\r\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H'¢\u0006\u0002\u0010\u0007J!\u0010\u000e\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H'¢\u0006\u0002\u0010\u0007¨\u0006\u000f"}, d2 = {"Lcom/phone/number/gpstracker/familylocator/phonetracker/database/ZoneAlertDao;", "", "delete", "", "zone", "", "Lcom/phone/number/gpstracker/familylocator/phonetracker/database/AlertZoneEntity;", "([Lcom/phone/number/gpstracker/familylocator/phonetracker/database/AlertZoneEntity;)V", "zoneId", "", "", "getListZone", "", "insert", "update", "APP78_NumberLocator_v1.1.0(110)_Nov.27.2023_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ZoneAlertDao.kt */
public interface ZoneAlertDao {
    void delete(int... iArr);

    void delete(AlertZoneEntity... alertZoneEntityArr);

    List<AlertZoneEntity> getListZone();

    void insert(AlertZoneEntity... alertZoneEntityArr);

    void update(AlertZoneEntity... alertZoneEntityArr);
}
