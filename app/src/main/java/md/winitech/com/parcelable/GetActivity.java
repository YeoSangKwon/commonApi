/*
 * Create by SangKwon on 2019. 7. 16.
 */

package md.winitech.com.parcelable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import md.winitech.com.R;

public class GetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
        Bundle bundle = getIntent().getExtras();
        Data mData = bundle.getParcelable("Parcelable");
        ArrayList<Data> mDataArrayList = bundle.getParcelableArrayList("ParcelableList");

        if (null != mData) {
            /*** 단일 Object 출력**/
            Log.e("TAG", "" + mData.getName());
            Log.e("TAG", "" + mData.getPhoneNumber());
            Log.e("TAG", "" + mData.getAddress());
        } else {
            /*** 다중 Object 출력**/
            for(Data data : mDataArrayList){
                Log.e("TAG", "" + data.getName());
                Log.e("TAG", "" + data.getPhoneNumber());
                Log.e("TAG", "" + data.getAddress());
            }

        }
    }
}
