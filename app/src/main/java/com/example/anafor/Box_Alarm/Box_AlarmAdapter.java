package com.example.anafor.Box_Alarm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.R;

import java.util.ArrayList;

public class Box_AlarmAdapter extends RecyclerView.Adapter<Box_AlarmAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<Box_AlarmDTO> list;

    public Box_AlarmAdapter(LayoutInflater inflater, ArrayList<Box_AlarmDTO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_box_alarm, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgv_box_alarm_img.setImageResource(list.get(position).getImgv_url());
        holder.tv_box_alarm_title.setText(list.get(position).getTitle());
        holder.tv_box_alarm_location.setText(list.get(position).getLocation());
        holder.tv_box_alarm_date.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        View itemview;
        ImageView imgv_box_alarm_img;
        TextView tv_box_alarm_title, tv_box_alarm_location, tv_box_alarm_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;

            imgv_box_alarm_img = itemview.findViewById(R.id.imgv_box_alarm_img);
            tv_box_alarm_title = itemview.findViewById(R.id.tv_box_alarm_title);
            tv_box_alarm_location = itemview.findViewById(R.id.tv_box_alarm_location);
            tv_box_alarm_date = itemview.findViewById(R.id.tv_box_alarm_date);

        }
    }
}
