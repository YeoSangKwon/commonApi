/*
 * Create by SangKwon on 2019. 9. 2.
 */

package md.ysk5898.kotlin

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import md.ysk5898.com.R
import md.ysk5898.com.databinding.ActivityMainKotlinBinding
import md.ysk5898.kotlin.bluetooth.BluetoothKtActivity
import md.ysk5898.kotlin.kakao.KakaoMapActivity
import md.ysk5898.kotlin.retrofit.RetrofitKtActivity


class MainKotlinActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainKotlinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_kotlin)
        binding.activity = this
        binding.txt1.text = "Kotlin Main Activity"
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_1 -> {
                var mIntent = Intent(this, RetrofitKtActivity::class.java)
                startActivity(mIntent)
            }
            R.id.btn_2 -> {
                var mIntent = Intent(this, BluetoothKtActivity::class.java)
                startActivity(mIntent)
            }
            R.id.btn_3 ->{
                var mIntent = Intent(this, KakaoMapActivity::class.java)
                startActivity(mIntent)
            }
        }
    }
}
