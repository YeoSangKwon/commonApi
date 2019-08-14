/*
 * Create by SangKwon on 2019. 8. 14.
 */

package md.ysk5898.com.retrofit.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.List;

import md.ysk5898.com.R;
import md.ysk5898.com.databinding.ActivityRetrofitBinding;
import md.ysk5898.com.recycler.adapter.recyclerAdapter;
import md.ysk5898.com.recycler.data.Data;
import md.ysk5898.com.retrofit.URLS;
import md.ysk5898.com.retrofit.callback.getListener;
import md.ysk5898.com.retrofit.inf.retroInterface;
import md.ysk5898.com.retrofit.model.nodeModel;
import md.ysk5898.com.retrofit.okhttp3.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitActivity extends AppCompatActivity implements recyclerAdapter.ViewClickListener{

    Context mContext;

    private Retrofit retrofit;
    private retroInterface mInterface;
    private APIClient mApiClient;

    private Call<List<nodeModel.data>> mListdata;   //select
    private Call<JsonObject> mPostdata;             //insert

    private recyclerAdapter adapter;

    ActivityRetrofitBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_retrofit);
        binding.setActivity(this);
        mContext = this;

        mApiClient = new APIClient();
        retrofit = mApiClient.getClient(URLS.API_URL);
        mInterface = retrofit.create(retroInterface.class);

    }

    public void clearButon() {
        binding.edtName.setText("");
        binding.edtAge.setText("");
    }

    public void initView(List<nodeModel.data> mData){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.myRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new recyclerAdapter();
        adapter.setOnClickListener(this);
        binding.myRecyclerView.setAdapter(adapter);
        dataSet(mData);
    }

    private void dataSet(List<nodeModel.data> mData){
        for(int i=0; i <mData.size(); i++){
            Data dataList = new Data();
            dataList.setTitle(mData.get(i).getName());
            dataList.setContent(mData.get(i).getAge()+"");
            dataList.setResId(R.drawable.check_box);
            adapter.addItem(dataList);
        }
        adapter.notifyDataSetChanged();
    }

    public void onClickButton(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                if ("".equals(binding.edtName.getText().toString()) || "".equals(binding.edtAge.getText().toString())) {
                    Toast.makeText(mContext, "필수값을 모두 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                JsonObject obj = new JsonObject();
                obj.addProperty("name", binding.edtName.getText().toString());
                obj.addProperty("age", Integer.parseInt(binding.edtAge.getText().toString()));
                mPostdata = mInterface.postData(obj);
                mPostdata.enqueue(new getListener(mContext).mInsertListener);
                break;

            case R.id.btn_2:
                mListdata = mInterface.getData();
                mListdata.enqueue(new getListener(mContext).mListener);
                break;
        }
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onItemLongClicked(int position) {

    }
}
