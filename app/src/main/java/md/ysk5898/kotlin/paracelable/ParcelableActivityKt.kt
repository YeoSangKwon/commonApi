/*
 * Create by SangKwon on 2019. 10. 10.
 */

package md.ysk5898.kotlin.paracelable

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import md.ysk5898.com.R
import md.ysk5898.com.databinding.ActivityParcelableKtBinding

class ParcelableActivityKt : AppCompatActivity() {

    lateinit var binding: ActivityParcelableKtBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parcelable_kt)
        binding.activity = this
    }

    fun makeParcelable(){

    }

    fun onClick(v:View) {
        when(v.id){
            R.id.btn_1 ->{
                var data:data
                makeParcelable()
            }
        }
    }
}
