package com.wormos.nalandaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyGrievanceAdapter extends FirebaseRecyclerAdapter<MyGrievanceModel,MyGrievanceAdapter.grievanceViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyGrievanceAdapter(@NonNull FirebaseRecyclerOptions<MyGrievanceModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull grievanceViewHolder holder, int position, @NonNull MyGrievanceModel model) {
        holder.grievanceStatus.setText(model.getStatus());
        holder.grievanceDescription.setText(model.getDescription());
        holder.grievanceSubject.setText(model.getSubject());

    }

    @NonNull
    @Override
    public grievanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grievence_item,parent,false);
        return new grievanceViewHolder(myView);
    }

    static class grievanceViewHolder extends RecyclerView.ViewHolder{

        View grievanceView;
        TextView grievanceSubject,grievanceDescription,grievanceStatus;
        public grievanceViewHolder(@NonNull View itemView) {
            super(itemView);
            grievanceView = itemView;
            grievanceSubject = itemView.findViewById(R.id.grievanceItemTV);
            grievanceDescription = itemView.findViewById(R.id.grievanceItemSubject);
            grievanceStatus = itemView.findViewById(R.id.grievanceItemStatus);

        }
    }
}
