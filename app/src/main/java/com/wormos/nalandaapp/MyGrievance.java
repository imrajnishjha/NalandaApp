package com.wormos.nalandaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MyGrievance extends AppCompatActivity {

    AppCompatButton myGrievanceBackBtn;
    RecyclerView myGrievanceRV;
    MyGrievanceAdapter myGrievanceAdapter;
    FirebaseRecyclerOptions<MyGrievanceModel> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_grievance);
        myGrievanceBackBtn= findViewById(R.id.myGrievanceBackBtn);
        myGrievanceRV = findViewById(R.id.myGrievanceRV);

        myGrievanceBackBtn.setOnClickListener(view -> finish());

        myGrievanceRV.setLayoutManager(new LinearLayoutManager(this));
        options = new FirebaseRecyclerOptions.Builder<MyGrievanceModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Grievance by User")
                        .child(Dashboard.userEmailConverted), MyGrievanceModel.class)
                .build();
        myGrievanceAdapter = new MyGrievanceAdapter(options);
        myGrievanceRV.setAdapter(myGrievanceAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        myGrievanceAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myGrievanceAdapter.stopListening();
    }
}