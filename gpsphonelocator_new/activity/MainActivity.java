package com.app.gpsphonelocator_new.activity;


import static com.app.gpsphonelocator_new.other.UserFriendDatabase.instance;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.gpsphonelocator_new.other.MyApp;
import com.app.gpsphonelocator_new.R;
import com.app.gpsphonelocator_new.other.RTDB_DEFINE;
import com.app.gpsphonelocator_new.other.UserFriend;
import com.app.gpsphonelocator_new.other.UserFriendAdapter;
import com.app.gpsphonelocator_new.other.UserFriendDao;
import com.app.gpsphonelocator_new.other.UserFriendDatabase;
import com.app.gpsphonelocator_new.other.ViewModal;
import com.app.gpsphonelocator_new.adapter.CategoryNearByAdapter;
import com.app.gpsphonelocator_new.adapter.CustomInfoWindowAdapter;
import com.app.gpsphonelocator_new.adapter.FriendAdapter;
import com.app.gpsphonelocator_new.adapter.FriendsPopUpAdapter;
import com.app.gpsphonelocator_new.adapter.StyleMapAdapter;
import com.app.gpsphonelocator_new.adapter.TrackedSimpleAdapter;
import com.app.gpsphonelocator_new.adapter.ZoneAlertPopUpAdapter;
import com.app.gpsphonelocator_new.common.Common;
import com.app.gpsphonelocator_new.common.CustomToast;
import com.app.gpsphonelocator_new.common.ExtensionKt;
import com.app.gpsphonelocator_new.common.InforSaved;
import com.app.gpsphonelocator_new.database.AlertHistoryEntity;
import com.app.gpsphonelocator_new.database.AlertZoneEntity;
import com.app.gpsphonelocator_new.database.UserDatabase;
import com.app.gpsphonelocator_new.database.UserTracked;
import com.app.gpsphonelocator_new.database.UserTrackedDao;
import com.app.gpsphonelocator_new.database.ZoneAlertDao;
import com.app.gpsphonelocator_new.databinding.ActivityMainBinding;
import com.app.gpsphonelocator_new.databinding.LayoutCategoryBinding;
import com.app.gpsphonelocator_new.databinding.LayoutStyleMapBinding;
import com.app.gpsphonelocator_new.extensions.AppExtensionKt;
import com.app.gpsphonelocator_new.phone.model.CategoryNearBy;
import com.app.gpsphonelocator_new.phone.model.StyleMap;
import com.app.gpsphonelocator_new.phone.service.GPSLocationService;
import com.app.gpsphonelocator_new.phone.util.AppAuthen;
import com.app.gpsphonelocator_new.phone.util.Constants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paypal.android.sdk.payments.PayPalService;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import mumayank.com.airlocationlibrary.AirLocation;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements OnMapReadyCallback {

    private final int REQUEST_CODE_OPTIONS = 1011;
    private AirLocation airloc ;
    public AppAuthen.AuthUser appUser;
    private final Lazy categoryNearByAdapter$delegate = LazyKt.lazy((Function0<Object>) () -> new CategoryNearByAdapter());
    private int drawableFriendsOne = R.drawable.ic_friends;
    private int drawableFriendsTwo = R.drawable.ic_friends_disable;
    private int drawableShareOne = R.drawable.ic_share_v2;
    private int drawableShareTwo = R.drawable.ic_share_disable_v2;
    private int drawableYourLocationOne = R.drawable.ic_your_location;
    private int drawableYourLocationTwo = R.drawable.ic_your_location_disable;
    private int drawableZoneAlertOne = R.drawable.ic_zone_alert_v2;
    private int drawableZoneAlertTwo = R.drawable.ic_zone_alert_disable_v2;
    private FriendAdapter friendAdapter;

    public List<InforSaved> friendList = new ArrayList();


    private final Lazy friendsPopUpAdapter$delegate = LazyKt.lazy(new Function0<Object>() {
        @Override
        public Object invoke() {
            return new FriendsPopUpAdapter();

        }
    });
    public GoogleMap gmap;
    public Marker homeMarker;
    public double lat1;
    public Double lat2 = Double.valueOf(0.0d);
    public String latZoneAlert = "";
    private ArrayList<JSONObject> list = new ArrayList<>();
    private List<CategoryNearBy> listCategory = new ArrayList();
    private List<AlertHistoryEntity> listNotification = new ArrayList();
    private List<StyleMap> listStyleMap = new ArrayList();
    private List<AlertZoneEntity> listZoneAlert = new ArrayList();
    public LatLng ll;
    public double lng1;
    public double lng2;
    public List<UserFriend> friendsdata = new ArrayList<>();

    public String lngZoneAlert = "";
    private String mcity = "";
    private String mcountry = "";
    private final String message = "from_map";
    private String mpostalCode = "";
    private String mstate = "";
    private String myaddressnow = "";

    public TrackedSimpleAdapter simpleFriendAdapter;

    private final Lazy styleMapAdapter$delegate = LazyKt.lazy((Function0<Object>) () -> new StyleMapAdapter());

    private final Lazy zoneAlertPopUpAdapter$delegate = LazyKt.lazy((Function0<Object>) () -> new ZoneAlertPopUpAdapter());

    public final StyleMapAdapter getStyleMapAdapter() {
        return (StyleMapAdapter) this.styleMapAdapter$delegate.getValue();
    }
    private final FriendsPopUpAdapter getFriendsPopUpAdapter() {
        return (FriendsPopUpAdapter) this.friendsPopUpAdapter$delegate.getValue();
    }
    private final ZoneAlertPopUpAdapter getZoneAlertPopUpAdapter() {
        return (ZoneAlertPopUpAdapter) this.zoneAlertPopUpAdapter$delegate.getValue();
    }
    private final CategoryNearByAdapter getCategoryNearByAdapter() {
        return (CategoryNearByAdapter) this.categoryNearByAdapter$delegate.getValue();
    }

    public ActivityMainBinding getViewBinding() {
        ActivityMainBinding inflate = ActivityMainBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        return inflate;
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        bindComponent();
        bindEvent();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private final void bindComponent() {
        List<StyleMap> list2 = this.listStyleMap;
        String string = getString(R.string.normal);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.normal)");
        list2.add(new StyleMap(string, "file:///android_asset/style_map/image_style_map_hybrid.png"));
        String string2 = getString(R.string.hybrid);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.hybrid)");
        list2.add(new StyleMap(string2, "file:///android_asset/style_map/image_style_map_normal.png"));
        String string3 = getString(R.string.satellite);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.satellite)");
        list2.add(new StyleMap(string3, "file:///android_asset/style_map/image_style_map_satellite.png"));
        String string4 = getString(R.string.terrain);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.terrain)");
        list2.add(new StyleMap(string4, "file:///android_asset/style_map/image_style_map_terrain.png"));

        listCategory.add(new CategoryNearBy(getString(R.string.location_near_by_coffee), "file:///android_asset/category/coffee_category.png"));
        listCategory.add(new CategoryNearBy(getString(R.string.location_near_by_restaurants), "file:///android_asset/category/restaurant_category.png"));
        listCategory.add(new CategoryNearBy(getString(R.string.beauty_salon), "file:///android_asset/category/beauty_salons_category.png"));
        listCategory.add(new CategoryNearBy(getString(R.string.bookstore), "file:///android_asset/category/bookstore_category.png"));
        listCategory.add(new CategoryNearBy(getString(R.string.fashion_shop), "file:///android_asset/category/fashion_shop_category.png"));
        listCategory.add(new CategoryNearBy(getString(R.string.gym), "file:///android_asset/category/gym_category.png"));
        listCategory.add(new CategoryNearBy(getString(R.string.shopping_mall), "file:///android_asset/category/shopping_mall_category.png"));
        listCategory.add(new CategoryNearBy(getString(R.string.airport), "file:///android_asset/category/airport_category.png"));
        listCategory.add(new CategoryNearBy(getString(R.string.location_near_by_ATM), "file:///android_asset/category/atm_category.png"));
        listCategory.add(new CategoryNearBy(getString(R.string.bank), "file:///android_asset/category/bank_category.png"));
        listCategory.add(new CategoryNearBy(getString(R.string.bus_stop), "file:///android_asset/category/bus_category.png"));
        listCategory.add(new CategoryNearBy(getString(R.string.hospital_amd_clinics), "file:///android_asset/category/hospital_category.png"));
        listCategory.add(new CategoryNearBy(getString(R.string.hotel), "file:///android_asset/category/hotel_category.png"));
        listCategory.add(new CategoryNearBy(getString(R.string.parks), "file:///android_asset/category/park_category.png"));
        listCategory.add(new CategoryNearBy(getString(R.string.parking), "file:///android_asset/category/parking_category.png"));
        listCategory.add(new CategoryNearBy(getString(R.string.post_offices), "file:///android_asset/category/post_office_category.png"));
        Common.Companion.setPreviousAddActivity("Main");
        initMap();
        Context context = this;
        this.friendAdapter = new FriendAdapter(context);
        this.appUser = AppAuthen.getInstance().getCurrentUser();
    }
    private final void bindEvent() {
        ActivityMainBinding activityMainBinding = (ActivityMainBinding) getMBinding();
        activityMainBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intrinsics.checkNotNullParameter(this, "this$0");
               onBackPressed();
            }
        });

        activityMainBinding.bottomNavMain.linearFriends.setOnClickListener(view -> {
            setViewWhenClickFriend();
            setRecyclerViewFriendsPopUp();
        });

        activityMainBinding.bottomNavMain.linearYourLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intrinsics.checkNotNullParameter(this, "this$0");
                setViewWhenClickNumberLocation();
            }
        });
        activityMainBinding.bottomNavMain.linearZoneAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intrinsics.checkNotNullParameter(this, "this$0");
                setViewWhenClickZoneAlter();
            }
        });

        activityMainBinding.bottomNavMain.linearShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intrinsics.checkNotNullParameter(this, "this$0");
               setViewWhenClickShare();
            }
        });
        activityMainBinding.imgMapType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intrinsics.checkNotNullParameter(this, "this$0");
                clickOpenPopupStymap();
            }
        });
        activityMainBinding.outSideBottomFriends.setOnClickListener(view -> {
            Intrinsics.checkNotNullParameter(activityMainBinding, "$this_apply");
            LinearLayout linearLayout = activityMainBinding.bottomFriends;
            Intrinsics.checkNotNullExpressionValue(linearLayout, "bottomFriends");
            AppExtensionKt.hide(linearLayout);
            View view2 = activityMainBinding.outSideBottomFriends;
            Intrinsics.checkNotNullExpressionValue(view2, "outSideBottomFriends");
            AppExtensionKt.hide(view2);
        });
      getFriendsPopUpAdapter().callBackFriendsPopUp((i, str, str2, str3, str4, str5, str6) -> {
          UserFriendDao userFriendDao;
          List<UserFriend> checkedFriends;
          Intrinsics.checkNotNullParameter(str, "name");

            UserFriendDatabase userFriendDatabase = UserFriendDatabase.getInstance(MainActivity.this);
          if (instance != null && (userFriendDao = instance.Dao()) != null && (checkedFriends = userFriendDao.getAllUser().getValue()) != null) {

              for (UserFriend checked : checkedFriends) {
                  if (Intrinsics.areEqual((Object) checked.getname(), (Object) "true")) {
                      LinearLayout linearLayout = activityMainBinding.bottomFriends;
                      Intrinsics.checkNotNullExpressionValue(linearLayout, "bottomFriends");
                      AppExtensionKt.hide(linearLayout);
                      View view = activityMainBinding.outSideBottomFriends;
                      Intrinsics.checkNotNullExpressionValue(view, "outSideBottomFriends");
                      AppExtensionKt.hide(view);
                      LinearLayout linearLayout2 = activityMainBinding.bottomFriendsDetail;
                      Intrinsics.checkNotNullExpressionValue(linearLayout2, "bottomFriendsDetail");
                      AppExtensionKt.show(linearLayout2);
                      View view2 = activityMainBinding.outSideBottomFriendsDetail;
                      Intrinsics.checkNotNullExpressionValue(view2, "outSideBottomFriendsDetail");
                      AppExtensionKt.show(view2);
                      activityMainBinding.bottomPopupFriendsDetail.tvName.setText(str);
                     drawMarkerUser();
                  } else {
                      CustomToast.Companion.customToast(str + ' ' + getString(R.string.not_allow_tracking), MainActivity.this);
                  }
              }
          }
      });
        activityMainBinding.bottomPopupFriends.ivAdd.setOnClickListener(view -> {

try {
    activityMainBinding.outSideBottomFriends.setVisibility(View.GONE);
    startActivity(new Intent(MainActivity.this,TrackingUserListActivity.class).putExtra(Constants.message_key, Constants.click_add_btn));

}catch (Exception e){
    throw e;
}
        });

        activityMainBinding.outSideBottomFriendsDetail.setOnClickListener(view -> {
            Intrinsics.checkNotNullParameter(activityMainBinding, "$this_apply");
            LinearLayout linearLayout = activityMainBinding.bottomFriendsDetail;
            Intrinsics.checkNotNullExpressionValue(linearLayout, "bottomFriendsDetail");
            AppExtensionKt.hide(linearLayout);
            View view2 = activityMainBinding.outSideBottomFriendsDetail;
            Intrinsics.checkNotNullExpressionValue(view2, "outSideBottomFriendsDetail");
            AppExtensionKt.hide(view2);
        });
        activityMainBinding.bottomPopupFriendsDetail.ivClose.setOnClickListener(view -> {

            Intrinsics.checkNotNullParameter(activityMainBinding, "$this_apply");
            LinearLayout linearLayout = activityMainBinding.bottomFriendsDetail;
            Intrinsics.checkNotNullExpressionValue(linearLayout, "bottomFriendsDetail");
            AppExtensionKt.hide(linearLayout);
            View view2 = activityMainBinding.outSideBottomFriendsDetail;
            Intrinsics.checkNotNullExpressionValue(view2, "outSideBottomFriendsDetail");
            AppExtensionKt.hide(view2);
        });
        activityMainBinding.outSideBottomZoneAlert.setOnClickListener(view -> {
            Intrinsics.checkNotNullParameter(activityMainBinding, "$this_apply");
            LinearLayout linearLayout = activityMainBinding.bottomZoneAlert;
            Intrinsics.checkNotNullExpressionValue(linearLayout, "bottomZoneAlert");
            AppExtensionKt.hide(linearLayout);
            View view2 = activityMainBinding.outSideBottomZoneAlert;
            Intrinsics.checkNotNullExpressionValue(view2, "outSideBottomZoneAlert");
            AppExtensionKt.hide(view2);
        });
        activityMainBinding.outSideBottomZoneAlertDetail.setOnClickListener(view -> {
            Intrinsics.checkNotNullParameter(activityMainBinding, "$this_apply");
            LinearLayout linearLayout = activityMainBinding.bottomZoneAlertDetail;
            Intrinsics.checkNotNullExpressionValue(linearLayout, "bottomZoneAlertDetail");
            AppExtensionKt.hide(linearLayout);
            View view2 = activityMainBinding.outSideBottomZoneAlertDetail;
            Intrinsics.checkNotNullExpressionValue(view2, "outSideBottomZoneAlertDetail");
            AppExtensionKt.hide(view2);
        });
        getZoneAlertPopUpAdapter().callBackZoneAlertPopUp((i, str, str2, str3, str4, i2) -> {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(str2, "address");
            Intrinsics.checkNotNullParameter(str3, "latitudes");
            Intrinsics.checkNotNullParameter(str4, "longitudes");
            LinearLayout linearLayout = activityMainBinding.bottomZoneAlert;
            Intrinsics.checkNotNullExpressionValue(linearLayout, "bottomZoneAlert");
            AppExtensionKt.hide(linearLayout);
            View view = activityMainBinding.outSideBottomZoneAlert;
            Intrinsics.checkNotNullExpressionValue(view, "outSideBottomZoneAlert");
            AppExtensionKt.hide(view);
            LinearLayout linearLayout2 = activityMainBinding.bottomZoneAlertDetail;
            Intrinsics.checkNotNullExpressionValue(linearLayout2, "bottomZoneAlertDetail");
            AppExtensionKt.show(linearLayout2);
            View view2 = activityMainBinding.outSideBottomZoneAlertDetail;
            Intrinsics.checkNotNullExpressionValue(view2, "outSideBottomZoneAlertDetail");
            AppExtensionKt.show(view2);
            activityMainBinding.bottomPopupZoneAlertDetail.tvName.setText(str);
            activityMainBinding.bottomPopupZoneAlertDetail.tvAddress.setText(str2);
            MainActivity.this.latZoneAlert = str3;
            MainActivity.this.lngZoneAlert = str4;
            MainActivity mainActivity =  MainActivity.this;
            mainActivity.moveToLocation(ExtensionKt.toDouble(mainActivity.latZoneAlert), ExtensionKt.toDouble(MainActivity.this.lngZoneAlert));
            if (i2 == 1) {
                activityMainBinding.bottomPopupZoneAlertDetail.ivZoneAlert.setImageResource(R.drawable.ic_safe_zone_alert_popup);
                MainActivity.this.drawMyMarkerSafe();
                return;
            }
           activityMainBinding.bottomPopupZoneAlertDetail.ivZoneAlert.setImageResource(R.drawable.ic_danger_zone_alert_popup);
           MainActivity.this.drawMyMarkerDanger();
        });
        activityMainBinding.bottomPopupZoneAlertDetail.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intrinsics.checkNotNullParameter(activityMainBinding, "$this_apply");
                LinearLayout linearLayout = activityMainBinding.bottomZoneAlertDetail;
                Intrinsics.checkNotNullExpressionValue(linearLayout, "bottomZoneAlertDetail");
                AppExtensionKt.hide(linearLayout);
                View view2 = activityMainBinding.outSideBottomZoneAlertDetail;
                Intrinsics.checkNotNullExpressionValue(view2, "outSideBottomZoneAlertDetail");
                AppExtensionKt.hide(view2);

            }
        });
        activityMainBinding.bottomPopupZoneAlertDetail.ivDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intrinsics.checkNotNullParameter(activityMainBinding, "$this_apply");
                Intrinsics.checkNotNullParameter(MainActivity.this, "this$0");
                LinearLayout linearLayout = activityMainBinding.bottomZoneAlertDetail;
                Intrinsics.checkNotNullExpressionValue(linearLayout, "bottomZoneAlertDetail");
                AppExtensionKt.hide(linearLayout);
                View view2 = activityMainBinding.outSideBottomZoneAlertDetail;
                Intrinsics.checkNotNullExpressionValue(view2, "outSideBottomZoneAlertDetail");
                AppExtensionKt.hide(view2);
               startActivity(Intent.createChooser(new Intent("android.intent.action.VIEW", Uri.parse("http://maps.google.com/maps?f=d&hl=en&saddr=" + MainActivity.this.lat1 + "," + MainActivity.this.lng1 + "&daddr=" + MainActivity.this.latZoneAlert + "," + MainActivity.this.lngZoneAlert)), MainActivity.this.getString(R.string.custom_toast_main_Select_an_application)));
            }
        });
        activityMainBinding.icListHomeCategory.setOnClickListener(view -> {
            Intrinsics.checkNotNullParameter(MainActivity.this, "this$0");
           clickOpenPopupListCategory();

        });
    }

    private final void setViewWhenClickNumberLocation() {
        changeImageIconWhenTap(this.drawableYourLocationOne, this.drawableZoneAlertTwo, this.drawableFriendsTwo, this.drawableShareTwo);
        TextView textView = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvYourLocation;
        Intrinsics.checkNotNullExpressionValue(textView, "mBinding.bottomNavMain.tvYourLocation");
        TextView textView2 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvZoneAlert;
        Intrinsics.checkNotNullExpressionValue(textView2, "mBinding.bottomNavMain.tvZoneAlert");
        TextView textView3 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvFriend;
        Intrinsics.checkNotNullExpressionValue(textView3, "mBinding.bottomNavMain.tvFriend");
        TextView textView4 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvShare;
        Intrinsics.checkNotNullExpressionValue(textView4, "mBinding.bottomNavMain.tvShare");
        TextView textView5 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvYourLocation;
        Intrinsics.checkNotNullExpressionValue(textView5, "mBinding.bottomNavMain.tvYourLocation");
        AppExtensionKt.setColorText(textView5, R.color.color_0E1724);
        TextView textView6 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvZoneAlert;
        Intrinsics.checkNotNullExpressionValue(textView6, "mBinding.bottomNavMain.tvZoneAlert");
        AppExtensionKt.setColorText(textView6, R.color.color_6F7B8C);
        TextView textView7 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvFriend;
        Intrinsics.checkNotNullExpressionValue(textView7, "mBinding.bottomNavMain.tvFriend");
        AppExtensionKt.setColorText(textView7, R.color.color_6F7B8C);
        TextView textView8 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvShare;
        Intrinsics.checkNotNullExpressionValue(textView8, "mBinding.bottomNavMain.tvShare");
        AppExtensionKt.setColorText(textView8, R.color.color_6F7B8C);
        LinearLayout linearLayout = ((ActivityMainBinding) getMBinding()).bottomFriends;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "mBinding.bottomFriends");
        AppExtensionKt.hide(linearLayout);
        View view = ((ActivityMainBinding) getMBinding()).outSideBottomFriends;
        Intrinsics.checkNotNullExpressionValue(view, "mBinding.outSideBottomFriends");
        AppExtensionKt.hide(view);
        LinearLayout linearLayout2 = ((ActivityMainBinding) getMBinding()).bottomFriendsDetail;
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "mBinding.bottomFriendsDetail");
        AppExtensionKt.hide(linearLayout2);
        View view2 = ((ActivityMainBinding) getMBinding()).outSideBottomFriendsDetail;
        Intrinsics.checkNotNullExpressionValue(view2, "mBinding.outSideBottomFriendsDetail");
        AppExtensionKt.hide(view2);
        LinearLayout linearLayout3 = ((ActivityMainBinding) getMBinding()).bottomZoneAlert;
        Intrinsics.checkNotNullExpressionValue(linearLayout3, "mBinding.bottomZoneAlert");
        AppExtensionKt.hide(linearLayout3);
        View view3 = ((ActivityMainBinding) getMBinding()).outSideBottomZoneAlert;
        Intrinsics.checkNotNullExpressionValue(view3, "mBinding.outSideBottomZoneAlert");
        AppExtensionKt.hide(view3);
        LinearLayout linearLayout4 = ((ActivityMainBinding) getMBinding()).bottomZoneAlertDetail;
        Intrinsics.checkNotNullExpressionValue(linearLayout4, "mBinding.bottomZoneAlertDetail");
        AppExtensionKt.hide(linearLayout4);
        View view4 = ((ActivityMainBinding) getMBinding()).outSideBottomZoneAlertDetail;
        Intrinsics.checkNotNullExpressionValue(view4, "mBinding.outSideBottomZoneAlertDetail");
        AppExtensionKt.hide(view4);
        getMyLocation();
    }
    private final void setViewWhenClickZoneAlter() {
        changeImageIconWhenTap(this.drawableYourLocationTwo, this.drawableZoneAlertOne, this.drawableFriendsTwo, this.drawableShareTwo);
        TextView textView = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvZoneAlert;
        Intrinsics.checkNotNullExpressionValue(textView, "mBinding.bottomNavMain.tvZoneAlert");
        TextView textView2 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvYourLocation;
        Intrinsics.checkNotNullExpressionValue(textView2, "mBinding.bottomNavMain.tvYourLocation");
        TextView textView3 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvFriend;
        Intrinsics.checkNotNullExpressionValue(textView3, "mBinding.bottomNavMain.tvFriend");
        TextView textView4 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvShare;
        Intrinsics.checkNotNullExpressionValue(textView4, "mBinding.bottomNavMain.tvShare");
        TextView textView5 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvZoneAlert;
        Intrinsics.checkNotNullExpressionValue(textView5, "mBinding.bottomNavMain.tvZoneAlert");
        AppExtensionKt.setColorText(textView5, R.color.color_0E1724);
        TextView textView6 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvYourLocation;
        Intrinsics.checkNotNullExpressionValue(textView6, "mBinding.bottomNavMain.tvYourLocation");
        AppExtensionKt.setColorText(textView6, R.color.color_6F7B8C);
        TextView textView7 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvFriend;
        Intrinsics.checkNotNullExpressionValue(textView7, "mBinding.bottomNavMain.tvFriend");
        AppExtensionKt.setColorText(textView7, R.color.color_6F7B8C);
        TextView textView8 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvShare;
        Intrinsics.checkNotNullExpressionValue(textView8, "mBinding.bottomNavMain.tvShare");
        AppExtensionKt.setColorText(textView8, R.color.color_6F7B8C);
        LinearLayout linearLayout = ((ActivityMainBinding) getMBinding()).bottomFriends;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "mBinding.bottomFriends");
        AppExtensionKt.hide(linearLayout);
        View view = ((ActivityMainBinding) getMBinding()).outSideBottomFriends;
        Intrinsics.checkNotNullExpressionValue(view, "mBinding.outSideBottomFriends");
        AppExtensionKt.hide(view);
        LinearLayout linearLayout2 = ((ActivityMainBinding) getMBinding()).bottomFriendsDetail;
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "mBinding.bottomFriendsDetail");
        AppExtensionKt.hide(linearLayout2);
        View view2 = ((ActivityMainBinding) getMBinding()).outSideBottomFriendsDetail;
        Intrinsics.checkNotNullExpressionValue(view2, "mBinding.outSideBottomFriendsDetail");
        AppExtensionKt.hide(view2);
        LinearLayout linearLayout3 = ((ActivityMainBinding) getMBinding()).bottomZoneAlert;
        Intrinsics.checkNotNullExpressionValue(linearLayout3, "mBinding.bottomZoneAlert");
        AppExtensionKt.show(linearLayout3);
        View view3 = ((ActivityMainBinding) getMBinding()).outSideBottomZoneAlert;
        Intrinsics.checkNotNullExpressionValue(view3, "mBinding.outSideBottomZoneAlert");
        AppExtensionKt.show(view3);
        LinearLayout linearLayout4 = ((ActivityMainBinding) getMBinding()).bottomZoneAlertDetail;
        Intrinsics.checkNotNullExpressionValue(linearLayout4, "mBinding.bottomZoneAlertDetail");
        AppExtensionKt.hide(linearLayout4);
        View view4 = ((ActivityMainBinding) getMBinding()).outSideBottomZoneAlertDetail;
        Intrinsics.checkNotNullExpressionValue(view4, "mBinding.outSideBottomZoneAlertDetail");
        AppExtensionKt.hide(view4);
        getListAlertZoneDB();
    }
    private final void setViewWhenClickFriend() {
        changeImageIconWhenTap(this.drawableYourLocationTwo, this.drawableZoneAlertTwo, this.drawableFriendsOne, this.drawableShareTwo);
        TextView textView = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvFriend;
        Intrinsics.checkNotNullExpressionValue(textView, "mBinding.bottomNavMain.tvFriend");
        TextView textView2 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvZoneAlert;
        Intrinsics.checkNotNullExpressionValue(textView2, "mBinding.bottomNavMain.tvZoneAlert");
        TextView textView3 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvYourLocation;
        Intrinsics.checkNotNullExpressionValue(textView3, "mBinding.bottomNavMain.tvYourLocation");
        TextView textView4 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvShare;
        Intrinsics.checkNotNullExpressionValue(textView4, "mBinding.bottomNavMain.tvShare");
        TextView textView5 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvFriend;
        Intrinsics.checkNotNullExpressionValue(textView5, "mBinding.bottomNavMain.tvFriend");
        AppExtensionKt.setColorText(textView5, R.color.color_0E1724);
        TextView textView6 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvZoneAlert;
        Intrinsics.checkNotNullExpressionValue(textView6, "mBinding.bottomNavMain.tvZoneAlert");
        AppExtensionKt.setColorText(textView6, R.color.color_6F7B8C);
        TextView textView7 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvYourLocation;
        Intrinsics.checkNotNullExpressionValue(textView7, "mBinding.bottomNavMain.tvYourLocation");
        AppExtensionKt.setColorText(textView7, R.color.color_6F7B8C);
        TextView textView8 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvShare;
        Intrinsics.checkNotNullExpressionValue(textView8, "mBinding.bottomNavMain.tvShare");
        AppExtensionKt.setColorText(textView8, R.color.color_6F7B8C);
        LinearLayout linearLayout = ((ActivityMainBinding) getMBinding()).bottomFriends;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "mBinding.bottomFriends");
        AppExtensionKt.show(linearLayout);
        View view = ((ActivityMainBinding) getMBinding()).outSideBottomFriends;
        Intrinsics.checkNotNullExpressionValue(view, "mBinding.outSideBottomFriends");
        AppExtensionKt.show(view);
        LinearLayout linearLayout2 = ((ActivityMainBinding) getMBinding()).bottomFriendsDetail;
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "mBinding.bottomFriendsDetail");
        AppExtensionKt.hide(linearLayout2);
        View view2 = ((ActivityMainBinding) getMBinding()).outSideBottomFriendsDetail;
        Intrinsics.checkNotNullExpressionValue(view2, "mBinding.outSideBottomFriendsDetail");
        AppExtensionKt.hide(view2);
        LinearLayout linearLayout3 = ((ActivityMainBinding) getMBinding()).bottomZoneAlert;
        Intrinsics.checkNotNullExpressionValue(linearLayout3, "mBinding.bottomZoneAlert");
        AppExtensionKt.hide(linearLayout3);
        View view3 = ((ActivityMainBinding) getMBinding()).outSideBottomZoneAlert;
        Intrinsics.checkNotNullExpressionValue(view3, "mBinding.outSideBottomZoneAlert");
        AppExtensionKt.hide(view3);
        LinearLayout linearLayout4 = ((ActivityMainBinding) getMBinding()).bottomZoneAlertDetail;
        Intrinsics.checkNotNullExpressionValue(linearLayout4, "mBinding.bottomZoneAlertDetail");
        AppExtensionKt.hide(linearLayout4);
        View view4 = ((ActivityMainBinding) getMBinding()).outSideBottomZoneAlertDetail;
        Intrinsics.checkNotNullExpressionValue(view4, "mBinding.outSideBottomZoneAlertDetail");
        AppExtensionKt.hide(view4);

    }

    private final void setViewWhenClickShare() {
        changeImageIconWhenTap(this.drawableYourLocationTwo, this.drawableZoneAlertTwo, this.drawableFriendsTwo, this.drawableShareOne);
        TextView textView = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvShare;
        Intrinsics.checkNotNullExpressionValue(textView, "mBinding.bottomNavMain.tvShare");
        TextView textView2 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvZoneAlert;
        Intrinsics.checkNotNullExpressionValue(textView2, "mBinding.bottomNavMain.tvZoneAlert");
        TextView textView3 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvFriend;
        Intrinsics.checkNotNullExpressionValue(textView3, "mBinding.bottomNavMain.tvFriend");
        TextView textView4 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvYourLocation;
        Intrinsics.checkNotNullExpressionValue(textView4, "mBinding.bottomNavMain.tvYourLocation");
        TextView textView5 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvShare;
        Intrinsics.checkNotNullExpressionValue(textView5, "mBinding.bottomNavMain.tvShare");
        AppExtensionKt.setColorText(textView5, R.color.color_0E1724);
        TextView textView6 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvZoneAlert;
        Intrinsics.checkNotNullExpressionValue(textView6, "mBinding.bottomNavMain.tvZoneAlert");
        AppExtensionKt.setColorText(textView6, R.color.color_6F7B8C);
        TextView textView7 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvFriend;
        Intrinsics.checkNotNullExpressionValue(textView7, "mBinding.bottomNavMain.tvFriend");
        AppExtensionKt.setColorText(textView7, R.color.color_6F7B8C);
        TextView textView8 = ((ActivityMainBinding) getMBinding()).bottomNavMain.tvYourLocation;
        Intrinsics.checkNotNullExpressionValue(textView8, "mBinding.bottomNavMain.tvYourLocation");
        AppExtensionKt.setColorText(textView8, R.color.color_6F7B8C);
        LinearLayout linearLayout = ((ActivityMainBinding) getMBinding()).bottomFriends;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "mBinding.bottomFriends");
        AppExtensionKt.hide(linearLayout);
        View view = ((ActivityMainBinding) getMBinding()).outSideBottomFriends;
        Intrinsics.checkNotNullExpressionValue(view, "mBinding.outSideBottomFriends");
        AppExtensionKt.hide(view);
        LinearLayout linearLayout2 = ((ActivityMainBinding) getMBinding()).bottomFriendsDetail;
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "mBinding.bottomFriendsDetail");
        AppExtensionKt.hide(linearLayout2);
        View view2 = ((ActivityMainBinding) getMBinding()).outSideBottomFriendsDetail;
        Intrinsics.checkNotNullExpressionValue(view2, "mBinding.outSideBottomFriendsDetail");
        AppExtensionKt.hide(view2);
        LinearLayout linearLayout3 = ((ActivityMainBinding) getMBinding()).bottomZoneAlert;
        Intrinsics.checkNotNullExpressionValue(linearLayout3, "mBinding.bottomZoneAlert");
        AppExtensionKt.hide(linearLayout3);
        View view3 = ((ActivityMainBinding) getMBinding()).outSideBottomZoneAlert;
        Intrinsics.checkNotNullExpressionValue(view3, "mBinding.outSideBottomZoneAlert");
        AppExtensionKt.hide(view3);
        LinearLayout linearLayout4 = ((ActivityMainBinding) getMBinding()).bottomZoneAlertDetail;
        Intrinsics.checkNotNullExpressionValue(linearLayout4, "mBinding.bottomZoneAlertDetail");
        AppExtensionKt.hide(linearLayout4);
        View view4 = ((ActivityMainBinding) getMBinding()).outSideBottomZoneAlertDetail;
        Intrinsics.checkNotNullExpressionValue(view4, "mBinding.outSideBottomZoneAlertDetail");
        AppExtensionKt.hide(view4);
        onShareLink("http://maps.google.com/maps?f=d&hl=en&saddr=&daddr=" + this.lat1 + "," + this.lng1);
            onWindowFocusChanged(true);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    private final void changeImageIconWhenTap(int i, int i2, int i3, int i4) {
        ActivityMainBinding activityMainBinding = (ActivityMainBinding) getMBinding();
        activityMainBinding.bottomNavMain.ivYourLocation.setImageResource(i);
        activityMainBinding.bottomNavMain.ivZoneAlert.setImageResource(i2);
        activityMainBinding.bottomNavMain.ivFriend.setImageResource(i3);
        activityMainBinding.bottomNavMain.ivShare.setImageResource(i4);
    }

    @SuppressLint("WrongConstant")
    public final void setRecyclerViewFriendsPopUp() {
        UserFriendAdapter adapter = new UserFriendAdapter(friendsdata);
        RecyclerView recyclerView = ((ActivityMainBinding) getMBinding()).bottomPopupFriends.recyclerView;
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        ViewModal viewmodal = ViewModelProviders.of(this).get(ViewModal.class);
        viewmodal.alluser().observe(this, models -> {
            adapter.submitList(models);
            if (adapter.getItemCount()>0){
                getMBinding().bottomPopupFriends.tvContent.setVisibility(View.GONE);
            }
            else {
                getMBinding().bottomPopupFriends.tvContent.setVisibility(View.VISIBLE);
            }
        });
    }
    @SuppressLint("WrongConstant")
    private final void getListAlertZoneDB() {
        ZoneAlertDao zoneDAO;
        List<AlertZoneEntity> listZone;
        List<AlertZoneEntity> list2 = this.listZoneAlert;
        boolean z = true;
        if (list2 != null && (list2.isEmpty() ^ true)) {
            this.listZoneAlert = new ArrayList();
        }
        Context context = this;
        UserDatabase instance = UserDatabase.Companion.getInstance(context);
        List<AlertZoneEntity> reversed = (instance == null || (zoneDAO = instance.zoneDAO()) == null || (listZone = zoneDAO.getListZone()) == null) ? null : CollectionsKt.reversed(listZone);
        this.listZoneAlert = reversed;
        if (reversed != null) {
            getZoneAlertPopUpAdapter().setData(reversed);
        }
        List<AlertZoneEntity> list3 = this.listZoneAlert;
        if (list3 == null || !(!list3.isEmpty())) {
            z = false;
        }
        if (z) {
            RecyclerView recyclerView = ((ActivityMainBinding) getMBinding()).bottomPopupZoneAlter.recyclerView;
            Intrinsics.checkNotNullExpressionValue(recyclerView, "mBinding.bottomPopupZoneAlter.recyclerView");
            AppExtensionKt.show(recyclerView);
            ImageView imageView = ((ActivityMainBinding) getMBinding()).bottomPopupZoneAlter.ivZoneAlert;
            Intrinsics.checkNotNullExpressionValue(imageView, "mBinding.bottomPopupZoneAlter.ivZoneAlert");
            AppExtensionKt.hide(imageView);
            TextView textView = ((ActivityMainBinding) getMBinding()).bottomPopupZoneAlter.tvContent;
            Intrinsics.checkNotNullExpressionValue(textView, "mBinding.bottomPopupZoneAlter.tvContent");
            AppExtensionKt.hide(textView);
        } else {
            RecyclerView recyclerView2 = ((ActivityMainBinding) getMBinding()).bottomPopupZoneAlter.recyclerView;
            Intrinsics.checkNotNullExpressionValue(recyclerView2, "mBinding.bottomPopupZoneAlter.recyclerView");
            AppExtensionKt.hide(recyclerView2);
            ImageView imageView2 = ((ActivityMainBinding) getMBinding()).bottomPopupZoneAlter.ivZoneAlert;
            Intrinsics.checkNotNullExpressionValue(imageView2, "mBinding.bottomPopupZoneAlter.ivZoneAlert");
            AppExtensionKt.show(imageView2);
            TextView textView2 = ((ActivityMainBinding) getMBinding()).bottomPopupZoneAlter.tvContent;
            Intrinsics.checkNotNullExpressionValue(textView2, "mBinding.bottomPopupZoneAlter.tvContent");
            AppExtensionKt.show(textView2);
        }
        RecyclerView recyclerView3 = ((ActivityMainBinding) getMBinding()).bottomPopupZoneAlter.recyclerView;
        recyclerView3.setLayoutManager(new LinearLayoutManager(context, 0, false));
        recyclerView3.setAdapter(getZoneAlertPopUpAdapter());
    }

    private final void getMyLocation() {
        if (this.gmap != null) {
            askForLocations();
            pushlocation();
            this.airloc = new AirLocation(MainActivity.this, true, true, new AirLocation.Callbacks() {
                @Override
                public void onSuccess(@NonNull Location location) {

                    try {
                        if (homeMarker != null) {
                            Marker access$getHomeMarker$p = homeMarker;
                            Intrinsics.checkNotNull(access$getHomeMarker$p);
                            access$getHomeMarker$p.remove();
                        }
                       ll = new LatLng(lat1, lng1);
                        myAddress();
                       drawMyMarker();
                        GoogleMap access$getGmap$p = gmap;
                        if (access$getGmap$p != null) {
                            access$getGmap$p.animateCamera(CameraUpdateFactory.newLatLngZoom(ll, 14.0f));
                        }
                        drawMarkerUser();
                    } catch (Exception unused) {
                        CustomToast.Companion.customToast(getString(R.string.custom_toast_main_Failed_get_device_position), getApplicationContext());
                    }
                }
                @Override
                public void onFailed(@NonNull AirLocation.LocationFailedEnum locationFailedEnum) {
                    Intrinsics.checkNotNullParameter(locationFailedEnum, "locationFailedEnum");
                    CustomToast.Companion.customToast(getString(R.string.custom_toast_create_zone_Failed_get_current_position), getApplicationContext());
                }
            });
        }
    }

    private final void initMap() {

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        AirLocation airLocation = this.airloc;
        if (airLocation == null) {
            Intrinsics.throwUninitializedPropertyAccessException("airloc");
            airLocation = null;
        }
        airLocation.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != this.REQUEST_CODE_OPTIONS) {
            return;
        }
        else if (resultCode == -1) {
            Context context = this;
            if (MyApp.Companion.getNormalMap(context)) {
                GoogleMap googleMap = this.gmap;
                if (googleMap != null) {
                    googleMap.setMapType(1);
                }
            } else if (MyApp.Companion.getHybridMap(context)) {
                GoogleMap googleMap2 = this.gmap;
                if (googleMap2 != null) {
                    googleMap2.setMapType(4);
                }
            } else if (MyApp.Companion.getSatelliteMap(context)) {
                GoogleMap googleMap3 = this.gmap;
                if (googleMap3 != null) {
                    googleMap3.setMapType(2);
                }
            } else if (MyApp.Companion.getTerrainMap(context)) {
                GoogleMap googleMap4 = this.gmap;
                if (googleMap4 != null) {
                    googleMap4.setMapType(3);
                }
            } else {
                GoogleMap googleMap5 = this.gmap;
                if (googleMap5 != null) {
                    googleMap5.setMapType(1);
                }
            }
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Intrinsics.checkNotNullParameter(strArr, "permissions");
        Intrinsics.checkNotNullParameter(iArr, "grantResults");
        AirLocation airLocation = this.airloc;
        if (airLocation == null) {
            Intrinsics.throwUninitializedPropertyAccessException("airloc");
            airLocation = null;
        }
        airLocation.onRequestPermissionsResult(i, strArr, iArr);
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    public void onMapReady(GoogleMap googleMap) {
        this.gmap = googleMap;
        Context context = this;

        if (MyApp.Companion.getNormalMap(context)) {
            GoogleMap googleMap2 = this.gmap;
            if (googleMap2 != null) {
                googleMap2.setMapType(1);
            }
        } else if (MyApp.Companion.getHybridMap(context)) {
            GoogleMap googleMap3 = this.gmap;
            if (googleMap3 != null) {
                googleMap3.setMapType(4);
            }
        } else if (MyApp.Companion.getSatelliteMap(context)) {
            GoogleMap googleMap4 = this.gmap;
            if (googleMap4 != null) {
                googleMap4.setMapType(2);
            }
        } else if (MyApp.Companion.getTerrainMap(context)) {
            GoogleMap googleMap5 = this.gmap;
            if (googleMap5 != null) {
                googleMap5.setMapType(3);
            }
        } else {
            GoogleMap googleMap6 = this.gmap;
            if (googleMap6 != null) {
                googleMap6.setMapType(1);
            }
        }
        if (this.gmap != null) {
            this.airloc = new AirLocation(MainActivity.this, true, true, new AirLocation.Callbacks() {
                @Override
                public void onSuccess(@NonNull Location location) {
                    Intrinsics.checkNotNullParameter(location, FirebaseAnalytics.Param.LOCATION);
                    try {
                        lat1 = location.getLatitude();
                        lng1 = location.getLongitude();
                        pushlocation();
                        ll = new LatLng(lat1, lng1);
                        myAddress();
                        drawMyMarker();
                        GoogleMap access$getGmap$p = gmap;
                        if (access$getGmap$p != null) {
                            access$getGmap$p.animateCamera(CameraUpdateFactory.newLatLngZoom(ll, 14.0f));
                        }
                        drawMarkerUser();
                    } catch (Exception unused) {
                        CustomToast.Companion.customToast(getString(R.string.custom_toast_create_zone_Failed_get_current_position),getApplicationContext());
                    }
                }
                @Override
                public void onFailed(@NonNull AirLocation.LocationFailedEnum locationFailedEnum) {
                    Intrinsics.checkNotNullParameter(locationFailedEnum, "locationFailedEnum");
                    CustomToast.Companion.customToast(getString(R.string.custom_toast_create_zone_Failed_get_current_position),getApplicationContext());
                }
            });
        }
    }
    private final BitmapDescriptor getMarkerIconFromDrawable(Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(createBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(createBitmap);
    }
    public final void myAddress() {
        try {
            List<Address> fromLocation = new Geocoder(this, Locale.getDefault()).getFromLocation(this.lat1, this.lng1, 1);
            boolean z = true;
            if (fromLocation == null || !(!fromLocation.isEmpty())) {
                z = false;
            }
            if (z) {
                String addressLine = fromLocation.get(0).getAddressLine(0);
                Intrinsics.checkNotNullExpressionValue(addressLine, "addresses[0].getAddressLine(0)");
                this.myaddressnow = addressLine;
                String locality = fromLocation.get(0).getLocality();
                Intrinsics.checkNotNullExpressionValue(locality, "addresses[0].locality");
                this.mcity = locality;
                String adminArea = fromLocation.get(0).getAdminArea();
                Intrinsics.checkNotNullExpressionValue(adminArea, "addresses[0].adminArea");
                this.mstate = adminArea;
                String countryName = fromLocation.get(0).getCountryName();
                Intrinsics.checkNotNullExpressionValue(countryName, "addresses[0].countryName");
                this.mcountry = countryName;
                String postalCode = fromLocation.get(0).getPostalCode();
                Intrinsics.checkNotNullExpressionValue(postalCode, "addresses[0].postalCode");
                this.mpostalCode = postalCode;
            }
        } catch (Exception unused) {
        }
    }

    public final void drawMarkerUser() {
        UserTrackedDao userDAO;
        List<UserTracked> checkedFriends;
        Marker marker;
        Marker marker2;
        GoogleMap googleMap = this.gmap;
        if (googleMap != null) {
            googleMap.clear();
        }
        drawMyMarker();
        Marker marker3 = null;
        for (InforSaved inforSaved : this.friendList) {
            Context context = this;
            UserDatabase instance = UserDatabase.Companion.getInstance(context);
            if (!(instance == null || (userDAO = instance.userDAO()) == null || (checkedFriends = userDAO.getCheckedFriends(inforSaved.getlocateuid())) == null)) {
                for (UserTracked checked : checkedFriends) {
                    if (Intrinsics.areEqual((Object) checked.getChecked(), (Object) true)) {
                        String str = "file:///android_asset/avatar/avatar1.png";
                        boolean z = false;
                        if (Intrinsics.areEqual((Object) inforSaved.getSos(), (Object) true)) {
                            CharSequence avatar = inforSaved.getAvatar();
                            if (avatar == null || avatar.length() == 0) {
                                z = true;
                            }
                            if (!z && !Intrinsics.areEqual((Object) inforSaved.getAvatar(), (Object) "null")) {
                                str = inforSaved.getAvatar();
                            }
                            Drawable drawable = ContextCompat.getDrawable(context, setAvatarMarkerFriendsSos(str));
                            Intrinsics.checkNotNull(drawable);
                            BitmapDescriptor markerIconFromDrawable = getMarkerIconFromDrawable(new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(DrawableKt.toBitmap(drawable, 340, 340, (Bitmap.Config) null), 340, 340, true)));
                            GoogleMap googleMap2 = this.gmap;
                            Marker addMarker = googleMap2 != null ? googleMap2.addMarker(new MarkerOptions().position(new LatLng(ExtensionKt.toDouble(inforSaved.getLatitudes()), ExtensionKt.toDouble(inforSaved.getLongitudes()))).title(inforSaved.getnickname()).snippet(inforSaved.getSos_msg()).icon(markerIconFromDrawable)) : null;
                            CustomInfoWindowAdapter customInfoWindowAdapter = new CustomInfoWindowAdapter(this);
                            GoogleMap googleMap3 = this.gmap;
                            if (googleMap3 != null) {
                                googleMap3.setInfoWindowAdapter(customInfoWindowAdapter);
                            }
                            if (addMarker != null) {
                                addMarker.showInfoWindow();
                            }
                            marker = marker3;
                        } else {
                            CharSequence avatar2 = inforSaved.getAvatar();
                            if (!(avatar2 == null || avatar2.length() == 0) && !Intrinsics.areEqual((Object) inforSaved.getAvatar(), (Object) "null")) {
                                str = inforSaved.getAvatar();
                            }
                            Log.d("iiiiiiavatar333", str);
                            Drawable drawable2 = ContextCompat.getDrawable(context, setAvatarMarkerFriends(str));
                            Intrinsics.checkNotNull(drawable2);
                            BitmapDescriptor markerIconFromDrawable2 = getMarkerIconFromDrawable(new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(DrawableKt.toBitmap(drawable2, 80, 90, (Bitmap.Config) null), 110, 110, true)));
                            GoogleMap googleMap4 = this.gmap;
                            if (googleMap4 != null) {
                                marker = marker3;
                                marker2 = googleMap4.addMarker(new MarkerOptions().position(new LatLng(ExtensionKt.toDouble(inforSaved.getLatitudes()), ExtensionKt.toDouble(inforSaved.getLongitudes()))).title(inforSaved.getnickname()).snippet(inforSaved.getAddress()).icon(markerIconFromDrawable2));
                            } else {
                                marker = marker3;
                                marker2 = null;
                            }
                            CustomInfoWindowAdapter customInfoWindowAdapter2 = new CustomInfoWindowAdapter(this);
                            GoogleMap googleMap5 = this.gmap;
                            if (googleMap5 != null) {
                                googleMap5.setInfoWindowAdapter(customInfoWindowAdapter2);
                            }
                            if (marker2 != null) {
                                marker2.showInfoWindow();
                            }
                            if (Intrinsics.areEqual(this.lat2, ExtensionKt.toDouble(inforSaved.getLatitudes()))) {
                                if (this.lng2 == ExtensionKt.toDouble(inforSaved.getLongitudes())) {
                                    z = true;
                                }
                                if (z) {
                                    marker3 = marker2;
                                }
                            }
                        }
                        marker3 = marker;
                    } else {
                        Marker marker4 = marker3;
                    }
                }
                Marker marker5 = marker3;
            }
        }
        if (marker3 != null) {
            marker3.showInfoWindow();
        }
    }

    private final int setAvatarMarkerFriends(String str) {
        switch (str.hashCode()) {
            case -2010351075:
                boolean equals = str.equals("file:///android_asset/avatar/avatar1.png");
                return R.drawable.marker_avatar_1;
            case -2009427554:
                return !str.equals("file:///android_asset/avatar/avatar2.png") ? R.drawable.marker_avatar_1 : R.drawable.marker_avatar_2;
            case -2008504033:
                return !str.equals("file:///android_asset/avatar/avatar3.png") ? R.drawable.marker_avatar_1 : R.drawable.marker_avatar_3;
            case -2007580512:
                return !str.equals("file:///android_asset/avatar/avatar4.png") ? R.drawable.marker_avatar_1 : R.drawable.marker_avatar_4;
            case -2006656991:
                return !str.equals("file:///android_asset/avatar/avatar5.png") ? R.drawable.marker_avatar_1 : R.drawable.marker_avatar_5;
            case -2005733470:
                return !str.equals("file:///android_asset/avatar/avatar6.png") ? R.drawable.marker_avatar_1 : R.drawable.marker_avatar_6;
            case -2004809949:
                return !str.equals("file:///android_asset/avatar/avatar7.png") ? R.drawable.marker_avatar_1 : R.drawable.marker_avatar_7;
            case -2003886428:
                return !str.equals("file:///android_asset/avatar/avatar8.png") ? R.drawable.marker_avatar_1 : R.drawable.marker_avatar_8;
            case -2002962907:
                return !str.equals("file:///android_asset/avatar/avatar9.png") ? R.drawable.marker_avatar_1 : R.drawable.marker_avatar_9;
            case 2103509193:
                return !str.equals("file:///android_asset/avatar/avatar10.png") ? R.drawable.marker_avatar_1 : R.drawable.marker_avatar_10;
            case 2104432714:
                return !str.equals("file:///android_asset/avatar/avatar11.png") ? R.drawable.marker_avatar_1 : R.drawable.marker_avatar_11;
            case 2105356235:
                return !str.equals("file:///android_asset/avatar/avatar12.png") ? R.drawable.marker_avatar_1 : R.drawable.marker_avatar_12;
            default:
                return R.drawable.marker_avatar_1;
        }
    }

    private final int setAvatarMarkerFriendsSos(String str) {
        switch (str.hashCode()) {
            case -2010351075:
                boolean equals = str.equals("file:///android_asset/avatar/avatar1.png");
                return R.drawable.marker_avatar_sos_1;
            case -2009427554:
                return !str.equals("file:///android_asset/avatar/avatar2.png") ? R.drawable.marker_avatar_sos_1 : R.drawable.marker_avatar_sos_2;
            case -2008504033:
                return !str.equals("file:///android_asset/avatar/avatar3.png") ? R.drawable.marker_avatar_sos_1 : R.drawable.marker_avatar_sos_3;
            case -2007580512:
                return !str.equals("file:///android_asset/avatar/avatar4.png") ? R.drawable.marker_avatar_sos_1 : R.drawable.marker_avatar_sos_4;
            case -2006656991:
                return !str.equals("file:///android_asset/avatar/avatar5.png") ? R.drawable.marker_avatar_sos_1 : R.drawable.marker_avatar_sos_5;
            case -2005733470:
                return !str.equals("file:///android_asset/avatar/avatar6.png") ? R.drawable.marker_avatar_sos_1 : R.drawable.marker_avatar_sos_6;
            case -2004809949:
                return !str.equals("file:///android_asset/avatar/avatar7.png") ? R.drawable.marker_avatar_sos_1 : R.drawable.marker_avatar_sos_7;
            case -2003886428:
                return !str.equals("file:///android_asset/avatar/avatar8.png") ? R.drawable.marker_avatar_sos_1 : R.drawable.marker_avatar_sos_8;
            case -2002962907:
                return !str.equals("file:///android_asset/avatar/avatar9.png") ? R.drawable.marker_avatar_sos_1 : R.drawable.marker_avatar_sos_9;
            case 2103509193:
                return !str.equals("file:///android_asset/avatar/avatar10.png") ? R.drawable.marker_avatar_sos_1 : R.drawable.marker_avatar_sos_10;
            case 2104432714:
                return !str.equals("file:///android_asset/avatar/avatar11.png") ? R.drawable.marker_avatar_sos_1 : R.drawable.marker_avatar_sos_11;
            case 2105356235:
                return !str.equals("file:///android_asset/avatar/avatar12.png") ? R.drawable.marker_avatar_sos_1 : R.drawable.marker_avatar_sos_12;
            default:
                return R.drawable.marker_avatar_sos_1;
        }
    }
    public final void drawMyMarker() {
        GoogleMap googleMap = this.gmap;
        if (googleMap != null) {
            googleMap.clear();
        }
        LatLng latLng = this.ll;
        if (latLng != null) {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_marker);
            Intrinsics.checkNotNullExpressionValue(drawable, "cm");
            BitmapDescriptor markerIconFromDrawable = getMarkerIconFromDrawable(new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(DrawableKt.toBitmap(drawable, 80, 90, (Bitmap.Config) null) ,110, 110, true)));
            GoogleMap googleMap2 = this.gmap;
            this.homeMarker = googleMap2 != null ? googleMap2.addMarker(new MarkerOptions().position(latLng).title(getString(R.string.custom_toast_main_My_Device)).snippet(this.myaddressnow).icon(markerIconFromDrawable)) : null;
            CustomInfoWindowAdapter customInfoWindowAdapter = new CustomInfoWindowAdapter(this);
            GoogleMap googleMap3 = this.gmap;
            if (googleMap3 != null) {
                googleMap3.setInfoWindowAdapter(customInfoWindowAdapter);
            }
            Marker marker = this.homeMarker;
            if (marker != null) {
                marker.showInfoWindow();
            }
        }
    }

    public final void drawMyMarkerSafe() {
        if (this.ll != null) {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_marker_safe);
            Intrinsics.checkNotNullExpressionValue(drawable, "cm");
            BitmapDescriptor markerIconFromDrawable = getMarkerIconFromDrawable(new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(DrawableKt.toBitmap(drawable, 400, 400, (Bitmap.Config) null), 400, 400, true)));
            GoogleMap googleMap = this.gmap;
            Marker addMarker = googleMap != null ? googleMap.addMarker(new MarkerOptions().position(new LatLng(ExtensionKt.toDouble(this.latZoneAlert), ExtensionKt.toDouble(this.lngZoneAlert))).icon(markerIconFromDrawable)) : null;
            GoogleMap googleMap2 = this.gmap;
            if (googleMap2 != null) {
                googleMap2.setInfoWindowAdapter((GoogleMap.InfoWindowAdapter) null);
            }
            if (addMarker != null) {
                addMarker.showInfoWindow();
            }
        }
    }
    public final void drawMyMarkerDanger() {
        if (this.ll != null) {
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_marker_danger);
            Intrinsics.checkNotNull(drawable);
            BitmapDescriptor markerIconFromDrawable = getMarkerIconFromDrawable(new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(DrawableKt.toBitmap(drawable, 400, 400, (Bitmap.Config) null), 400, 400, true)));
            GoogleMap googleMap = this.gmap;
            Marker addMarker = googleMap != null ? googleMap.addMarker(new MarkerOptions().position(new LatLng(ExtensionKt.toDouble(this.latZoneAlert), ExtensionKt.toDouble(this.lngZoneAlert))).icon(markerIconFromDrawable)) : null;
            GoogleMap googleMap2 = this.gmap;
            if (googleMap2 != null) {
                googleMap2.setInfoWindowAdapter((GoogleMap.InfoWindowAdapter) null);
            }
            if (addMarker != null) {
                addMarker.showInfoWindow();
            }
        }
    }

    public final void moveToLocation(double d, double d2) {
        GoogleMap googleMap = this.gmap;
        if (googleMap != null) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(d, d2), 14.0f));
        }
    }
    public void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, PayPalService.class));
    }

    public void onResume() {
        super.onResume();
        askForLocations();
        refreshService();
        setViewWhenClickNumberLocation();
    }

    public void onStart() {
        super.onStart();
        pushlocation();
    }
    public final void pushlocation() {
        if (Common.Companion.getCheckShare()) {
            AppAuthen.AuthUser currentUser = AppAuthen.getInstance().getCurrentUser();
            Intrinsics.checkNotNull(currentUser);
            try {
                DatabaseReference child = FirebaseDatabase.getInstance().getReference().child(RTDB_DEFINE.TABLE_USER).child(currentUser.uid);
                Intrinsics.checkNotNullExpressionValue(child, "getInstance().reference.….TABLE_USER).child(f.uid)");
                HashMap hashMap = new HashMap();
                hashMap.put(RTDB_DEFINE.TBL_SAVE.INSTANCE.getLATITUDES(), String.valueOf(this.lat1));
                hashMap.put(RTDB_DEFINE.TBL_SAVE.INSTANCE.getLONGITUDES(), String.valueOf(this.lng1));
                child.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        Intrinsics.checkNotNullParameter(task, "tasks");
                        task.isSuccessful();
                    }
                });
            } catch (Exception unused) {
            }
        }
    }
    @SuppressLint("MissingPermission")
    private final void askForLocations() {
        @SuppressLint("WrongConstant") Object systemService = getSystemService(FirebaseAnalytics.Param.LOCATION);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        LocationManager locationManager = (LocationManager) systemService;

        if (Build.VERSION.SDK_INT >= 28 && locationManager.isLocationEnabled() && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            try {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, Common.Companion.getMIN_TIME_GPS(), Common.Companion.getMIN_DISTANCE_GPS(), new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        Intrinsics.checkNotNullParameter(location, "p0");
                        try {
                            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (lastKnownLocation != null) {
                                lng1 = lastKnownLocation.getLongitude();
                                lat1 = lastKnownLocation.getLatitude();
                                ll = new LatLng(lat1, lng1);
                                drawMarkerUser();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                        LocationListener.super.onStatusChanged(provider, status, extras);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private final void refreshService() {
        if (Build.VERSION.SDK_INT >= 26) {
            Log.d("@@247","map not visible");
            startForegroundService(new Intent(this, GPSLocationService.class));
        } else {
            startService(new Intent(this, GPSLocationService.class));
        }
    }
    private final void clickOpenPopupStymap() {
        try {
            Context context = this;
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.SheetDialog);
            LayoutStyleMapBinding inflate = LayoutStyleMapBinding.inflate(LayoutInflater.from(context));
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(this@MainActivity))");
            bottomSheetDialog.setContentView((View) inflate.getRoot());
            getStyleMapAdapter().setData(this.listStyleMap);
            RecyclerView recyclerView = inflate.recyclerView;
            recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 3));
            recyclerView.setAdapter(getStyleMapAdapter());
            setChecked();
            getStyleMapAdapter().callBackStyleMap(i -> {

                MainActivity.this.getStyleMapAdapter().checkSelectView(i);
                setStyleMap(i);
                bottomSheetDialog.dismiss();
            });
            inflate.ivClose.setOnClickListener(view -> {
                Intrinsics.checkNotNullParameter(bottomSheetDialog, "$bottomSheetDialog");
                bottomSheetDialog.dismiss();
            });
            bottomSheetDialog.show();
        }
        catch (Exception e){

        }

    }

    private final void setChecked() {
        Context context = this;
        boolean normalMap = MyApp.Companion.getNormalMap(context);
        boolean hybridMap = MyApp.Companion.getHybridMap(context);
        boolean satelliteMap = MyApp.Companion.getSatelliteMap(context);
        boolean terrainMap = MyApp.Companion.getTerrainMap(context);
        if (normalMap) {
            getStyleMapAdapter().checkSelectView(0);
        }
        if (hybridMap) {
            getStyleMapAdapter().checkSelectView(1);
        }
        if (satelliteMap) {
            getStyleMapAdapter().checkSelectView(2);
        }
        if (terrainMap) {
            getStyleMapAdapter().checkSelectView(3);
        }
    }

    public final void setStyleMap(int i) {

        if (i == 0) {
            Context context = this;
            MyApp.Companion.setNormalMap(context, true);
            MyApp.Companion.setHybridMap(context, false);
            MyApp.Companion.setSatelliteMap(context, false);
            MyApp.Companion.setTerrainMap(context, false);
        } else if (i == 1) {
            Context context2 = this;
            MyApp.Companion.setNormalMap(context2, false);
            MyApp.Companion.setHybridMap(context2, true);
            MyApp.Companion.setSatelliteMap(context2, false);
            MyApp.Companion.setTerrainMap(context2, false);
        } else if (i == 2) {
            Context context3 = this;
            MyApp.Companion.setNormalMap(context3, false);
            MyApp.Companion.setHybridMap(context3, false);
            MyApp.Companion.setSatelliteMap(context3, true);
            MyApp.Companion.setTerrainMap(context3, false);
        } else if (i != 3) {
            Context context4 = this;
            MyApp.Companion.setNormalMap(context4, false);
            MyApp.Companion.setHybridMap(context4, false);
            MyApp.Companion.setSatelliteMap(context4, false);
            MyApp.Companion.setTerrainMap(context4, true);
        } else {
            Context context5 = this;
            MyApp.Companion.setNormalMap(context5, false);
            MyApp.Companion.setHybridMap(context5, false);
            MyApp.Companion.setSatelliteMap(context5, false);
            MyApp.Companion.setTerrainMap(context5, false);
        }
        startActivityForResult(getIntent(), this.REQUEST_CODE_OPTIONS);
        finish();
    }

    private final void onShareLink(String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, getString(R.string.share)));
    }

    @SuppressLint("WrongConstant")
    private final void clickOpenPopupListCategory() {
        Context context = this;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.SheetDialog);
        LayoutCategoryBinding inflate = LayoutCategoryBinding.inflate(LayoutInflater.from(context));
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(this@MainActivity))");
        bottomSheetDialog.setContentView((View) inflate.getRoot());
        getCategoryNearByAdapter().setData(this.listCategory);
        RecyclerView recyclerView = inflate.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, false));
        recyclerView.setAdapter(getCategoryNearByAdapter());
        getCategoryNearByAdapter().callBackStyleMap(str -> {
            Intrinsics.checkNotNullParameter(str, "titleCategory");
            MainActivity.this.openMap(str);
           bottomSheetDialog.dismiss();
        });
        inflate.ivClose.setOnClickListener(view -> {
            Intrinsics.checkNotNullParameter(bottomSheetDialog, "$bottomSheetDialog");
            bottomSheetDialog.dismiss();
        });
        bottomSheetDialog.show();
    }


    public final void openMap(String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.google.com/maps/search/" + str + "/@" + this.lat1 + "," + this.lng1 + ",16z/data=!3m1!4b1?hl=vi-VN"));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);
    }


    public void onBackPressed() {

            super.onBackPressed();
        }
    }

