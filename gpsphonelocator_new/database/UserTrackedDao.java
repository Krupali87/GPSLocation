package com.app.gpsphonelocator_new.database;

import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H'¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH'J\u0010\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\fH'J\u0018\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\nH'J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\nH'J!\u0010\u000f\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H'¢\u0006\u0002\u0010\u0007J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\nH'¨\u0006\u0013"}, d2 = {"Lcom/phone/number/gpstracker/familylocator/phonetracker/database/UserTrackedDao;", "", "delete", "", "userTracked", "", "Lcom/phone/number/gpstracker/familylocator/phonetracker/database/UserTracked;", "([Lcom/phone/number/gpstracker/familylocator/phonetracker/database/UserTracked;)V", "deleteById", "uid", "", "getAllUser", "", "getCheckedFriends", "getUser", "insert", "updateChecked", "checked", "uuid", "APP78_NumberLocator_v1.1.0(110)_Nov.27.2023_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: UserTrackedDao.kt */
public interface UserTrackedDao {
    void delete(UserTracked... userTrackedArr);

    void deleteById(String str);

    List<UserTracked> getAllUser();

    List<UserTracked> getCheckedFriends(String str);

    UserTracked getUser(String str);

    void insert(UserTracked... userTrackedArr);

    void updateChecked(String str, String str2);
}
