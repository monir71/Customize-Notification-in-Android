Note here:
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

        //Big Picture Style (here Bitmap in a single line)
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

        //Version checking for setChanelID()
        
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
