package tingting.xiazhenjie.mysmartrefreshlayout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import tingting.xiazhenjie.mysmartrefreshlayout.R;
import tingting.xiazhenjie.mysmartrefreshlayout.beans.Person;

public class RecycleAdapter2 extends RecyclerView.Adapter<RecycleAdapter2.MyViewHolder> implements View.OnClickListener {
    private static final String TAG = "RecycleAdapter";

    private onRecyclerViewListener onRecyclerViewListener;
    private List<Person> mList;
    private Context context;

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private View mHeaderView;

    public RecycleAdapter2(Context context, List<Person> mList) {
        this.context = context;
        this.mList = mList;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }
    
    public View getHeaderView() {
        return mHeaderView;
    }

    public void setDatas(List<Person> mList){
        this.mList = mList;
    }

    public void addMoreValue(List<Person> mList){
        this.mList.addAll(mList);
    }

    @Override
    public int getItemViewType(int position) {
        if(mHeaderView == null) return TYPE_NORMAL;
        if(position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER) return new MyViewHolder(mHeaderView);
        View layout = LayoutInflater.from(context).inflate(R.layout.adapter_item_person_info, parent, false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(getItemViewType(position) == TYPE_HEADER) return;

        final int pos = getRealPosition(holder);
        Person person = mList.get(pos);

        if(holder instanceof MyViewHolder) {
            holder.username.setText(person.getUsername());
            holder.mobile.setText(person.getMobile());
            holder.content.setText(person.getContent());
            Glide.with(context).load(person.getAvatar()).into(holder.avatar);
            Glide.with(context).load(person.getBigImage()).into(holder.bigImage);
        }

    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mList.size() : mList.size() + 1;
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.iv_edit_head:
//                int position= (int) v.getTag();
//                Log.i(TAG, "onClick: position:::"+position);
//                onRecyclerViewListener.editHeadIcon(position);
//                break;
//            case R.id.iv_delete:
//                int position1= (int) v.getTag();
//                onRecyclerViewListener.deleteInfo(position1);
//                break;
//            default:
//                break;
//        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView avatar,bigImage;
        TextView username,mobile,content;
        public MyViewHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            bigImage = itemView.findViewById(R.id.bigImage);
            username = itemView.findViewById(R.id.username);
            mobile = itemView.findViewById(R.id.mobile);
            content = itemView.findViewById(R.id.content);
        }
    }

    public void setOnRecyclerViewListener(RecycleAdapter2.onRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    interface onRecyclerViewListener {
        void editHeadIcon(int position);

        void deleteInfo(int position);
    }
}

