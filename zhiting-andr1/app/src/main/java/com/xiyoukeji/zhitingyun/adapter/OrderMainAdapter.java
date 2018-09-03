package com.xiyoukeji.zhitingyun.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiyoukeji.zhitingyun.R;

import java.util.List;

/**
 * Created by dasiy on 2018/7/5.
 */

public class OrderMainAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    Context context;

    public OrderMainAdapter(FragmentManager fm, List<Fragment> fragmentList, Context context) {
        super(fm);
        this.fragmentList = fragmentList;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public View getTabView(int position) {
        View view = null;
        // 我这里因为有4个标签
        if (position == 0) {
            view = View.inflate(context, R.layout.item_tab0, null);
        } else if (position == 1) {
            view = View.inflate(context, R.layout.item_tab1, null);
        }

        TextView tv = (TextView) view.findViewById(R.id.textview);
        return view;
    }


}
