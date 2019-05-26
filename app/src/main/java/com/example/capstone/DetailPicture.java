package com.example.capstone;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.capstone.Common.Infomation;
import com.example.capstone.Model.Picture;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class DetailPicture extends AppCompatActivity {

    // 서명
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private ImageView deletePicture, sharePicrue, downloadPicture;
    private Picture picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_picture);

        // 서명
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // do your stuff
        } else {
            signInAnonymously();
        }

        picture = (Picture) getIntent().getSerializableExtra("picture");

        Picasso.with(getApplicationContext()).load(picture.getUri()).fit().centerInside().into((ImageView) findViewById(R.id.detail_picture));

        deletePicture = findViewById(R.id.delete_picture);
        sharePicrue = findViewById(R.id.share_picture);
        downloadPicture = findViewById(R.id.download_picture);
        deletePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePicture_alert();
            }
        });
        sharePicrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        downloadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadPicture_alert();
            }
        });

    }

    // 사진 업로드 할 지 물어보는 알람
    private void deletePicture_alert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("사진 삭제");
        builder.setMessage("사진을 삭제하시겠습니까?\n한번 삭제한 사진은 복구가 불가능합니다.");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        picture.setDeleted(true);
                        Infomation.getAlbumData(picture.getUploadID()).child(picture.getPictureID()).setValue(picture);
                        finish();
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.show();
    }

    // 사진 다운로드 .. 안됨.
    private void downloadPicture_alert() {
        try {
            File localFile = File.createTempFile(picture.getFileName(), "jpg");
            final StorageReference albumRef = FirebaseStorage.getInstance().getReference("Album");
            Log.e("??? L : ", picture.getUploadID() + "/" + picture.getFileName());
            albumRef.child(picture.getUploadID()).child(picture.getFileName()).getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getApplicationContext(), "저장 완료", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(getApplicationContext(), "저장 실패", Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 서명
    private void signInAnonymously() {
        mAuth.signInAnonymously().addOnSuccessListener(this, new  OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                // do your stuff
            }
        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e("에러요! : ", "signInAnonymously:FAILURE", exception);
                    }
                });
    }

}
