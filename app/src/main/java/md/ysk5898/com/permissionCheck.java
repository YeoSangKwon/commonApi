/*
 * Create by SangKwon on 2019. 8. 28.
 */

package md.ysk5898.com;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class permissionCheck {

    private Context mContext;

    public permissionCheck(Context mContext) {
        this.mContext = mContext;
    }

    // TODO
    //  try : 해당 클래스의 객체를 생성하는 액티비티에서는 콜백함수를 작성해야한다 (onRequestPermissionsResult)

    /**
     //생성자 생성
     permissionCheck pCk = new permissionCheck(this);
     //단일 퍼미션 호출
     pCk.check(BlueToothActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION, PERMISSIONS_REQUEST);
     //다중 퍼미션 호출
     pCk.checkList(BlueToothActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_REQUEST);
     **/

    /**
     * 사용자 권한을 추가하는 함수
     * @param activity :  권한이필요한 액티비티
     * @param permissionNm : 요청할 권한명
     * @param PERMISSIONS_REQUEST : 콜백 함수로 보낼 키
     * */
    public void check(Activity activity,  String permissionNm, int PERMISSIONS_REQUEST){
        int permissionCheck = ContextCompat.checkSelfPermission(mContext, permissionNm);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity, permissionNm)){
                //사용자가 거부를 눌렀을 때 동작
            }else{
                ActivityCompat.requestPermissions(activity, new String[]{permissionNm}, PERMISSIONS_REQUEST);
            }
        }
    }

    /**
     * 사용자 권한을 추가하는 함수
     * @param activity :  권한이필요한 액티비티
     * @param permissionNm : 요청할 권한명 (List)
     * @param PERMISSIONS_REQUEST : 콜백 함수로 보낼 키
     * */
    public void checkList(Activity activity,  String[] permissionNm, int PERMISSIONS_REQUEST){

        List<String> DENIED_LIST = new ArrayList<>();
        for(String permission : permissionNm){
            int permissionCheck = ContextCompat.checkSelfPermission(mContext, permission);
            if(permissionCheck != PackageManager.PERMISSION_GRANTED){
                DENIED_LIST.add(permission);
            }
        }

        if(DENIED_LIST.size() > 0){
            ActivityCompat.requestPermissions(activity, DENIED_LIST.toArray(new String[DENIED_LIST.size()]), PERMISSIONS_REQUEST);
        }
    }

}
