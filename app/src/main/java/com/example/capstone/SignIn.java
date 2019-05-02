package com.example.capstone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.capstone.Common.Common;
import com.example.capstone.Model.User;
import com.example.capstone.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {
    EditText logId, logPassword;
    CheckBox edtCheck;
    Button btnSignIn;
    public static String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        logPassword = (MaterialEditText)findViewById(R.id.logPassword);
        logId = (MaterialEditText)findViewById(R.id.logId);
        edtCheck = findViewById(R.id.edtCheck);
        btnSignIn = findViewById(R.id.btnSignIn);

        //Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");
        //table_user.child("message").push().setValue('2');
        Toast.makeText(SignIn.this, "message", Toast.LENGTH_SHORT).show();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please waiting...");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //Check if user not exist in database
                        if (dataSnapshot.child(logId.getText().toString()).exists()) {
                            //Get User information
                            mDialog.dismiss();
                            User user = dataSnapshot.child(logId.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(logPassword.getText().toString())){
                                if(edtCheck.isChecked()){
                                    //phone, password 일치시, 자동 로그인에 필요한 정보 저장
                                    SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("edtPhone", logId.getText().toString());
                                    editor.putString("logPassword", logPassword.getText().toString());
                                    editor.apply();
                                }
                                userID = user.getName();
                                Toast.makeText(SignIn.this, user.getName()+"님 환영합니다!", Toast.LENGTH_SHORT).show();
                                Intent homeIntent = new Intent(SignIn.this, Home.class);
                                homeIntent.putExtra("userName", user.getName());
                                Common.currentUser = user;
                                startActivity(homeIntent);
                                finish();
                            }
                            else
                                Toast.makeText(SignIn.this, "Wrong Password !!!", Toast.LENGTH_SHORT).show();
                        } else {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "User not exist in Database", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                //Toast.makeText(SignIn.this, "여기", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
