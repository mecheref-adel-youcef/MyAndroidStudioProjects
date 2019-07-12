package joseph.youcef.serviceapp;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

public class MusicService extends Service {

    private MyReceiver receiver;
    private MediaPlayer player;

    public MusicService(){}

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        receiver = new MyReceiver();
        registerReceiver(receiver,new IntentFilter("PlayPause"));
        player = MediaPlayer.create(this,R.raw.zamar);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //ce qu’on va faire si user clique sur la notif
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        // ce qu’on va faire si user clique sur le bouton de la motif
        PendingIntent pPPendingIntent = PendingIntent.getBroadcast(this, 0, new Intent("PlayPause"), FLAG_UPDATE_CURRENT);

        Notification notification =
                new Notification.Builder(this)
                        .setContentTitle("Lecture en cours")
                        .setContentText("")
                        .setSmallIcon(R.drawable.lin)
                        .addAction(R.drawable.pause, "Play/Pause", pPPendingIntent)
                        .setContentIntent(pendingIntent)
                        .setPriority(Notification.PRIORITY_MAX)
                        .build();
        startForeground(110, notification);
        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(player.isPlaying()){
            player.stop();
            unregisterReceiver(receiver);
        }
    }

    public class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("PlayPause")) {
                if(player.isPlaying()) {player.pause();}
                else {player.start();}
            }

        }
    }
}
