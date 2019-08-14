/*
 * Create by SangKwon on 2019. 7. 8.
 */

/*
 * Create by SangKwon on 2019. 7. 8.
 *
 * 공통뷰
 *  - 상속 하는 뷰에서 해당 뷰의 바인딩된 뷰를 가져옴
 *  - 공통 바인딩만을 위한 뷰로 화면은 따로 존재하지 않음
 */

package md.ysk5898.com.bindAPI.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class commActivity<T extends ViewDataBinding> extends AppCompatActivity {

    private T vb;

    protected void setBinding(@LayoutRes int layoutResID){
        if(vb == null){
            vb = DataBindingUtil.setContentView(this, layoutResID);
        }
    }

    protected T getBinding(){
        return vb;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
