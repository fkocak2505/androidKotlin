package com.fkocak.uetsonesignalpndev;

import android.content.Context;
import android.content.Intent;

import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

public class CustomNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {

    private Context context;

    public CustomNotificationOpenedHandler(Context context) {
        this.context = context;
    }

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        context.startActivity(new Intent(context,Diger.class));
    }
}
