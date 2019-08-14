/*
 * Create by SangKwon on 2019. 7. 8.
 */

package md.ysk5898.com;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class apiApplication extends Application{

    private SharedPreferences mPref;
    SharedPreferences.Editor editor;

    Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        //SharedPreferences 선언
        mPref = getSharedPreferences("commonApi", Context.MODE_PRIVATE);
        editor = mPref.edit();

        //Realm 초기화
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    //컴포넌트가 실해되는 동안 단말의 화면이 바뀌면 실행
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public Context getContext() {return mContext;}
    public void setContext(Context mContext) {this.mContext = mContext;}

}
