package com.example.anafor.Pill_Main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anafor.R;


import java.util.ArrayList;


public class Pill_MainFragment extends Fragment {

    ArrayList<Pill_MainDTO> list;
    Pill_MainAdapter adapter;
    TextView tv_pill_main_date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pill_main, container, false);

        RecyclerView recv_select = v.findViewById(R.id.recv_select);
        tv_pill_main_date = v.findViewById(R.id.tv_pill_main_date);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(
                getContext(), RecyclerView.VERTICAL, false);

        list = new ArrayList<>();
        list.add(new Pill_MainDTO("A 조 병원", "2022.05.17"));
        list.add(new Pill_MainDTO("B 조 병원", "2022.06.17"));
        list.add(new Pill_MainDTO("C 조 병원", "2022.07.17"));

        adapter = new Pill_MainAdapter(inflater, list);

        recv_select.setAdapter(adapter);
        recv_select.setLayoutManager(manager);
        return v;

    }
}