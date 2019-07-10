package com.fkocak.uetsonesignalpndev;

import android.content.Context;
import android.widget.Toast;

import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

public class CustomNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {

    Context context;

    public CustomNotificationReceivedHandler(Context context) {
        this.context = context;
    }

    @Override
    public void notificationReceived(OSNotification notification) {
        String title = notification.payload.title;
        String body = notification.payload.body;

        Toast.makeText(context, title + " --> " + body, Toast.LENGTH_SHORT).show();
    }
}
