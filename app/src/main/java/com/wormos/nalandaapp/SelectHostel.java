package com.wormos.nalandaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectHostel extends AppCompatActivity {

    int seater=0;
    String selectedHostel=null;
    TextView oneSeater,twoSeater,threeSeater,fourSeater;
    ImageView chanakayaHostel,dronaHostel,collegeHostel;
    AppCompatButton selectDetailBackBtn;
    CardView chanakayaDetail,dronaDetail,collegeDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_hostel);

        //Initialization
        oneSeater = findViewById(R.id.select_1_seater);
        twoSeater = findViewById(R.id.select_2_seater);
        threeSeater = findViewById(R.id.select_3_seater);
        fourSeater = findViewById(R.id.select_4_seater);
        chanakayaHostel = findViewById(R.id.select_chanakya_hostel);
        dronaHostel = findViewById(R.id.select_drona_hostel);
        collegeHostel = findViewById(R.id.select_college_hostel);
        selectDetailBackBtn = findViewById(R.id.select_hostel_back_btn);
        chanakayaDetail = findViewById(R.id.chanakaya_info);
        dronaDetail= findViewById(R.id.drona_info);
        collegeDetail = findViewById(R.id.college_info);

//Methodology
        selectDetailBackBtn.setOnClickListener(view -> finish());

        oneSeater.setOnClickListener(view ->{
            roomSeatSelector(oneSeater,twoSeater,threeSeater,fourSeater);
            seater=1;
        });
        twoSeater.setOnClickListener(view ->{
            roomSeatSelector(twoSeater,oneSeater,threeSeater,fourSeater);
            seater=2;
        });
        threeSeater.setOnClickListener(view -> {
            roomSeatSelector(threeSeater,oneSeater,twoSeater,fourSeater);
            seater=3;
        });
        fourSeater.setOnClickListener(view -> {
            roomSeatSelector(fourSeater,oneSeater,twoSeater,threeSeater);
            seater=4;
        });

        chanakayaHostel.setOnClickListener(view -> {
            HostelSelector(chanakayaHostel,dronaHostel,collegeHostel);
            selectedHostel="chanakaya";
        });

        dronaHostel.setOnClickListener(view -> {
            HostelSelector(dronaHostel,chanakayaHostel,collegeHostel);
            selectedHostel="drona";
        });

        collegeHostel.setOnClickListener(view -> {
            HostelSelector(collegeHostel,chanakayaHostel,dronaHostel);
            selectedHostel="college";
        });

        collegeDetail.setOnClickListener(view ->
                startActivity(new Intent(this,HostelDetail.class).putExtra("HostelName","college"))
        );

        chanakayaDetail.setOnClickListener(view ->
                startActivity(new Intent(this,HostelDetail.class).putExtra("HostelName","chanakaya"))
        );

        dronaDetail.setOnClickListener(view ->
                startActivity(new Intent(this,HostelDetail.class).putExtra("HostelName","drona"))
        );

    }


    //No of seat Selection
    public void roomSeatSelector(TextView selected, TextView other1, TextView other2, TextView other3){
        selected.setBackground(ContextCompat.getDrawable(SelectHostel.this,R.drawable.button_style_curve));
        other1.setBackgroundColor(Color.parseColor("#f5f5f5"));
        other2.setBackgroundColor(Color.parseColor("#f5f5f5"));
        other3.setBackgroundColor(Color.parseColor("#f5f5f5"));

    }
    //Different Type of Hostel Selection
    public void HostelSelector(ImageView selected, ImageView other1, ImageView other2){
        selected.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(SelectHostel.this,R.color.seat_no_background_tint)));
        selected.setBackground(ContextCompat.getDrawable(SelectHostel.this,R.drawable.button_style_more_curve));
        other1.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(SelectHostel.this,R.color.seat_no_white_background)));
        other2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(SelectHostel.this,R.color.seat_no_white_background)));

    }
}