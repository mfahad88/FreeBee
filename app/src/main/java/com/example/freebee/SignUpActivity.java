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
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.freebee.helper.Utils;
import com.example.freebee.models.country.Country;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.juanpabloprado.countrypicker.CountryPicker;
import com.juanpabloprado.countrypicker.CountryPickerListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity {


    Gson gson;
    List<Country> countries;
    EmojiTextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_sign_up);
        countries=new ArrayList<>();

        for(Locale locale:Locale.getAvailableLocales()){
            if(!TextUtils.isEmpty(locale.getDisplayCountry())) {
                countries.add(new Country(PhoneNumberUtil.getInstance().getCountryCodeForRegion(locale.getCountry()), locale.getDisplayCountry(), countryCodeToEmoji(locale.getCountry())));
                Log.e("LOcale--->", locale.getCountry()+", "+locale.getDisplayCountry()+ ", "+PhoneNumberUtil.getInstance().getCountryCodeForRegion(locale.getCountry()));
            }


        }

        final Dialog dialog=new Dialog(this);
        ListView listView=new ListView(this);
        dialog.addContentView(listView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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
                return countries.get(i).getCountryCode();
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                final Country country=countries.get(i);
                if(view==null){
                    EmojiCompat.Config config = new BundledEmojiCompatConfig(SignUpActivity.this);
                    EmojiCompat.init(config);
                    view=LayoutInflater.from(SignUpActivity.this).inflate(R.layout.list_countries_row,viewGroup,false);
                }

                EmojiTextView txtView=view.findViewById(R.id.txtView);
                TextView textView_country=view.findViewById(R.id.textView_country);
                textView_country.setText(country.getCountryName());
                txtView.setText(country.getCountryFlag());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(SignUpActivity.this, ""+country.toString(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                return view;
            }
        });

        dialog.show();

   }

    public String countryCodeToEmoji(String code) {

        // offset between uppercase ascii and regional indicator symbols
        int OFFSET = 127397;

        // validate code
        if (code == null || code.length() != 2) {
            return "";
        }

        //fix for uk -> gb
        if (code.equalsIgnoreCase("uk")) {
            code = "gb";
        }

        // convert code to uppercase
        code = code.toUpperCase();

        StringBuilder emojiStr = new StringBuilder();

        //loop all characters
        for (int i = 0; i < code.length(); i++) {
            emojiStr.appendCodePoint(code.charAt(i) + OFFSET);
        }

        // return emoji
        return emojiStr.toString();
    }

}