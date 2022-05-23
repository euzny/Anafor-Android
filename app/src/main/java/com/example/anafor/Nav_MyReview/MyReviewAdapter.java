package com.example.anafor.Nav_MyReview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anafor.R;

import java.util.ArrayList;

public class MyReviewAdapter extends RecyclerView.Adapter<MyReviewAdapter.MyReview>{

    LayoutInflater inflater;
    ArrayList<MyReviewDTO> list;

    public MyReviewAdapter(LayoutInflater inflater, ArrayList<MyReviewDTO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public MyReview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_my_review, parent, false);
        return new MyReview(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyReview holder, int position) {
        holder.tv_my_review_name.setText(list.get(position).getName());
        holder.tv_my_review_date.setText(list.get(position).getDate());
        holder.tv_my_review_category.setText(list.get(position).getCategory());
        holder.tv_my_review_content.setText(list.get(position).getContent());
        holder.tv_my_review_survey1.setText(list.get(position).getExplanation());
        holder.tv_my_review_survey2.setText(list.get(position).getDiagnosis());
        holder.tv_my_review_survey3.setText(list.get(position).getKindness());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyReview extends RecyclerView.ViewHolder {

        View itemview;
        TextView tv_my_review_name, tv_my_review_date,
                tv_my_review_category,tv_my_review_content,
                tv_my_review_survey1, tv_my_review_survey2, tv_my_review_survey3;

        public MyReview(@NonNull View itemView) {
            super(itemView);
            this.itemview = itemView;

            tv_my_review_name = itemView.findViewById(R.id.tv_my_review_name);
            tv_my_review_date = itemView.findViewById(R.id.tv_my_review_date);
            tv_my_review_category = itemView.findViewById(R.id.tv_my_review_category);
            tv_my_review_content = itemView.findViewById(R.id.tv_my_review_content);
            tv_my_review_survey1 = itemView.findViewById(R.id.tv_my_review_survey1);
            tv_my_review_survey2 = itemView.findViewById(R.id.tv_my_review_survey2);
            tv_my_review_survey3 = itemView.findViewById(R.id.tv_my_review_survey3);

        }
    }
}
