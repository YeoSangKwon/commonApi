/*
 * Create by SangKwon on 2019. 9. 27.
 */

package md.ysk5898.kotlin.bluetooth

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.*
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import md.ysk5898.com.R
import md.ysk5898.com.permissionCheck
import java.util.*
import kotlin.collections.ArrayList

class BluetoothKtActivity : AppCompatActivity() {

    lateinit var mBluetoothAdapter: BluetoothAdapter
    lateinit var mBluetoothScanner: BluetoothLeScanner

    private val PERMISSIONS_REQUEST = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth_kt)

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        mBluetoothScanner = mBluetoothAdapter.bluetoothLeScanner

        if (!packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "Not Supported BLE", Toast.LENGTH_SHORT).show()
            finish()
        }

        if (!mBluetoothAdapter.isEnabled) {
            mBluetoothAdapter.enable()
        }

        var pCheck = permissionCheck(this)
        pCheck.checkList(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION) , PERMISSIONS_REQUEST)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSIONS_REQUEST -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "권한 허용이 필요합니다.", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        var sFilterList = ArrayList<ScanFilter>()
        var sFilter = ScanFilter.Builder().build()
        sFilterList.add(sFilter)

        var sSetting = ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_POWER).build()

        mBluetoothScanner.startScan(sFilterList,sSetting,BluetoothCallback)
    }

    override fun onStop() {
        super.onStop()
        mBluetoothScanner.stopScan(BluetoothCallback)
    }

    object BluetoothCallback : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            Log.e("111111", "onScanResult = " + result?.device)
        }

        override fun onBatchScanResults(results: MutableList<ScanResult>?) {
            super.onBatchScanResults(results)
            for (result in results!!) {
                Log.e("111111", "onBatchScanResults = " + result.device)
            }
        }

        override fun onScanFailed(errorCode: Int) {
            super.onScanFailed(errorCode)
            Log.e("111111", "onScanFailed = $errorCode")
        }
    }
}
