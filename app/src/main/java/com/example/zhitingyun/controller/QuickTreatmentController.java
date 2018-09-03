package com.example.zhitingyun.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhitingyun.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dasiy on 2018/7/9.
 */

public class QuickTreatmentController extends MainController {

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

    public QuickTreatmentController(@NonNull Context context) {
        super(context);
        View view = View.inflate(context, R.layout.controller_treatment_common, null);
        ButterKnife.bind(this, view);
    }

    @Override
    protected String getTitle() {
        return null;
    }

    @OnClick({R.id.llArrTime, R.id.llArrState})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llArrTime:
                break;
            case R.id.llArrState:
                break;
        }
    }
}
