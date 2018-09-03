package com.xiyoukeji.zhitingyun.view.mine;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ConnectStepTwoActivity extends BaseActivity {
    @BindView( R.id.next )
    Button btn_next;

    public static ConnectStepTwoActivity instance;

    public ConnectStepTwoActivity() {
        super( R.layout.activity_step2 );
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

    @OnClick({R.id.next, R.id.bluetooth,R.id.back_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
            case R.id.next:
                startActivity( ConnectDeviceActivity.class );
                break;
            case R.id.bluetooth:
                startActivity(new Intent( Settings.ACTION_BLUETOOTH_SETTINGS));
                break;
        }
    }
}
