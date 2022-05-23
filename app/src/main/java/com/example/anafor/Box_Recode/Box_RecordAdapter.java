package com.example.anafor.Box_Recode;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.R;

import java.util.ArrayList;

public class Box_RecordAdapter extends RecyclerView.Adapter<Box_RecordAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<Box_RecordDTO> list;

    public Box_RecordAdapter(LayoutInflater inflater, ArrayList<Box_RecordDTO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_box_recode, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_box_recode_title.setText(list.get(position).getTitle());
        holder.tv_box_recode_content.setText(list.get(position).getContent());
        holder.tv_box_recode_date.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View itemview;
        TextView tv_box_recode_title, tv_box_recode_content, tv_box_recode_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;

            tv_box_recode_title = itemView.findViewById(R.id.tv_box_recode_title);
            tv_box_recode_content = itemView.findViewById(R.id.tv_box_recode_content);
            tv_box_recode_date = itemView.findViewById(R.id.tv_box_recode_date);
        }
    }


}
