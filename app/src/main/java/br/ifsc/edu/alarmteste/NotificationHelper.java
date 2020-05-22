package br.ifsc.edu.alarmteste;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;

import com.example.application.alarmmanagerproject.R;


public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    MediaPlayer player;


    MainActivity my = new MainActivity();

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification() {




        int miliseconds = 500000;
        if (player == null) {
            miliseconds= 50000+50000;
            player = MediaPlayer.create(this, R.raw.morning_flower);
//            vibrator.vibrate(VibrationEffect.createOneShot(miliseconds, VibrationEffect.DEFAULT_AMPLITUDE));
            //precisa continuar vibrando

            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    my.stopPlayer();
                }
            });
        }

        player.start();

        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("MAXIPE")
                .setContentText("O lembrete do dia!!!")
                .setSmallIcon(R.drawable.ic_launcher_background);
    }
}
