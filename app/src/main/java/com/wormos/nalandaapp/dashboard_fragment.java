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
    CardView dashboardTransportCv, dashboardLaundryCv, dashboardServicesCv, dashboardReferCv;

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
        dashboardServicesCv = dView.findViewById(R.id.dashboard_services_cv);
        dashboardReferCv = dView.findViewById(R.id.dashboard_refer_cv);

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

        dashboardServicesCv.setOnClickListener(view -> {
            selectSubMenu(dashboardServicesCv);
            fragmentInflater("Services");
        });

        dashboardReferCv.setOnClickListener(view -> {
            selectSubMenu(dashboardReferCv);
            fragmentInflater("Refer");
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
        dashboardServicesCv.setCardBackgroundColor(getResources().getColor(R.color.ligh_grey));
        dashboardReferCv.setCardBackgroundColor(getResources().getColor(R.color.ligh_grey));
    }

    public void fragmentInflater(String submenuChoice){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        switch (submenuChoice) {
            case "Laundry":
                transaction.replace(R.id.dashboard_fragment_frame, new laundry());
                break;
            case "Refer":
                transaction.replace(R.id.dashboard_fragment_frame, new ReferFragment());
                break;
            case "Services":
                transaction.replace(R.id.dashboard_fragment_frame, new ServicesFragment());
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