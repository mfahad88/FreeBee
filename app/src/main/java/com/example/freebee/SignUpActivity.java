package com.example.freebee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.freebee.helper.Utils;
import com.example.freebee.models.country.Country;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {
    Dialog dialog;
    ListView listView;
    Gson gson;
    List<Country> countries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        dialog=new Dialog(this);
        gson=new Gson();

        countries = gson.fromJson(Utils.loadFromAsset(this,"country.json"), new TypeToken<List<Country>>(){}.getType());
        dialog.setContentView(R.layout.dialog_country);
        listView=dialog.findViewById(R.id.listView);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return countries.size();
            }

            @Override
            public Object getItem(int i) {
                return countries.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if(view==null){
                    view= LayoutInflater.from(SignUpActivity.this).inflate(R.layout.list_countries_row,viewGroup,false);
                }
                final Country country=countries.get(i);
                ImageView imageView_flag=view.findViewById(R.id.imageView_flag);
                TextView textView_country=view.findViewById(R.id.textView_country);
                SvgLoader.pluck().with(SignUpActivity.this).load(country.getFlag(),imageView_flag);
                textView_country.setText(country.getName());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(SignUpActivity.this, ""+country.getFlag(), Toast.LENGTH_SHORT).show();
                    }
                });
                return view;
            }
        });
        dialog.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

}