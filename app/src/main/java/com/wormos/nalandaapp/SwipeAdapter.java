package com.wormos.nalandaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.swipeView>{

    Context context;
    String[] supportName;
    int[] supportImg;

    public SwipeAdapter(Context context, String[] supportName, int[] supportImg) {
        this.context = context;
        this.supportName = supportName;
        this.supportImg = supportImg;
    }

    @NonNull
    @Override
    public swipeView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.support_swipe_item,parent,false);
        return new swipeView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull swipeView holder, int position) {
        holder.supportText.setText(supportName[position]);
        holder.supportImg.setImageResource(supportImg[position]);
        holder.motionLayout.addTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                Toast.makeText(context.getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return supportName.length;
    }

    static class swipeView extends RecyclerView.ViewHolder{

        TextView supportText;
        ImageView supportImg;
        MotionLayout motionLayout;
        public swipeView(@NonNull View itemView) {
            super(itemView);
            supportImg = itemView.findViewById(R.id.support_img);
            supportText = itemView.findViewById(R.id.support_text);
            motionLayout = itemView.findViewById(R.id.swipeMotionLayout);
        }
    }
}
