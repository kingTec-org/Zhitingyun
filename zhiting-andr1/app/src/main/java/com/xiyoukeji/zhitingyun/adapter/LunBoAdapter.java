package com.xiyoukeji.zhitingyun.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.constant.Urls;
import com.xiyoukeji.zhitingyun.data.entity.LunBoEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;

import java.util.List;

public class LunBoAdapter extends StaticPagerAdapter {
    private Context mContext;
    private List<LunBoEntity>list;

    public LunBoAdapter(Context context,List<LunBoEntity>list){
        this.mContext=context;
        this.list=list;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        Glide.with(mContext).load( Urls.BASE_URL + "/"+list.get( position ).getCoverPic()).into(view);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
