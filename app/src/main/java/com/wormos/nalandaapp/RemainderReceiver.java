package com.wormos.nalandaapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Objects;

public class RemainderReceiver extends BroadcastReceiver {

    NotificationManager mNotificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context,Dashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,i,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"nalandaapp");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "nalandaapp";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Notifications",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Your booked vehicle will start in 10min");
            mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(channel);
            builder.setSmallIcon(R.drawable.ic_nalanda_icon);
            builder.setChannelId(channelId);
            builder.setContentIntent(pendingIntent);
            builder.setContentTitle("Transport");
            builder.setContentText("Your booked vehicle has arrived");
            builder.setAutoCancel(true);
            builder.setDefaults(NotificationCompat.DEFAULT_ALL);
            builder.setPriority(Notification.PRIORITY_MAX);
        }else{
            builder.setSmallIcon(R.drawable.ic_nalanda_icon);
            builder.setContentTitle("Transport");
            builder.setContentTitle("Your booked vehicle has arrived");
            builder.setAutoCancel(true);
            builder.setDefaults(NotificationCompat.DEFAULT_ALL);
            builder.setPriority(NotificationCompat.PRIORITY_MAX);
            builder.setContentIntent(pendingIntent);
        }



        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123,builder.build());
    }
}
