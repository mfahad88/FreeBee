package com.example.freebee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freebee.helper.Utils;
import com.example.freebee.views.OtpEditText;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private LinearLayout linear_header;
    private TextView txt_number,txt_changeNumber,txt_resend;
    private String phoneNumber;
    private Button btn_continue;
    private OtpEditText et_otp;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        if(getIntent()!=null){
            phoneNumber=getIntent().getStringExtra("phoneNumber");
        }
        linear_header=findViewById(R.id.linear_header);
        txt_number=findViewById(R.id.txt_number);
        txt_resend=findViewById(R.id.txt_resend);
        txt_changeNumber=findViewById(R.id.txt_changeNumber);
        btn_continue=findViewById(R.id.btn_continue);
        et_otp=findViewById(R.id.et_otp);
        progressBar=findViewById(R.id.progressBar);
        txt_changeNumber.setOnClickListener(this);
        linear_header.setOnClickListener(this);
        btn_continue.setOnClickListener(this);
        txt_number.append(phoneNumber);
        et_otp.addTextChangedListener(this);
        txt_resend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.linear_header){
            finish();
        }else if(view.getId()==R.id.btn_continue){
            progressBar.setVisibility(View.VISIBLE);
           ApiClient.getInstance().activateaccount(phoneNumber,"123123",et_otp.getText().toString())
                   .enqueue(new Callback<JsonElement>() {
                       @Override
                       public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                           if(response.code()==200){
                               JsonObject object=response.body().getAsJsonObject();

                               JsonObject data=object.getAsJsonObject("data");
                               String message=data.get("message").getAsString();

                               if(message.equalsIgnoreCase("User Activated")) {
                                   Utils.hideKeyboard(OtpActivity.this);
                                   startActivity(new Intent(OtpActivity.this, RegistrationActivity.class));
                                   finish();
                               }


                           }else if(response.code()==401){
                               try {
                                   Log.e("Error: ",response.errorBody().string()+"\n"+response.message());
                                   if(response.message().equalsIgnoreCase("Unauthorized")){
                                       Toast.makeText(OtpActivity.this, "Error\n Invalid OTP ", Toast.LENGTH_SHORT).show();
                                   }
                                   progressBar.setVisibility(View.GONE);

                                   new Handler().postDelayed(new Runnable() {
                                       @Override
                                       public void run() {
                                           startActivity(new Intent(OtpActivity.this, SignUpActivity.class));
                                       }
                                   },2000);
                               } catch (IOException e) {
                                   e.printStackTrace();
                               }


                           }
                       }

                       @Override
                       public void onFailure(Call<JsonElement> call, Throwable t) {

                           t.printStackTrace();
                       }
                   });
        }else if(view.getId()==R.id.txt_changeNumber){
            finish();
        }else if(view.getId()==R.id.txt_resend){
            progressBar.setVisibility(View.VISIBLE);
            ApiClient.getInstance().createuser(phoneNumber,"123123").enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    if(response.isSuccessful()){
                        Utils.hideKeyboard(OtpActivity.this);
                        JsonObject object=response.body().getAsJsonObject();
                        JsonObject data=object.getAsJsonObject("data");
                        String message=data.get("message").getAsString();
                        if(message.equalsIgnoreCase("User created")){
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        btn_continue.setEnabled(!TextUtils.isEmpty(et_otp.getText()) && et_otp.getText().length()==4);
        if(!TextUtils.isEmpty(et_otp.getText()) && et_otp.getText().length()==4){
            btn_continue.performClick();
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}