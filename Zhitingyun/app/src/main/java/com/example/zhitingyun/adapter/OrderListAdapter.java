package com.example.zhitingyun.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhitingyun.R;
import com.example.zhitingyun.model.Confirm;
import com.example.zhitingyun.model.Order;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import coder.mylibrary.util.DateUtil;

/**
 * Created by dasiy on 2018/7/7.
 */

public class OrderListAdapter extends BaseQuickAdapter<Confirm, BaseViewHolder> {
    public OrderListAdapter(@Nullable List<Confirm> data) {
        super(R.layout.item_order, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, Confirm item) {
        QMUILinearLayout linearLayout = helper.getView(R.id.linearlayout);
        helper.setText(R.id.tvTime, DateUtil.format(item.getOrderTimeStart(), "yyyy-MM-dd HH:mm") + "-" + DateUtil.format(item.getOrderTimeEnd(), "HH:mm")).setText(R.id.tvName, "患者：" + item.getUserName());
        linearLayout.setRadius(10);
        if (item.getReturnVisit())
            helper.setText(R.id.tvStatus, "复诊");
        else
            helper.setText(R.id.tvStatus, "");

        helper.getView(R.id.btCheck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOnItemChildClickListener().onItemChildClick(OrderListAdapter.this, helper.getView(R.id.btCheck), helper.getLayoutPosition());
            }
        });


    }
}
