package com.example.proiectandroid;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class PurchaseReminderBroadcast extends BroadcastReceiver {

    @SuppressLint("MissingPermission")
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        Intent intentSend = new Intent(context,ProductPageActivity.class);
        intentSend.putExtra("numeProdus",bundle.getString("numeProdus"));
        intentSend.putExtra("idProduct",bundle.getInt("idProduct"));
        PendingIntent pendingIntent = PendingIntent.getActivity(context,1,intentSend,PendingIntent.FLAG_MUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"PurchaseNotification");
        builder.setContentTitle("Purchase reminder");
        builder.setContentText("Nu uita sa cumperi: "+bundle.getString("numeProdus").toString()); // de rezolvat chestia asta, probabil din intent
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(1,builder.build());
    }
}
