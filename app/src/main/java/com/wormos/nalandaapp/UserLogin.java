package com.wormos.nalandaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserLogin extends AppCompatActivity {

    TextView loginNewUserTv;
    AppCompatButton loginBackBtn,loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        loginNewUserTv = findViewById(R.id.login_new_user_tv);
        loginBackBtn = findViewById(R.id.login_back_btn);
        loginBtn = findViewById(R.id.login_btn);

        //Methodology
        loginBackBtn.setOnClickListener(view -> finish());

        loginNewUserTv.setOnClickListener(view -> {
            startActivity(new Intent(UserLogin.this, UserRegistration.class));
            finish();
        });

        loginBtn.setOnClickListener(view -> startActivity(new Intent(this,Dashboard.class)));
    }
}