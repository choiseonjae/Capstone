package com.example.capstone;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstone.Common.Common;
import com.example.capstone.Model.Picture;
import com.example.capstone.Model.User;
import com.example.capstone.ViewHolder.FamilyAdapter;
import com.example.capstone.ViewHolder.PictureAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class FamilyInformation extends AppCompatActivity {

    FamilyAdapter adapter;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_information);


        // 내 정보 가져오기 : user <-  내 정보 넣음
        final DatabaseReference myInfo = Common.getDatabase("User").child(Common.getMyId());
        myInfo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
        getData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                familyAdd_alert();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


// 가족 없으면
//        if(adapter.isEmpty()){
//
//            // RelativeLayout 객체를 생성합니다.
//            RelativeLayout rl = new RelativeLayout(this);
//            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT
//            );
//            rl.setLayoutParams(params);
//
//
//            // 두 번째 화면 중앙 버튼 구현
//            TextView textView_noFamily = new TextView(this);
//            textView_noFamily.setId(View.generateViewId());
//            textView_noFamily.setText("연결된 가족이 없습니다.");
//
//            RelativeLayout.LayoutParams middleTextViewParams = new RelativeLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//            );
//            textView_noFamily.setLayoutParams(middleTextViewParams);
//
//            middleTextViewParams.addRule(RelativeLayout.CENTER_IN_PARENT, 1);
//
//            rl.addView(textView_noFamily);
//
//            setContentView(rl);
//
//
//        }

    }

    private void init() {  //리사이클러뷰 초기화 및 동작
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new FamilyAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void familyAdd_alert() {
        AlertDialog.Builder ad = new AlertDialog.Builder(FamilyInformation.this);

        ad.setTitle("가족 관계 추가");       // 제목 설정
        ad.setMessage("생성할 관계의 이름을 입력해주세요.");   // 내용 설정

        // EditText 삽입하기
        final EditText et = new EditText(FamilyInformation.this);
        ad.setView(et);

        // 확인 버튼 설정
        ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Text 값
                final String familyName = et.getText().toString();

                // 가족 관계 새로 생성
                final DatabaseReference userRef = Common.getDatabase("Family").push();

                // 구성원 나 혼자
                userRef.child(familyName).push().setValue(Common.getMyId());
//                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        // 가족이 존재하면 (+ 넣어야 할 사항 -> 상대방의 의사 물어보기 + 상대방도 가족있을 때 처리)
//                        if (dataSnapshot.exists()) {
//
//                            if(user.getFamilyID() != null){
//                                Log.e("","!=null");
//                                final DatabaseReference familyRef = Common.getDatabase("Family").child(user.getFamilyID());
//                                familyRef.push().setValue(familyID);
//                            }else{
//                                Log.e("?","s");
//                                final DatabaseReference familyRef = Common.getDatabase("Family").push();
//                                user.setFamilyID(familyRef.getKey());
//                                Common.getDatabase("User").child(Common.getMyId()).setValue(user);
//                                // + 상대편 가족도 추가해주기
//                                // 가족 묶음에 우리 ID 추가
//                                familyRef.push().setValue(Common.getMyId());
//                                familyRef.push().setValue(familyID);
//                            }
//
//
//                        } else
//                            Toast.makeText(getApplicationContext(), "ID가 존재하지 않습니다.", Toast.LENGTH_LONG);
//
//                        Log.e("exist : ", dataSnapshot.exists() + "");
//                        Log.e("data : ", dataSnapshot.toString());
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });

                // 닫기
                dialog.dismiss();

            }
        });

        // 취소 버튼 설정
        ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
            private DialogInterface dialog;
            private int which;

            @Override
            public void onClick(DialogInterface dialog, int which) {
                this.dialog = dialog;
                this.which = which;

                Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_SHORT);

                dialog.dismiss();     //닫기
            }
        });

        // 창 띄우기
        ad.show();

    }

    private void getData() {

        final DatabaseReference familyRef = Common.getDatabase("Family");
        familyRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (DataSnapshot child : dataSnapshot.getChildren())
                    adapter.addItem(new String[]{dataSnapshot.getKey(), child.getKey()});
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
