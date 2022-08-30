package com.wormos.nalandaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserRegistration extends AppCompatActivity {

    TextView registrationAlreadyUserTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        registrationAlreadyUserTv = findViewById(R.id.registration_already_user_tv);

        registrationAlreadyUserTv.setOnClickListener(view -> startActivity(new Intent(UserRegistration.this, UserLogin.class)));
    }
}