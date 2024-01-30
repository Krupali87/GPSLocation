package com.app.gpsphonelocator_new.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.app.gpsphonelocator_new.other.UserFriend;
import com.app.gpsphonelocator_new.other.UserFriendDao;
import com.app.gpsphonelocator_new.other.UserFriendDatabase;
import com.app.gpsphonelocator_new.common.InforSaved;
import com.app.gpsphonelocator_new.databinding.ItemFriendPopupBinding;
import com.app.gpsphonelocator_new.phone.callback.CallBack;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.Intrinsics;


public final class FriendsPopUpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public CallBack.CallBackFriendsPopUp callBackFriendsPopUp;

    public List<InforSaved> listFriendPopUp = new ArrayList();

    public final void callBackFriendsPopUp(CallBack.CallBackFriendsPopUp callBackFriendsPopUp2) {
        Intrinsics.checkNotNullParameter(callBackFriendsPopUp2, "callBackFriendsPopUp");
        this.callBackFriendsPopUp = callBackFriendsPopUp2;
    }

    public final void setData(List<InforSaved> list) {
        Intrinsics.checkNotNullParameter(list, "listFriendPopUp");
        this.listFriendPopUp = list;
        notifyDataSetChanged();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        ItemFriendPopupBinding inflate = ItemFriendPopupBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        ((ViewHolder) viewHolder).bindData(i);
    }

    public int getItemCount() {
        return this.listFriendPopUp.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemFriendPopupBinding binding;
        final  FriendsPopUpAdapter this$0;

        public ViewHolder(FriendsPopUpAdapter friendsPopUpAdapter, ItemFriendPopupBinding itemFriendPopupBinding) {
            super(itemFriendPopupBinding.getRoot());
            Intrinsics.checkNotNullParameter(itemFriendPopupBinding, "binding");
            this.this$0 = friendsPopUpAdapter;
            this.binding = itemFriendPopupBinding;
        }

        public final void bindData(int i) {
            UserFriendDao userFriendDao;
            List<UserFriend> checkedFriends;
            UserFriendDatabase.instance.Dao();
            Context context = this.binding.getRoot().getContext();
            Intrinsics.checkNotNullExpressionValue(context, "binding.root.context");
            UserFriendDatabase instance = UserFriendDatabase.getInstance(context);
            if (!(instance == null || (userFriendDao = instance.Dao()) == null || (checkedFriends = userFriendDao.getAllUser().getValue()) == null)) {
                for (UserFriend checked : checkedFriends) {

                }
            }
            if (this.this$0.listFriendPopUp.get(i).getAvatar().length() == 0) {
                Glide.with(this.binding.getRoot().getContext()).load("file:///android_asset/avatar/avatar1.png").into(this.binding.ivFriends);
            } else {
                Glide.with(this.binding.getRoot().getContext()).load(((InforSaved) this.this$0.listFriendPopUp.get(i)).getAvatar()).into(this.binding.ivFriends);
            }
            this.binding.tvFriends.setText(((InforSaved) this.this$0.listFriendPopUp.get(i)).getnickname());
            this.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intrinsics.checkNotNullParameter(this, "this$0");
                    CallBack.CallBackFriendsPopUp access$getCallBackFriendsPopUp$p = this$0.callBackFriendsPopUp;
                    if (access$getCallBackFriendsPopUp$p == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("callBackFriendsPopUp");
                        access$getCallBackFriendsPopUp$p = null;
                    }
                    access$getCallBackFriendsPopUp$p.selectFriendsPopUp(i, ((InforSaved) this$0.listFriendPopUp.get(i)).getnickname(), ((InforSaved) this$0.listFriendPopUp.get(i)).getphone(), ((InforSaved) this$0.listFriendPopUp.get(i)).getAddress(), ((InforSaved) this$0.listFriendPopUp.get(i)).getLatitudes(), ((InforSaved) this$0.listFriendPopUp.get(i)).getLongitudes(), ((InforSaved) this$0.listFriendPopUp.get(i)).getlocateuid());
                }
            });
        }

    }
}
