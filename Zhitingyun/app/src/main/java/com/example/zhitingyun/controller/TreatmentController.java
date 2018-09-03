package com.example.zhitingyun.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhitingyun.R;
import com.example.zhitingyun.adapter.TreatmentListAdapter;
import com.example.zhitingyun.fragment.OrderHistoryFragment;
import com.example.zhitingyun.fragment.TreatmentDetailFragment;
import com.example.zhitingyun.model.Confirm;
import com.example.zhitingyun.model.Treatment;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogBuilder;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dasiy on 2018/7/9.
 */

public class TreatmentController extends MainController implements ViewsContainer.ConfirmView {


    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.imgArrTime)
    ImageView imgArrTime;
    @BindView(R.id.tvState)
    TextView tvState;
    @BindView(R.id.imgArrState)
    ImageView imgArrState;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.llStatus)
    LinearLayout llStatus;
    TreatmentListAdapter adapter;
    Context context;
    List<Confirm> confirms = new ArrayList<>();
    int from;
    Long weekFirstMillion, weekEndMillion, monthFirstMillion, monthEndMillion;
    int timeSelect = 0, statusSelect = 0;
    int page = 1;
    int per_page = 10;
    Presenter presenter;

    public TreatmentController(@NonNull Context context, final int from) {
        super(context);
        this.context = context;
        this.from = from;
        View view = View.inflate(context, R.layout.controller_treatment_common, this);
        ButterKnife.bind(this, view);
        presenter = new Presenter(this);
        imgArrTime.setBackgroundResource(R.mipmap.down_white);
        imgArrState.setBackgroundResource(R.mipmap.down_white);
        tvTime.setText("一周内");
        tvState.setText("已诊疗");
        switch (from) {
            case 0:
                llStatus.setVisibility(View.VISIBLE);
                break;
            case 1:
                llStatus.setVisibility(View.GONE);
                break;
        }
//        list.add(new Treatment("2018.04.06 09:15-10:15", "患者：马", "已诊疗"));
//        list.add(new Treatment("2018.04.06 09:15-10:15", "患者：马", "未诊疗"));
        recycler.setLayoutManager(new LinearLayoutManager(context));
        adapter = new TreatmentListAdapter(confirms);
        adapter.bindToRecyclerView(recycler);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (confirms.get(position).getStatus() == 3 || confirms.get(position).getStatus() == 4) {
                    listener.startFragment(new OrderHistoryFragment(0,from,  confirms.get(position).getId()));
//
//                    switch (from) {
//                        case 0:
//
//                            break;
//                        case 1:
//                            listener.startFragment(new OrderHistoryFragment(0, confirms.get(position).getId()));
//
//                            break;
//                    }
                } else if (confirms.get(position).getStatus() == 5) {
                    listener.startFragment(new TreatmentDetailFragment(confirms.get(position).getId()));
                }

            }
        });
        View view1 = View.inflate(context, R.layout.item_empty, null);
        adapter.setEmptyView(view1);
        getWeekMillions();
        getMonthMillions();
        getData();


    }

    private void getData() {
        Map map = new HashMap();

        switch (timeSelect) {
            case 0:
                map.put("startTime", weekFirstMillion);
                map.put("endTime", weekEndMillion);
                break;
            case 1:
                map.put("startTime", monthFirstMillion);
                map.put("endTime", monthEndMillion);
                break;
            case 2:
                map.put("startTime", "");
                map.put("endTime", "");
                break;
        }
        map.put("pageNo", page);
        map.put("pageSize", per_page);

        switch (from) {
            case 0:
                switch (statusSelect) {
                    case 0:
                        map.put("status", 4);
                        break;
                    case 1:
                        map.put("status", 5);
                        break;
                    case 2:
                        break;

                }
                map.put("classify", 2);
                presenter.getOrderList(map);

                break;
            case 1:
                presenter.getQuickOrderList(map);
                break;
        }


    }

    private void getWeekMillions() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        weekFirstMillion = cal.getTimeInMillis();
        weekEndMillion = weekFirstMillion + 24 * 60 * 60 * 7 * 1000;
        Log.d("sss", weekFirstMillion + "");
        Log.d("sss", weekEndMillion + "");


    }

    private void getMonthMillions() {
        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        monthFirstMillion = cal.getTimeInMillis();


        cal.add(Calendar.MONTH, 1);
        monthEndMillion = cal.getTimeInMillis();
        Log.d("sss", monthFirstMillion + "");
        Log.d("sss", monthEndMillion + "");


    }


    @Override
    protected String getTitle() {
        return null;
    }

    @OnClick({R.id.llArrTime, R.id.llArrState})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llArrTime:
                imgArrTime.setBackgroundResource(R.mipmap.up_white);
                final String[] items = new String[]{"一周内", "一个月内", "所有"};

                QMUIDialog dialog = new QMUIDialog.MenuDialogBuilder(context)
                        .addItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                tvTime.setText(items[which]);
                                timeSelect = which;
                                getData();

                            }
                        })
                        .show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        imgArrTime.setBackgroundResource(R.mipmap.down_white);
                    }
                });


                break;
            case R.id.llArrState:
                imgArrState.setBackgroundResource(R.mipmap.up_white);
                final String[] _items = new String[]{"已诊疗", "未诊疗", "所有"};
                QMUIDialog dialog1 = new QMUIDialog.MenuDialogBuilder(context)
                        .addItems(_items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                tvState.setText(_items[which]);
                                statusSelect = which;
                                getData();

                            }
                        })
                        .show();
                dialog1.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        imgArrState.setBackgroundResource(R.mipmap.down_white);

                    }
                });

                break;
        }
    }

    @Override
    public void getOrderList(List<Confirm> list) {
        if (page == 1)
            confirms.clear();
        if(list!=null){
            for (int i = 0; i < list.size(); i++) {
                Confirm confirm = list.get(i);
                if (from == 0)
                    confirm.setTreatmentType(0);
                else if (from == 1)
                    confirm.setTreatmentType(1);
                confirms.add(confirm);
            }
        }

        adapter.notifyDataSetChanged();

    }

}
