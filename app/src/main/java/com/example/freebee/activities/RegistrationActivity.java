package com.example.freebee.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.freebee.ApiClient;
import com.example.freebee.R;
import com.example.freebee.models.CreateEmail.CreateEmail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edt_first;
    EditText edt_last;
    EditText edt_birthday;
    EditText edt_email;
    Spinner spinner_gender;
    LinearLayout linear_header;
    Button btn_continue;
    String phoneNumber;
    private String []Months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
        if(getIntent()!=null){
            phoneNumber=getIntent().getStringExtra(OtpActivity.PHONE_NO);

        }
        Toast.makeText(RegistrationActivity.this, "PhoneNumber: "+phoneNumber, Toast.LENGTH_SHORT).show();
        btn_continue.setOnClickListener(this);
        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0){
                    btn_continue.setEnabled(true);
                    int backgroundColor = ContextCompat.getColor(view.getContext(), R.color.colorAccent);
                    btn_continue.setBackgroundColor(backgroundColor);
                }else{
                    btn_continue.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edt_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(edt_first.getText()) && !TextUtils.isEmpty(edt_last.getText()) && !TextUtils.isEmpty(edt_email.getText())) {
                    btn_continue.setEnabled(true);
                    int backgroundColor = ContextCompat.getColor(RegistrationActivity.this, R.color.colorAccent);
                    btn_continue.setBackgroundColor(backgroundColor);
                }else{
                    btn_continue.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_birthday.setOnClickListener(this);
        linear_header.setOnClickListener(this);
        btn_continue.setOnClickListener(this);
    }

    private void initViews() {
        edt_first=findViewById(R.id.edt_first);
        edt_last=findViewById(R.id.edt_last);
        spinner_gender=findViewById(R.id.spinner_gender);
        edt_birthday=findViewById(R.id.edt_birthday);
        edt_email=findViewById(R.id.edt_email);
        linear_header=findViewById(R.id.linear_header);
        btn_continue=findViewById(R.id.btn_continue);
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.edt_birthday){
            final DatePickerDialog datePickerDialog=new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    Log.e("Date------->",datePicker.getDayOfMonth()+","+datePicker.getMonth()+","+datePicker.getYear());
                    edt_birthday.setText(datePicker.getDayOfMonth()+"-"+Months[datePicker.getMonth()]+"-"+datePicker.getYear());
                }
            },1990,0,1);
            datePickerDialog.show();


        }else if(view.getId()==R.id.linear_header){
            finish();
        }else if(view.getId()==R.id.btn_continue){
            ApiClient.getInstance().setEmail(phoneNumber,"123123",edt_email.getText().toString())
                    .enqueue(new Callback<CreateEmail>() {
                        @Override
                        public void onResponse(Call<CreateEmail> call, Response<CreateEmail> response) {
                            if(response.isSuccessful()){

                                if(response.body().getData().getMessage().equalsIgnoreCase("Updated emailid successfully")){
                                    Intent intent=new Intent(RegistrationActivity.this, CreatePasswordActivity.class);
                                    intent.putExtra(OtpActivity.PHONE_NO,phoneNumber);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(RegistrationActivity.this, "Error: "+response.body().getData().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<CreateEmail> call, Throwable t) {

                        }
                    });
            
        }
    }
}