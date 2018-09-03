package com.example.zhitingyun.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.model.News;
import com.example.zhitingyun.model.NewsDetail;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.google.gson.Gson;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xrichtext.XRichText;
import coder.mylibrary.base.ProjectConfig;
import coder.mylibrary.util.DateUtil;
import coder.mylibrary.util.ToastUtil;

/**
 * Created by dasiy on 2018/7/9.
 */

@SuppressLint("ValidFragment")
public class NewsDetailFragment extends BaseFragment implements ViewsContainer.NewsDetailView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.tvTop)
    TextView tvTop;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvFeedbackContent)
    TextView tvFeedbackContent;
    @BindView(R.id.tvFeedbackReply)
    TextView tvFeedbackReply;

    @BindView(R.id.llPlatform)
    LinearLayout llPlatform;
    @BindView(R.id.llFeedback)
    LinearLayout llFeedback;

    @BindView(R.id.imgContent)
    ImageView imgContent;
    @BindView(R.id.tvText)
    XRichText tvText;
    News news;
    Presenter presenter;

    public NewsDetailFragment(News news) {
        this.news = news;
    }

    @Override
    protected View onCreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_news_detail, null);
        ButterKnife.bind(this, view);
        tvTitle.setText("消息详情");
        presenter = new Presenter(this);

        Map map = new HashMap();
        map.put("newsId", news.getNewsId());
        map.put("newsType", news.getNewsType());
        presenter.readNews(map, 0);

        return view;
    }


    @OnClick({R.id.imgBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                popBackStack();
                break;

        }
    }


    @Override
    public void readNews(NewsDetail newsDetail) {
        switch (news.getNewsType()) {
            case 2://反馈
                llFeedback.setVisibility(View.VISIBLE);
                llPlatform.setVisibility(View.GONE);

                tvFeedbackContent.setText("反馈内容：" + newsDetail.getContent());
                tvFeedbackReply.setText("回复内容：" + newsDetail.getReply());

                break;
            case 3://平台
                llFeedback.setVisibility(View.GONE);
                llPlatform.setVisibility(View.VISIBLE);

                tvTop.setText(newsDetail.getTitle());
                tvTime.setText(DateUtil.format(newsDetail.getLastPushTime(), "yyyy-MM-dd HH:mm"));
                if (newsDetail.getCoverPic() != null)
                    Picasso.with(getContext())
                            .load(ProjectConfig.BASE_URL + "/" + newsDetail.getCoverPic())
                            .fit()
                            .error(R.mipmap.treatting_bg)           //设置错误图片
                            .placeholder(R.mipmap.treatting_bg)
                            .into(imgContent);
                else
                    imgContent.setVisibility(View.GONE);
                tvText.text(newsDetail.getRichText());
                break;
        }


    }
}
