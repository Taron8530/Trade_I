package com.example.solo_project;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class main_adapter extends RecyclerView.Adapter<main_adapter.ViewHolder> {
    String TAG = "main_adapter";
    ArrayList<item_model> lists;
    private OnItemClickListener mListener = null ;
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener ;
    }
    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }
    public main_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        Log.e(TAG, "onCreateViewHolder: 호출됨" );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull main_adapter.ViewHolder holder, int position) {
        holder.onbind(lists.get(position));
        Log.e(TAG, "onBindViewHolder: 호충");
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
    public void setlist(ArrayList<item_model> list){
        lists = list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView price;
        TextView used_item;
        TextView nickname;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.e(TAG, "ViewHolder: 호출됨" );
            price = itemView.findViewById(R.id.price);
            used_item = itemView.findViewById(R.id.used_name);
            nickname = itemView.findViewById(R.id.r_nickname);
            image = itemView.findViewById(R.id.used_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        mListener.onItemClick(view,position);
                    }
                }
            });
        }

        public void onbind(item_model item) {
            Log.e(TAG, "onbind: 호출됨");
            price.setText(item.getPrice()+"원");
            used_item.setText(item.getusedname());
            nickname.setText("판매자: "+item.getNickname());
            Log.e("adapter", String.valueOf(item.getImage_size()));
            Glide.with(itemView).load("http://35.166.40.164/used_image/"+item.getNum()+"0"+".jpeg").override(100, 100).error(R.drawable.app_icon).into(image);
        }
    }
}
