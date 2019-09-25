/*
 * Create by SangKwon on 2019. 9. 17.
 */

package md.ysk5898.kotlin.retrofit

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import md.ysk5898.com.R
import md.ysk5898.com.databinding.ActivityRetrofitKtBinding
import md.ysk5898.com.retrofit.URLS
import md.ysk5898.com.retrofit.model.nodeModel
import md.ysk5898.kotlin.retrofit.inf.retroKtinterface
import md.ysk5898.kotlin.retrofit.model.ktModel
import md.ysk5898.kotlin.retrofit.okhttp3.apiClientKt
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RetrofitKtActivity : AppCompatActivity() {

    lateinit var binding: ActivityRetrofitKtBinding
    lateinit var retrofit: Retrofit
    lateinit var api: apiClientKt
    lateinit var interfaceKt: retroKtinterface

    var mListdata: Call<List<ktModel.data>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_retrofit_kt)
        binding.activity = this

        retrofit = apiClientKt(URLS.API_URL).getClient()
        interfaceKt = retrofit.create(retroKtinterface::class.java)
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_1 -> {
                mListdata = interfaceKt.getData()
                mListdata!!.enqueue(object: Callback<List<ktModel.data>> {
                    override fun onResponse(call: Call<List<ktModel.data>>, response: Response<List<ktModel.data>>) {
                        var mList: List<ktModel.data>? = response.body()
                        interfaceSet(mList)
                    }

                    override fun onFailure(call: Call<List<ktModel.data>>, t: Throwable) {
                        Log.e("TAG","onFailure")
                    }
                })


            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun interfaceSet(mList: List<ktModel.data>?) {
        if (mList!!.isNotEmpty()) {
            binding.txt1.text = mList[0].name + " " + mList[0].age
        }
    }
}
