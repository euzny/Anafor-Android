package com.example.anafor.Hp_Information;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonVal;
import com.example.anafor.R;
import com.example.anafor.User.LoginActivity;
import com.example.anafor.utils.GetDate;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Hp_InformationActivity extends AppCompatActivity {

    TabLayout hp_infor_tab_layout;
    ImageView imgv_hp_infor_back;
    TextView hp_infor_time, hp_infor_infor, hp_infor_review, tv_hp_today,tv_hp_todayTime,
            tv_hp_tlunch, tv_hp_name, tv_hp_addr , tv_hp_url, tv_hp_phone, tv_hp_wlunch,tv_hp_holi,
            tv_hp_mon, tv_hp_tue, tv_hp_wed, tv_hp_thu, tv_hp_fri, tv_hp_sat, tv_hp_sun, tv_hp_dlunch;
    ScrollView hp_infor_scview;
    Button btn_review;
    ImageView imgv_heartclick;
    String [] weekName = {"일요일","월요일","화요일","수요일","목요일","금요일","토요일"};  //요일
    TextView[] tv_time = { tv_hp_mon, tv_hp_tue, tv_hp_wed, tv_hp_thu, tv_hp_fri, tv_hp_sat,tv_hp_holi,tv_hp_dlunch,tv_hp_wlunch}; //8번째 마지막
    String [] weekTime;             //진료시간
    int today = GetDate.getCurrentWeek(); //오늘 요일
    Hp_infoDTO infoDTO;

    boolean heartclick = false; // 찜하기 클릭 안 한 상태 (default)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hp_information);
        Intent intent = getIntent();
         infoDTO = (Hp_infoDTO) intent.getSerializableExtra("infoDTO"); //DTO 값 저장

        weekTime = new String[]{infoDTO.getStart_m(), infoDTO.getEnd_m(), infoDTO.getStart_t(), infoDTO.getEnd_t(), infoDTO.getStart_w(),
        infoDTO.getEnd_w(), infoDTO.getStart_th(), infoDTO.getEnd_th(), infoDTO.getStart_f(), infoDTO.getEnd_f(), infoDTO.getStart_s(), infoDTO.getEnd_s(),
        infoDTO.getClose_ho(), infoDTO.getLunch_d(), infoDTO.getLunch_w()};

        
        imgv_heartclick = findViewById(R.id.imgv_heartclick);               //찜하기 기능

        hp_infor_scview = findViewById(R.id.hp_infor_scview);

        btn_review = findViewById(R.id.btn_review);                                 //리뷰 등록
        //탭 이동시 사용
        hp_infor_time = findViewById(R.id.hp_infor_time);                    //진료시간
        hp_infor_infor = findViewById(R.id.hp_infor_infor);                 //병원정보
        hp_infor_review = findViewById(R.id.hp_infor_review);                //병원 리뷰
        
        tv_hp_name = findViewById(R.id.tv_hp_name);                         //병원 이름
        tv_hp_addr = findViewById(R.id.tv_hp_addr);                         //병원 주소

        tv_hp_url = findViewById(R.id.tv_hp_url);                                        //병원 url
        tv_hp_phone = findViewById(R.id.tv_hp_phone);                        //병원 전화번호

        tv_hp_today = findViewById(R.id.tv_hp_today);                                 // 오늘 요일
        tv_hp_todayTime = findViewById(R.id.tv_hp_todayTime);               //오늘 진료시간
        tv_hp_tlunch =findViewById(R.id.tv_hp_tlunch);                              // 오늘 점심시간

        //요일별 진료시간
       tv_time[0] = findViewById(R.id.tv_hp_mon);                           //월요일
        tv_time[1] = findViewById(R.id.tv_hp_tue);                           //화요일
       tv_time[2] = findViewById(R.id.tv_hp_wed);                           //수요일
        tv_time[3] = findViewById(R.id.tv_hp_thu);                            //목요일
       tv_time[4] = findViewById(R.id.tv_hp_fri);                           //금요일
        tv_time[5] = findViewById(R.id.tv_hp_sat);                            //토요일
        tv_time[6] = findViewById(R.id.tv_hp_holi);                          //공휴일
        tv_time[7] = findViewById(R.id.tv_hp_dlunch);                      //평일 점심시간
        tv_time[8] = findViewById(R.id.tv_hp_wlunch);                      //주말 점심시간
        tv_hp_sun = findViewById(R.id.tv_hp_sun);                            //일요일

        writeTextView();  //전체 진료시간 출력
        writeHpInfo();



        //로그인이 된 경우
        if(CommonVal.loginInfo !=null) {
            AskTask task = new AskTask("");
        
        }

        //찜하기 기능
        //로그인이 안되었을 경우 - 로그인이 필요한 기능
        imgv_heartclick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(CommonVal.loginInfo ==null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Hp_InformationActivity.this);
                            builder.setTitle("로그인");
                            builder.setMessage("로그인이 필요한 기능입니다. 로그인 하시겠습니까?");
                            builder.setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent loginIntent = new Intent(Hp_InformationActivity.this, LoginActivity.class);
                                    startActivity(loginIntent);
                                }
                            });
                            builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.show();
                        }
                }
            });


        hp_infor_tab_layout = findViewById(R.id.hp_infor_tab_layout);  //탭 레이아웃
        imgv_hp_infor_back = findViewById(R.id.imgv_hp_infor_back);


        imgv_hp_infor_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이 곳에서 찜하기 기능 insert나 delete 하고 넘기기
                onBackPressed();    // 바로 이전에 왔던 곳으로 이동 (마이페이지 유지)
            }
        });

        hp_infor_tab_layout = findViewById(R.id.hp_infor_tab_layout);
        hp_infor_tab_layout.addTab(hp_infor_tab_layout.newTab().setText("진료시간").setId(0));
        hp_infor_tab_layout.addTab(hp_infor_tab_layout.newTab().setText("병원정보").setId(1));
        hp_infor_tab_layout.addTab(hp_infor_tab_layout.newTab().setText("리뷰").setId(2));

        //탭 클릭시 해당하는 위치로 이동
        hp_infor_tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();

                if(position == 0){
                    hp_infor_scview.smoothScrollTo(0, hp_infor_time.getTop());
                }else if (position == 1){
                    hp_infor_scview.smoothScrollTo(0, hp_infor_infor.getTop());
                }else if (position == 2){
                    hp_infor_scview.smoothScrollTo(0, hp_infor_review.getTop());
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //리뷰 등록 버튼 클릭시 리뷰 작성액티비티로 이동
        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hp_InformationActivity.this, Hp_InformationReviewActivity.class);
                startActivity(intent);
            }
        });
    }

    //전체 진료시간 TextView에 작성하는 메소드
    public void writeTextView(){

        //오늘 진료시간 
        tv_hp_today.setText(weekName[today]); //오늘 요일

        //오늘 점심시간 
        if(today == 6 || today == 0){     //주말일때
            if(infoDTO.getLunch_w()!=null){
                tv_hp_tlunch.setText(infoDTO.getLunch_w());
            }
        }else{
            if(infoDTO.getLunch_d()!=null){         //평일일때
                tv_hp_tlunch.setText(infoDTO.getLunch_d());
            }
        }
        
        int cnt = 0;
            for(int i = 0; i<9; i++){
                if(i<6){               //월~토요일 
                    if(today+1 == i){   //1,2,3,4,5,6
                            if(weekTime[cnt]!= null && weekTime[cnt+1]!= null){
                                //Log.d("today@@@@@@", i+" "+today+"  writeTextView: "+cnt);
                                    tv_hp_todayTime.setText(weekTime[cnt]+"~"+weekTime[cnt+1]);
                            }
                        }
                    if(weekTime[cnt]!= null && weekTime[cnt+1] != null){
                    //    Log.d("@@@@@@@@", i+"  writeTextView: "+cnt);
                        tv_time[i].setText(weekTime[cnt]+"~"+weekTime[cnt+1]);
                        cnt+=2;
                    }
                }else{                              //평일, 주말점심시간
                    if(weekTime[cnt]!= null){
                   //     Log.d("@@@@@@@@", i+"  writeTextView: "+cnt);
                        tv_time[i].setText(weekTime[cnt]);
                        cnt++;
                    }
                }
            }

        //일요일은 휴무이거나 휴무가 아닌 병원이 존재
        if(infoDTO.getStart_su()!= null && infoDTO.getEnd_su()!= null){  //일요일 진료
            if(today==0){
                tv_hp_todayTime.setText(infoDTO.getStart_su()+"~"+infoDTO.getEnd_su());
            }
            tv_hp_sun.setText(infoDTO.getStart_su()+"~"+infoDTO.getEnd_su());
            
        }else if (infoDTO.getClose_su()!= null){                        //일요일 휴무
            if(today==0){
                tv_hp_todayTime.setText(infoDTO.getClose_su());
            }
            tv_hp_sun.setText(infoDTO.getClose_su());
        }
    }

    //병원 정보 출력(이름, 주소, 전화번호,  사이트 링크)
    public void writeHpInfo(){
        //병원 정보 띄우기
        tv_hp_name.setText(infoDTO.getHp_name());
        tv_hp_addr.setText(infoDTO.getHp_addr());

        // 병원 사이트 링크가 존재할 경우 해당 링크로 이동
        if(infoDTO.getHp_url()!= null){
            tv_hp_url.setText(infoDTO.getHp_url());
            tv_hp_url.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                    urlIntent.setData(Uri.parse(tv_hp_url.getText().toString()));
                    startActivity(urlIntent);
                }
            });
        }
        //병원 전화번호가 존재할 경우 전화 걸기 기능
        if(infoDTO.getHp_tel()!= null){
            tv_hp_phone.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_call_24,0,0,0);
            tv_hp_phone.setText(infoDTO.getHp_tel());
            tv_hp_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent phIntent = new Intent(Intent.ACTION_VIEW);
                    phIntent.setData(Uri.parse("tel:"+tv_hp_phone.getText().toString()));
                    startActivity(phIntent);
                }
            });
        }

    }
}