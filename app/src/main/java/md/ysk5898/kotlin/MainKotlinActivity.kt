/*
 * Create by SangKwon on 2019. 9. 2.
 */

package md.ysk5898.kotlin

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import md.ysk5898.com.R
import md.ysk5898.com.databinding.ActivityMainKotlinBinding
import md.ysk5898.kotlin.retrofit.RetrofitKtActivity

lateinit var binding: ActivityMainKotlinBinding

class MainKotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_kotlin)
        binding.activity = this
        binding.txt1.text = "Kotlin Main Activity"
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_1 -> {
                var mIntent: Intent = Intent(this, RetrofitKtActivity::class.java)
                startActivity(mIntent)
            }
            R.id.btn_2 -> Log.e("1111", "222222")
        }
    }
}
