/*
 * Create by SangKwon on 2019. 7. 8.
 *
 * 바인딩 할 때 object를 넘기는 페이지
 *  - 객체를 전달하여 해당 객체의 값이 바로 데이터로 적용되도록 반영
 */

package md.winitech.com.bindAPI;

import android.os.Bundle;

import md.winitech.com.R;
import md.winitech.com.bindAPI.base.commomActivity;
import md.winitech.com.bindAPI.data.User;
import md.winitech.com.databinding.ActivityBindObjectBinding;

public class ObjectActivity extends commomActivity<ActivityBindObjectBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setBinding(R.layout.activity_bind_object);
        ActivityBindObjectBinding binding = getBinding();

        User user = new User("Test", "User");
        binding.setActivity(this);
        binding.setUser(user);
    }
}
