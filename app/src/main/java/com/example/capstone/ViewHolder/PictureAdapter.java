package com.example.capstone.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.capstone.DetailPicture;
import com.example.capstone.Model.Picture;
import com.example.capstone.R;
import com.example.capstone.StartActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ItemViewHolder> {
    // adapter에 들어갈 list 입니다.
    ArrayList<Picture> mBoard = new ArrayList<>();
    Context context;
    int temp_position;
    View view;

    public PictureAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(mBoard.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return mBoard.size();
    }

    public void addItem(Picture picture) {
        // 외부에서 item을 추가시킬 함수입니다.
        mBoard.add(picture);
    }

    public void remove(Picture picture) {
        for (Picture delete : mBoard) {
            if (delete.getPictureID().equals(picture.getPictureID())) {
                mBoard.remove(mBoard.indexOf(delete));
                return;
            }
        }
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private Picture picture;

        ItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailPicture.class);
                    intent.putExtra("picture", picture);
                    context.startActivity(intent);
                }
            });
        }

        void onBind(Picture picture) {
            this.picture = picture;
            Picasso.with(context).load(picture.getUri()).fit().centerInside().into(imageView);
        }

    }

}
