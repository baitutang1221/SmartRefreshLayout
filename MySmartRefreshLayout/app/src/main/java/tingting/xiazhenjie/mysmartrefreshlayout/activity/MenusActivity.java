package tingting.xiazhenjie.mysmartrefreshlayout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import tingting.xiazhenjie.mysmartrefreshlayout.R;

public class MenusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menus_activity);

        findViewById(R.id.tab1).setOnClickListener(v -> startActivity(new Intent(MenusActivity.this, SmartRefRecyclerViewActivity.class)));
        findViewById(R.id.tab2).setOnClickListener(v -> startActivity(new Intent(MenusActivity.this, SmartRefListViewActivity.class)));
        findViewById(R.id.tab3).setOnClickListener(v -> startActivity(new Intent(MenusActivity.this, SmartRefRecyclerDiyAdapterActivity.class)));
        findViewById(R.id.tab4).setOnClickListener(v -> startActivity(new Intent(MenusActivity.this, WeiboPracticeActivity.class)));
        findViewById(R.id.tab5).setOnClickListener(v -> startActivity(new Intent(MenusActivity.this, RealtimeBlurActivity.class)));

    }


}