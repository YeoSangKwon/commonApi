/*
 * Create by SangKwon on 2019. 7. 17.
 */

package md.ysk5898.com.pattern.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

public class MainViewModel implements BaseViewModel {
    public final ObservableField<String> helloText = new ObservableField<>();

    @Override
    public void onCreate() {
        helloText.set("hello!");
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    public void showCurrentTime(){
        helloText.set(String.valueOf(System.currentTimeMillis()));
    }

    public View.OnClickListener currentTime2ClickListener = v -> {
        helloText.set(String.valueOf(System.currentTimeMillis()));
    };
}
