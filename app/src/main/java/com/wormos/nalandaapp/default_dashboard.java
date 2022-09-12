package com.wormos.nalandaapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class default_dashboard extends Fragment {

    View view;
    RecyclerView storyRv;
    FirebaseRecyclerOptions<StoryModel> options;
    StoryAdapter storyAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_default_dashboard, container, false);

        storyRv = view.findViewById(R.id.dashboard_story_rv);
        storyRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        options  = new FirebaseRecyclerOptions.Builder<StoryModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Stories"), StoryModel.class)
                .build();
        storyAdapter = new StoryAdapter(options);
        storyRv.setAdapter(storyAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        storyAdapter.startListening();
    }
}