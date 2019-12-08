package anshul5404834.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

public class notification extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("abcd", "DailyBreaking", NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{200, 100, 200, 100, 200, 300, 100});
            channel.setShowBadge(true);
            channel.setSound(Settings.System.DEFAULT_NOTIFICATION_URI, channel.getAudioAttributes());
            channel.setLockscreenVisibility(1);

            notificationManager.createNotificationChannel(channel);
        Intent intent1 = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,"abcd");
        builder.setChannelId("abcd");
        Bitmap bitmap=BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.news);

        builder.setAutoCancel(false).setContentText("Time for new news ").setLargeIcon(bitmap).setSmallIcon(R.mipmap.news)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.news).setContentTitle("Daily Breaking")
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);


        notificationManager.notify(0,builder.build());

    }
}
