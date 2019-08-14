/*
 * Create by SangKwon on 2019. 7. 16.
 */

package md.ysk5898.com.recycler.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import md.ysk5898.com.R;
import md.ysk5898.com.recycler.data.Data;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ViewHolder> {

    private ArrayList<Data> listData = new ArrayList<>();

    /**
     * 클릭이벤트를 다른곳에서 사용하기 위해 인터페이스 생성
     * */
    private ViewClickListener mListener;

    public interface ViewClickListener {
        // 아이템 전체 부분 클릭
        void onItemClicked(int position);

        // 아이템 롱클릭
        void onItemLongClicked(int position);
    }

    /**
     * 인터페이스가 구현되있는곳의 리스너와 연결
     * */
    public void setOnClickListener(ViewClickListener listener) {
        mListener = listener;
    }


    /**
     * 리사이클러뷰에 item 연결
     * */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }


    /**
     * 리사이클러뷰의 아이템들에 데이터를 연결 하고 리스너 등록
     * */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.onBind(listData.get(position));

        if (null != mListener) {
            viewHolder.itemView.setOnClickListener(v -> {
                mListener.onItemClicked(position);
            });

            viewHolder.itemView.setOnLongClickListener(v -> {
                mListener.onItemLongClicked(position);
                return true;    //false 변경 시 다음이벤트 계속 진행
            });
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addItem(Data data) {
        listData.add(data);
    }

    public void addItem(int position, Data data) {
        listData.add(position, data);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, listData.size());
    }

    public void removeItem(int position) {
        listData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listData.size());
    }

    /**
     * 리사이클러뷰에 등록하는 아이템 클래스
     * */
    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView1) TextView textView1;
        @BindView(R.id.textView2) TextView textView2;
        @BindView(R.id.imageView) ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void onBind(Data data) {
            textView1.setText(data.getTitle());
            textView2.setText(data.getContent());
            imageView.setImageResource(data.getResId());
        }

    }
}
