package com.wormos.nalandaapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.Calendar;
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
        Calendar calendar = Calendar.getInstance();
        String busTime = model.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(busTime.substring(0,2)));
        calendar.set(Calendar.MINUTE, Integer.parseInt(busTime.substring(3,5)));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)>calendar.get(Calendar.HOUR_OF_DAY)){
            calendar.add(Calendar.DAY_OF_MONTH,1);
        }


        holder.remainderCV.setOnClickListener(v->{
            AlarmManager alarmManager = (AlarmManager) v.getContext().getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(v.getContext(),RemainderReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(v.getContext(),0,intent,0);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
            Toast.makeText(v.getContext(),"Remainder has been set",Toast.LENGTH_SHORT).show();
            Log.d("tome", "onBindViewHolder: "+calendar.getTimeInMillis()+" "+calendar.getTime()+" "+Calendar.getInstance().getTime());

        });

    }

    @NonNull
    @Override
    public transportScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transport_schedule_item,parent,false);
        return new transportScheduleViewHolder(view);
    }

    static class transportScheduleViewHolder extends RecyclerView.ViewHolder{

        TextView driverNameTv, timeTv, driverContactNumberTv;
        CardView remainderCV;

        public transportScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            driverNameTv = itemView.findViewById(R.id.transport_schedule_item_driver_name_tv);
            timeTv = itemView.findViewById(R.id.transport_schedule_item_timing_tv);
            driverContactNumberTv = itemView.findViewById(R.id.transport_schedule_item_driver_phone_number_tv);
            remainderCV = itemView.findViewById(R.id.transport_schedule_item_set_reminder_cv);
        }
    }
}