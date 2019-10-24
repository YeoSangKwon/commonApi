/*
 * Create by SangKwon on 2019. 7. 10.
 */

package md.ysk5898.com.lamda;

import android.os.Bundle;
import android.view.View;

import md.ysk5898.com.R;
import md.ysk5898.com.bindapi.base.commActivity;
import md.ysk5898.com.databinding.ActivityLamdaBinding;

public class LamdaActivity extends commActivity <ActivityLamdaBinding> {

    interface Compare{
        public int compareTo(int a, int b);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBinding(R.layout.activity_lamda);
        getBinding().txt1.setText("초기상태");
        getBinding().btn1.setOnClickListener(onClickListener);
    }

    /**
     * 리스너를 람다식으로 변환하여 구현
     * */
    View.OnClickListener onClickListener = (v)->{

        /**
         * 인터페이스를 생성 후 해당 인터페이스를 다른곳에서 사용 할 때 간단하게 구현하기 위해 람다식 사용
         * */
        test((a,b)-> a+b, 1, 2);
    };

    public void test(Compare compare, int a, int b){
        int value = compare.compareTo(a,b);
        getBinding().btn1.setText(value+"");
    }
}
