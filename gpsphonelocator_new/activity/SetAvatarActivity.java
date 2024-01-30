package com.app.gpsphonelocator_new.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.gpsphonelocator_new.adapter.AvatarAdapter;
import com.app.gpsphonelocator_new.databinding.ActivitySetAvatarBinding;
import com.app.gpsphonelocator_new.extensions.AppExtensionKt;
import com.app.gpsphonelocator_new.phone.util.AppUserSingleton;
import com.app.gpsphonelocator_new.phone.util.Constants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;


public final class SetAvatarActivity extends BaseActivity<ActivitySetAvatarBinding> {
    private final Lazy avatarAdapter$delegate = LazyKt.lazy((Function0<Object>) () -> new AvatarAdapter());
    private List<String> listAvatar = new ArrayList();
    private String strAvatar = "file:///android_asset/avatar/avatar1.png";

    public  AvatarAdapter getAvatarAdapter() {
        return (AvatarAdapter) this.avatarAdapter$delegate.getValue();
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        bindComponent();

        bindEvent();
    }

    public ActivitySetAvatarBinding getViewBinding() {
        ActivitySetAvatarBinding inflate = ActivitySetAvatarBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        return inflate;
    }

    private final void bindComponent()  {
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        if (AppExtensionKt.getPref(applicationContext, Constants.SET_AVATAR, false)) {
            ImageView imageView = ((ActivitySetAvatarBinding) getMBinding()).ivBackGround;
            Intrinsics.checkNotNullExpressionValue(imageView, "mBinding.ivBackGround");
            AppExtensionKt.show(imageView);
            View view = ((ActivitySetAvatarBinding) getMBinding()).viewBackground;
            Intrinsics.checkNotNullExpressionValue(view, "mBinding.viewBackground");
            AppExtensionKt.show(view);
            ImageView imageView2 = ((ActivitySetAvatarBinding) getMBinding()).ivBack;
            Intrinsics.checkNotNullExpressionValue(imageView2, "mBinding.ivBack");
            AppExtensionKt.show(imageView2);
            TextView textView = ((ActivitySetAvatarBinding) getMBinding()).tvTitle;
            Intrinsics.checkNotNullExpressionValue(textView, "mBinding.tvTitle");
            AppExtensionKt.hide(textView);
            TextView textView2 = ((ActivitySetAvatarBinding) getMBinding()).tvTitleSetting;
            Intrinsics.checkNotNullExpressionValue(textView2, "mBinding.tvTitleSetting");
            AppExtensionKt.show(textView2);
        } else {
            ImageView imageView3 = ((ActivitySetAvatarBinding) getMBinding()).ivBackGround;
            Intrinsics.checkNotNullExpressionValue(imageView3, "mBinding.ivBackGround");
            AppExtensionKt.hide(imageView3);
            ImageView imageView4 = ((ActivitySetAvatarBinding) getMBinding()).ivBack;
            Intrinsics.checkNotNullExpressionValue(imageView4, "mBinding.ivBack");
            AppExtensionKt.hide(imageView4);
            View view2 = ((ActivitySetAvatarBinding) getMBinding()).viewBackground;
            Intrinsics.checkNotNullExpressionValue(view2, "mBinding.viewBackground");
            AppExtensionKt.hide(view2);
            TextView textView3 = ((ActivitySetAvatarBinding) getMBinding()).tvTitle;
            Intrinsics.checkNotNullExpressionValue(textView3, "mBinding.tvTitle");
            AppExtensionKt.show(textView3);
            TextView textView4 = ((ActivitySetAvatarBinding) getMBinding()).tvTitleSetting;
            Intrinsics.checkNotNullExpressionValue(textView4, "mBinding.tvTitleSetting");
            AppExtensionKt.hide(textView4);
        }

        List<String> list = this.listAvatar;
        list.add("file:///android_asset/avatar/avatar1.png");
        list.add("file:///android_asset/avatar/avatar2.png");
        list.add("file:///android_asset/avatar/avatar3.png");
        list.add("file:///android_asset/avatar/avatar4.png");
        list.add("file:///android_asset/avatar/avatar5.png");
        list.add("file:///android_asset/avatar/avatar6.png");
        list.add("file:///android_asset/avatar/avatar7.png");
        list.add("file:///android_asset/avatar/avatar8.png");
        list.add("file:///android_asset/avatar/avatar9.png");
        list.add("file:///android_asset/avatar/avatar10.png");
        list.add("file:///android_asset/avatar/avatar11.png");
        list.add("file:///android_asset/avatar/avatar12.png");
        list.add("file:///android_asset/avatar/avatar13.png");
        list.add("file:///android_asset/avatar/avatar14.png");
        list.add("file:///android_asset/avatar/avatar15.png");
        RecyclerView recyclerView = ((ActivitySetAvatarBinding) getMBinding()).recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        getAvatarAdapter().setData(this.listAvatar);
        getAvatarAdapter().checkSelectView(this.listAvatar.indexOf(AppUserSingleton.getInstance().getAvt()));
        getAvatarAdapter().notifyDataSetChanged();
        recyclerView.setAdapter(getAvatarAdapter());

    }

    private final void bindEvent() {
        getAvatarAdapter().callBackAvatar((i, i2) -> {
            getAvatarAdapter().checkSelectView(i);
            setAvatar(i);
        });
        ((ActivitySetAvatarBinding) getMBinding()).ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context applicationContext = getApplicationContext();
                if (AppExtensionKt.getPref(applicationContext, Constants.SET_AVATAR, true)) {
                    Intent intent = new Intent(SetAvatarActivity.this, SettingActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
        ((ActivitySetAvatarBinding) getMBinding()).ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intrinsics.checkNotNullParameter(this, "this$0");
                onBackPressed();
            }
        });
    }


    public final void setAvatar(int i) {
        switch (i) {
            case 0:
                this.strAvatar = "file:///android_asset/avatar/avatar1.png";
                break;
            case 1:
                this.strAvatar = "file:///android_asset/avatar/avatar2.png";
                break;
            case 2:
                this.strAvatar = "file:///android_asset/avatar/avatar3.png";
                break;
            case 3:
                this.strAvatar = "file:///android_asset/avatar/avatar4.png";
                break;
            case 4:
                this.strAvatar = "file:///android_asset/avatar/avatar5.png";
                break;
            case 5:
                this.strAvatar = "file:///android_asset/avatar/avatar6.png";
                break;
            case 6:
                this.strAvatar = "file:///android_asset/avatar/avatar7.png";
                break;
            case 7:
                this.strAvatar = "file:///android_asset/avatar/avatar8.png";
                break;
            case 8:
                this.strAvatar = "file:///android_asset/avatar/avatar9.png";
                break;
            case 9:
                this.strAvatar = "file:///android_asset/avatar/avatar10.png";
                break;
            case 10:
                this.strAvatar = "file:///android_asset/avatar/avatar11.png";
                break;
            case 11:
                this.strAvatar = "file:///android_asset/avatar/avatar12.png";
                break;
            case 12:
                this.strAvatar = "file:///android_asset/avatar/avatar13.png";
                break;
            case 13:
                this.strAvatar = "file:///android_asset/avatar/avatar14.png";
                break;
            case 14:
                this.strAvatar = "file:///android_asset/avatar/avatar15.png";
                break;
            default:
                this.strAvatar = "file:///android_asset/avatar/avatar1.png";
                break;
        }

        AppUserSingleton.getInstance().setAvt(this.strAvatar);
    }

}
