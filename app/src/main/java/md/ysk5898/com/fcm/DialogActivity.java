/*
 * Create by SangKwon on 2019. 8. 16.
 */

package md.ysk5898.com.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import md.ysk5898.com.MainActivity;
import md.ysk5898.com.R;

public class DialogActivity extends AppCompatActivity {

    @BindView(R.id.txt_1) TextView txt_1;
    @BindView(R.id.txt_2) TextView txt_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);

        ButterKnife.bind(this);

        Intent mIntent = getIntent();

        txt_1.setText(mIntent.getStringExtra("title"));
        txt_2.setText(mIntent.getStringExtra("body"));

        NotificationManager manager = createNotificationChannel();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification = new NotificationCompat.Builder(this, getString(R.string.default_notification_channel_id))
                    .setContentTitle("FCM Service")
                    .setContentTitle(mIntent.getStringExtra("title"))
                    .setContentText(mIntent.getStringExtra("body"))
                    .setSmallIcon(R.drawable.check_box)
                    .setContentIntent(pendingIntent)
                    .build();

            manager.notify(0, notification);
        }
    }

    private NotificationManager createNotificationChannel() {
        String channelId = getString(R.string.default_notification_channel_id);
        NotificationChannel serviceChannel = null;
        NotificationManager manager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            serviceChannel = new NotificationChannel(
                    channelId,
                    "FCM Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }


        return manager;
    }
}
