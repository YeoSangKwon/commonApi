/*
 * Create by SangKwon on 2019. 7. 16.
 */

package md.winitech.com.recycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import md.winitech.com.R;
import md.winitech.com.recycler.adapter.recyclerAdapter;
import md.winitech.com.recycler.data.Data;

public class recyclerActivity extends AppCompatActivity implements recyclerAdapter.ViewClickListener{

    private recyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        init();
        dataSet();
    }

    /**
     * 뷰 초기셋팅
     * */
    public void init(){
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new recyclerAdapter();
        //어뎁터에서 선언한 인터페이스를 현재 뷰에 있는 Override 된 함수로 연결
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 뷰 데이터 셋팅
     * */
    public void dataSet(){

        for(int i=1; i <6; i++){
            Data mData = new Data();
            mData.setTitle("순번");
            mData.setContent("리스트"+i);
            mData.setResId(R.drawable.check_box);

            adapter.addItem(mData);
        }

        adapter.notifyDataSetChanged();
    }


    /**
     * 어뎁터에 선언한 인터페이스 함수 구현
     * */
    @Override
    public void onItemClicked(int position) {
        Toast.makeText(this, "onItemClicked "+position, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemLongClicked(int position) {
        Toast.makeText(this, "onItemLongClicked "+position, Toast.LENGTH_LONG).show();
    }
}
