package com.example.zhitingyun.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhitingyun.R;
import com.example.zhitingyun.model.Confirm;
import com.example.zhitingyun.model.Schedual;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dasiy on 2018/7/7.
 */

public class SchedualListAdapter extends BaseQuickAdapter<Schedual, BaseViewHolder> {
    public SchedualListAdapter(@Nullable List<Schedual> data) {
        super(R.layout.item_schedual, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, Schedual item) {
        TextView tvDate = helper.getView(R.id.tvDate);
        switch (item.getStatus()) {
            case 0://今天
                tvDate.setText("今");
                tvDate.setTextColor(ContextCompat.getColor(mContext, R.color.colorGray33));
                tvDate.setBackground(null);
                break;
            case 1://有排班
                tvDate.setText(item.getDate() + "");
                tvDate.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                tvDate.setBackgroundResource(R.drawable.shape_blue_oval_stroke);
                break;
            case 2://选中
                tvDate.setText(item.getDate() + "");
                tvDate.setTextColor(ContextCompat.getColor(mContext, R.color.colorWhite));
                tvDate.setBackgroundResource(R.drawable.shape_blue_oval_solid);
                break;
            case 3:
                tvDate.setText(item.getDate() + "");
                tvDate.setTextColor(ContextCompat.getColor(mContext, R.color.colorGray33));
                tvDate.setBackground(null);
                break;
            case 4://选中今天
                tvDate.setText("今");
                tvDate.setTextColor(ContextCompat.getColor(mContext, R.color.colorWhite));
                tvDate.setBackgroundResource(R.drawable.shape_blue_oval_solid);
                break;
            case 5://排班今天
                tvDate.setText("今");
                tvDate.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                tvDate.setBackgroundResource(R.drawable.shape_blue_oval_stroke);
                break;
        }


    }
}
