/*
 * Create by SangKwon on 2019. 10. 28.
 */

package md.ysk5898.com.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import md.ysk5898.com.MainActivity;
import md.ysk5898.com.R;

public class AlwaysTopService extends Service {
    public static final String CHANNEL_ID = "ForegroundServiceChannel";

    private View mView;
    private WindowManager windowManager;
    WindowManager.LayoutParams params;
    private Handler mHandler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("RtlHardcoded")
    @Override
    public void onCreate() {
        super.onCreate();

        LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.alwaus_on_touch_view, null);
        mView.setOnTouchListener(mOnTouchListener);
        Button btn_test = mView.findViewById(R.id.btn_test);
        btn_test.setOnClickListener(v -> Log.e("AlwaysTopService", "btn_test.setOnClickListener"));

        try {
            windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

            //here is all the science of params


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                params = new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                                | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR,
                        PixelFormat.TRANSLUCENT
                );
            } else {
                params = new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                                | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR,
                        PixelFormat.TRANSLUCENT
                );
            }

            params.gravity = Gravity.TOP | Gravity.LEFT;
            windowManager.addView(mView, params);
            mHandler = new Handler();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.check_box)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

        //do heavy work on a background thread
        return START_NOT_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mView != null) {
            try {
                windowManager.removeView(mView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    private boolean isMove = false;
    private float mTouchX, mTouchY;
    private int mViewX, mViewY;
    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isMove = false;
                    mTouchX = event.getRawX();
                    mTouchY = event.getRawY();
                    mViewX = params.x;
                    mViewY = params.y;
                    break;
                case MotionEvent.ACTION_UP:
                    if (!isMove) {
                        Toast.makeText(getApplicationContext(), "클릭", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    isMove = true;
                    int x = (int) (event.getRawX() - mTouchX);
                    int y = (int) (event.getRawY() - mTouchY);
                    final int num = 5;
                    if ((x > -num && x < num) && (y > -num && y < num)) {
                        isMove = false;
                        break;
                    }
                    params.x = mViewX + x;
                    params.y = mViewY + y;
                    windowManager.updateViewLayout(mView, params);
                    break;
            }
            return true;
        }
    };
}
