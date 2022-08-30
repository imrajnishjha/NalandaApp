package com.wormos.nalandaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.TextView;

public class HostelDetail extends AppCompatActivity {
    AppCompatButton hostelDetailBackBtn;
    String HostelInfo;
    TextView hostelNameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_detail);

        //Initialization
        hostelDetailBackBtn = findViewById(R.id.hostel_detail_back_btn);
        hostelNameTV = findViewById(R.id.hostel_detail_name);
        HostelInfo = getIntent().getStringExtra("HostelName");

//Methodology

        hostelDetailBackBtn.setOnClickListener(view -> finish());

        switch (HostelInfo) {
            case "chanakaya":
                hostelNameTV.setText("Chanakaya Hostel");
                break;
            case "drona":
                hostelNameTV.setText("Drona Hostel");
                break;
            case "college":
                hostelNameTV.setText("College Hostel");
                break;
        }


    }
}