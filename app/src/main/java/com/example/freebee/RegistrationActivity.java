package com.example.freebee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edt_first;
    EditText edt_last;
    EditText edt_birthday;
    Spinner spinner_gender;
    LinearLayout linear_header;
    Button btn_continue;
    private String []Months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
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
        edt_birthday.setOnClickListener(this);
        linear_header.setOnClickListener(this);
    }

    private void initViews() {
        edt_first=findViewById(R.id.edt_first);
        edt_last=findViewById(R.id.edt_last);
        spinner_gender=findViewById(R.id.spinner_gender);
        edt_birthday=findViewById(R.id.edt_birthday);
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
        }
    }
}