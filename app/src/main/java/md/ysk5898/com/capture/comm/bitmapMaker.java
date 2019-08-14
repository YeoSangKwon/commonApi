/*
 * Create by SangKwon on 2019. 7. 25.
 */

package md.ysk5898.com.capture.comm;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;

public class bitmapMaker implements captureView{

    @Override
    public Bitmap captureView(View View) {
        if(View == null) return null;

        View.setDrawingCacheEnabled(true);
        View.buildDrawingCache();
        return View.getDrawingCache();
    }

    @Override
    public Bitmap captureActivity(Activity activity) {
        if(activity == null) return null;

        View root = activity.getWindow().getDecorView().getRootView();
        root.setDrawingCacheEnabled(true);
        root.buildDrawingCache();
        // 루트뷰의 캐시를 가져옴
        Bitmap screenshot = root.getDrawingCache();

        // get view coordinates
        int[] location = new int[2];
        root.getLocationInWindow(location);
        return Bitmap.createBitmap(screenshot, location[0], location[1], root.getWidth(), root.getHeight(), null, false);
    }

}
