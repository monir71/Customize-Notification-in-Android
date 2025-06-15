package com.example.customizenotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE = 100;
    private static final String CHANNNEL_ID = "My Channel";
    private static final int NOTIFICATION_ID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Bitmap
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.icon, null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap largeIcon = null;
        if(bitmapDrawable != null)
        {
            largeIcon = bitmapDrawable.getBitmap();
        }

        //Pending Intent
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, REQ_CODE, intent, PendingIntent.FLAG_IMMUTABLE);

        //Big Picture Style
        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle()
                .bigPicture(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.icon, null)).getBitmap()) //Bitmap
                .bigLargeIcon(largeIcon)
                .setBigContentTitle("Message Heading")
                .setSummaryText("All about the message");

        //Inbox Style (Any style you want to use in setStyle())
        Notification.InboxStyle inboxStyle = new Notification.InboxStyle()
                .addLine("A")
                .addLine("B")
                .addLine("C")
                .addLine("D")
                .addLine("E")
                .addLine("F")
                .addLine("G") //Upto 7 lines permitted
                .addLine("H")
                .addLine("I")
                .addLine("J")
                .addLine("K")
                .addLine("L")
                .setBigContentTitle("Inbox Style Title")
                .setSummaryText("This message from inbox style");


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.icon)
                    .setContentText("New Message")
                    .setSubText("Message from Monir")
                    .setChannelId(CHANNNEL_ID)
                    .setContentIntent(pendingIntent)
                    //.setStyle(bigPictureStyle)
                    .setStyle(inboxStyle)
                    .build();
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNNEL_ID, "My Channel 1", NotificationManager.IMPORTANCE_HIGH));
        }
        else{
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.icon)
                    .setContentText("New Message")
                    .setSubText("Message from Monir")
                    .setContentIntent(pendingIntent)
                    //.setStyle(bigPictureStyle)
                    .setStyle(inboxStyle)
                    .build();
        }

        notificationManager.notify(NOTIFICATION_ID, notification);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}