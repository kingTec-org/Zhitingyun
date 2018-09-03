package com.example.zhitingyun.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhitingyun.R;
import com.example.zhitingyun.model.Confirm;
import com.example.zhitingyun.model.Treatment;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import coder.mylibrary.util.DateUtil;

/**
 * Created by dasiy on 2018/7/7.
 */

public class TreatmentListAdapter extends BaseQuickAdapter<Confirm, BaseViewHolder> {
    public TreatmentListAdapter(@Nullable List<Confirm> data) {
        super(R.layout.item_treatment, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, Confirm item) {
        QMUILinearLayout linearLayout = helper.getView(R.id.linearlayout);
        helper.setText(R.id.tvName, item.getUserName())
        ;
        if (item.getTreatmentType() == 0) {
            switch (item.getStatus()) {
                case 4:
                    helper.setText(R.id.tvStatus, "已诊疗")
                            .setText(R.id.tvTime, DateUtil.format(item.getOrderTimeStart(), "yyyy.MM.dd HH:mm") + "-" + DateUtil.format(item.getOrderTimeEnd(), "HH:mm"))
                    ;
                    break;
                case 5:
                    helper.setText(R.id.tvStatus, "未诊疗")
                            .setText(R.id.tvTime, DateUtil.format(item.getOrderTimeStart(), "yyyy.MM.dd HH:mm") + "-" + DateUtil.format(item.getOrderTimeEnd(), "HH:mm"));
                    break;
            }
        } else {
//            helper.setText(R.id.tvTime,DateUtil.format(item.getEndTime(), "yyyy.MM.dd HH:mm"));
            if (item.getCureTimeStart() != null && item.getCureTimeEnd() != null)
                helper.setText(R.id.tvStatus, "").setText(R.id.tvTime, DateUtil.format(item.getCureTimeStart(), "yyyy.MM.dd HH:mm") + "-" + DateUtil.format(item.getCureTimeEnd(), "HH:mm"));
        }


        linearLayout.setRadius(10);
        helper.getView(R.id.btCheck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOnItemChildClickListener().onItemChildClick(TreatmentListAdapter.this, helper.getView(R.id.btCheck), helper.getLayoutPosition());
            }
        });


    }
}
