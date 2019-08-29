/*
 * Create by SangKwon on 2019. 8. 28.
 */

package md.ysk5898.com.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import md.ysk5898.com.R;

public class BlueToothBleActivity extends AppCompatActivity {

    private static final String TAG = "BlueToothBleActivity";
    BluetoothAdapter mBluetoothAdapter;
    BluetoothLeScanner mBluetoothLeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth_ble);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();

        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
            Toast.makeText(this, "Not Supported BLE", Toast.LENGTH_SHORT).show();
            finish();
        }

        if(null == mBluetoothAdapter){
            Toast.makeText(this, "Not Supported BlueTooth", Toast.LENGTH_SHORT).show();
            finish();
        }

        if(!mBluetoothAdapter.isEnabled()){
            mBluetoothAdapter.enable();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBluetoothLeScanner.startScan(mBluetoothCallback);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBluetoothLeScanner.stopScan(mBluetoothCallback);
        mBluetoothAdapter = null;
        mBluetoothLeScanner = null;
    }

    ScanCallback mBluetoothCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            Log.e(TAG, "onScanResult = "+result.getDevice());
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
            for(ScanResult result : results){
                Log.e(TAG, "onBatchScanResults = "+result.getDevice());
            }
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            Log.e(TAG, "onScanFailed = "+errorCode);
        }
    };
}
