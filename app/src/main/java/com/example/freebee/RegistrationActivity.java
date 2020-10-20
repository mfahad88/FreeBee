package com.example.freebee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edt_first;
    EditText edt_last;
    Spinner spinner_gender;
    DatePicker datePicker;
    Button btn_continue;
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
    }

    private void initViews() {
        edt_first=findViewById(R.id.edt_first);
        edt_last=findViewById(R.id.edt_last);
        spinner_gender=findViewById(R.id.spinner_gender);
        datePicker=findViewById(R.id.datePicker);
        btn_continue=findViewById(R.id.btn_continue);
    }

    @Override
    public void onClick(View view) {

    }
}