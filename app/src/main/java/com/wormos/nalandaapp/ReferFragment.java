package com.wormos.nalandaapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class ReferFragment extends Fragment {

    View view;
    EditText referredName,referredCollege,referredNumber;
    CardView referSubmitBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_refer, container, false);
        referredCollege=view.findViewById(R.id.refer_college_name_edtTxt);
        referredName = view.findViewById(R.id.refer_name_edtTxt);
        referredNumber = view.findViewById(R.id.refer_phone_edtTxt);
        referSubmitBtn = view.findViewById(R.id.refer_submit_btn);

        referSubmitBtn.setOnClickListener(v-> {
            if(referredName.getText().toString().isEmpty()){
                referredName.setError("Enter the name");
                referredName.requestFocus();
            }else if(referredCollege.getText().toString().isEmpty()){
                referredCollege.setError("Enter the college/university");
                referredCollege.requestFocus();
            }else if(referredNumber.getText().toString().isEmpty() || referredNumber.getText().toString().length()<10){
                referredNumber.setError("Enter the correct contact");
                referredNumber.requestFocus();
            } else {
                openIntent();
                uploadToDatabase();
            }
        });

        return view;
    }


    //send message to person user refer
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @SuppressLint("IntentReset")
    private void openIntent() {

        //Uri uri = Uri.parse("https://wa.me/91"+referredNumber.getText().toString()+"?");
        Intent intent = new Intent(Intent.ACTION_SEND);
        String message = "This is referral message";

        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,message);
        startActivity(Intent.createChooser(intent,"Share with"));
    }

    //send a data to firebase Realtime Database;
    public void uploadToDatabase(){
        DatabaseReference referRef = FirebaseDatabase.getInstance().getReference("Refer").child(Dashboard.userEmailConverted);
        HashMap<String,Object> dataMap = new HashMap<>();
        dataMap.put("name",referredName.getText().toString());
        dataMap.put("college",referredCollege.getText().toString());
        dataMap.put("phone no",referredNumber.getText().toString());
        String key = referRef.push().getKey();
        referRef.child(Objects.requireNonNull(key)).updateChildren(dataMap).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                referredName.setText("");
                referredCollege.setText("");
                referredNumber.setText("");
            } else {
                Toast.makeText(getContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}