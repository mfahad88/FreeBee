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
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.freebee.helper.Utils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ybs.countrypicker.CountryPicker;
import com.ybs.countrypicker.CountryPickerListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private CountryPicker picker;  // dialog title
    private LinearLayout linear_header;
    private EditText edt_country;
    private EditText edt_dialCode;
    private EditText edt_phoneNumber;
    private Button btn_continue;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        linear_header=findViewById(R.id.linear_header);
        edt_country=findViewById(R.id.edt_country);
        edt_dialCode=findViewById(R.id.edt_dialCode);
        edt_phoneNumber=findViewById(R.id.edt_phoneNumber);
        btn_continue=findViewById(R.id.btn_continue);
        progressBar=findViewById(R.id.progressBar);
        showCountry();
        linear_header.setOnClickListener(this);
        edt_country.setOnClickListener(this);
        btn_continue.setOnClickListener(this);
        edt_phoneNumber.addTextChangedListener(this);
   }

    private void showCountry() {
        if(picker==null) {
            picker = CountryPicker.newInstance("Select Country");
        }
        picker.setCancelable(false);
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
    public void onBackPressed() {

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.linear_header){
            finish();
        }else if(view.getId()==R.id.edt_country){
            showCountry();
        }else if(view.getId()==R.id.btn_continue){

            final String phoneNumber=edt_dialCode.getText().toString()+""+edt_phoneNumber.getText().toString();
            progressBar.setVisibility(View.VISIBLE);
            ApiClient.getInstance().createuser(phoneNumber,"123123")
                    .enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            if(response.isSuccessful()){
                                Utils.hideKeyboard(SignUpActivity.this);
                                JsonObject object=response.body().getAsJsonObject();
                                JsonObject data=object.getAsJsonObject("data");
                                String message=data.get("message").getAsString();
                                if(message.equalsIgnoreCase("User created")) {

                                    Intent intent=new Intent(SignUpActivity.this,OtpActivity.class);
                                    getIntent().removeExtra("phoneNumber");
                                    intent.putExtra("phoneNumber",phoneNumber);
                                    startActivity(intent);
                                    finish();


                                }else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(SignUpActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                                }

                            }



                        }

                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {

                        }
                    });

        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(edt_phoneNumber.length()==10){
            btn_continue.setEnabled(true);
            btn_continue.setBackground(getResources().getDrawable(R.color.colorAccent));
        }else{
            btn_continue.setEnabled(false);
            btn_continue.setBackgroundColor(Color.LTGRAY);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}