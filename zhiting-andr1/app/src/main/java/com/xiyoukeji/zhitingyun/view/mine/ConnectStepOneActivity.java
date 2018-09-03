package com.xiyoukeji.zhitingyun.view.mine;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ConnectStepOneActivity extends BaseActivity {
    @BindView( R.id.next )
    Button btn_next;
    public static ConnectStepOneActivity instance;

    public ConnectStepOneActivity() {
        super( R.layout.activity_stepone );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        instance=this;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR );
            StatusBarUtil.setStatusBarColor( this, R.color.white );
        }
    }

    @OnClick({R.id.next,R.id.back_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.next:
                startActivity( ConnectStepTwoActivity.class );
                break;
            case R.id.back_layout:
                finish();
                break;
        }
    }
}
