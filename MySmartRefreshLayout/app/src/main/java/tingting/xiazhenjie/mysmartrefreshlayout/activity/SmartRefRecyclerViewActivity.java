package tingting.xiazhenjie.mysmartrefreshlayout.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.ArrayList;
import java.util.List;

import tingting.xiazhenjie.mysmartrefreshlayout.R;
import tingting.xiazhenjie.mysmartrefreshlayout.adapter.RecycleAdapter2;
import tingting.xiazhenjie.mysmartrefreshlayout.beans.Person;

/**
 * SmartRefreshLayout + RecyclerView + 自定义Header
 */
public class SmartRefRecyclerViewActivity extends AppCompatActivity {

    private SmartRefreshLayout mRefreshLayout;
    private RecycleAdapter2 recycleAdapter;
    private List<Person> mList = new ArrayList<>();
    private RecyclerView recyclerView;
    private int totalPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRefreshLayout = findViewById(R.id.refreshLayout);
        initRefreshLayout();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recycleAdapter = new RecycleAdapter2(this,mList);
        recyclerView.setAdapter(recycleAdapter);

        //自定义Header
        setHeader(recyclerView);

        refreshData();

        mRefreshLayout.setOnRefreshListener(refreshLayout -> refreshData());

        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            List<Person>  data;
            if(totalPage <= 3){
                data = initDatas();
                totalPage ++;
                Message message = new Message();
                message.what = 2;
                message.obj = data ;
                mHandler.sendMessageDelayed(message,1200);
            }
            else{
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            }
        });

    }

    private void refreshData(){
        List<Person> data = initDatas();
        Message message = new Message();
        message.what = 1 ;
        message.obj = data ;
        mHandler.sendMessageDelayed(message,1200);
    }

    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:         //刷新加载
                    List<Person> mList  = (List<Person>) msg.obj;
                    mRefreshLayout.finishRefresh(true);
                    recycleAdapter.setDatas(mList);
                    recycleAdapter.notifyDataSetChanged();
                    totalPage = 1;
                    break;
                case 2:         //加载更多
                    List<Person> mLoadMoreDatas = (List<Person>) msg.obj;
                    mRefreshLayout.finishLoadMore(true);
                    recycleAdapter.addMoreValue(mLoadMoreDatas);
                    recycleAdapter.notifyDataSetChanged();
                    break;
            }
            return false;
        }
    });

    private List<Person> initDatas(){
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(new Person("https://ttfm2018pub.oss-cn-beijing.aliyuncs.com/pic/avatar_default.png","浪里小白龙","18506458844","https://t7.baidu.com/it/u=727460147,2222092211&fm=193&f=GIF","FalsifyHeader 虚假的Header，用于 真正的 Header 在RefreshLayout外部时"));
        }
        return list;
    }

    @SuppressLint("ResourceAsColor")
    private void initRefreshLayout(){
        mRefreshLayout.setBackgroundColor(R.color.white);
        mRefreshLayout.setPrimaryColors(R.color.material_on_primary_disabled);
    }

    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(this).inflate(R.layout.adapter_item_person_info, view, false);
        recycleAdapter.setHeaderView(header);
    }

}