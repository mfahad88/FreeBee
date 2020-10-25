package com.example.freebee.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.freebee.R;

public class GetStartActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_getstarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start);
        initViews();
        clickListeners();
    }

    private void clickListeners() {
        btn_getstarted.setOnClickListener(this);
    }

    private void initViews() {
        btn_getstarted=findViewById(R.id.btn_getstarted);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_getstarted){
            startActivity(new Intent(view.getContext(),SignUpActivity.class));
        }
    }
}