/*
 * Create by SangKwon on 2019. 7. 25.
 */

package md.winitech.com.Capture.comm;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import md.winitech.com.R;


/**
 * 20190718 여상권
 * 화면 캡쳐를 위한 공통 함수
 */
public class captureFunction {

    private static final String CAPTURE_PATH = "/CAPTURE";

    public void capture(final Activity activity, final Context mContext) {
        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        FileOutputStream fos;
                        Bitmap bitmap = new bitmapMaker().captureActivity(activity);

                        String strFolderPath = Environment.getExternalStorageDirectory().getAbsolutePath() + CAPTURE_PATH;
                        File folder = new File(strFolderPath);
                        if(!folder.exists()) {
                            folder.mkdirs();
                        }

                        String strFilePath = strFolderPath + "/" + System.currentTimeMillis() + ".png";
                        File fileCacheItem = new File(strFilePath);

                        try {
                            fos = new FileOutputStream(fileCacheItem);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(mContext, R.string.please_write_externam_permission, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
}
