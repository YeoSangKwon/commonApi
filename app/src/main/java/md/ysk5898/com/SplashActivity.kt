/*
 * Create by SangKwon on 2019. 9. 17.
 */

package md.ysk5898.com

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import md.ysk5898.com.databinding.ActivitySplashBinding
import md.ysk5898.kotlin.MainKotlinActivity
import md.ysk5898.quiz.QuizActivity

class SplashActivity : AppCompatActivity() {

    lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.activity = this


    }

    fun onClick(v:View){
        var mIntent : Intent? = null
        if(v.id == R.id.btn_android){
            mIntent = Intent(this, MainActivity::class.java)
        }else if(v.id == R.id.btn_kotlin){
            mIntent = Intent(this, MainKotlinActivity::class.java)
        }else if(v.id == R.id.btn_quiz){
            mIntent = Intent(this, QuizActivity::class.java)
        }
        startActivity(mIntent)
    }
}
