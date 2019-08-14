/*
 * Create by SangKwon on 2019. 8. 2.
 */

package md.ysk5898.com.service;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import md.ysk5898.com.R;
import md.ysk5898.com.databinding.ActivityServiceBinding;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityServiceBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_service);
        binding.setActivity(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_start:
                Toast.makeText(this, "onClick start", Toast.LENGTH_SHORT).show();
                startService();
                break;

            case R.id.btn_stop:
                Toast.makeText(this, "onClick stop", Toast.LENGTH_SHORT).show();
                stopService();
                break;
        }
    }

    public void startService() {
        Intent serviceIntent = new Intent(this, reqService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Test");

        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService() {
        Intent serviceIntent = new Intent(this, reqService.class);
        stopService(serviceIntent);
    }
}
