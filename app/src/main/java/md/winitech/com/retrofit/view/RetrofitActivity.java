/*
 * Create by SangKwon on 2019. 8. 14.
 */

package md.winitech.com.retrofit.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import md.winitech.com.R;
import md.winitech.com.databinding.ActivityRetrofitBinding;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRetrofitBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_retrofit);

    }
}
