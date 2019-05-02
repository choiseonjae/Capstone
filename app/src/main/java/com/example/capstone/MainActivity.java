package com.example.capstone;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstone.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;
    private String edtPhone, edtPassword;

    // 카메라라
    private static final int MY_PERMISSION_STORAGE = 1111;


    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        backPressCloseHandler = new BackPressCloseHandler(this);
        //로그인 되어있으면 바로 홈으로 아니면, 회원가입/로그인 뜨는 창으로
//        if(isLogined()){
//            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            final DatabaseReference table_user = database.getReference("User");
//            table_user.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    User user = dataSnapshot.child(edtPhone).getValue(User.class);
//                    Intent homeIntent = new Intent(MainActivity.this, Home.class);
//                    Common.currentUser = user;
//                    Toast.makeText(MainActivity.this, user.getName()+"님 환영합니다!", Toast.LENGTH_SHORT).show();
//                    startActivity(homeIntent);
//                    finish();
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//        }
//        else{
        Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();
//        }
        txtSlogan = findViewById(R.id.txtSlogan);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/NABILA.TTF");
        txtSlogan.setTypeface(face);
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }

    public boolean isLogined() {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        edtPhone = pref.getString("edtPhone", null);
        edtPassword = pref.getString("edtPassword", null);
        return edtPhone != null && edtPassword != null;
    }


}
