package com.example.anafor.utils;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;


public class GetDate {
    //오늘 날짜 구하기
    public static String getCurrentDate(){
        Date today = Calendar.getInstance().getTime();
        Log.d("TAG", "getCurrentDate: "+today.toString());
        return today.toString();
    }
    // 오늘 요일 구하기 (일:0~ 토:6)
    public static int getCurrentWeek() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int dayOfWeekNumber = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfWeekNumber-=1;
        return dayOfWeekNumber;
    }
}
