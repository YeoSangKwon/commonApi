/*
 * Create by SangKwon on 2019. 9. 17.
 */

package md.ysk5898.kotlin.retrofit.okhttp3

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class apiClientKt(URLS:String) {

    var URLS = ""
    var retrofit:Retrofit

    init {
        this.URLS = URLS

        var clientKt : OkHttpClient = OkHttpClient.Builder().build()
        var gson = GsonBuilder().setLenient().create()
        this.retrofit = Retrofit.Builder().baseUrl(this.URLS).addConverterFactory(GsonConverterFactory.create(gson)).client(clientKt).build()
    }

    fun getClient():Retrofit{

        return this.retrofit
    }
}