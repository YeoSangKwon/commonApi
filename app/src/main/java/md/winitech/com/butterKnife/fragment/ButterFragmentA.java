/*
 * Create by SangKwon on 2019. 7. 8.
 */

/*
 * Create by SangKwon on 2019. 7. 8.
 *
 * 안드로이드 플래그먼트
 */

package md.winitech.com.butterKnife.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import md.winitech.com.R;

public class ButterFragmentA extends Fragment {
    View view;

    private onReceivedData ReceiveData;

    public interface onReceivedData{
        void onReceivedData(String _std);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //플래그먼트가 최초 액티비티에서 생성 될 때 리스너를 생성
        if(getActivity() != null && getActivity() instanceof onReceivedData){
            ReceiveData = (onReceivedData) getActivity();
        }else{throw  new RuntimeException(context.toString());}
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_butter1, container, false);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //플래그먼트에서 동작 시 액티비티에 있는 리스터 함수를 호출
        ReceiveData.onReceivedData("TEST");
        ReceiveData = null;
    }

    public void showToast(Context mContext, String MSG){
        Toast.makeText(mContext, MSG, Toast.LENGTH_LONG).show();
    }
}
