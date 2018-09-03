package com.example.zhitingyun.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhitingyun.R;
import com.example.zhitingyun.adapter.NewsListAdapter;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.model.News;
import com.example.zhitingyun.model.NewsDetail;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.util.ToastUtil;

/**
 * Created by dasiy on 2018/7/21.
 */

public class NewsListFragment extends BaseFragment implements ViewsContainer.NewsListView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvFunction)
    TextView tvFunction;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Presenter presenter;
    List<News> news = new ArrayList<>();
    NewsListAdapter adapter;

    int pos;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_news_list, null);
        ButterKnife.bind(this, view);
        tvTitle.setText("消息");
        tvFunction.setText("全部已读");
        tvFunction.setVisibility(View.VISIBLE);
        presenter = new Presenter(this);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsListAdapter(news);
        adapter.bindToRecyclerView(recycler);
        View footer = View.inflate(getContext(), R.layout.item_more, null);
        adapter.setFooterView(footer);
        TextView tvMore = footer.findViewById(R.id.tvMore);
        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();

            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                pos = position;
                switch (news.get(position).getNewsType()) {
                    case 1:
                        Map map = new HashMap();
                        map.put("newsId", news.get(position).getNewsId());
                        map.put("newsType", news.get(position).getNewsType());
                        presenter.readNews(map, 1);
                        break;
                    case 2:
                        startFragment(new NewsDetailFragment(news.get(position)));
                        break;
                    case 3:
                        startFragment(new NewsDetailFragment(news.get(position)));
                        break;
                }
            }
        });
        initData();
        return view;
    }

    private void initData() {
        Map map = new HashMap();
        if (news.size() == 0)
            map.put("lastTime", System.currentTimeMillis());
        else
            map.put("lastTime", news.get(news.size() - 1).getCreateTime());
        presenter.getNewsList(map);


    }


    @OnClick({R.id.imgBack, R.id.tvFunction})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                popBackStack();
                break;
            case R.id.tvFunction:
                presenter.readAll(new HashMap());

                break;

        }
    }

    @Override
    public void getNewsList(List<News> list) {
        if (list != null) {
            news.addAll(list);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void readNews(NewsDetail newsDetail) {
        startFragment(new OrderDetailFragment(news.get(pos).getNewsId(), 0));

    }

    @Override
    public void readAll() {
        ToastUtil.showShort("标记全部已读成功");

    }
}
