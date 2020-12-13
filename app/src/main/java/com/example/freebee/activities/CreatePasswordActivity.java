package com.example.freebee.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.freebee.ApiClient;
import com.example.freebee.ApiInterface;
import com.example.freebee.R;
import com.example.freebee.models.CreatePassword.CreatePassword;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout linear_header;
    private EditText edt_password,edt_confirm_password;
    private Button btn_continue;
    private ProgressBar progress_circular;
    private String phoneNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);
        if (getIntent()!=null){
            phoneNo=getIntent().getStringExtra(OtpActivity.PHONE_NO);
        }
        linear_header=findViewById(R.id.linear_header);
        edt_password=findViewById(R.id.edt_password);
        edt_confirm_password=findViewById(R.id.edt_confirm_password);
        btn_continue=findViewById(R.id.btn_continue);
        progress_circular=findViewById(R.id.progress_circular);
        edt_confirm_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.equals(edt_password.getText(),edt_confirm_password.getText())){
                    btn_continue.setEnabled(true);
                    int backgroundColor = ContextCompat.getColor(CreatePasswordActivity.this, R.color.colorAccent);
                    btn_continue.setBackgroundColor(backgroundColor);
                }else{
                    btn_continue.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        linear_header.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.linear_header){
            finish();
        }else if(v.getId()==R.id.btn_continue){
            String password=edt_password.getText().toString();
            String confirm_password=edt_confirm_password.getText().toString();
            ApiClient.getInstance().createPassword(phoneNo,password,confirm_password)
                    .enqueue(new Callback<CreatePassword>() {
                        @Override
                        public void onResponse(Call<CreatePassword> call, Response<CreatePassword> response) {
                            if (response.isSuccessful()){
                                if(response.body().getData().getMessage().equalsIgnoreCase("Password updated successfully")){
                                    startActivity(new Intent(CreatePasswordActivity.this,MainActivity.class));
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<CreatePassword> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
        }
    }
}