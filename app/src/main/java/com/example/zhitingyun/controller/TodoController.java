package com.example.zhitingyun.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.adapter.QuickListAdapter;
import com.example.zhitingyun.adapter.TodoListAdapter;
import com.example.zhitingyun.model.Confirm;
import com.example.zhitingyun.model.HomePage;
import com.example.zhitingyun.model.Quick;
import com.example.zhitingyun.model.Todo;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.qmuiteam.qmui.layout.QMUILinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import c.springviewlibrary.container.AliHeader;
import c.springviewlibrary.widget.SpringView;

import static com.qmuiteam.qmui.layout.IQMUILayout.HIDE_RADIUS_SIDE_TOP;

/**
 * Created by dasiy on 2018/7/7.
 */

public class TodoController extends MainController implements ViewsContainer.TodoView {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.springview)
    SpringView springView;
    @BindView(R.id.linearlayout0)
    QMUILinearLayout linearlayout0;
    TodoListAdapter adapter;
    List<Confirm> todos = new ArrayList<>();
    Context context;
    Presenter presenter;

    public TodoController(@NonNull Context context) {
        super(context);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.controller_quick, this);
        ButterKnife.bind(this, view);
        presenter = new Presenter(this);
        linearlayout0.setRadius(10, HIDE_RADIUS_SIDE_TOP);
        recycler.setLayoutManager(new LinearLayoutManager(context));
        adapter = new TodoListAdapter(todos);
        recycler.setAdapter(adapter);
        View emptyView = View.inflate(getContext(), R.layout.item_todo_empty, null);
        adapter.setEmptyView(emptyView);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                page = 1;
                getData();
            }

            @Override
            public void onLoadmore() {
//                page++;
                getData();
            }
        });

        springView.setHeader(new AliHeader(getContext(), false));
        getData();

    }

    private void getData() {
        presenter.homePage(new HashMap());

    }

    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    public void homePage(HomePage homePage) {
        todos.clear();
        if (homePage.getOrderListDtos() != null)
            todos.addAll(homePage.getOrderListDtos());
        if (homePage.getRemind() != null) {
            Confirm confirm = new Confirm();
            confirm.setUserName(homePage.getRemind());
            todos.add(confirm);
        }

        adapter.notifyDataSetChanged();

    }
}
