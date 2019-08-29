/*
 * Create by SangKwon on 2019. 8. 23.
 */

package md.ysk5898.com.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import md.ysk5898.com.R;
import md.ysk5898.com.databinding.ActivityBlueToothBinding;
import md.ysk5898.com.permissionCheck;

public class BlueToothActivity extends AppCompatActivity {

    private static final String TAG = "BlueToothActivity";
    ActivityBlueToothBinding binding;

    private BluetoothAdapter mAdapter;
    private Set<BluetoothDevice> bluetoothDeviceSet;
    private static final int BLUETOOTH_REQUEST = 100;
    private static final int PERMISSIONS_REQUEST = 200;


    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_blue_tooth);
        binding.setActivity(this);

        //생성자 생성
//        permissionCheck pCk = new permissionCheck(this);
//        pCk.check(BlueToothActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION, PERMISSIONS_REQUEST);
//        pCk.checkList(BlueToothActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_REQUEST);

        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
            Toast.makeText(this, "Not Supported BLE", Toast.LENGTH_SHORT).show();
            finish();
        }

        BluetoothManager mManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        if(null != mManager){
            mAdapter = mManager.getAdapter();
        }

        if(null == mAdapter){
            Toast.makeText(this, "Not Supported BlueTooth", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler = new Handler();

        if(!mAdapter.isEnabled()){
            Intent mIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(mIntent, BLUETOOTH_REQUEST);
        }else{
            selectDevide();
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);    //BluetoothAdapter.ACTION_DISCOVERY_STARTED : 블루투스 검색 시작
        filter.addAction(BluetoothDevice.ACTION_FOUND);                 //BluetoothDevice.ACTION_FOUND : 블루투스 디바이스 찾음
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);   //BluetoothAdapter.ACTION_DISCOVERY_FINISHED : 블루투스 검색 종료
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cancelDiscovery();
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case BLUETOOTH_REQUEST:
                if(resultCode == RESULT_OK){
                    selectDevide();
                }else{
                    Toast.makeText(this, "종료합니다.",Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSIONS_REQUEST:
                //다중일 경우는 따로 처리하면됨
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "권한승인완료",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    protected void selectDevide(){
        bluetoothDeviceSet = mAdapter.getBondedDevices();
        List<String> mList = new ArrayList<>();
        for(BluetoothDevice mDevice : bluetoothDeviceSet){
            mList.add(mDevice.getName());
            Log.e(TAG, ""+mDevice.getName());
            Log.e(TAG, ""+mDevice.getAddress());
        }
        if(!mAdapter.isDiscovering()){
            mAdapter.startDiscovery();
        }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)){
                Toast.makeText(BlueToothActivity.this, "블루투스 검색 시작", Toast.LENGTH_SHORT).show();
            }else if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.e(TAG, ""+device.getName());
                Log.e(TAG, ""+device.getAddress());
            }else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                Toast.makeText(BlueToothActivity.this, "블루투스 검색 종료", Toast.LENGTH_SHORT).show();
            }
        }
    };

}
