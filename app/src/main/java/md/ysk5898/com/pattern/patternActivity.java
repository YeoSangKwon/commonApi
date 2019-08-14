/*
 * Create by SangKwon on 2019. 7. 17.
 */

package md.ysk5898.com.pattern;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import md.ysk5898.com.R;
import md.ysk5898.com.databinding.ActivityPatternBinding;
import md.ysk5898.com.pattern.viewmodel.InputViewModel;
import md.ysk5898.com.pattern.viewmodel.MainViewModel;

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
