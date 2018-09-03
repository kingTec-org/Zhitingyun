package com.example.zhitingyun.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhitingyun.R;
import com.example.zhitingyun.adapter.OrderListAdapter;
import com.example.zhitingyun.adapter.QuickListAdapter;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.model.Confirm;
import com.example.zhitingyun.model.Order;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dasiy on 2018/7/9.
 */

public class OrderListFragment extends BaseFragment implements ViewsContainer.ConfirmView {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    List<Confirm> orders = new ArrayList<>();
    OrderListAdapter adapter;
    Presenter presenter;


    @Override
    protected View onCreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_order_list, null);
        ButterKnife.bind(this, view);
        presenter = new Presenter(this);
        tvTitle.setText("预约列表");
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new OrderListAdapter(orders);
        recycler.setAdapter(adapter);
        View view1 = View.inflate(getContext(), R.layout.item_empty, null);
        adapter.setEmptyView(view1);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startFragment(new OrderDetailFragment(orders.get(position).getId(), 0));
            }
        });
        getData();
        return view;
    }

    private void getData() {
        Map map = new HashMap();
        map.put("status", 1);
        map.put("startTime", "");
        map.put("endTime", "");
        map.put("pageNo", "");
        map.put("pageSize", "");
        map.put("sort", "");
        map.put("classify", 1);

        presenter.getOrderList(map);
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
    public void getOrderList(List<Confirm> list) {
        if (list != null) {
            orders.clear();
            orders.addAll(list);
            adapter.notifyDataSetChanged();

        }


    }
}
