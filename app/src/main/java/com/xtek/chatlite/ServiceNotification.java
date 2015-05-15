package com.xtek.chatlite;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class ServiceNotification extends Service {

    NotificationCompat.Builder notifyBuilder;
    NotificationManager notificationManager;

    public ServiceNotification() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void simpleNotification(){

        notifyBuilder = new NotificationCompat.Builder(this)
                .setContentIntent(getDefaultIntent())
                .setContentTitle("New Messages")
                .setContentText("You Received a New Message")
                .setSmallIcon(R.drawable.pewpewpew)
                .setAutoCancel(true);
        notificationManager.notify(100, notifyBuilder.build());
    }

    public PendingIntent getDefaultIntent(){
        PendingIntent pendingIntent= PendingIntent.getActivity(this, 1, new Intent(getApplicationContext(), ActivityChat.class), PendingIntent.FLAG_CANCEL_CURRENT);
        return pendingIntent;
    }
}
