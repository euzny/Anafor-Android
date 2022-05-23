package com.example.anafor.Nav_Schedule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.anafor.Hp_List.Hp_ListAdapter;
import com.example.anafor.Hp_List.Hp_ListDTO;
import com.example.anafor.R;

import java.util.ArrayList;

public class ScheduleFragment2 extends Fragment {

    ArrayList<ScheduleDTO> list;
    ScheduleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule2, container, false);
//==================================================================================================
        RecyclerView recv_schedule = v.findViewById(R.id.recv_schedule);

        // 리사이클러뷰의 진행 방향을 가로로 할 건지 세로로 할 건지 정하는 코드
        RecyclerView.LayoutManager manager = new LinearLayoutManager(
                getContext(), RecyclerView.VERTICAL, false);

        // 리사이클러뷰에 표시할 데이터의 리스트를 생성
        list = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            list.add(new ScheduleDTO("내과 가는 날", "진료 받고 상담도 하고 오기" ));
            list.add(new ScheduleDTO("좀 되라", "진료 받고 상담도 하고 오기" ));
        }

        adapter = new ScheduleAdapter(inflater, list, getActivity());

        // 리사이클러뷰에 어댑터를 세팅
        recv_schedule.setAdapter(adapter);
        recv_schedule.setLayoutManager(manager);

        return v;
//==================================================================================================
    }
}