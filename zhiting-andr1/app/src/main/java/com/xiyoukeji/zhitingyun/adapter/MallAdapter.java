package com.xiyoukeji.zhitingyun.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.constant.Urls;
import com.xiyoukeji.zhitingyun.data.entity.MallEntity;
import com.xiyoukeji.zhitingyun.databinding.ItemMallBinding;


import java.util.List;

public class MallAdapter extends BaseQuickAdapter<MallEntity,BaseViewHolder> {
    public MallAdapter(@Nullable List<MallEntity> data) {
        super( R.layout.item_mall, data );
    }

    @Override
    protected void convert(BaseViewHolder helper, MallEntity item) {
        Glide.with(mContext).load( Urls.BASE_URL + "/"+item.getCoverPic()).into((ImageView) helper.getView(R.id.goods));

        helper.setText( R.id.title,item.getTitle() );

        ItemMallBinding binding= DataBindingUtil.bind( helper.getConvertView() );
        binding.setMall(item  );
        binding.executePendingBindings();

    }
}
