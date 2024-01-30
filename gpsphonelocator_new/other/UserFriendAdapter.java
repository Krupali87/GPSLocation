package com.app.gpsphonelocator_new.other;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gpsphonelocator_new.R;
import com.app.gpsphonelocator_new.phone.callback.CallBack;

import java.util.List;

import kotlin.jvm.internal.Intrinsics;

public  class UserFriendAdapter extends ListAdapter<UserFriend,UserFriendAdapter.ViewHolderFriends> {
    private UserRVAdapter.OnItemClickListener listener;

    private UserRVAdapter.OnItemClickListener onItemClickListener;

    public CallBack.CallBackFriendsPopUp callBackFriendsPopUp;

    private UserFriendDao userFriendDao;

    private ViewModal viewModal;
    private Context context;

    public UserFriendAdapter(List<UserFriend> friendsdata) {
        super(DIFF_CALLBACK);
        this.userFriendDao = userFriendDao;
        this.context = context;

    }

    public final void callBackFriendsPopUp(CallBack.CallBackFriendsPopUp callBackFriendsPopUp2) {
        Intrinsics.checkNotNullParameter(callBackFriendsPopUp2, "callBackFriendsPopUp");
        this.callBackFriendsPopUp = callBackFriendsPopUp2;
    }



    private static final DiffUtil.ItemCallback<UserFriend> DIFF_CALLBACK = new DiffUtil.ItemCallback<UserFriend>() {
        @Override
        public boolean areItemsTheSame(UserFriend oldItem, UserFriend newItem) {
            return oldItem.getId() == newItem.getId();

        }

        @Override
        public boolean areContentsTheSame(@NonNull UserFriend oldItem, @NonNull UserFriend newItem) {
            return oldItem.getname().equals(newItem.getname()) &&
                    oldItem.getsecurityCode().equals(newItem.getsecurityCode()) &&
                    oldItem.getphoneNumber().equals(newItem.getphoneNumber());
        }
    };

    @NonNull

    public UserFriendAdapter.ViewHolderFriends onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friend_detail, parent, false);
        return new ViewHolderFriends(item);
    }


    public void onBindViewHolder( UserFriendAdapter.ViewHolderFriends holder, @SuppressLint("RecyclerView") int position) {
        UserFriend userFriend = getUserAt(position);
        holder.nameTV.setText(userFriend.getname());


    }
    public UserFriend getUserAt(int position) {
        return (UserFriend) getItem(position);
    }





    public class ViewHolderFriends extends RecyclerView.ViewHolder {
        TextView nameTV;
        public ViewHolderFriends(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.idTVName);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(UserFriend userFriend);
    }
    public void setOnItemClickListener(UserFriendAdapter.OnItemClickListener listener) {
        this.listener = (UserRVAdapter.OnItemClickListener) listener;;
    }
}


