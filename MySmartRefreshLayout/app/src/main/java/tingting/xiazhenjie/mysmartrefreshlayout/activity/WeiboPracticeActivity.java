package tingting.xiazhenjie.mysmartrefreshlayout.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

import tingting.xiazhenjie.mysmartrefreshlayout.R;
import tingting.xiazhenjie.mysmartrefreshlayout.utils.StatusBarUtil;

/**
 * 微博主页
 */
public class WeiboPracticeActivity extends AppCompatActivity {

    private int mOffset = 0;
    private int mScrollY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_weibo);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //状态栏透明和间距处理
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, toolbar);
        StatusBarUtil.setMargin(this, findViewById(R.id.header));

        final View parallax = findViewById(R.id.parallax);
        final View buttonBar = findViewById(R.id.buttonBarLayout);
        final NestedScrollView scrollView = findViewById(R.id.scrollView);
        final RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);

//        findViewById(R.id.attention).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"点击了关注",Toast.LENGTH_SHORT).show();
//            }
//        });
//        findViewById(R.id.leaveword).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"点击了留言",Toast.LENGTH_SHORT).show();
//            }
//        });

        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(3000);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
            }

            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                mOffset = offset / 2;
                parallax.setTranslationY(mOffset - mScrollY);
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }
        });
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            private int lastScrollY = 0;
            private int h = dp2px(170);
            private int color = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)&0x00ffffff;
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
                    buttonBar.setAlpha(1f * mScrollY / h);
                    toolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                    parallax.setTranslationY(mOffset - mScrollY);
                }
                lastScrollY = scrollY;
            }
        });
        buttonBar.setAlpha(0);
        toolbar.setBackgroundColor(0);
    }

    private static float density = Resources.getSystem().getDisplayMetrics().density;


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @param dpValue 虚拟像素
     * @return 像素
     */
    public static int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * density);
    }

}
