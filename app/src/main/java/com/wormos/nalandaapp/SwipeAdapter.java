package com.wormos.nalandaapp;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

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
                Log.d("sfadf", "onTransitionStarted: "+startId+" "+endId);
            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                if(currentId==2131362009){
                    new Handler().postDelayed(()->holder.motionLayout.setProgress(0),350);
                    Dialog grievanceDialog = new Dialog(motionLayout.getContext());
                    View grievanceView = LayoutInflater.from(motionLayout.getContext()).inflate(R.layout.grievance_popup,motionLayout,false);
                    EditText subjectEdx = grievanceView.findViewById(R.id.subjectEdt);
                    EditText descriptionEdx =  grievanceView.findViewById(R.id.descriptionEdt);
                    TextView submitEdx = grievanceView.findViewById(R.id.submitEdt);
                    grievanceDialog.setContentView(grievanceView);
                    grievanceDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    grievanceDialog.show();

                    submitEdx.setOnClickListener(v->{
                        if(subjectEdx.getText().toString().isEmpty()){
                            subjectEdx.setError("Provide the subject");
                            subjectEdx.requestFocus();
                        } else if(descriptionEdx.getText().toString().isEmpty()){
                            descriptionEdx.setError("Provide the description");
                            descriptionEdx.requestFocus();
                        } else {
                            String issueTopic;
                            switch (holder.getBindingAdapterPosition()){
                                case 0:
                                    issueTopic = "Wi-Fi";
                                    break;
                                case 1:
                                    issueTopic = "HouseKeeping";
                                    break;
                                case 2:
                                    issueTopic = "Laundry";
                                    break;
                                default:
                                    issueTopic = "Other";
                                    break;
                            }
                            DatabaseReference grievanceUserRef = FirebaseDatabase.getInstance().getReference("Grievance by User")
                                    .child(Dashboard.userEmailConverted);
                            DatabaseReference grievanceRef = FirebaseDatabase.getInstance().getReference("Grievance")
                                    .child(issueTopic).child(Dashboard.userEmailConverted);
                            HashMap<String,Object> grievanceMap = new HashMap<>();
                            grievanceMap.put("subject",subjectEdx.getText().toString());
                            grievanceMap.put("description",descriptionEdx.getText().toString());
                            grievanceMap.put("relation",issueTopic);
                            grievanceMap.put("date",food_fragment.todaysDateFormatter("YYYY-MM-dd"));
                            grievanceMap.put("status","unsolved");
                            String key = grievanceRef.push().getKey();
                            assert key != null;
                            grievanceUserRef.child(key).updateChildren(grievanceMap);
                            grievanceRef.child(key).updateChildren(grievanceMap);
                            grievanceDialog.dismiss();
                            Toast.makeText(motionLayout.getContext(), "✔Submitted", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

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
