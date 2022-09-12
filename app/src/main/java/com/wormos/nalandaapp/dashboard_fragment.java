package com.wormos.nalandaapp;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class dashboard_fragment extends Fragment {

    View dView;
    CardView dashboardTransportCv, dashboardLaundryCv, dashboardHouseKeepingCv, dashboardRepairCv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dView = inflater.inflate(R.layout.fragment_dashboard_fragment, container, false);
        dashboardTransportCv = dView.findViewById(R.id.dashboard_transport_cv);
        dashboardLaundryCv = dView.findViewById(R.id.dashboard_laundry_cv);
        dashboardHouseKeepingCv = dView.findViewById(R.id.dashboard_house_keeping_cv);
        dashboardRepairCv = dView.findViewById(R.id.dashboard_repair_cv);

        //methodology

        FragmentTransaction subMenuTrans = getChildFragmentManager().beginTransaction();
        subMenuTrans.replace(R.id.dashboard_fragment_frame,new default_dashboard());
        subMenuTrans.commit();

        dashboardTransportCv.setOnClickListener(view -> {
            selectSubMenu(dashboardTransportCv);
            fragmentInflater("Transport");
        });

        dashboardLaundryCv.setOnClickListener(view -> {
            selectSubMenu(dashboardLaundryCv);
            fragmentInflater("Laundry");
        });

        dashboardHouseKeepingCv.setOnClickListener(view -> {
            selectSubMenu(dashboardHouseKeepingCv);
        });

        dashboardRepairCv.setOnClickListener(view -> {
            selectSubMenu(dashboardRepairCv);
        });


        return dView;
    }

    private void selectSubMenu(CardView selectedSubMenuCv) {
        greyOutAllSubMenuCv();
        selectedSubMenuCv.setCardBackgroundColor(getResources().getColor(R.color.nalanda_blue));
    }

    private void greyOutAllSubMenuCv() {
        dashboardTransportCv.setCardBackgroundColor(getResources().getColor(R.color.ligh_grey));
        dashboardLaundryCv.setCardBackgroundColor(getResources().getColor(R.color.ligh_grey));
        dashboardHouseKeepingCv.setCardBackgroundColor(getResources().getColor(R.color.ligh_grey));
        dashboardRepairCv.setCardBackgroundColor(getResources().getColor(R.color.ligh_grey));
    }

    public void fragmentInflater(String submenuChoice){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        switch (submenuChoice) {
            case "Laundry":
                transaction.replace(R.id.dashboard_fragment_frame, new laundry());
                break;
            case "My Room":
                transaction.replace(R.id.dashboard_fragment_frame, new my_room_fragment());
                break;
            case "Dashboard":
                transaction.replace(R.id.dashboard_fragment_frame, new dashboard_fragment());
                break;
            case "Explore":
                transaction.replace(R.id.dashboard_fragment_frame, new explore_fragment());
                break;
            case "Transport":
                transaction.replace(R.id.dashboard_fragment_frame, new TransportFragment());
                break;
            default:
                transaction.replace(R.id.dashboard_fragment_frame, new default_dashboard());
                break;
        }
        transaction.commit();
    }
}