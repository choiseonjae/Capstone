package com.example.capstone.Model;

import android.app.ProgressDialog;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FireBaseRef {
    static final String ALBUM = "Album";
    static final String storageUrl = "gs://capstone-843d1.appspot.com";

    // firebase 객체 생성
    public static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static final FirebaseStorage storage = FirebaseStorage.getInstance();

    // storage와 객체 바인딩
    public static final StorageReference ALBUM_STORAGE = storage.getReferenceFromUrl(storageUrl);
    // firebase와 객체 바인딩
    public static final DatabaseReference ALBUM_DATABASE = database.getReference(ALBUM);

}
