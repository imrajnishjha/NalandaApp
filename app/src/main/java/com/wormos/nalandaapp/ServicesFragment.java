package com.wormos.nalandaapp;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class ServicesFragment extends Fragment {

   View view;
   CardView instantSupport;
   FrameLayout instantSupportFragment;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_services, container, false);
        instantSupport = view.findViewById(R.id.services_instant_support_btn);
        instantSupportFragment = view.findViewById(R.id.servicesSupportFragment);

        //Methodology
        instantSupport.setOnClickListener(v->{
            instantSupportFragment.setVisibility(View.VISIBLE);
            FragmentTransaction dashboardTrans = getChildFragmentManager().beginTransaction();
            dashboardTrans.replace(R.id.servicesSupportFragment, new SupportFragment());
            dashboardTrans.commit();
        });

        return view;
    }
}