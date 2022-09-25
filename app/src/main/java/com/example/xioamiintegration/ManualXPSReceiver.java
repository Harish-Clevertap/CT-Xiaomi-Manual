package com.example.xioamiintegration;

import android.content.Context;
import android.util.Log;

import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

public class ManualXPSReceiver extends PushMessageReceiver {

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        super.onNotificationMessageClicked(context, miPushMessage);
        Log.d("onMessageClicked",miPushMessage.getContent());
    }

    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
        super.onReceivePassThroughMessage(context, miPushMessage);
        Log.d("onReceiveMessage",miPushMessage.getContent());
    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
        super.onNotificationMessageArrived(context, miPushMessage);
        Log.d("onMessageArrived",miPushMessage.getContent());
    }
}
