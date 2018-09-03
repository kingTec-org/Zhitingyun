package com.example.zhitingyun.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhitingyun.R;
import com.example.zhitingyun.adapter.ConfirmListAdapter;
import com.example.zhitingyun.controller.MainController;
import com.example.zhitingyun.fragment.OrderDetailFragment;
import com.example.zhitingyun.model.Confirm;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import c.springviewlibrary.container.AliFooter;
import c.springviewlibrary.container.AliHeader;
import c.springviewlibrary.widget.SpringView;

/**
 * Created by dasiy on 2018/7/7.
 */

public class ConfirmController extends MainController implements ViewsContainer.ConfirmView {
    @BindView(R.id.springview)
    SpringView springView;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    int from;
    List<Confirm> confirms = new ArrayList<>();
    ConfirmListAdapter adapter;
    int page = 1;
    int per_page = 10;
    Presenter presenter;

    public void initData() {
        page = 1;
        getData();
    }


    public ConfirmController(@NonNull Context context, final int from) {
        super(context);
        this.from = from;
        View view = LayoutInflater.from(context).inflate(R.layout.controller_confirm, this);
        ButterKnife.bind(this, view);
        presenter = new Presenter(this);
        recycler.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ConfirmListAdapter(confirms);
        adapter.bindToRecyclerView(recycler);
        View empty = View.inflate(context, R.layout.item_empty, null);
        adapter.setEmptyView(empty);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                listener.startFragment(new OrderDetailFragment(confirms.get(position).getId(), from));
            }
        });
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getData();
            }

            @Override
            public void onLoadmore() {
                page++;
                getData();
            }
        });

        springView.setHeader(new AliHeader(getContext(), false));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new AliFooter(getContext(), false));
        getData();
    }

    private void getData() {
        Map map = new HashMap();
        map.put("status", from);
        map.put("startTime", "");
        map.put("endTime", "");
        map.put("pageNo", page);
        map.put("pageSize", per_page);
        map.put("sort", "");
        map.put("classify", 1);

        presenter.getOrderList(map);


    }

    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    public void getOrderList(List<Confirm> list) {
        springView.onFinishFreshAndLoad();
        if (page == 1)
            confirms.clear();
        if (list != null) {
            confirms.addAll(list);
            if (from == 1) {
                for (int i = 0; i < confirms.size(); i++) {
                    Confirm confirm = confirms.get(i);
                    confirm.setCurrentMillion(System.currentTimeMillis());
                    confirms.set(i, confirm);
                }
            }
        }

        adapter.notifyDataSetChanged();

    }
}
