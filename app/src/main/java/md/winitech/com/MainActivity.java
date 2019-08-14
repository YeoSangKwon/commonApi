/*
 * Create by SangKwon on 2019. 7. 5.
 */

package md.winitech.com;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.stetho.Stetho;

import md.winitech.com.capture.CaptureActivity;
import md.winitech.com.generic.GenericActivity;
import md.winitech.com.lamda.LamdaActivity;
import md.winitech.com.parcelable.ParcelableActivity;
import md.winitech.com.realm.RealmActivity;
import md.winitech.com.bindAPI.BindActivity;
import md.winitech.com.bindAPI.ObjectActivity;
import md.winitech.com.broadCast.BroadcastActivity;
import md.winitech.com.butterKnife.ButterActivity;
import md.winitech.com.databinding.ActivityMainBinding;
import md.winitech.com.opensource.openSourceActivity;
import md.winitech.com.pattern.ListActivity;
import md.winitech.com.pattern.patternActivity;
import md.winitech.com.recycler.recyclerActivity;
import md.winitech.com.retrofit.view.RetrofitActivity;
import md.winitech.com.service.ServiceActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
        binding.txtMain.setText("Main Activity");

        /**
         * XML Broadcast Receiver
         * */
        this.sendBroadcast(new Intent("md.winitech.com.Broadcast.test"));

        /**
         * Stetho 라이브러리 (디버깅용 : 크롬에서  chrome://inspect )
         * */
        Stetho.initializeWithDefaults(this);
    }


    public void onClickButton(View view){
        Intent mIntent;
        switch (view.getId()){
            case R.id.btn_1:
                mIntent= new Intent(this, BindActivity.class);
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
            case R.id.btn_15:
                mIntent = new Intent(this, RetrofitActivity.class);
                startActivity(mIntent);
                break;
        }

    }
}
