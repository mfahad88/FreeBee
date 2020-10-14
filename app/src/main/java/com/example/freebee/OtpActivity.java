package com.example.freebee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.freebee.views.OtpEditText;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private LinearLayout linear_header;
    private TextView txt_number,txt_changeNumber;
    private String phoneNumber;
    private Button btn_continue;
    private OtpEditText et_otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        if(getIntent()!=null){
            phoneNumber=getIntent().getStringExtra("phoneNumber");
        }
        linear_header=findViewById(R.id.linear_header);
        txt_number=findViewById(R.id.txt_number);
        txt_changeNumber=findViewById(R.id.txt_changeNumber);
        btn_continue=findViewById(R.id.btn_continue);
        et_otp=findViewById(R.id.et_otp);
        txt_changeNumber.setOnClickListener(this);
        linear_header.setOnClickListener(this);
        btn_continue.setOnClickListener(this);
        txt_number.append(phoneNumber);
        et_otp.addTextChangedListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.linear_header){
            finish();
        }else if(view.getId()==R.id.btn_continue){

        }else if(view.getId()==R.id.txt_changeNumber){
            finish();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        btn_continue.setEnabled(!TextUtils.isEmpty(et_otp.getText()) && et_otp.getText().length()==4);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}