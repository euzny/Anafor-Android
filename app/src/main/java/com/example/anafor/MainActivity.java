package com.example.anafor;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


import com.example.anafor.Common.CommonVal;
import com.example.anafor.Nav_Schedule.ScheduleActivity;
import com.example.anafor.Nav_Choice.ChoiceActivity;
import com.example.anafor.Pill_Main.Pill_MainFragment;
import com.example.anafor.Hp_Main.Hp_MainFragment;

import com.example.anafor.Hp_Hash.Hp_HashActivity;
import com.example.anafor.Box_Main.Box_MainFragment;


import com.example.anafor.Nav_MyReview.MyReviewActivity;
import com.example.anafor.User.LoginActivity;
import com.example.anafor.Nav_Vaccine.VaccineActivity;
import com.example.anafor.User.UserInfoActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView btm_nav;
    Toolbar main_toolbar;
    DrawerLayout drawer;
    NavigationView nav_view;
    ViewFlipper pic_Slid; // 사진 슬라이드
    TextView tv_login; //신보배 0502 코드추가

    /* 위치 권한 확인을 위한 코드 */
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DrawerLayout → 레이아웃이 감춰져 있다가 나오는 것
        // 카톡을 키고 아무 대화방이나 들어가서 메뉴 누르면 나왔다가 슬라이드로 없어졌다가 함
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        main_toolbar = findViewById(R.id.main_toolbar);
        btm_nav = findViewById(R.id.btm_nav);
        drawer = findViewById(R.id.drawer);
        nav_view = findViewById(R.id.nav_view);
        pic_Slid = findViewById(R.id.mainMidMenu);

        slidePic();

        setSupportActionBar(main_toolbar);

        ActionBar ac = getSupportActionBar();
        ac.setTitle("");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, main_toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        View headerView = nav_view.getHeaderView(0);

        //신보배 0502 코드 추가
        tv_login = headerView.findViewById(R.id.tv_main_header_login);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        changeFragment(new Hp_MainFragment());


        //위치 권한 퍼미션
        if (!checkLocationServicesStatus()) {
            showDialogForLocationServiceSetting();
        } else {
            checkRunTimePermission();
        }

        ImageView imgv_search = findViewById(R.id.imgv_main_search);

        imgv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Hp_HashActivity.class);
                startActivity(intent);
            }
        });

        btm_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // 프래그먼트가 각각의 화면에 맞게 전환 됨
                // 광고 ViewFlipper 를 메인 엑티비티에만 적용시키고 
                // 나머지 바텀메뉴 프래그먼트에는 안보이게 GONE 처리
                if (item.getItemId() == R.id.btm_home){
                    pic_Slid.setVisibility(View.VISIBLE);
                    changeFragment(new Hp_MainFragment());
                }else if (item.getItemId() == R.id.btm_cheobang){
                    pic_Slid.setVisibility(View.GONE);
                    changeFragment(new Pill_MainFragment());
                }else if (item.getItemId() == R.id.btm_yagtong){
                    pic_Slid.setVisibility(View.GONE);
                    changeFragment(new Box_MainFragment());
                }
                return true;
            }
        });

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            private static final String TAG = "홈";

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 if (item.getItemId() == R.id.nav_schedule){
                    Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                    startActivity(intent);
                }else if(item.getItemId() == R.id.nav_choice){
                    Intent intent = new Intent(MainActivity.this, ChoiceActivity.class);
                    startActivity(intent);
                }else if (item.getItemId() == R.id.nav_review) {
                    Intent intent = new Intent(MainActivity.this, MyReviewActivity.class);
                    startActivity(intent);
                }else if(item.getItemId() == R.id.nav_information){
                    Intent intent = new Intent(MainActivity.this, VaccineActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });
    }

    // 이미지를 보여주기 위한 리스트 추가
    public void slidePic(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.a);
        list.add(R.drawable.b);
        list.add(R.drawable.human);
        list.add(R.drawable.inplu);
        list.add(R.drawable.pasang);

        for(int image : list) {
            fllipperImages(image);
        }
    }

    // 사진 슬라이드 구동
    private void fllipperImages(int image) {
        ImageView imageView = new ImageView(this);

        // 가져온 사진을 채움
        imageView.setBackgroundResource(image);

        pic_Slid.addView(imageView);

        // 사진 넘어가는 시간
        pic_Slid.setFlipInterval(3500);

        // 자동으로 계속 넘어가게 할 것 인지
        pic_Slid.setAutoStart(true);

        // 오른쪽에서 왼쪽으로 (xml 파일 따로 추가했음)
        pic_Slid.setInAnimation(this,R.anim.slide_in_right);
        pic_Slid.setOutAnimation(this,R.anim.slide_out_left);
    }

    public void changeFragment(Fragment fragment){   /* 플래그먼트가 바뀌면서 */
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, fragment).commit();
    }


    //신보배 0517 코드 추가
    @Override
    protected void onResume() {
        super.onResume();
        if(CommonVal.loginInfo != null){
            tv_login.setText(CommonVal.loginInfo.getUser_name()+"님 반갑습니다");
            tv_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
                    startActivity(intent);
                }
            });
            //btn_modify.setVisibility(View.VISIBLE);
        }
    }

    // ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        super.onRequestPermissionsResult(permsRequestCode, permissions, grandResults);
        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면
            boolean check_result = true;

            // 모든 퍼미션을 허용했는지 체크합니다.
            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }

            if (check_result) {
                Log.d("@@@", "start");
                //위치 값을 가져올 수 있음

            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있다
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {
                    Toast.makeText(MainActivity.this,"퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    void checkRunTimePermission(){

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED ) {
            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식)
            // 3.  위치 값을 가져올 수 있음

        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요 2가지 경우(3-1, 4-1)
            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, REQUIRED_PERMISSIONS[0])) {
                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                // Toast.makeText(MainActivity.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }
        }
    }

    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하시겠습니까?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case GPS_ENABLE_REQUEST_CODE:
                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {
                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }
                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

}