package com.app.gpsphonelocator_new.common;

import java.io.Serializable;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


public final class InforSaved implements Serializable {
    private String addedTime;
    private String address;
    private String avatar;
    private String checked;
    private String latitudes;
    private String locateemail;
    private String locatepin;
    private String locateuid;
    private String longitudes;
    private String nickname;
    private String phone;
    private String photouri;
    private String sos;
    private String sos_msg;
    private String sosmgs;
    private String uid;
    private String username;

    private String friendsname;

    public final String getUid() {
        return this.uid;
    }

    public final void setUid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.uid = str;
    }

    public final String getAvatar() {
        return this.avatar;
    }

    public final void setAvatar(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.avatar = str;
    }

    public final String getLatitudes() {
        return this.latitudes;
    }

    public final void setLatitudes(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.latitudes = str;
    }

    public final String getLongitudes() {
        return this.longitudes;
    }

    public final void setLongitudes(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.longitudes = str;
    }

    public final String getAddedTime() {
        return this.addedTime;
    }

    public final void setAddedTime(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.addedTime = str;
    }

    public final String getAddress() {
        return this.address;
    }

    public final void setAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.address = str;
    }

    public final String getSos() {
        return this.sos;
    }

    public final void setSos(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sos = str;
    }

    public final String getSos_msg() {
        return this.sos_msg;
    }

    public final void setSos_msg(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sos_msg = str;
    }

    public final String getSosmgs() {
        return this.sosmgs;
    }

    public final void setSosmgs(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sosmgs = str;
    }

    public String getFriendsname(){
        return this.friendsname;
    }
    public  final void setFriendsname(String str){
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sosmgs = str;
    }

    public InforSaved() {
        this.locateemail = "";
        this.locatepin = "";
        this.nickname = "";
        this.locateuid = "";
        this.phone = "";
        this.checked = "";
        this.photouri = "";
        this.username = "";
        this.uid = "";
        this.avatar = "";
        this.latitudes = "";
        this.longitudes = "";
        this.addedTime = "";
        this.address = "";
        this.sos = "";
        this.sos_msg = "";
        this.sosmgs = "";
        this.friendsname="";
    }

    public InforSaved(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        Intrinsics.checkNotNullParameter(str, "locateemail");
        Intrinsics.checkNotNullParameter(str2, "locatepin");
        Intrinsics.checkNotNullParameter(str3, "nickname");
        Intrinsics.checkNotNullParameter(str4, "locateuid");
        Intrinsics.checkNotNullParameter(str5, "phone");
        Intrinsics.checkNotNullParameter(str6, "checked");
        Intrinsics.checkNotNullParameter(str7, "photouri");
        Intrinsics.checkNotNullParameter(str9, "sos");
        Intrinsics.checkNotNullParameter(str10, "sos_msg");
        Intrinsics.checkNotNullParameter(str11, "sosmgs");
        Intrinsics.checkNotNullParameter(str12, "friendsname");
        this.uid = "";
        this.avatar = "";
        this.latitudes = "";
        this.longitudes = "";
        this.addedTime = "";
        this.address = "";
        this.locateemail = str;
        this.locatepin = str2;
        this.nickname = str3;
        this.checked = str6;
        this.phone = str5;
        this.locateuid = str4;
        this.photouri = str7;
        this.username = str8;
        this.sos = str9;
        this.sos_msg = str10;
        this.sosmgs = str11;
        this.friendsname = str12;
    }

    public final String getlocatepin() {
        return this.locatepin;
    }

    public final void setlocatepin(String str) {
        Intrinsics.checkNotNullParameter(str, "locatepin");
        this.locatepin = str;
    }

    public final String getlocateemail() {
        return this.locateemail;
    }

    public final void setlocateemail(String str) {
        Intrinsics.checkNotNullParameter(str, "locateemail");
        this.locateemail = str;
    }

    public final String getnickname() {
        return this.nickname;
    }

    public final void setnickname(String str) {
        Intrinsics.checkNotNullParameter(str, "nickname");
        this.nickname = str;
    }

    public final String getchecked() {
        return this.checked;
    }

    public final void setchecked(String str) {
        Intrinsics.checkNotNullParameter(str, "checked");
        this.checked = str;
    }

    public final String getlocateuid() {
        return this.locateuid;
    }

    public final void setlocateuid(String str) {
        Intrinsics.checkNotNullParameter(str, "locateuid");
        this.locateuid = str;
    }

    public final String getphotouri() {
        return this.photouri;
    }

    public final void setphotouri(String str) {
        Intrinsics.checkNotNullParameter(str, "photouri");
        this.photouri = str;
    }

    public final String getphone() {
        return this.phone;
    }

    public final void setphone(String str) {
        Intrinsics.checkNotNullParameter(str, "phone");
        this.phone = str;
    }

    public final String getusername() {
        return this.username;
    }

    public final void setusername(String str) {

        this.username = str;


    }

    public String getFriendsName(){
        return this.friendsname;
    }

    public void setFriendsName(String str){
        this.friendsname = str;


    }
}
