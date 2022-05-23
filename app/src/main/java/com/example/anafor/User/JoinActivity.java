package com.example.anafor.User;

import static android.content.res.ColorStateList.valueOf;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anafor.Common.AskTask;
import com.example.anafor.Common.CommonMethod;
import com.example.anafor.MainActivity;
import com.example.anafor.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class JoinActivity extends AppCompatActivity {
    private static final String TAG = "회원가입";
    private String str_result;

    TextView tv_logo;
    ImageView back;
    Button btn_join;
    TextInputLayout til_id,til_pw,til_pwChk,til_name,til_birth,til_tel;
    TextInputEditText tiedt_id,tiedt_pw,tiedt_pwChk,tiedt_name,tiedt_birth,tiedt_tel;
    RadioGroup radioGroup;
    RadioButton rdo_male, rdo_female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        //TextInputLayout 아이디 찾기
        til_id = findViewById(R.id.til_join_email);
        til_pw = findViewById(R.id.til_join_pw);
        til_pwChk = findViewById(R.id.til_join_pwChk);
        til_name = findViewById(R.id.til_join_name);
        til_birth = findViewById(R.id.til_join_birth);
        til_tel = findViewById(R.id.til_join_tel);

        //TextInputEditText 아이디 찾기
        tiedt_id = findViewById(R.id.tiet_join_email);
        tiedt_pw = findViewById(R.id.tiedt_join_pw);
        tiedt_pwChk = findViewById(R.id.tiedt_join_pwChk);
        tiedt_name = findViewById(R.id.tiedt_join_name);
        tiedt_birth = findViewById(R.id.tiedt_join_birth);
        tiedt_tel = findViewById(R.id.tiedt_join_tel);

        //성별 라디오버튼
        radioGroup = findViewById(R.id.radioGroup);
        rdo_male = findViewById(R.id.rdoBtn_join_male);
        rdo_female = findViewById(R.id.rdoBtn_join_female);

        //메인액티비티 이동
        tv_logo = findViewById(R.id.tv_join_anaforlogo);
        tv_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //뒤로가기
        back = findViewById(R.id.imgv_join_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        /* 유효성 검사 (1.이메일, 2.비번, 3.비번확인, 4.이름, 5.전화번호, 6.생년월일, 7.성별 ) */

        // 1.아이디(=이메일)
        tiedt_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String idInput = tiedt_id.getText().toString();
                if(!idInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(idInput).matches()) {
                    til_id.setHelperText("사용 가능한 이메일입니다.");
                    til_id.setHelperTextColor(valueOf(Color.parseColor("#FF6200EE")));
                }else if(idInput.replace(" ", "").equals("")){
                    til_id.setError("아이디는 이메일 형식으로 입력해주세요");
                }else{
                    til_id.setError("아이디는 이메일 형식으로 입력해주세요");
                }
            }
        });

        // 2.비밀번호
        tiedt_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String pwInput = tiedt_pw.getText().toString();
                if(!pwInput.isEmpty() && Pattern.matches("^[a-zA-Z0-9]{8,16}$", pwInput)){
                    til_pw.setHelperText("사용 가능한 비밀번호입니다.");
                    til_pw.setHelperTextColor(valueOf(Color.parseColor("#FF6200EE")));
                }else if(pwInput.replace(" ", "").equals("")){
                    til_pw.setError("비밀번호를 입력하세요");
                    til_pwChk.setHelperText(null);
                    til_pwChk.setError(null);
                }else{
                    til_pw.setError("사용할 수 없는 비밀번호입니다.");
                }
            }
        });

        // 3.비밀번호확인
        tiedt_pwChk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String pwInput = tiedt_pw.getText().toString();
                String pwChkInput = tiedt_pwChk.getText().toString();
                if(pwChkInput.equals(pwInput) && !(pwChkInput.replace(" ", "").equals("")) && !(pwInput.isEmpty()) ) {
                    til_pwChk.setHelperText("비밀번호가 일치합니다");
                    til_pwChk.setHelperTextColor(valueOf(Color.parseColor("#FF6200EE")));
                    til_pwChk.setError(null);
                }else if(pwChkInput.replace(" ", "").equals("")){
                    til_pwChk.setError("비밀번호를 입력하세요");
                }else{
                    til_pwChk.setError("비밀번호 정보가 일치하지 않습니다.");
                }
            }
        });

        // 4.이름
        tiedt_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String nameInput = tiedt_name.getText().toString();
                if (!nameInput.isEmpty() && Pattern.matches("^[가-힣]*$", nameInput)) {
                    til_name.setHelperText(" ");
                } else if (nameInput.replace(" ", "").equals("")) {
                    til_name.setError("이름을 입력하세요");
                } else {
                    til_name.setError("이름을 한글로 입력해주세요");
                }
            }
        });

        // 5.전화번호
        tiedt_tel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String telInput = tiedt_tel.getText().toString();
                if (!telInput.isEmpty() && Pattern.matches("\\d{11}", telInput)) {
                    til_tel.setHelperText(" ");
                } else if (telInput.replace(" ", "").equals("")) {
                    til_tel.setError("핸드폰번호를 입력하세요");
                } else {
                    til_tel.setError("번호만 입력하세요 예)01012345678");
                }
            }
        });

        // 6.생년월일
        tiedt_birth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String birthInput = tiedt_birth.getText().toString();
                SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyyMMdd"); //검증할 날짜 포맷 설정
                dateFormatParser.setLenient(false); //false일경우 처리시 입력한 값이 잘못된 형식일 시 오류가 발생
                try {
                    dateFormatParser.parse(birthInput); //대상 값 포맷에 적용되는지 확인
                    til_birth.setHelperText("");
                    til_birth.setError(null);
                } catch (ParseException e) {
                    e.printStackTrace();
                    til_birth.setError("생년월일 입력하세요 예)19950101");
                }
            }
        });

        // 7. 성별
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rdoBtn_join_male) {
                    str_result = "남";
                }else if (checkedId == R.id.rdoBtn_join_female){
                    str_result = "여";
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        //가입하기 버튼
        btn_join = findViewById(R.id.btn_join_join);
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AskTask task = new AskTask("join");
                task.addParam("user_id",tiedt_id.getText().toString());
                task.addParam("user_pw",tiedt_pw.getText().toString());
                task.addParam("user_name",tiedt_name.getText().toString());
                task.addParam("user_tel",tiedt_tel.getText().toString());
                task.addParam("user_birth",tiedt_birth.getText().toString());
                task.addParam("user_gender",str_result);
                CommonMethod.executeAskGet(task);
                Intent intent = new Intent(JoinActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    //포커스
    public void requestFocus(View view){
        if (view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}


