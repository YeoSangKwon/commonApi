/*
 * Create by SangKwon on 2019. 7. 17.
 */

package md.winitech.com.pattern;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import md.winitech.com.R;
import md.winitech.com.databinding.ActivityListBinding;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
    }
}
