/*
 * Create by SangKwon on 2019. 7. 16.
 */

package md.ysk5898.com.broadCast;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import md.ysk5898.com.R;
import md.ysk5898.com.broadCast.receiver.Receiver;

public class BroadcastActivity extends AppCompatActivity {

    private BroadcastReceiver receiver;
    private String bcAction = "com.ysk5898.broadCastTest";

    @BindView(R.id.btn_broad_1)
    Button btn_broad_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        ButterKnife.bind(this);

        btn_broad_1.setOnClickListener((v) -> {
            Intent mIntent = new Intent(bcAction);
            sendBroadcast(mIntent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver();
    }

    public void test(){
        btn_broad_1.setText("receiver callBack");
    }

    public void registerReceiver() {
        if (null != receiver) {
            receiver = null;
        }

        this.receiver = new Receiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction(bcAction);
        this.registerReceiver(receiver, filter);
    }

    private void unregisterReceiver() {
        if (receiver != null) {
            this.unregisterReceiver(receiver);
            receiver = null;
        }
    }
}
