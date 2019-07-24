/*
 * Create by SangKwon on 2019. 7. 8.
 */

package md.winitech.com.butterKnife;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import md.winitech.com.R;
import md.winitech.com.butterKnife.fragment.ButterFragmentA;
import md.winitech.com.butterKnife.fragment.ButterFragmentB;

public class ButterActivity extends AppCompatActivity implements ButterFragmentA.onReceivedData {

    @BindView(R.id.txt_1)
    TextView txt_1;
    @BindView(R.id.btn_1)
    Button btn_1;
    @BindView(R.id.btn_2)
    Button btn_2;

    FragmentManager manager;
    FragmentTransaction transaction;
    ButterFragmentA fragmentA;
    ButterFragmentB fragmentB;

    private String _std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter);
        ButterKnife.bind(this);

        txt_1.setText("Butter Knife Page");

        //플래그먼트 생성
        fragmentA = new ButterFragmentA();
        fragmentB = new ButterFragmentB();



    }

    @OnClick({R.id.btn_1, R.id.btn_2})
    public void onClickButton(View view) {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.btn_1:
                Toast.makeText(this, "Button Click", Toast.LENGTH_LONG).show();
                transaction.replace(R.id.frame_butter, fragmentA,"TagA");
                transaction.commit();
                break;
            case R.id.btn_2:
                Toast.makeText(this, "Button Click", Toast.LENGTH_LONG).show();
                transaction.replace(R.id.frame_butter, fragmentB, "TagB");
                transaction.commit();
                break;
        }
    }

    @OnClick(R.id.btn_3)
    public void onClickFragment(View view){
        //Activity -> Fragment 함수 호출 (ID 또는 TAG를 이용해서 호출 가능하다)
        ButterFragmentA a = (ButterFragmentA)getSupportFragmentManager().findFragmentByTag("TagA");
        if(null != a){
            a.showToast(this, "Main Activity Call Function");
        }
    }


    //FragmentA 에서 선언해둔 리스터를 implements 했기 때문에 구현
    @Override
    public void onReceivedData(String _std) {
        this._std = _std;
        Log.e("commomAPI", "=====================  onReceivedData  ==================");
    }
}
