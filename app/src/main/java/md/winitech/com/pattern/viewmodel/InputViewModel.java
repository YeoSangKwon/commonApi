
/*
 * Create by SangKwon on 2019. 7. 17.
 */

package md.winitech.com.pattern.viewmodel;

import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.RatingBar;

public class InputViewModel implements BaseViewModel {
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableInt score = new ObservableInt();
    public final ObservableBoolean isValid = new ObservableBoolean();

    @Override
    public void onCreate() {
        score.set(0);
        isValid.set(false);

        name.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                validation();
            }
        });

        email.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                validation();
            }
        });
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

    private void validation() {
        boolean isValidName = !TextUtils.isEmpty(name.get());
        boolean isValidEmail = !TextUtils.isEmpty(email.get()) && Patterns.EMAIL_ADDRESS.matcher(email.get()).matches();
        boolean isValidScore = score.get() > 0;
        isValid.set(isValidName && isValidEmail && isValidScore);
    }

    public RatingBar.OnRatingBarChangeListener scoreChangeListener = (ratingBar, v, b) -> {
        score.set((int) v);
        validation();
    };
}
