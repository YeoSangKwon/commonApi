/*
 * Create by SangKwon on 2019. 8. 23.
 */

package md.ysk5898.com.technology.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import md.ysk5898.com.technology.view.FirstFragment;
import md.ysk5898.com.technology.view.SecondFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 2;


    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FirstFragment.newInstance(0, " #1");
            case 1:
                return SecondFragment.newInstance(1, " #2");
            case 2:
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
