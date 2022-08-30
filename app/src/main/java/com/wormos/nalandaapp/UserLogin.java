package com.wormos.nalandaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserLogin extends AppCompatActivity {

    TextView loginNewUserTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        loginNewUserTv = findViewById(R.id.login_new_user_tv);

        loginNewUserTv.setOnClickListener(view -> startActivity(new Intent(UserLogin.this, UserRegistration.class)));
    }
}