package com.example.capstone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;
    private String userID;
    TextView txtSlogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Here, thisActivity is the current activity
//        if (ContextCompat.checkSelfPermission(MainActivity.this,
//                Manifest.permission.READ_CONTACTS)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
//                    Manifest.permission.READ_CONTACTS)) {
//
//                // Show an expanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//
//            } else {
//
//                // No explanation needed, we can request the permission.
////
////                ActivityCompat.requestPermissions(MainActivity.this,
////                        new String[]{Manifest.permission.READ_CONTACTS}, 0);
////
////                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
////                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        }
//
////        //test
//        startActivity(new Intent(getApplicationContext(), GPS.class));


        backPressCloseHandler = new BackPressCloseHandler(this);
        //자동 로그인 되어있으면 바로 홈으로 아니면, 회원가입/로그인 뜨는 창으로 이동
//        if(isLogined()){
//
//            Common.getDatabase("User").addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    User user = dataSnapshot.child(userID).getValue(User.class);
//                    Intent homeIntent = new Intent(MainActivity.this, Home.class);
//
//                    // id 이름 저장
//                    Common.setUserName(user.getName());
//                    Common.setMyId(userID);
//
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
            Intent startIntent   = new Intent(MainActivity.this, StartActivity.class);
            startActivity(startIntent);
            finish();
//        }
        txtSlogan = findViewById(R.id.txtSlogan);
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/NABILA.TTF");
        txtSlogan.setTypeface(face);
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }

    public boolean isLogined(){
        String userPassword;
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        userID = pref.getString("userID", null);
        userPassword = pref.getString("userPassword", null);
        Log.d("LOG", userID + " " + userPassword);
        return userID != null && userPassword != null;
    }
}
