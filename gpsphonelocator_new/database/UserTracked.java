package com.app.gpsphonelocator_new.database;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003JC\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/phone/number/gpstracker/familylocator/phonetracker/database/UserTracked;", "", "uid", "", "name", "", "phone", "uuid", "checked", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getChecked", "()Ljava/lang/String;", "getName", "getPhone", "getUid", "()I", "getUuid", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "APP78_NumberLocator_v1.1.0(110)_Nov.27.2023_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: UserTracked.kt */
public final class UserTracked {
    private final String checked;
    private final String name;
    private final String phone;
    private final int uid;
    private final String uuid;

    public static /* synthetic */ UserTracked copy$default(UserTracked userTracked, int i, String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = userTracked.uid;
        }
        if ((i2 & 2) != 0) {
            str = userTracked.name;
        }
        String str5 = str;
        if ((i2 & 4) != 0) {
            str2 = userTracked.phone;
        }
        String str6 = str2;
        if ((i2 & 8) != 0) {
            str3 = userTracked.uuid;
        }
        String str7 = str3;
        if ((i2 & 16) != 0) {
            str4 = userTracked.checked;
        }
        return userTracked.copy(i, str5, str6, str7, str4);
    }

    public final int component1() {
        return this.uid;
    }

    public final String component2() {
        return this.name;
    }

    public final String component3() {
        return this.phone;
    }

    public final String component4() {
        return this.uuid;
    }

    public final String component5() {
        return this.checked;
    }

    public final UserTracked copy(int i, String str, String str2, String str3, String str4) {
        return new UserTracked(i, str, str2, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserTracked)) {
            return false;
        }
        UserTracked userTracked = (UserTracked) obj;
        return this.uid == userTracked.uid && Intrinsics.areEqual((Object) this.name, (Object) userTracked.name) && Intrinsics.areEqual((Object) this.phone, (Object) userTracked.phone) && Intrinsics.areEqual((Object) this.uuid, (Object) userTracked.uuid) && Intrinsics.areEqual((Object) this.checked, (Object) userTracked.checked);
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.uid) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.phone;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.uuid;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.checked;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        return "UserTracked(uid=" + this.uid + ", name=" + this.name + ", phone=" + this.phone + ", uuid=" + this.uuid + ", checked=" + this.checked + ')';
    }

    public UserTracked(int i, String str, String str2, String str3, String str4) {
        this.uid = i;
        this.name = str;
        this.phone = str2;
        this.uuid = str3;
        this.checked = str4;
    }

    public final int getUid() {
        return this.uid;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final String getUuid() {
        return this.uuid;
    }

    public final String getChecked() {
        return this.checked;
    }
}
