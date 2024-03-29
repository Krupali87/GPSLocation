package com.app.gpsphonelocator_new.phone.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


public final class Constants {
    public static final String AVATAR_USER = "AVATAR_USER";
    public static final Constants INSTANCE = new Constants();
    public static final String LANGUAGE_FIRST = "LANGUAGE_FIRST";
    public static final String NOTIFICATION_SERVICE = "NOTIFICATION_SERVICE";
    public static final String NOTIFICATION_SOS_ALERT = "NOTIFICATION_SOS_ALERT";
    public static final String NOTIFICATION_ZONE_ALERT = "NOTIFICATION_ZONE_ALERT";
    public static final String PERMISSION = "PERMISSION";
    public static final String PREFERENCE_SELECTED_LANGUAGE = "preference_selected_language";

    public static final String SECURITY_CODE = "security_code";
    public static final String SET_AVATAR = "SET_AVATAR";
    public static final String USER_LIVE_SHARE = "USER_LIVE_SHARE";
    private static String add_account = "add_account";
    private static String add_account2 = "add_account2";
    public static final String click_add_btn = "click_add_btn";
    private static String create_zone = "create_zone";
    private static String create_zone2 = "create_zone2";
    private static String edit_account = "edit_account";
    private static String edit_account2 = "edit_account2";
    public static final String from_home = "from_home";
    private static String guide = "guide";
    public static final String message = "message";
    public static final String message_key = "message_key";
    private static String save_add_account = "save_add_account";
    private static String save_edit_account2 = "save_edit_account2";
    private static String update_zone = "update_zone";
    private static String update_zone2 = "update_zone2";
    public static final String zone_alert_from_home = "zone_alert_from_home";

    private Constants() {
    }


    public final String getSave_edit_account2() {
        return save_edit_account2;
    }

    public final void setSave_edit_account2(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        save_edit_account2 = str;
    }

    public final String getCreate_zone() {
        return create_zone;
    }

    public final void setCreate_zone(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        create_zone = str;
    }

    public final String getCreate_zone2() {
        return create_zone2;
    }

    public final void setCreate_zone2(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        create_zone2 = str;
    }

    public final String getUpdate_zone() {
        return update_zone;
    }

    public final void setUpdate_zone(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        update_zone = str;
    }

    public final String getUpdate_zone2() {
        return update_zone2;
    }

    public final void setUpdate_zone2(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        update_zone2 = str;
    }
}
