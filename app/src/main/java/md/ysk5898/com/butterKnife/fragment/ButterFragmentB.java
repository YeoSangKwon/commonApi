/*
 * Create by SangKwon on 2019. 7. 8.
 */

/*
 * Create by SangKwon on 2019. 7. 8.
 */

package md.ysk5898.com.butterKnife.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import md.ysk5898.com.R;

public class ButterFragmentB extends Fragment {
    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_butter2, container, false);

        return view;
    }
}
