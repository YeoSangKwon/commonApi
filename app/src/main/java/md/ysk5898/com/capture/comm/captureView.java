/*
 * Create by SangKwon on 2019. 7. 25.
 */

package md.ysk5898.com.capture.comm;

import android.graphics.Bitmap;
import android.view.View;
import android.app.Activity;

public interface captureView {
    //특정뷰 캡쳐
    Bitmap captureView(View View);
    //액티비티 캡쳐
    Bitmap captureActivity(Activity activity);
}
