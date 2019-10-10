/*
 * Create by SangKwon on 2019. 7. 5.
 */

package md.ysk5898.com;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import md.ysk5898.com.bluetooth.BlueToothBleActivity;
import md.ysk5898.com.capture.CaptureActivity;
import md.ysk5898.com.generic.GenericActivity;
import md.ysk5898.com.lamda.LamdaActivity;
import md.ysk5898.com.livedata.NameActivity;
import md.ysk5898.com.parcelable.ParcelableActivity;
import md.ysk5898.com.realm.RealmActivity;
import md.ysk5898.com.bindAPI.BindActivity;
import md.ysk5898.com.bindAPI.ObjectActivity;
import md.ysk5898.com.broadCast.BroadcastActivity;
import md.ysk5898.com.butterKnife.ButterActivity;
import md.ysk5898.com.databinding.ActivityMainBinding;
import md.ysk5898.com.opensource.openSourceActivity;
import md.ysk5898.com.pattern.ListActivity;
import md.ysk5898.com.pattern.patternActivity;
import md.ysk5898.com.recycler.recyclerActivity;
import md.ysk5898.com.remote.RemoteConfigActivity;
import md.ysk5898.com.retrofit.view.RetrofitActivity;
import md.ysk5898.com.service.ServiceActivity;
import md.ysk5898.com.technology.TechActivity;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
        binding.txtMain.setText("Main Activity");

        /**
         * XML Broadcast Receiver
         * */
        this.sendBroadcast(new Intent("md.ysk5898.com.Broadcast.test"));

        /**
         * Stetho 라이브러리 (디버깅용 : 크롬에서  chrome://inspect )
         * */
        Stetho.initializeWithDefaults(this);

        //푸쉬 키 발급
        initTask();

        //푸시 Topic 등록
        regeistTopic();
    }

    private void initTask() {

        // FCM 토큰생성
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.e(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.e(TAG, msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void regeistTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = getString(R.string.msg_subscribed);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.msg_subscribe_failed);
                        }
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void onClickButton(View view) {
        Intent mIntent;
        switch (view.getId()) {
            case R.id.btn_1:
                mIntent = new Intent(this, BindActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_2:
                mIntent = new Intent(this, ObjectActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_3:
                mIntent = new Intent(this, ButterActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_4:
                mIntent = new Intent(this, RealmActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_5:
                mIntent = new Intent(this, LamdaActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_6:
                mIntent = new Intent(this, ParcelableActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_7:
                mIntent = new Intent(this, BroadcastActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_8:
                mIntent = new Intent(this, recyclerActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_9:
                mIntent = new Intent(this, patternActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_10:
                mIntent = new Intent(this, ListActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_11:
                mIntent = new Intent(this, CaptureActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_12:
                mIntent = new Intent(this, GenericActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_13:
                mIntent = new Intent(this, openSourceActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_14:
                mIntent = new Intent(this, ServiceActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_15:
                mIntent = new Intent(this, RetrofitActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_16:
                mIntent = new Intent(this, RemoteConfigActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_17:
                mIntent = new Intent(this, TechActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_18:
                mIntent = new Intent(this, BlueToothBleActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_19:
                mIntent = new Intent(this, NameActivity.class);
                startActivity(mIntent);
                break;
        }

    }
}
