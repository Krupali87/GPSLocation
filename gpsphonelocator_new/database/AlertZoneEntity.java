package com.app.gpsphonelocator_new.database;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001f\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u0010!\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u000bHÆ\u0003J\t\u0010$\u001a\u00020\u000bHÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003Jp\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010(J\u0013\u0010)\u001a\u00020\u000b2\b\u0010*\u001a\u0004\u0018\u00010+HÖ\u0003J\t\u0010,\u001a\u00020\u0003HÖ\u0001J\t\u0010-\u001a\u00020\u0005HÖ\u0001R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0017\u0010\u0015R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\f\u001a\u00020\u000b8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0016\u0010\r\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0011¨\u0006."}, d2 = {"Lcom/phone/number/gpstracker/familylocator/phonetracker/database/AlertZoneEntity;", "Ljava/io/Serializable;", "id", "", "zoneName", "", "latitude", "", "longitude", "address", "onEnter", "", "onLeave", "status", "radius", "(ILjava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;ZZII)V", "getAddress", "()Ljava/lang/String;", "getId", "()I", "getLatitude", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getLongitude", "getOnEnter", "()Z", "getOnLeave", "getRadius", "getStatus", "getZoneName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(ILjava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;ZZII)Lcom/phone/number/gpstracker/familylocator/phonetracker/database/AlertZoneEntity;", "equals", "other", "", "hashCode", "toString", "APP78_NumberLocator_v1.1.0(110)_Nov.27.2023_productionRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AlertZoneEntity.kt */
public final class AlertZoneEntity implements Serializable {
    private final String address;
    private final int id;
    private final Double latitude;
    private final Double longitude;
    private final boolean onEnter;
    private final boolean onLeave;
    private final int radius;
    private final int status;
    private final String zoneName;

    public static /* synthetic */ AlertZoneEntity copy$default(AlertZoneEntity alertZoneEntity, int i, String str, Double d, Double d2, String str2, boolean z, boolean z2, int i2, int i3, int i4, Object obj) {
        AlertZoneEntity alertZoneEntity2 = alertZoneEntity;
        int i5 = i4;
        return alertZoneEntity.copy((i5 & 1) != 0 ? alertZoneEntity2.id : i, (i5 & 2) != 0 ? alertZoneEntity2.zoneName : str, (i5 & 4) != 0 ? alertZoneEntity2.latitude : d, (i5 & 8) != 0 ? alertZoneEntity2.longitude : d2, (i5 & 16) != 0 ? alertZoneEntity2.address : str2, (i5 & 32) != 0 ? alertZoneEntity2.onEnter : z, (i5 & 64) != 0 ? alertZoneEntity2.onLeave : z2, (i5 & 128) != 0 ? alertZoneEntity2.status : i2, (i5 & 256) != 0 ? alertZoneEntity2.radius : i3);
    }

    public final int component1() {
        return this.id;
    }

    public final String component2() {
        return this.zoneName;
    }

    public final Double component3() {
        return this.latitude;
    }

    public final Double component4() {
        return this.longitude;
    }

    public final String component5() {
        return this.address;
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

    public final int component9() {
        return this.radius;
    }

    public final AlertZoneEntity copy(int i, String str, Double d, Double d2, String str2, boolean z, boolean z2, int i2, int i3) {
        return new AlertZoneEntity(i, str, d, d2, str2, z, z2, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AlertZoneEntity)) {
            return false;
        }
        AlertZoneEntity alertZoneEntity = (AlertZoneEntity) obj;
        return this.id == alertZoneEntity.id && Intrinsics.areEqual((Object) this.zoneName, (Object) alertZoneEntity.zoneName) && Intrinsics.areEqual((Object) this.latitude, (Object) alertZoneEntity.latitude) && Intrinsics.areEqual((Object) this.longitude, (Object) alertZoneEntity.longitude) && Intrinsics.areEqual((Object) this.address, (Object) alertZoneEntity.address) && this.onEnter == alertZoneEntity.onEnter && this.onLeave == alertZoneEntity.onLeave && this.status == alertZoneEntity.status && this.radius == alertZoneEntity.radius;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.id) * 31;
        String str = this.zoneName;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Double d = this.latitude;
        int hashCode3 = (hashCode2 + (d == null ? 0 : d.hashCode())) * 31;
        Double d2 = this.longitude;
        int hashCode4 = (hashCode3 + (d2 == null ? 0 : d2.hashCode())) * 31;
        String str2 = this.address;
        if (str2 != null) {
            i = str2.hashCode();
        }
        int i2 = (hashCode4 + i) * 31;
        boolean z = this.onEnter;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i3 = (i2 + (z ? 1 : 0)) * 31;
        boolean z3 = this.onLeave;
        if (!z3) {
            z2 = z3;
        }
        return ((((i3 + (z2 ? 1 : 0)) * 31) + Integer.hashCode(this.status)) * 31) + Integer.hashCode(this.radius);
    }

    public String toString() {
        return "AlertZoneEntity(id=" + this.id + ", zoneName=" + this.zoneName + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", address=" + this.address + ", onEnter=" + this.onEnter + ", onLeave=" + this.onLeave + ", status=" + this.status + ", radius=" + this.radius + ')';
    }

    public AlertZoneEntity(int i, String str, Double d, Double d2, String str2, boolean z, boolean z2, int i2, int i3) {
        this.id = i;
        this.zoneName = str;
        this.latitude = d;
        this.longitude = d2;
        this.address = str2;
        this.onEnter = z;
        this.onLeave = z2;
        this.status = i2;
        this.radius = i3;
    }

    public final int getId() {
        return this.id;
    }

    public final String getZoneName() {
        return this.zoneName;
    }

    public final Double getLatitude() {
        return this.latitude;
    }

    public final Double getLongitude() {
        return this.longitude;
    }

    public final String getAddress() {
        return this.address;
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

    public final int getRadius() {
        return this.radius;
    }
}
