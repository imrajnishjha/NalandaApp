package com.wormos.nalandaapp;

import android.os.Bundle;

import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class SupportFragment extends Fragment {

    View view;
    RecyclerView swipeMenu;
    String[] supportName ={"Internet","Housekeeping","Laundry","Others"};
    int[] supportImage={R.drawable.wifibw,R.drawable.housekeepingbw,R.drawable.laundrybw,R.drawable.settingbe};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_support, container, false);

        //Support RecycleView

        swipeMenu = view.findViewById(R.id.support_menu_RV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        swipeMenu.setLayoutManager(linearLayoutManager);
        swipeMenu.setAdapter(new SwipeAdapter(view.getContext(),supportName,supportImage));

        return view;
    }
}