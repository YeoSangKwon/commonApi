/*
 * Create by SangKwon on 2019. 8. 23.
 */

package md.ysk5898.com.technology;

import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import md.ysk5898.com.R;
import md.ysk5898.com.databinding.ActivityTechBinding;
import md.ysk5898.com.technology.adapter.FragmentAdapter;

public class TechActivity extends AppCompatActivity {

    ActivityTechBinding binding;
    FragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tech);
        binding.setActivity(this);

        adapter = new FragmentAdapter(getSupportFragmentManager());

        binding.viewpager.setAdapter(adapter);
        binding.indicator.setViewPager(binding.viewpager);

        adapter.registerDataSetObserver(binding.indicator.getDataSetObserver());

        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                Log.e("TechActivity", "current position = "+i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        Snackbar.make(binding.llMain, "show Snackbar", Snackbar.LENGTH_SHORT).show();
    }

}
