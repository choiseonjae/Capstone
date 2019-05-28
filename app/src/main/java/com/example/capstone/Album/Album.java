package com.example.capstone.Album;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.capstone.Common.Common;
import com.example.capstone.Model.Picture;
import com.example.capstone.R;
import com.example.capstone.ViewHolder.PictureAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;


public class Album extends AppCompatActivity {

    final String myID = Common.getMyId();
    PictureAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_main);
        init();
        getData();
    }

    private void init() {  //리사이클러뷰 초기화 및 동작
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new PictureAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void getData() {

        // 내 앨범 데이터 참조 가져오기
        final DatabaseReference albumDataRef = Common.getAlbumData(myID);
        albumDataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                // DB에 추가된 picture 정보를 가져온다.
                final Picture picture = dataSnapshot.getValue(Picture.class);
                if (!picture.isDeleted()) {
                    // 해당 파일은 내 ID / 파일 이름 에 존재
                    adapter.addItem(picture);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Picture updatePicture = dataSnapshot.getValue(Picture.class);
                adapter.remove(updatePicture);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Log.e("child", "remove");
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.e("child", "move");
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}