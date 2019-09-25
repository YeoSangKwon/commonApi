/*
 * Create by SangKwon on 2019. 9. 17.
 */

package md.ysk5898.kotlin.retrofit.inf

import md.ysk5898.kotlin.retrofit.model.ktModel
import retrofit2.Call
import retrofit2.http.GET

interface retroKtinterface {
    //DB 데이터 조회
    @GET("dbconn")
    fun getData(): Call<List<ktModel.data>>
}