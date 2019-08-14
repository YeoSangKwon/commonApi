/*
 * Create by SangKwon on 2019. 7.
 *
 * Parcelable : 인텐트 전달 시 객체를 전달 할 필요가 있는경우
 * 해당 객체를 직렬화 및 복원 해줌
 */

package md.ysk5898.com.parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import md.ysk5898.com.R;

public class ParcelableActivity extends AppCompatActivity {

    ArrayList<Data> mDataArrayList;

    @BindView(R.id.btn_1) Button btn_1;
    @BindView(R.id.btn_2) Button btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelable);
        ButterKnife.bind(this);

        btn_1.setOnClickListener(onClickListener);
        btn_2.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = (v) ->{
        Intent mIntent = new Intent(ParcelableActivity.this, GetActivity.class);
        Data mData = null;
        switch (v.getId()){
            case R.id.btn_1:
                mData = new Data("SangKwon", "010-0000-0000","Daegu");
                mIntent.putExtra("Parcelable",mData);
                startActivity(mIntent);
                break;

            case R.id.btn_2:

                mDataArrayList = new ArrayList<Data>();
                for(int i = 0 ; i <3; i++){
                    mData = new Data("SangKwon"+i, "010-0000-000"+i,"Daegu");
                    mDataArrayList.add(mData);
                }
                mIntent.putParcelableArrayListExtra("ParcelableList",mDataArrayList);
                startActivity(mIntent);
                break;
        }

    };
}
