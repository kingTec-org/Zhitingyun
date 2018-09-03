package com.example.zhitingyun.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhitingyun.R;
import com.example.zhitingyun.model.Confirm;
import com.example.zhitingyun.model.News;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import coder.mylibrary.base.ProjectConfig;
import coder.mylibrary.util.DateUtil;

/**
 * Created by dasiy on 2018/7/7.
 */

public class NewsListAdapter extends BaseQuickAdapter<News, BaseViewHolder> {
    public NewsListAdapter(@Nullable List<News> data) {
        super(R.layout.item_news, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, News item) {
        QMUILinearLayout linearLayout = helper.getView(R.id.linearlayout);
        linearLayout.setRadius(20);
        ImageView imgContent = helper.getView(R.id.imgContent);
        TextView tvContent = helper.getView(R.id.tvContent);
        helper.setText(R.id.tvTitle, item.getTitle());
        if (item.getNewsType() == 3) {
            imgContent.setVisibility(View.VISIBLE);
            tvContent.setVisibility(View.GONE);
            Picasso.with(mContext)
                    .load(ProjectConfig.BASE_URL + "/" + item.getCoverPic())
                    .fit()
                    .error(R.mipmap.treatting_bg)           //设置错误图片
                    .placeholder(R.mipmap.treatting_bg)
                    .into(imgContent);

        } else {
            imgContent.setVisibility(View.GONE);
            tvContent.setVisibility(View.VISIBLE);
            helper.setText(R.id.tvContent, item.getContent());
        }
        helper.setText(R.id.tvTitle, item.getTitle()).setText(R.id.tvContent, item.getContent()).setText(R.id.tvTime, DateUtil.format(item.getCreateTime(), "yyyy-MM-dd HH:mm"));
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOnItemChildClickListener().onItemChildClick(NewsListAdapter.this, view, helper.getLayoutPosition());
            }
        });

    }
}
