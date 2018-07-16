package com.mobileapps.training.week3daily4;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class SoundService extends Service {

    private static final String TAG = "SoundService";
    MediaPlayer mediaPlayer;

    public SoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: Started");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: Activating sound");
        
        if("STOP".equals(intent.getAction())){
            Log.d(TAG, "onStartCommand: Stopped service");
            onDestroy();
        }
        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_NOTIFICATION_URI);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        Intent intentStop = new Intent(getApplicationContext(), SoundService.class);
        intentStop.setAction("STOP");
        PendingIntent pendingStop =
                PendingIntent.getService(getApplicationContext(), 0, intentStop, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification notification = new NotificationCompat.Builder(getApplicationContext(),"1")
                // Show controls on lock screen even when user hides sensitive content.
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.ic_music_note_black_24dp)
                .addAction(R.drawable.ic_stop_black_24dp, "Stop", pendingStop) // #0
                .setContentTitle("Service Foreground")
                .setContentText("Started")
                .build();

        startForeground(1, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: Service Stopped");
        mediaPlayer.stop();
        stopSelf();
        super.onDestroy();
    }
}
