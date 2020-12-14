package com.example.freebee.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freebee.R;

import org.abtollc.sdk.AbtoApplication;
import org.abtollc.sdk.AbtoPhone;
import org.abtollc.sdk.OnRegistrationListener;
import org.abtollc.utils.Log;
import org.mp4parser.muxer.Edit;

public class GetStartActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private Button btn_sign_in;
    private EditText edt_username;
    private EditText edt_password;
    private TextView txt_sign_up;
    private AbtoPhone abtoPhone;
    private ProgressDialog dialog;
    public static String RegDomain   = "itel.vokka.net";
    public static String RegProxy    = "sip.vokka.net";
    private String username,password;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_start);
        initViews();
        clickListeners();
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }
            abtoPhone = ((AbtoApplication) getApplication()).getAbtoPhone();
            // Set registration event
            abtoPhone.setRegistrationStateListener(new OnRegistrationListener() {

                public void onRegistrationFailed(long accId, int statusCode, String statusText) {

                    if(dialog != null) dialog.dismiss();

                    try{
                        Toast.makeText(GetStartActivity.this, "Registeration failed "+statusCode + " - " + statusText, Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                public void onRegistered(long accId) {

                    //Hide progress
                    if(dialog != null) dialog.dismiss();

                    //Unsubscribe reg events
                    abtoPhone.setRegistrationStateListener(null);

                    //Start main activity
                    if(accId>0) {
                        intent = new Intent(GetStartActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
                }

                @Override
                public void onUnRegistered(long accId) {
                    Log.e(this.toString(), "log succ acc = " + accId);

                    Toast.makeText(getApplicationContext(), "RegisterActivity::onUnRegistered", Toast.LENGTH_SHORT).show();
                }
            }); //registration listener
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            String username=edt_username.getText().toString();
            String password=edt_password.getText().toString();
            if(!TextUtils.isEmpty(username)
                    && !TextUtils.isEmpty(password)){
                //Show progress
                if(dialog==null)
                {
                    dialog = new ProgressDialog(view.getContext());
                    dialog.setCancelable(false);
                    dialog.setMessage("Registering...");
                    dialog.setCancelable(false);
                }
                dialog.show();
                int regExpire = 3600;


                //Register
                try {
                    long accId1 = abtoPhone.getConfig().addAccount(RegDomain, RegProxy, username, password, null, "", regExpire, false);


                    //Close this activity
//                    finish();
                    abtoPhone.register();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
//            startNext(MainActivity.class);
            }else{
                final AlertDialog.Builder dialog=new AlertDialog.Builder(view.getContext());
                dialog.setTitle("Error");
                dialog.setMessage("Please check username and password...");
                dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog=dialog.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
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
            btn_sign_in.setBackgroundColor(Color.LTGRAY);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}