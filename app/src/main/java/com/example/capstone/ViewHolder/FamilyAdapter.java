package com.example.capstone.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.capstone.Model.User;
import com.example.capstone.R;


import java.util.ArrayList;

public class FamilyAdapter extends RecyclerView.Adapter<FamilyAdapter.ItemViewHolder> {
    // adapter에 들어갈 list 입니다.
    ArrayList<String[]> family = new ArrayList<>();
    Context context;
    View view;

    public FamilyAdapter(Context context) {
        this.context = context;
    }

    public boolean isEmpty() {
        return family.isEmpty();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.family_list_item, parent, false);
        Log.e("SSS", "s");
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(family.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return family.size();
    }

    public void addItem(String[] value) {
        // 외부에서 item을 추가시킬 함수입니다.
        family.add(value);
    }

//    public void remove(User user) {
//        for (User delete : mBoard) {
//            if (delete.getPictureID().equals(picture.getPictureID())) {
//                mBoard.remove(mBoard.indexOf(delete));
//                return;
//            }
//        }
//    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private String[] value;
        private TextView familyTitle_textView;

        ItemViewHolder(View view) {
            super(view);
            Log.e("AAA: ", "");
            familyTitle_textView = view.findViewById(R.id.family_title_textView);

            view.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Log.e("??????::", "");
                    Toast.makeText(v.getContext(), "클릭!", Toast.LENGTH_SHORT);
                }
            });

        }

        void onBind(String[] value) {
            this.value = value;
            familyTitle_textView.setText(value[1]);
        }

    }

}
