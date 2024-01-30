package com.app.gpsphonelocator_new.database;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u001c\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\n¢\u0006\u0002\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\nHÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010$\u001a\u00020\nHÆ\u0003J\t\u0010%\u001a\u00020\nHÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u000eHÆ\u0003Js\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\nHÆ\u0001J\u0013\u0010)\u001a\u00020\n2\b\u0010*\u001a\u0004\u0018\u00010+HÖ\u0003J\t\u0010,\u001a\u00020\u0003HÖ\u0001J\t\u0010-\u001a\u00020\u0006HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u000f\u001a\u00020\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0013R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0016\u0010\u000b\u001a\u00020\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0016\u0010\f\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001a¨\u0006."}, d2 = {"Lcom/phone/number/gpstracker/familylocator/phonetracker/database/AlertHistoryEntity;", "Ljava/io/Serializable;", "id", "", "zoneId", "zoneName", "", "uid", "userName", "onEnter", "", "onLeave", "status", "time", "", "isTmp", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZIJZ)V", "getId", "()I", "()Z", "getOnEnter", "getOnLeave", "getStatus", "getTime", "()J", "getUid", "()Ljava/lang/String;", "getUserName", "getZoneId", "getZoneName", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "APP78_NumberLocator_v1.1.0(110)_Nov.27.2023_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AlertHistoryEntity.kt */
public final class AlertHistoryEntity implements Serializable {
    private final int id;
    private final boolean isTmp;
    private final boolean onEnter;
    private final boolean onLeave;
    private final int status;
    private final long time;
    private final String uid;
    private final String userName;
    private final int zoneId;
    private final String zoneName;

    public static /* synthetic */ AlertHistoryEntity copy$default(AlertHistoryEntity alertHistoryEntity, int i, int i2, String str, String str2, String str3, boolean z, boolean z2, int i3, long j, boolean z3, int i4, Object obj) {
        AlertHistoryEntity alertHistoryEntity2 = alertHistoryEntity;
        int i5 = i4;
        return alertHistoryEntity.copy((i5 & 1) != 0 ? alertHistoryEntity2.id : i, (i5 & 2) != 0 ? alertHistoryEntity2.zoneId : i2, (i5 & 4) != 0 ? alertHistoryEntity2.zoneName : str, (i5 & 8) != 0 ? alertHistoryEntity2.uid : str2, (i5 & 16) != 0 ? alertHistoryEntity2.userName : str3, (i5 & 32) != 0 ? alertHistoryEntity2.onEnter : z, (i5 & 64) != 0 ? alertHistoryEntity2.onLeave : z2, (i5 & 128) != 0 ? alertHistoryEntity2.status : i3, (i5 & 256) != 0 ? alertHistoryEntity2.time : j, (i5 & 512) != 0 ? alertHistoryEntity2.isTmp : z3);
    }

    public final int component1() {
        return this.id;
    }

    public final boolean component10() {
        return this.isTmp;
    }

    public final int component2() {
        return this.zoneId;
    }

    public final String component3() {
        return this.zoneName;
    }

    public final String component4() {
        return this.uid;
    }

    public final String component5() {
        return this.userName;
    }

    public final boolean component6() {
        return this.onEnter;
    }

    public final boolean component7() {
        return this.onLeave;
    }

    public final int component8() {
        return this.status;
    }

    public final long component9() {
        return this.time;
    }

    public final AlertHistoryEntity copy(int i, int i2, String str, String str2, String str3, boolean z, boolean z2, int i3, long j, boolean z3) {
        return new AlertHistoryEntity(i, i2, str, str2, str3, z, z2, i3, j, z3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AlertHistoryEntity)) {
            return false;
        }
        AlertHistoryEntity alertHistoryEntity = (AlertHistoryEntity) obj;
        return this.id == alertHistoryEntity.id && this.zoneId == alertHistoryEntity.zoneId && Intrinsics.areEqual((Object) this.zoneName, (Object) alertHistoryEntity.zoneName) && Intrinsics.areEqual((Object) this.uid, (Object) alertHistoryEntity.uid) && Intrinsics.areEqual((Object) this.userName, (Object) alertHistoryEntity.userName) && this.onEnter == alertHistoryEntity.onEnter && this.onLeave == alertHistoryEntity.onLeave && this.status == alertHistoryEntity.status && this.time == alertHistoryEntity.time && this.isTmp == alertHistoryEntity.isTmp;
    }

    public int hashCode() {
        int hashCode = ((Integer.hashCode(this.id) * 31) + Integer.hashCode(this.zoneId)) * 31;
        String str = this.zoneName;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.uid;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.userName;
        if (str3 != null) {
            i = str3.hashCode();
        }
        int i2 = (hashCode3 + i) * 31;
        boolean z = this.onEnter;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i3 = (i2 + (z ? 1 : 0)) * 31;
        boolean z3 = this.onLeave;
        if (z3) {
            z3 = true;
        }
        int hashCode4 = (((((i3 + (z3 ? 1 : 0)) * 31) + Integer.hashCode(this.status)) * 31) + Long.hashCode(this.time)) * 31;
        boolean z4 = this.isTmp;
        if (!z4) {
            z2 = z4;
        }
        return hashCode4 + (z2 ? 1 : 0);
    }

    public String toString() {
        return "AlertHistoryEntity(id=" + this.id + ", zoneId=" + this.zoneId + ", zoneName=" + this.zoneName + ", uid=" + this.uid + ", userName=" + this.userName + ", onEnter=" + this.onEnter + ", onLeave=" + this.onLeave + ", status=" + this.status + ", time=" + this.time + ", isTmp=" + this.isTmp + ')';
    }

    public AlertHistoryEntity(int i, int i2, String str, String str2, String str3, boolean z, boolean z2, int i3, long j, boolean z3) {
        this.id = i;
        this.zoneId = i2;
        this.zoneName = str;
        this.uid = str2;
        this.userName = str3;
        this.onEnter = z;
        this.onLeave = z2;
        this.status = i3;
        this.time = j;
        this.isTmp = z3;
    }

    public final int getId() {
        return this.id;
    }

    public final int getZoneId() {
        return this.zoneId;
    }

    public final String getZoneName() {
        return this.zoneName;
    }

    public final String getUid() {
        return this.uid;
    }

    public final String getUserName() {
        return this.userName;
    }

    public final boolean getOnEnter() {
        return this.onEnter;
    }

    public final boolean getOnLeave() {
        return this.onLeave;
    }

    public final int getStatus() {
        return this.status;
    }

    public final long getTime() {
        return this.time;
    }

    public final boolean isTmp() {
        return this.isTmp;
    }
}
