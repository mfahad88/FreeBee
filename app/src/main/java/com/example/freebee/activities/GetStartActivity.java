package com.example.freebee.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.freebee.R;

import org.mp4parser.muxer.Edit;

public class GetStartActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    Button btn_sign_in;
    EditText edt_username;
    EditText edt_password;
    TextView txt_sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start);
        initViews();
        clickListeners();
    }

    private void clickListeners() {
        txt_sign_up.setOnClickListener(this);
        btn_sign_in.setOnClickListener(this);
        edt_password.addTextChangedListener(this);
    }

    private void initViews() {
        btn_sign_in=findViewById(R.id.btn_sign_in);
        edt_username=findViewById(R.id.edt_username);
        edt_password=findViewById(R.id.edt_password);
        txt_sign_up=findViewById(R.id.txt_sign_up);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_sign_in){
            startActivity(new Intent(view.getContext(),MainActivity.class));
        }else if(view.getId()==R.id.txt_sign_up){
            startActivity(new Intent(view.getContext(),SignUpActivity.class));
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(!TextUtils.isEmpty(edt_username.getText()) && !TextUtils.isEmpty(edt_password.getText())){
            btn_sign_in.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            btn_sign_in.setEnabled(true);
        }else{
            btn_sign_in.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}