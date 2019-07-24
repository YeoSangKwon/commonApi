/*
 * Create by SangKwon on 2019. 7. 16.
 */

package md.winitech.com.broadCast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import md.winitech.com.broadCast.BroadcastActivity;

public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "onReceive", Toast.LENGTH_LONG).show();
        ((BroadcastActivity)context).test();
    }
}
