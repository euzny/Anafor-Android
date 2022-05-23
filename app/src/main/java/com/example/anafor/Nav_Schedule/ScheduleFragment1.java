package com.example.anafor.Nav_Schedule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anafor.R;

public class ScheduleFragment1 extends Fragment {

    TextView tv_schedule_title_schedule, tv_schedule_memo_schedule;
    EditText edt_schedule_title_schedule, edt_schedule_memo_schedule;
    Button btn_schedule_insert_schedule;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_schedule1, container, false);

        tv_schedule_title_schedule = v.findViewById(R.id.tv_schedule_title_schedule);
        tv_schedule_memo_schedule = v.findViewById(R.id.tv_schedule_memo_schedule);
        edt_schedule_title_schedule = v.findViewById(R.id.edt_schedule_title_schedule);
        edt_schedule_memo_schedule = v.findViewById(R.id.edt_schedule_memo_schedule);
        btn_schedule_insert_schedule = v.findViewById(R.id.btn_schedule_insert_schedule);

//==================================================================================================
        btn_schedule_insert_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_schedule_title_schedule.getText().toString().length() == 0 || edt_schedule_title_schedule.getText().toString().equals(" ")){
                    Toast.makeText(getContext().getApplicationContext(), "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    edt_schedule_title_schedule.requestFocus();
                }else if(edt_schedule_memo_schedule.getText().toString().length() == 0 || edt_schedule_memo_schedule.getText().toString().equals(" ")){
                    Toast.makeText(getContext().getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    edt_schedule_memo_schedule.requestFocus();
                }else{
                    Toast.makeText(getContext().getApplicationContext(), "일정 등록 완료", Toast.LENGTH_SHORT).show();
                    ((ScheduleActivity)getActivity()).changeFragment(new ScheduleFragment2());
                }
            }
        });
        return v;
//==================================================================================================
    }
}