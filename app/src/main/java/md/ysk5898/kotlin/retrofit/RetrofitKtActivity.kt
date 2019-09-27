/*
 * Create by SangKwon on 2019. 9. 17.
 */

package md.ysk5898.kotlin.retrofit

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import md.ysk5898.com.R
import md.ysk5898.com.databinding.ActivityRetrofitKtBinding
import md.ysk5898.com.retrofit.URLS
import md.ysk5898.kotlin.retrofit.adapter.recyclerAdapter
import md.ysk5898.kotlin.retrofit.inf.retroKtinterface
import md.ysk5898.kotlin.retrofit.model.ktModel
import md.ysk5898.kotlin.retrofit.okhttp3.apiClientKt
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import md.ysk5898.kotlin.retrofit.model.dataModelList

class RetrofitKtActivity : AppCompatActivity(), recyclerAdapter.ViewClickListener {
    override fun onItemLongClicked(position: Int) {

    }

    override fun onItemClicked(position: Int) {
        Log.e("11111", "onItemClicked")
    }

    lateinit var binding: ActivityRetrofitKtBinding
    lateinit var retrofit: Retrofit
    lateinit var api: apiClientKt
    lateinit var interfaceKt: retroKtinterface

    private var adapter: recyclerAdapter? = null
    var mListdata: Call<List<dataModelList.dataModel>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_retrofit_kt)
        binding.activity = this

        retrofit = apiClientKt(URLS.API_URL).getClient()
        interfaceKt = retrofit.create(retroKtinterface::class.java)

        initRecycler()
    }

    private fun dataSet(mList: List<dataModelList.dataModel>) {
        run loop@{
            var index =0;
            mList.forEach { item ->
                adapter?.addItem(item)
            }
        }

        adapter!!.notifyDataSetChanged()
    }

    private fun initRecycler() {
        val recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        adapter = recyclerAdapter()
        adapter!!.setOnClickListener(this)
        recyclerView.adapter = adapter
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_1 -> {
                mListdata = interfaceKt.getData()
                mListdata!!.enqueue(object : Callback<List<dataModelList.dataModel>> {
                    override fun onFailure(call: Call<List<dataModelList.dataModel>>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<List<dataModelList.dataModel>>, response: Response<List<dataModelList.dataModel>>) {
                        var mList: List<dataModelList.dataModel>? = response.body()
                        interfaceSet(mList)
                    }
                })

            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun interfaceSet(mList: List<dataModelList.dataModel>?) {
        if (mList!!.isNotEmpty()) {
            dataSet(mList)
        }
    }
}
