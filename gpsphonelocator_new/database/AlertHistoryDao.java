package com.app.gpsphonelocator_new.database;

import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H'¢\u0006\u0002\u0010\u0007J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\b\u001a\u00020\t\"\u00020\nH'J\b\u0010\u000b\u001a\u00020\u0003H'J\u001a\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH'J*\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014H'J\u0010\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0011H'J!\u0010\u0017\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H'¢\u0006\u0002\u0010\u0007¨\u0006\u0018"}, d2 = {"Lcom/phone/number/gpstracker/familylocator/phonetracker/database/AlertHistoryDao;", "", "delete", "", "zone", "", "Lcom/phone/number/gpstracker/familylocator/phonetracker/database/AlertHistoryEntity;", "([Lcom/phone/number/gpstracker/familylocator/phonetracker/database/AlertHistoryEntity;)V", "id", "", "", "deleteAll", "getFirstHistory", "uid", "", "zoneId", "getHistoryFilter", "", "name", "startTime", "", "endTime", "getHistoryList", "insert", "APP78_NumberLocator_v1.1.0(110)_Nov.27.2023_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AlertHistoryDao.kt */
public interface AlertHistoryDao {
    void delete(int... iArr);

    void delete(AlertHistoryEntity... alertHistoryEntityArr);

    void deleteAll();

    AlertHistoryEntity getFirstHistory(String str, int i);

    List<AlertHistoryEntity> getHistoryFilter(String str, long j, long j2);

    List<AlertHistoryEntity> getHistoryList();

    void insert(AlertHistoryEntity... alertHistoryEntityArr);

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AlertHistoryDao.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ List getHistoryFilter$default(AlertHistoryDao alertHistoryDao, String str, long j, long j2, int i, Object obj) {
            if (obj == null) {
                if ((i & 4) != 0) {
                    j2 = System.currentTimeMillis();
                }
                return alertHistoryDao.getHistoryFilter(str, j, j2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getHistoryFilter");
        }
    }
}
