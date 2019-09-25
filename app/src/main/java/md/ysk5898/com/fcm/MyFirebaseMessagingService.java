/*
 * Create by SangKwon on 2019. 8. 16.
 */

package md.ysk5898.com.fcm;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static String TAG = "MyFirebaseMessagingService";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        // getData() 로 들어 올 경우
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Message data payload: " + remoteMessage.getData());
            sendNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("body"));
        }

        // getNotification() 으로 들어 올 경우
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Message Notification payload: " + remoteMessage.getNotification().getTitle());
            Log.e(TAG, "Message Notification payload: " + remoteMessage.getNotification().getBody());

            sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }


    }

    private void sendNotification(String title, String body) {
        Intent mIntent = new Intent(getApplicationContext(), DialogActivity.class);
        mIntent.putExtra("title", title);
        mIntent.putExtra("body", body);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mIntent);
    }

    /**
     * 토큰이 새로 생성 될 때마다 호출되는 함수
     */
    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d(TAG, "Refreshed token: " + token);

        sendRegistrationToServer(token);
    }

    /**
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }
}
