package com.xiyoukeji.zhitingyun.view.main;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.base.BaseFragment;

public class ExpertDateRecordFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_date_record;
    }

    public static Fragment newInstance(){return new ExpertDateRecordFragment();}

    @Override
    protected void initView(View view){
        super.initView( view );

        View emptyView= LayoutInflater.from( mContext ).inflate( R.layout.empty_view_appointment, null );

    }
}
