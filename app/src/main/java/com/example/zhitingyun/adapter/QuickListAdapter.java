package com.example.zhitingyun.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhitingyun.R;
import com.example.zhitingyun.model.Quick;

import java.util.List;

/**
 * Created by dasiy on 2018/7/7.
 */

public class QuickListAdapter extends BaseQuickAdapter<Quick, BaseViewHolder> {
    public QuickListAdapter(@Nullable List<Quick> data) {
        super(R.layout.item_quick, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, Quick item) {
        String name = "";
        if (item.getUserName() != null)
            name = item.getUserName();
        else
            name = "未知用户";

        helper.setText(R.id.tvContent, name + "发起快速诊疗");
        helper.getView(R.id.btAnswer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOnItemChildClickListener().onItemChildClick(QuickListAdapter.this, helper.getView(R.id.btAnswer), helper.getLayoutPosition());
            }
        });


    }
}
