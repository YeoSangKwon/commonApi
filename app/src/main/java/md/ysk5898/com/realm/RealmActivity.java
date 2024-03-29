/*
 * Create by SangKwon on 2019. 7. 8.
 */

package md.ysk5898.com.realm;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import md.ysk5898.com.R;
import md.ysk5898.com.apiApplication;

public class RealmActivity extends AppCompatActivity {

    //어플리케이션 및 Context 선언
    apiApplication mApplication;
    Context mContext;

    //Realm 객체선언
    private Realm realm;
    private RealmResults<User> allUser;
    private RealmChangeListener listener = new RealmChangeListener() {
        @Override
        public void onChange(Object o) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);

        mApplication = (apiApplication) getApplicationContext();
        mContext = this;

        User user = new User();
        user.setName("sangkwon");
        user.setAge(34);
        user.setSessionId(1);

        realm = Realm.getDefaultInstance();
        realm.addChangeListener(listener);

        allUser = realm.where(User.class).findAll();

        Log.e("commonApi", ""+allUser.size());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
