package com.example.zhitingyun.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhitingyun.R;
import com.example.zhitingyun.model.Schedual;
import com.example.zhitingyun.model.SchedualItem;

import java.util.List;

import coder.mylibrary.util.DateUtil;

/**
 * Created by dasiy on 2018/7/7.
 */

public class SchedualItemAdapter extends BaseQuickAdapter<SchedualItem, BaseViewHolder> {
    public SchedualItemAdapter(@Nullable List<SchedualItem> data) {
        super(R.layout.item_schdual_item, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, SchedualItem item) {
        TextView tvContent = helper.getView(R.id.tvContent);

        if (item.getStart() != null && item.getEnd() != null) {
            String endTime = DateUtil.getHM(item.getEnd());
            if (DateUtil.getHM(item.getEnd()).startsWith("00"))
                endTime = "00:" + DateUtil.getHM(item.getEnd()).split(":")[1];
            helper.setText(R.id.tvContent, DateUtil.getHM(item.getStart()) + "-" + endTime);

        } else
            helper.setText(R.id.tvContent, "");


        switch (item.getStatus()) {
            case 2://红色有预约
                tvContent.setBackgroundResource(R.mipmap.schedual_bg_red);
                tvContent.setTextColor(ContextCompat.getColor(mContext, R.color.colorRedff));
                break;
            case 1://蓝色灭有预约
                tvContent.setBackgroundResource(R.mipmap.schedual_bg_blue);
                tvContent.setTextColor(ContextCompat.getColor(mContext, R.color.colorGray33));
                break;
            case 3://灰色没有预约不接受预约
                tvContent.setBackgroundResource(R.drawable.shape_gray_rectangle_stroke);
                tvContent.setTextColor(ContextCompat.getColor(mContext, R.color.colorGray99));
                break;
            case 4:
                tvContent.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorWhite));
//                tvContent.setBackgroundResource(R.drawable.shape_gray_rectangle_stroke);
//                tvContent.setTextColor(ContextCompat.getColor(mContext, R.color.colorGray99));
                break;
        }


    }
}
