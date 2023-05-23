package com.example.proiectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProductPageActivity extends AppCompatActivity {

    private TextView NumeProdus;

    private TextView ingredienteProdus;

    private Button notificationButton;

    private String numeProdus;

    private Integer idProdus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();

        NumeProdus=findViewById(R.id.productNameText);
        ingredienteProdus=findViewById(R.id.productIngredientsText);
        notificationButton=findViewById(R.id.reminderButton);

        if(bundle != null){
            numeProdus=bundle.getString("numeProdus");
            idProdus=bundle.getInt("idProduct");
            NumeProdus.setText(numeProdus);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("PurchaseNotification","PurchaseNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        notificationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductPageActivity.this, "Reminder activat", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(ProductPageActivity.this, PurchaseReminderBroadcast.class);
                intent1.putExtra("numeProdus",numeProdus);
                intent1.putExtra("idProduct",idProdus);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(ProductPageActivity.this, 0, intent1, PendingIntent.FLAG_IMMUTABLE);

                AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);

                long timeClicked = System.currentTimeMillis();
                long tenSeconds= 1000*10;
                alarmManager.set(AlarmManager.RTC_WAKEUP,
                        timeClicked+tenSeconds,pendingIntent);
            }
        });



    }
}