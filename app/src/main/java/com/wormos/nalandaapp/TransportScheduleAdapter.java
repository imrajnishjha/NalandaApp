package com.wormos.nalandaapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.Objects;

public class TransportScheduleAdapter extends FirebaseRecyclerAdapter<TransportScheduleModel, TransportScheduleAdapter.transportScheduleViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     */
    public TransportScheduleAdapter(@NonNull FirebaseRecyclerOptions<TransportScheduleModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull transportScheduleViewHolder holder, int position, @NonNull TransportScheduleModel model) {
        holder.driverNameTv.setText(model.getDriverName());
        holder.timeTv.setText(model.getTime());
        holder.driverContactNumberTv.setText(model.getDriverContactNumber());


    }

    @NonNull
    @Override
    public transportScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transport_schedule_item,parent,false);
        return new transportScheduleViewHolder(view);
    }

    static class transportScheduleViewHolder extends RecyclerView.ViewHolder{

        TextView driverNameTv, timeTv, driverContactNumberTv;

        public transportScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            driverNameTv = itemView.findViewById(R.id.transport_schedule_item_driver_name_tv);
            timeTv = itemView.findViewById(R.id.transport_schedule_item_timing_tv);
            driverContactNumberTv = itemView.findViewById(R.id.transport_schedule_item_driver_phone_number_tv);
        }
    }
}