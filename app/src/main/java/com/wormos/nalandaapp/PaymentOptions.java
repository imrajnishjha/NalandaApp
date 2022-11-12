package com.wormos.nalandaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.security.SecureRandom;
import java.util.Random;

public class PaymentOptions extends AppCompatActivity {

    AppCompatButton getIdBtn, paymentBackBtn;
    TextView cashTv, chequeTv;
    int payOptionSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);
        getIdBtn = findViewById(R.id.payment_id_btn);
        paymentBackBtn = findViewById(R.id.payment_back_btn);
        cashTv = findViewById(R.id.payment_cash_edtTxt);
        chequeTv = findViewById(R.id.payment_cheque_edtTxt);

        paymentBackBtn.setOnClickListener(view -> finish());
        cashTv.setOnClickListener(view -> selectPaymentOptions(cashTv, chequeTv));
        chequeTv.setOnClickListener(view -> selectPaymentOptions(chequeTv, cashTv));
        getIdBtn.setOnClickListener(view -> {
            if (payOptionSelected == 0) {
                Toast.makeText(this, "Please select a payment option", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Your Id is \n" + getSaltString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    //function to select options
    public void selectPaymentOptions(TextView textView, TextView other) {
        payOptionSelected = 1;
        textView.setTextColor(Color.parseColor("#f5f5f5"));
        textView.setBackground(ContextCompat.getDrawable(PaymentOptions.this, R.drawable.rounded_black_background));
        other.setTextColor(Color.parseColor("#FF000000"));
        other.setBackground(ContextCompat.getDrawable(PaymentOptions.this, R.drawable.rounded_black_edt_txt));
    }

    //generate an Id
    protected String getSaltString() {
        String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 24) { // length of the random string.
            int index = (int) (rnd.nextFloat() * alphaNumeric.length());
            Log.d("TAG", "getSaltString: " + index + " " + rnd.nextFloat() + " " + alphaNumeric.length());
            salt.append(alphaNumeric.charAt(index));
        }
        return salt.toString();
    }
}