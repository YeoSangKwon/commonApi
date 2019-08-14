/*
 * Create by SangKwon on 2019. 7. 25.
 */

package md.winitech.com.generic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import md.winitech.com.R;

public class GenericActivity extends AppCompatActivity {

    @BindView(R.id.edt_1) EditText edt_1;
    @BindView(R.id.btn_1) Button btn_1;
    @BindView(R.id.txt_1) TextView txt_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic);
        ButterKnife.bind(this);

        genericObj<String> obj = new genericObj<>("STRING");    //String 객체생성
        genericObj<Integer> obj1 = new genericObj<>(12345);     //Integer 객체생성

        Toast.makeText(this, obj.getData()+" , "+ obj1.getData(), Toast.LENGTH_LONG).show();

    }

    @OnClick({R.id.btn_1})
    public void onClick(View v){
        if (v.getId() == R.id.btn_1) {

            //내가 왜 만들었는지 까먹음..
            txt_1.setText(String.format("%s",edt_1.getText().toString() +"\n"+ txt_1.getText()));
            edt_1.setText("");
        }
    }
}
