/*
 * Create by SangKwon on 2019. 7. 25.
 */

package md.ysk5898.com.capture;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import md.ysk5898.com.capture.comm.captureFunction;
import md.ysk5898.com.R;

public class CaptureActivity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        ButterKnife.bind(this);
        mContext = this;
    }


    @OnClick({R.id.btn_1})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                captureFunction function = new captureFunction();
                function.capture(CaptureActivity.this, mContext);
                break;
        }
    }
}
