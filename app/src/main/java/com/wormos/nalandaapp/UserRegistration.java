package com.wormos.nalandaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserRegistration extends AppCompatActivity {

    TextView registrationAlreadyUserTv;
    AppCompatButton continueRegBtn,registrationBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        registrationAlreadyUserTv = findViewById(R.id.registration_already_user_tv);
        continueRegBtn = findViewById(R.id.registration_continue_btn);
        registrationBackBtn = findViewById(R.id.registration_back_btn);

        //Methodology
        registrationBackBtn.setOnClickListener(view -> finish());

        continueRegBtn.setOnClickListener(view -> startActivity(new Intent(this,SelectHostel.class)));

        registrationAlreadyUserTv.setOnClickListener(view -> {
            startActivity(new Intent(UserRegistration.this, UserLogin.class));
            finish();
        });
    }
}