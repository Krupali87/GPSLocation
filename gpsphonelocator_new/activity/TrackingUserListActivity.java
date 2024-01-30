    package com.app.gpsphonelocator_new.activity;

    import android.app.AlertDialog;
    import android.content.Intent;
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.lifecycle.Observer;
    import androidx.lifecycle.ViewModelProviders;
    import androidx.recyclerview.widget.ItemTouchHelper;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;
    import com.app.gpsphonelocator_new.R;
    import com.app.gpsphonelocator_new.other.UserFriend;
    import com.app.gpsphonelocator_new.other.UserFriendDao;
    import com.app.gpsphonelocator_new.other.UserRVAdapter;
    import com.app.gpsphonelocator_new.other.ViewModal;
    import com.app.gpsphonelocator_new.databinding.ActivityTrackingUserListBinding;
    import com.app.gpsphonelocator_new.phone.util.AppAuthen;
    import com.app.gpsphonelocator_new.phone.util.AppUserSingleton;
    import com.app.gpsphonelocator_new.phone.view.dialog.DialogAddNewFriend;
    import java.util.List;

    import kotlin.jvm.internal.Intrinsics;


    public final class TrackingUserListActivity extends BaseActivity<ActivityTrackingUserListBinding>{

        private RecyclerView rvusertracked;
        private EditText etname, et_security_code, etphonenumber;

        private TextView tvaddtofriend;
        private Button saveBtn;
        private ImageView closedialog;
        private ViewModal viewmodal;
        public  AlertDialog alertDialog;
        private static final int ADD_USER_REQUEST = 1;
        private static final int EDIT_USER_REQUEST = 2;

        private UserFriendDao userFriendDao;

        private void showAlertDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_add_new_friend, null);
            builder.setView(dialogView);
            etname = dialogView.findViewById(R.id.et_name);
            et_security_code = dialogView.findViewById(R.id.et_security_code);
            etphonenumber = dialogView.findViewById(R.id.et_phone_number);
            saveBtn = dialogView.findViewById(R.id.btn_add_friend);
            closedialog = dialogView.findViewById(R.id.close_dialog);




            alertDialog = builder.create();


            saveBtn.setOnClickListener(v -> {
                String name = etname.getText().toString();
                String securityCode = et_security_code.getText().toString();
                String phoneNumber = etphonenumber.getText().toString();

                if (name.isEmpty() || securityCode.isEmpty() || phoneNumber.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter valid user details.", Toast.LENGTH_SHORT).show();
                    return;
                }  if (!isValidPhoneNumber(phoneNumber))
                {
                    Toast.makeText(getApplicationContext(), "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                    return;
                }




                saveUser(name, securityCode, phoneNumber);

                alertDialog.dismiss();
            });

            closedialog.setOnClickListener(view -> alertDialog.dismiss());

            alertDialog.show();
        }

        private boolean isValidPhoneNumber(String phoneNumber) {
            String phoneRegex =  "^\\+?\\d{10,13}$";
            return phoneNumber.matches(phoneRegex);
        }

        private void saveUser(String name, String securityCode, String phoneNumber) {
            UserFriend userFriend = new UserFriend(name, securityCode, phoneNumber);
            viewmodal.insert(userFriend);

        }

        @Override
        public ActivityTrackingUserListBinding getViewBinding() {
            ActivityTrackingUserListBinding inflate = ActivityTrackingUserListBinding.inflate(getLayoutInflater());
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
            return inflate;
        }

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            onWindowFocusChanged(true);
            AppAuthen.getInstance().appContext = getApplicationContext();
            AppUserSingleton.getInstance().appContext = getApplicationContext();

            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }
            setContentView(R.layout.activity_tracking_user_list);
            rvusertracked = findViewById(R.id.rv_user_tracked);
            ImageView add = findViewById(R.id.img_add_header);
            ImageView back = findViewById(R.id.img_back);
            tvaddtofriend = findViewById(R.id.tv_add_friend);


            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });


            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
            try {
                showAlertDialog();
            }
            catch (Exception e){
                e.printStackTrace();
            }

                }
            });
            rvusertracked.setLayoutManager(new LinearLayoutManager(this));
            rvusertracked.setHasFixedSize(true);
            UserRVAdapter adapter = new UserRVAdapter(userFriendDao,this);
            rvusertracked.setAdapter(adapter);
            viewmodal = ViewModelProviders.of(this).get(ViewModal.class);
            viewmodal.alluser().observe(this, new Observer<List<UserFriend>>() {
                @Override
                public void onChanged(List<UserFriend> models) {
                    adapter.submitList(models);

                    // Check for null data and display the TextView accordingly
                    if (models == null || models.isEmpty()) {
                        tvaddtofriend.setVisibility(View.VISIBLE);
                    } else {
                        tvaddtofriend.setVisibility(View.GONE);
                    }
                }
            });




            viewmodal.alluser().observe(this, new Observer<List<UserFriend>>() {
                @Override
                public void onChanged(List<UserFriend> models) {
                    adapter.submitList(models);
                }
            });

            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    viewmodal.delete(adapter.getUserAt(viewHolder.getAdapterPosition()));
                    Toast.makeText(getApplicationContext(), "User deleted", Toast.LENGTH_SHORT).show();
                }
            }).attachToRecyclerView(rvusertracked);


            adapter.setOnItemClickListener(userFriend -> {
                Intent intent = new Intent(getApplicationContext(), DialogAddNewFriend.class);
                intent.putExtra(DialogAddNewFriend.EXTRA_ID, userFriend.getId());
                intent.putExtra(DialogAddNewFriend.EXTRA_NAME, userFriend.getname());
                intent.putExtra(DialogAddNewFriend.EXTRA_SECURITY_CODE, userFriend.getsecurityCode());
                intent.putExtra(DialogAddNewFriend.EXTRA_PHONE_NUMBER, userFriend.getphoneNumber());
                startActivityForResult(intent, EDIT_USER_REQUEST);

            });

        }
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == ADD_USER_REQUEST && resultCode == RESULT_OK) {
                String Name = data.getStringExtra(DialogAddNewFriend.EXTRA_NAME);
                String securityCode = data.getStringExtra(DialogAddNewFriend.EXTRA_SECURITY_CODE);
                String phoneNumber = data.getStringExtra(DialogAddNewFriend.EXTRA_PHONE_NUMBER);
                UserFriend userFriend = new UserFriend(Name, securityCode, phoneNumber);
                viewmodal.insert(userFriend);
                Toast.makeText(this, "user saved", Toast.LENGTH_SHORT).show();
            } else if (requestCode == EDIT_USER_REQUEST && resultCode == RESULT_OK) {
                int id = data.getIntExtra(DialogAddNewFriend.EXTRA_ID, -1);
                if (id == -1) {
                    Toast.makeText(this, "user can't be updated", Toast.LENGTH_SHORT).show();
                    return;
                }
                String Name = data.getStringExtra(DialogAddNewFriend.EXTRA_NAME);
                String securityCode = data.getStringExtra(DialogAddNewFriend.EXTRA_SECURITY_CODE);
                String phoneNumber = data.getStringExtra(DialogAddNewFriend.EXTRA_PHONE_NUMBER);
                UserFriend userFriend = new UserFriend(Name, securityCode, phoneNumber);
                userFriend.setId(id);

            } else {
                Toast.makeText(this, "user not saved", Toast.LENGTH_SHORT).show();
            }




        }




    }
