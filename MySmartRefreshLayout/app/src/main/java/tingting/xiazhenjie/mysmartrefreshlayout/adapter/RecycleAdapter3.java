package tingting.xiazhenjie.mysmartrefreshlayout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import tingting.xiazhenjie.mysmartrefreshlayout.R;
import tingting.xiazhenjie.mysmartrefreshlayout.beans.Person;

/**
 * Created by qibin on 2015/11/7.
 */
public class RecycleAdapter3 extends MyBaseRecyclerAdapter<Person> {

    private Context context;

    public RecycleAdapter3(Context mContext, ArrayList<Person> mList) {
        super(mContext, mList);
        context = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_person_info, parent, false);
        return new MyHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, Person person) {
        if(viewHolder instanceof MyHolder) {
            ((MyHolder) viewHolder).username.setText(person.getUsername());
            ((MyHolder) viewHolder).mobile.setText(person.getMobile());
            ((MyHolder) viewHolder).content.setText(person.getContent());
            Glide.with(context).load(person.getAvatar()).into( ((MyHolder) viewHolder).avatar);
            Glide.with(context).load(person.getBigImage()).into( ((MyHolder) viewHolder).bigImage);
        }
    }

    class MyHolder extends MyBaseRecyclerAdapter.Holder {
        ImageView avatar,bigImage;
        TextView username,mobile,content;
        public MyHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            bigImage = itemView.findViewById(R.id.bigImage);
            username = itemView.findViewById(R.id.username);
            mobile = itemView.findViewById(R.id.mobile);
            content = itemView.findViewById(R.id.content);
        }
    }
}