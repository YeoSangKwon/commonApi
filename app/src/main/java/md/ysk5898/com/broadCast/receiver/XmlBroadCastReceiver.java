/*
 * Create by SangKwon on 2019. 7. 16.
 */

package md.ysk5898.com.broadCast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class XmlBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Broadcast receiver call",Toast.LENGTH_SHORT).show();
    }
}
