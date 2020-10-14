package com.example.freebee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.emoji.bundled.BundledEmojiCompatConfig;
import androidx.emoji.text.EmojiCompat;
import androidx.emoji.widget.EmojiTextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.freebee.helper.Utils;
import com.example.freebee.models.country.Country;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



import com.squareup.picasso.Picasso;
import com.ybs.countrypicker.CountryPicker;
import com.ybs.countrypicker.CountryPickerListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private CountryPicker picker;  // dialog title
    private LinearLayout linear_header;
    private EditText edt_country;
    private EditText edt_dialCode;
    private EditText edt_phoneNumber;
    private Button btn_continue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        linear_header=findViewById(R.id.linear_header);
        edt_country=findViewById(R.id.edt_country);
        edt_dialCode=findViewById(R.id.edt_dialCode);
        edt_phoneNumber=findViewById(R.id.edt_phoneNumber);
        btn_continue=findViewById(R.id.btn_continue);
        showCountry();
        linear_header.setOnClickListener(this);
        edt_country.setOnClickListener(this);
        btn_continue.setOnClickListener(this);

   }

    private void showCountry() {
        if(picker==null) {
            picker = CountryPicker.newInstance("Select Country");
        }
        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                // Implement your code here
                edt_country.setText(name);
                edt_dialCode.setText(dialCode);
                picker.dismiss();
            }
        });
        picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.linear_header){
            finish();
        }else if(view.getId()==R.id.edt_country){
            showCountry();
        }else if(view.getId()==R.id.btn_continue){
            Intent intent=new Intent(this,OtpActivity.class);
            String phoneNumber=edt_dialCode.getText().toString()+""+edt_phoneNumber.getText().toString();
            getIntent().removeExtra("phoneNumber");
            intent.putExtra("phoneNumber",phoneNumber);
            startActivity(intent);
        }
    }
}