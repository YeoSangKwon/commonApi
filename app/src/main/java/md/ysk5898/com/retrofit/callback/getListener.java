/*
 * Create by SangKwon on 2019. 8. 14.
 */

package md.ysk5898.com.retrofit.callback;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;

import md.ysk5898.com.retrofit.view.RetrofitActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

import md.ysk5898.com.retrofit.model.nodeModel;

public class getListener {
    private static final String TAG = "getListener";

    private Context mContext;
    public getListener(Context mContext) {
        this.mContext = mContext;
    }

    //DB select 리스너
    public Callback<List<nodeModel.data>> mListener = new Callback<List<nodeModel.data>>() {
        @Override
        public void onResponse(Call<List<nodeModel.data>> call, Response<List<nodeModel.data>> response) {
            List<nodeModel.data> mList = response.body();
            ((RetrofitActivity)mContext).initView(mList);
        }

        @Override
        public void onFailure(Call<List<nodeModel.data>> call, Throwable t) {
            Log.e(TAG,"error");
        }
    };

    public Callback<JsonObject> mInsertListener = new Callback<JsonObject>() {
        @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            JsonObject data = response.body();
            Log.e(TAG,"data = "+data);
            Toast.makeText(mContext, "데이터가 정상적으로 저장되었습니다.",Toast.LENGTH_SHORT).show();
            ((RetrofitActivity)mContext).clearButon();
        }

        @Override
        public void onFailure(Call<JsonObject> call, Throwable t) {
            Log.e(TAG,"error");
        }
    };
}
