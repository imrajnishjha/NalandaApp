package com.wormos.nalandaapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;


public class laundry extends Fragment {

    View view;
    CalendarView laundryDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_laundry, container, false);
        laundryDate = view.findViewById(R.id.laundry_calender);

        String selectedDate ="12/09/2022";
        try {
            laundryDate.setDate(Objects.requireNonNull(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(selectedDate)).getTime(),true,true);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return view;
    }
}