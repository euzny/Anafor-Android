package com.example.anafor.Pill_Main;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.R;

import java.util.ArrayList;

public class Pill_MainAdapter extends RecyclerView.Adapter<Pill_MainAdapter.ViewHolder> {

    LayoutInflater inflater;
    ArrayList<Pill_MainDTO> list;

    public ArrayList<Pill_MainDTO> getList() {
        return list;
    }

    public void setList(ArrayList<Pill_MainDTO> list) {
        this.list = list;
    }

    public Pill_MainAdapter(LayoutInflater inflater, ArrayList<Pill_MainDTO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_pill_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.tv_pill_main_name.setText(list.get(i).getName());
        holder.tv_pill_main_date.setText(list.get(i).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View itemview;
        ImageView imgv_pill_main_img;
        TextView tv_pill_main_name,tv_pill_main_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;

            imgv_pill_main_img = itemView.findViewById(R.id.imgv_pill_main_img);
            tv_pill_main_name = itemView.findViewById(R.id.tv_pill_main_name);
            tv_pill_main_date = itemView.findViewById(R.id.tv_pill_main_date);
        }
    }



}
