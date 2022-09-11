package com.wormos.nalandaapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import io.ak1.BubbleTabBar;
import io.ak1.OnBubbleClickListener;

public class Dashboard extends AppCompatActivity {

    BubbleTabBar bottomNavBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //initialization
        bottomNavBar = findViewById(R.id.bottom_nav_Bar);

        //methodology

        FragmentTransaction dashboardTrans = getSupportFragmentManager().beginTransaction();
        dashboardTrans.replace(R.id.dashboard_fragment_holder, new dashboard_fragment());
        dashboardTrans.commit();



        bottomNavBar.addBubbleListener(i -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Log.d("uhj", "onBubbleClick: " + i);
            switch (i) {
                case 2131362050:
                    transaction.replace(R.id.dashboard_fragment_holder, new food_fragment());
                    break;
                case 2131362248:
                    transaction.replace(R.id.dashboard_fragment_holder, new my_room_fragment());
                    break;
                case 2131361951:
                    transaction.replace(R.id.dashboard_fragment_holder, new dashboard_fragment());
                    break;
                case 2131362008:
                    transaction.replace(R.id.dashboard_fragment_holder, new explore_fragment());
                    break;
                case 2131362226:
                    transaction.replace(R.id.dashboard_fragment_holder, new refer_fragment());
                    break;
            }
            transaction.commit();
        });

    }


}
