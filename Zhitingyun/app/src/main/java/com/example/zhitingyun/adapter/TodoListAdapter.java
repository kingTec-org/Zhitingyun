package com.example.zhitingyun.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhitingyun.R;
import com.example.zhitingyun.model.Confirm;
import com.example.zhitingyun.model.Quick;
import com.example.zhitingyun.model.Todo;

import java.util.List;

import coder.mylibrary.util.DateUtil;

/**
 * Created by dasiy on 2018/7/7.
 */

public class TodoListAdapter extends BaseQuickAdapter<Confirm, BaseViewHolder> {
    public TodoListAdapter(@Nullable List<Confirm> data) {
        super(R.layout.item_todo, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, Confirm item) {
        if (item.getId() != null)
            helper.setText(R.id.tvContent, DateUtil.format(item.getOrderTimeStart(), "HH:mm") + "  " + "患者" + item.getUserName() + "预约诊疗");
        else
            helper.setText(R.id.tvContent, item.getUserName());


    }
}
