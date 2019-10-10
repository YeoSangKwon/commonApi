/*
 * Create by SangKwon on 2019. 10. 4.
 */

package md.ysk5898.com.livedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import md.ysk5898.com.R;
import md.ysk5898.com.databinding.ActivityNameBinding;

public class NameActivity extends AppCompatActivity {

    private NameViewModel model;

    ActivityNameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_name);
        binding.setActivity(this);

        model = ViewModelProviders.of(this).get(NameViewModel.class);

        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                binding.txt1.setText(s);
            }
        };

        model.getCurrentName().observe(this, nameObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * 메인스레드에서 LiveData를 갱신하려면 반드시 setValue(T) 메서드
     * 작업 스레드에서 LiveData를 갱신하려면 postValue(T) 메서드
     * */
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_1:
                model.getCurrentName().setValue("TEST");
                break;
        }

    }
}
