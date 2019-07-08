/*
 * Create by SangKwon on 2019. 7. 5.
 *
 * 공통 뷰를 이용한 데이터바인딩
 *
 * 1. 상속하는 부분에서 바인딩 객체를 전달
 * 2. setBinding에 View 를 전달하여 데이터 바인딩
 * 3. getBinding을 사용하여 바인딩된 View를 사용
 */

package md.winitech.com.bindAPI;

import android.os.Bundle;

import md.winitech.com.R;
import md.winitech.com.bindAPI.base.commomActivity;
import md.winitech.com.databinding.ActivityBindBinding;

public class BindActivity extends commomActivity<ActivityBindBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //extends 한 액티비티에 해당 액티비티 뷰를 넘겨서 binding
        setBinding(R.layout.activity_bind);

        //바인딩 된 뷰를 가져와서 해당 뷰를 셋팅한다
        ActivityBindBinding binding = getBinding();
        binding.setActivity(this);
        binding.txtFirst.setText("common Sub Page");
    }
}
