package com.wormos.nalandaapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class food_fragment extends Fragment {

    TextView day1,day2,day3,day4,day5,day6,day7,todayDate;
    ImageView star1,star2,star3,star4,star5;
    RecyclerView foodMenuRV;
    FirebaseRecyclerOptions<FoodMenuModel> options;
    FoodMenuAdapter foodMenuAdapter;
    View view;
    AppCompatButton addLunchBtn;
    DatabaseReference foodRatingRef = FirebaseDatabase.getInstance().getReference("Rating");
    DatabaseReference lunchRef = FirebaseDatabase.getInstance().getReference("Lunch");
    DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference("Students");
    CardView foodRatingCV;
    static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String hostelName,userId;
    String userEmailConverted = Objects.requireNonNull(Objects.requireNonNull(mAuth.getCurrentUser()).getEmail()).replaceAll("\\.","%7");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_food_fragment, container, false);


        //initialization
        day1 = view.findViewById(R.id.food_day1);
        day2 = view.findViewById(R.id.food_day2);
        day3 = view.findViewById(R.id.food_day3);
        day4 = view.findViewById(R.id.food_day4);
        day5 = view.findViewById(R.id.food_day5);
        day6 = view.findViewById(R.id.food_day6);
        day7 = view.findViewById(R.id.food_day7);
        star1 = view.findViewById(R.id.food_1star);
        star2 = view.findViewById(R.id.food_2star);
        star3 = view.findViewById(R.id.food_3star);
        star4 = view.findViewById(R.id.food_4star);
        star5 = view.findViewById(R.id.food_5star);
        foodRatingCV = view.findViewById(R.id.food_rating_cv);
        todayDate = view.findViewById(R.id.food_day_and_week);
        foodMenuRV = view.findViewById(R.id.food_menuRV);
        addLunchBtn = view.findViewById(R.id.food_add_lunchbtn);
        foodMenuRV.setLayoutManager(new LinearLayoutManager(getContext()));


        //Methodology

        //getting the name of Hostel and after getting that using the hostel name to check the availability of the lunch
        studentRef.child(userEmailConverted).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //gettingHostelDetail
                hostelName= Objects.requireNonNull(snapshot.child("hostel").getValue()).toString();
                userId = Objects.requireNonNull(snapshot.child("id").getValue()).toString();

                //checking the availability of the lunch
                lunchRef.child(hostelName).child("Lunch Available").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(!snapshot.exists()){
                            HashMap<String,Object> lunchAvailableMap = new HashMap<>();
                            lunchAvailableMap.put("Lunch Available","true");
                            lunchAvailableMap.put("date",todaysDateFormatter("YYYY-MM-dd"));
                            lunchRef.child(hostelName).updateChildren(lunchAvailableMap);
                        } else {
                            String status = Objects.requireNonNull(snapshot.getValue()).toString();
                            if(!status.equals("true")){
                                addLunchBtn.setVisibility(View.GONE);
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //


        //checking the last rating date store in device storage for fast loading;
        SharedPreferences foodRatingSP = requireContext().getSharedPreferences("foodRating", Context.MODE_PRIVATE);
        String lastDate = foodRatingSP.getString("lastFoodRatingDate"," ");
        if(foodRatingSP.contains("lastFoodRatingDate")){
            if(!lastDate.equals(todaysDateFormatter("YYYY-MM-dd"))){
                foodRatingCV.setVisibility(View.VISIBLE);

            } else {
                foodRatingCV.setVisibility(View.GONE);
            }
        }

        //checking the user has chosen for the lunch or not
        if(foodRatingSP.contains(todaysDateFormatter("YYYY-MM-dd"))){
            addLunchBtn.setText(R.string.adds);
            addLunchBtn.setEnabled(false);
            addLunchBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#7CE79A")));
        }



        dateSetter(todayDate,day1,day2,day3,day4,day5,day6,day7);

        addLunchBtn.setOnClickListener(v -> sendLunchData(lunchRef,addLunchBtn,userEmailConverted));


        day1.setBackgroundColor(Color.parseColor("#2D6BC8"));
        day1.setOnClickListener(v-> menuChange(day1,day2,day3,day4,day5,day6,day7));
        day2.setOnClickListener(v-> menuChange(day2,day1,day3,day4,day5,day6,day7));
        day3.setOnClickListener(v-> menuChange(day3,day2,day1,day4,day5,day6,day7));
        day4.setOnClickListener(v-> menuChange(day4,day2,day3,day1,day5,day6,day7));
        day5.setOnClickListener(v-> menuChange(day5,day2,day3,day4,day1,day6,day7));
        day6.setOnClickListener(v-> menuChange(day6,day2,day3,day4,day5,day1,day7));
        day7.setOnClickListener(v-> menuChange(day7,day2,day3,day4,day5,day6,day1));

        star1.setOnClickListener(view1 -> setRating(1,foodRatingRef,foodRatingCV));
        star2.setOnClickListener(view1 -> setRating(2,foodRatingRef,foodRatingCV));
        star3.setOnClickListener(view1 -> setRating(3,foodRatingRef,foodRatingCV));
        star4.setOnClickListener(view1 -> setRating(4,foodRatingRef,foodRatingCV));
        star5.setOnClickListener(view1 -> setRating(5,foodRatingRef,foodRatingCV));


        return view;
    }



    //Custom Functions Starts Here




    //Different day menuChange
    public void menuChange(TextView day1,TextView day2,TextView day3,TextView day4,TextView day5,TextView day6,TextView day7){
        day1.setBackgroundColor(Color.parseColor("#2D6BC8"));
        day2.setBackgroundColor(Color.parseColor("#7CB8E7"));
        day3.setBackgroundColor(Color.parseColor("#7CB8E7"));
        day4.setBackgroundColor(Color.parseColor("#7CB8E7"));
        day5.setBackgroundColor(Color.parseColor("#7CB8E7"));
        day6.setBackgroundColor(Color.parseColor("#7CB8E7"));
        day7.setBackgroundColor(Color.parseColor("#7CB8E7"));
        Log.d("test2", "menuChange: "+day1.getText().toString());

        FirebaseRecyclerOptions<FoodMenuModel> options2 ;
        if(day1.getText().toString().equals("Mon")){
            options2 = new FirebaseRecyclerOptions.Builder<FoodMenuModel>()
                    .setQuery(FirebaseDatabase.getInstance().getReference("Food Menu").child("Monday"), FoodMenuModel.class)
                    .build();
        } else if(day1.getText().toString().equals("Tue")) {
            options2 = new FirebaseRecyclerOptions.Builder<FoodMenuModel>()
                    .setQuery(FirebaseDatabase.getInstance().getReference("Food Menu").child("Tuesday"), FoodMenuModel.class)
                    .build();

        } else if(day1.getText().toString().equals("Wed")) {
            options2 = new FirebaseRecyclerOptions.Builder<FoodMenuModel>()
                    .setQuery(FirebaseDatabase.getInstance().getReference("Food Menu").child("Wednesday"), FoodMenuModel.class)
                    .build();

        } else if(day1.getText().toString().equals("Thu")) {
            options2 = new FirebaseRecyclerOptions.Builder<FoodMenuModel>()
                    .setQuery(FirebaseDatabase.getInstance().getReference("Food Menu").child("Thursday"), FoodMenuModel.class)
                    .build();

        } else if(day1.getText().toString().equals("Fri")) {
            options2 = new FirebaseRecyclerOptions.Builder<FoodMenuModel>()
                    .setQuery(FirebaseDatabase.getInstance().getReference("Food Menu").child("Friday"), FoodMenuModel.class)
                    .build();

        } else if(day1.getText().toString().equals("Sun")) {
            options2 = new FirebaseRecyclerOptions.Builder<FoodMenuModel>()
                    .setQuery(FirebaseDatabase.getInstance().getReference("Food Menu").child("Sunday"), FoodMenuModel.class)
                    .build();

        } else  {
            options2 = new FirebaseRecyclerOptions.Builder<FoodMenuModel>()
                    .setQuery(FirebaseDatabase.getInstance().getReference("Food Menu").child("Saturday"), FoodMenuModel.class)
                    .build();
        }

        FoodMenuAdapter shiftAdapter = new FoodMenuAdapter(options2);
        foodMenuRV.setAdapter(shiftAdapter);
        shiftAdapter.startListening();
    }

    //Setting the other week days according to today's date
    public void dateSetter(TextView todayDateTv, TextView day1, TextView day2, TextView day3, TextView day4, TextView day5, TextView day6, TextView day7){
        Calendar calendar = Calendar.getInstance();
        String todaysDate = todaysDateFormatter("dd MMM yyyy");
        final int weekNo = calendar.get(Calendar.DAY_OF_WEEK);
        String weekday = weekCalculator(weekNo);
        StringBuilder s = new StringBuilder(weekday);
        s.append(", ").append(todaysDate);
        todayDateTv.setText(s);
        day1.setText(weekday.substring(0,3));
        day2.setText(weekCalculator(weekNo+1).substring(0,3));
        day3.setText(weekCalculator(weekNo+2).substring(0,3));
        day4.setText(weekCalculator(weekNo+3).substring(0,3));
        day5.setText(weekCalculator(weekNo+4).substring(0,3));
        day6.setText(weekCalculator(weekNo+5).substring(0,3));
        day7.setText(weekCalculator(weekNo+6).substring(0,3));

        //Food RecycleView Implementation

        options = new FirebaseRecyclerOptions.Builder<FoodMenuModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Food Menu").child(weekday), FoodMenuModel.class)
                .build();
        foodMenuAdapter = new FoodMenuAdapter(options);
        foodMenuRV.setAdapter(foodMenuAdapter);
    }

    //weekDayCalculator
    public String weekCalculator(int i) {
        int k = i % 7;
        switch (k) {
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 0:
                return "Saturday";
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            default:
                return null;
        }
    }

    //to set the food rating
    public void setRating(int num,DatabaseReference foodRef,CardView ratingCV){
        if(num==5){
            star1.setImageResource(R.drawable.colorstarimg);
            star4.setImageResource(R.drawable.colorstarimg);
            star3.setImageResource(R.drawable.colorstarimg);
            star2.setImageResource(R.drawable.colorstarimg);
            star5.setImageResource(R.drawable.colorstarimg);
        } else if(num==4){
            star1.setImageResource(R.drawable.colorstarimg);
            star4.setImageResource(R.drawable.colorstarimg);
            star3.setImageResource(R.drawable.colorstarimg);
            star2.setImageResource(R.drawable.colorstarimg);
            star5.setImageResource(R.drawable.starimg);
        } else if(num==3){
            star1.setImageResource(R.drawable.colorstarimg);
            star4.setImageResource(R.drawable.starimg);
            star3.setImageResource(R.drawable.colorstarimg);
            star2.setImageResource(R.drawable.colorstarimg);
            star5.setImageResource(R.drawable.starimg);
        }else if(num==2){
            star1.setImageResource(R.drawable.colorstarimg);
            star4.setImageResource(R.drawable.starimg);
            star3.setImageResource(R.drawable.starimg);
            star2.setImageResource(R.drawable.colorstarimg);
            star5.setImageResource(R.drawable.starimg);
        }else if(num==1){
            star1.setImageResource(R.drawable.colorstarimg);
            star4.setImageResource(R.drawable.starimg);
            star3.setImageResource(R.drawable.starimg);
            star2.setImageResource(R.drawable.starimg);
            star5.setImageResource(R.drawable.starimg);
        }
        sendRating(num,foodRef.child("Food Review"),ratingCV,userEmailConverted);
    }

    //Provide the today date in given Format
    public static String todaysDateFormatter(String format){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        return df.format(date);
    }

    //Send Rating to Firebase
    public  void sendRating(int num,DatabaseReference ratingRef,CardView CV,String userEmail){

        HashMap<String,Object> ratingMap = new HashMap<>();
        ratingMap.put(todaysDateFormatter("YYYY-MM-dd"),num);
        ratingRef.child(userEmail).updateChildren(ratingMap).addOnSuccessListener(unused -> {
            HashMap<String,Object> ratingDateMap = new HashMap<>();
            ratingDateMap.put("0000Last Rating Date",todaysDateFormatter("YYYY-MM-dd"));
            ratingRef.child(userEmail).updateChildren(ratingDateMap).addOnSuccessListener(unused1 -> {
                CV.setVisibility(View.GONE);
                Toast.makeText(view.getContext(), "Thanks for submitting the feedback", Toast.LENGTH_SHORT).show();
                SharedPreferences foodRatingSP = requireContext().getSharedPreferences("foodRating", Context.MODE_PRIVATE);
                SharedPreferences.Editor foodRatingEditor = foodRatingSP.edit();
                foodRatingEditor.putString("lastFoodRatingDate",todaysDateFormatter("YYYY-MM-dd"));
                foodRatingEditor.apply();
            });
        });
    }
    public void sendLunchData(DatabaseReference lunchRef,AppCompatButton addLunchBtn,String userEmail){
        HashMap<String,Object> lunchData = new HashMap<>();
        lunchData.put("id",userId);
        lunchRef.child("Lunch Data").child(todaysDateFormatter("YYYY-MM-dd")).child(hostelName)
                .child(userEmail).updateChildren(lunchData).addOnSuccessListener( success ->{
            addLunchBtn.setText(R.string.adds);
            addLunchBtn.setEnabled(false);
            addLunchBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#7CE79A")));
            SharedPreferences foodRatingSP = requireContext().getSharedPreferences("foodRating", Context.MODE_PRIVATE);
            SharedPreferences.Editor foodRatingEditor = foodRatingSP.edit();
            foodRatingEditor.putString(todaysDateFormatter("YYYY-MM-dd"),"yes");
            foodRatingEditor.apply();
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        foodMenuAdapter.startListening();
    }



}