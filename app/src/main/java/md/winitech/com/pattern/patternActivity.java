/*
 * Create by SangKwon on 2019. 7. 17.
 */

package md.winitech.com.pattern;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import md.winitech.com.R;
import md.winitech.com.databinding.ActivityPatternBinding;
import md.winitech.com.pattern.viewModel.InputViewModel;
import md.winitech.com.pattern.viewModel.MainViewModel;

public class patternActivity extends AppCompatActivity {
    MainViewModel model = new MainViewModel();
    InputViewModel input = new InputViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPatternBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_pattern);
        binding.setModel(model);
        binding.setInput(input);
        model.onCreate();
        input.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
