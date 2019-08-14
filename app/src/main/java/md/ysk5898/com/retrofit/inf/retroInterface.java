/*
 * Create by SangKwon on 2019. 8. 14.
 */

package md.ysk5898.com.retrofit.inf;

import com.google.gson.JsonObject;

import java.util.List;

import md.ysk5898.com.retrofit.model.nodeModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface retroInterface {
    //DB 데이터 조회
    @GET("dbconn")
    Call<List<nodeModel.data>> getData();

    //DB 데이터 추가
    @POST("insert")
    Call<JsonObject> postData(@Body JsonObject jsonObject);
}
