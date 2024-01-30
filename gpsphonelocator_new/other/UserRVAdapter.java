package com.app.gpsphonelocator_new.other;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gpsphonelocator_new.R;

public class UserRVAdapter extends ListAdapter<UserFriend,UserRVAdapter.ViewHolder> {

    private OnItemClickListener listener;

    private OnItemClickListener onItemClickListener;

    private UserFriendDao userFriendDao;

    private ViewModal viewModal;
    private Context context;

    public UserRVAdapter(UserFriendDao userFriendDao,Context context) {
        super(DIFF_CALLBACK);
        this.userFriendDao = userFriendDao;
        this.context = context;

    }

    private static final DiffUtil.ItemCallback<UserFriend> DIFF_CALLBACK = new DiffUtil.ItemCallback<UserFriend>() {
        @Override
        public boolean areItemsTheSame(UserFriend oldItem, UserFriend newItem) {
            return oldItem.getId() == newItem.getId();

        }

        @Override
        public boolean areContentsTheSame(UserFriend oldItem, UserFriend newItem) {
            return oldItem.getname().equals(newItem.getname()) &&
                    oldItem.getsecurityCode().equals(newItem.getsecurityCode()) &&
                    oldItem.getphoneNumber().equals(newItem.getphoneNumber());
        }
    };

    @NonNull
    @Override
    public UserRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tracked_user, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        UserFriend userFriend = getUserAt(position);
        holder.nameTV.setText(userFriend.getname());
//        holder.securitycodeTV.setText(userFriend.getsecurityCode());
     //   holder.phonenumberTV.setText(userFriend.getphoneNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetailsDialog(userFriend);

            }
        });
        holder.deletedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserFriend userFriend = getUserAt(holder.getAdapterPosition());
                // userFriendDao.delete(userFriend);
                showDeleteDialog(userFriend)    ;
            }
        });

    }

    @SuppressLint("StaticFieldLeak")
    public void  deleteuser(UserFriend userFriend){
        UserFriendDatabase userFriendDatabase = UserFriendDatabase.getInstance(context);
        UserFriendDao userFriendDao = userFriendDatabase.Dao();
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {
                userFriendDao.delete(userFriend);
                return null;
            }


        }.execute();

        }

    private void showDetailsDialog(UserFriend userFriend) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("User Details");

        // Customize the layout of the dialog
        View dialogView = LayoutInflater.from(context).inflate(R.layout.item_friend_detail, null);
        TextView name = dialogView.findViewById(R.id.idTVName);
        TextView securityCode = dialogView.findViewById(R.id.idTVSecurityCode);
        TextView phoneNumber = dialogView.findViewById(R.id.idTVPhoneNumber);

        // Set user details
        name.setText("Name: " + userFriend.getname());
        securityCode.setText("Security Code: " + userFriend.getsecurityCode());
        phoneNumber.setText("Phone Number: " + userFriend.getphoneNumber());

        builder.setView(dialogView);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // You can perform any actions here if needed
            }
        });

        // Show the dialog
        builder.show();
    }

    private void showDeleteDialog(UserFriend userFriend) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete User");
        builder.setMessage("Are you sure you want to delete this user?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User clicked Yes, delete the user
                deleteuser(userFriend);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User clicked No, do nothing or dismiss the dialog
                dialogInterface.dismiss();
            }
        });

        // Show the dialog
        builder.show();
    }

    public UserFriend getUserAt(int position) {
        return getItem(position);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTV, securitycodeTV, phonenumberTV;
        ImageButton deletedata;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.idTVName);
            deletedata = itemView.findViewById(R.id.delete);


        }
    }

    public interface OnItemClickListener {
        void onItemClick(UserFriend userFriend);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;;
    }



}
