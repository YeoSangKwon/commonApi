/*
 * Create by SangKwon on 2019. 10. 4.
 */

package md.ysk5898.com.livedata;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class NameViewModel extends ViewModel {


    private MutableLiveData<String> mCurrentName;

    public MutableLiveData<String> getCurrentName() {
        if(null == mCurrentName){
            mCurrentName = new MutableLiveData<>();
        }
        return mCurrentName;
    }

    public void postValue(String value) {
        mCurrentName.postValue(value);
    }

    public void setValue(String value) {
        mCurrentName.setValue(value);
    }

    @MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<String> observer) {
        mCurrentName.observe(owner, observer);
    }

    @MainThread
    public void observeForever(@NonNull Observer<String> observer) {
        mCurrentName.observeForever(observer);
    }

    @MainThread
    public void removeObserver(@NonNull Observer<String> observer) {
        mCurrentName.removeObserver(observer);
    }

    @MainThread
    public void removeObservers(@NonNull LifecycleOwner owner) {
        mCurrentName.removeObservers(owner);
    }

    @Nullable
    public String getValue() {
        return mCurrentName.getValue();
    }

    public boolean hasObservers() {
        return mCurrentName.hasObservers();
    }

    public boolean hasActiveObservers() {
        return mCurrentName.hasActiveObservers();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
