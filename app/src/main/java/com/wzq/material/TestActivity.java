package com.wzq.material;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

/**
 * Created by wzq on 15/6/15.
 */
public class TestActivity extends BaseActivity{

    private Notification mNotification;
    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBodyView(R.layout.activity_test);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.news_body_oeder_n)
                .setContentTitle("title")
                .setContentText("TEST")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.image1))
                .build();
    }

    public void onTest(View view){
        mNotificationManager.notify(0x12312, mNotification);
    }
}
