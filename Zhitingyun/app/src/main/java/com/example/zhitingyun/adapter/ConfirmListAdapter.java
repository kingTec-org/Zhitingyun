package com.example.zhitingyun.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhitingyun.R;
import com.example.zhitingyun.model.Confirm;
import com.example.zhitingyun.model.Quick;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import coder.mylibrary.base.ProjectConfig;
import coder.mylibrary.util.DateUtil;

/**
 * Created by dasiy on 2018/7/7.
 */

public class ConfirmListAdapter extends BaseQuickAdapter<Confirm, BaseViewHolder> {
    public ConfirmListAdapter(@Nullable List<Confirm> data) {
        super(R.layout.item_confirm, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, Confirm item) {
        QMUILinearLayout linearLayout = helper.getView(R.id.linearlayout);
        String returnVisit = "";
        if (item.getReturnVisit())
            returnVisit = "复诊";
        if (item.getCurrentMillion() == null || item.getCurrentMillion() < item.getOrderTimeStart()) {
            helper.setText(R.id.tvWait, "");
        } else
            helper.setText(R.id.tvWait, "已等待" + (item.getCurrentMillion() - item.getOrderTimeStart()) / 60000 + "分钟");

        helper.setText(R.id.tvTime, DateUtil.getSecond(item.getOrderTimeStart()) + "-" + DateUtil.getOnlyMillon(item.getOrderTimeEnd())).setText(R.id.tvStatus, returnVisit).setText(R.id.tvName, item.getUserName());
        linearLayout.setRadius(10);
        Picasso.with(mContext)
                .load(ProjectConfig.BASE_URL + "/" + item.getUserHeadPic())
                .resize(100, 100)
                .error(R.mipmap.ic_launcher)           //设置错误图片
                .placeholder(R.mipmap.ic_launcher)
                .into((ImageView) helper.getView(R.id.imgAvatar));


    }
}
