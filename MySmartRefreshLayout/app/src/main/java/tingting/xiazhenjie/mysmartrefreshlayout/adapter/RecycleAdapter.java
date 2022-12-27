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

import java.util.List;

import tingting.xiazhenjie.mysmartrefreshlayout.beans.Person;
import tingting.xiazhenjie.mysmartrefreshlayout.R;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> implements View.OnClickListener {
    private static final String TAG = "RecycleAdapter";

    private onRecyclerViewListener onRecyclerViewListener;
    private List<Person> mList;
    private Context context;

    public RecycleAdapter(Context context, List<Person> mList) {
        this.context = context;
        this.mList = mList;
    }

    public void setDatas(List<Person> mList){
        this.mList = mList;
    }

    public void addMoreValue(List<Person> mList){
        this.mList.addAll(mList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder viewHolder;
        View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_item_person_info, parent, false);
        viewHolder = new MyViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Person person = mList.get(position);
        holder.username.setText(person.getUsername());
        holder.mobile.setText(person.getMobile());
        holder.content.setText(person.getContent());
        Glide.with(context).load(person.getAvatar()).into(holder.avatar);
        Glide.with(context).load(person.getBigImage()).into(holder.bigImage);
    }

    @Override
    public int getItemCount() {
        return mList.size();
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

    public void setOnRecyclerViewListener(RecycleAdapter.onRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    interface onRecyclerViewListener {
        void editHeadIcon(int position);

        void deleteInfo(int position);
    }
}

