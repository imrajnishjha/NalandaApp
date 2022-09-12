package com.wormos.nalandaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class UserLogin extends AppCompatActivity {

    TextView loginNewUserTv;
    AppCompatButton loginBackBtn,loginBtn;
    FirebaseAuth mAuth;
    EditText loginEmail;
    TextInputEditText loginPassword;
    RelativeLayout progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        loginNewUserTv = findViewById(R.id.login_new_user_tv);
        loginBackBtn = findViewById(R.id.login_back_btn);
        loginBtn = findViewById(R.id.login_btn);
        progressbar = findViewById(R.id.user_login_progressBarRL);
        mAuth = FirebaseAuth.getInstance();

        //Methodology
        loginBackBtn.setOnClickListener(view -> finish());

        loginNewUserTv.setOnClickListener(view -> {
            startActivity(new Intent(UserLogin.this, UserRegistration.class));
            finish();
        });

        loginBtn.setOnClickListener(view -> loginUser(progressbar));
    }

    private void loginUser(RelativeLayout progressbar){
        loginEmail = findViewById(R.id.login_username_edtTxt);
        loginPassword = findViewById(R.id.login_password_edtTxt);
        String login_email = loginEmail.getText().toString().toLowerCase();
        String login_password = Objects.requireNonNull(loginPassword.getText()).toString();

        if(TextUtils.isEmpty(login_email)){
            loginEmail.setError("Email cannot be empty!");
            loginEmail.requestFocus();
        }else if(TextUtils.isEmpty(login_password)){
            loginPassword.setError("Password cannot be empty!");
            loginPassword.requestFocus();
        } else {
            progressbar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(login_email, login_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        startActivity(new Intent(UserLogin.this,Dashboard.class).putExtra("userEmail",login_email.replaceAll("\\.","%7")));
                        finish();
                        Toast.makeText(UserLogin.this, "You are logged in!", Toast.LENGTH_SHORT).show();
                        progressbar.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(UserLogin.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        progressbar.setVisibility(View.GONE);
                    }
                }
            });
        }

    }
}