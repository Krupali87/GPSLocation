package com.app.gpsphonelocator_new.phone.view.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.gpsphonelocator_new.R;

public final class DialogAddNewFriend extends AppCompatActivity {
    private EditText etname, et_security_code, etphonenumber;
    private Button saveBtn;
    private ImageView closedialog;

    private TextView textView;
   public  AlertDialog alertDialog;
    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_NAME";
    public static final String EXTRA_SECURITY_CODE = "com.gtappdevelopers.gfgroomdatabase.EXTRA_SECURITY_CODE";
    public static final String EXTRA_PHONE_NUMBER = "com.gtappdevelopers.gfgroomdatabase.EXTRA_PHONE_NUMBER";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            etname.setText(intent.getStringExtra(EXTRA_NAME));
            et_security_code.setText(intent.getStringExtra(EXTRA_SECURITY_CODE));
            etphonenumber.setText(intent.getStringExtra(EXTRA_PHONE_NUMBER));

        }

        showAlertDialog();

    }

    @SuppressLint("MissingInflatedId")
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogTheme);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_new_friend, null);
        builder.setView(dialogView);
        etname = dialogView.findViewById(R.id.et_name);
        et_security_code = dialogView.findViewById(R.id.et_security_code);
        etphonenumber = dialogView.findViewById(R.id.et_phone_number);
        saveBtn = dialogView.findViewById(R.id.btn_add_friend);
        closedialog = dialogView.findViewById(R.id.close_dialog);


        alertDialog = builder.create();


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etname.getText().toString();
                String securityCode = et_security_code.getText().toString();
                String phoneNumber = etphonenumber.getText().toString();

                if (name.isEmpty() || securityCode.isEmpty() || phoneNumber.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter valid user details.", Toast.LENGTH_SHORT).show();
                    return;
                }

                saveUser(name, securityCode, phoneNumber);
                alertDialog.dismiss();
            }
        });

        closedialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  onBackPressed();
            }
        });

        alertDialog.show();
    }

    private void saveUser(String name, String securityCode, String phoneNumber) {
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_SECURITY_CODE, securityCode);
        data.putExtra(EXTRA_PHONE_NUMBER, phoneNumber);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
      finish();
    }

    @Override
    public void onBackPressed() {
        alertDialog.dismiss();
        finish();
    }
}