package com.example.zhitingyun.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.model.OrderDetail;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.qmuiteam.qmui.layout.QMUILinearLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.util.DateUtil;

/**
 * Created by dasiy on 2018/7/10.
 */

@SuppressLint("ValidFragment")
public class TreatmentDetailFragment extends BaseFragment implements ViewsContainer.OrderHistoryView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvTreatmentTime)
    TextView tvTreatmentTime;
    @BindView(R.id.tvReportTime)
    TextView tvReportTime;
    @BindView(R.id.tvExpert)
    TextView tvExpert;
    @BindView(R.id.tvPatient)
    TextView tvPatient;
    @BindView(R.id.linearlayout)
    QMUILinearLayout linearlayout;
    @BindView(R.id.tvReason)
    TextView tvReason;
    @BindView(R.id.linearlayout0)
    QMUILinearLayout linearlayout0;
    private int id;
    Presenter presenter;

    public TreatmentDetailFragment(int id) {
        this.id = id;
    }

    @Override
    protected View onCreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_treatment_detail, null);
        ButterKnife.bind(this, view);
        presenter = new Presenter( this );
        tvTitle.setText("未诊疗详情");
        linearlayout.setRadius(10);
        linearlayout0.setRadius(10);

        getData();
        return view;
    }

    private void getData() {
        Map map = new HashMap();
        map.put("id", id);
        presenter.getOrder(map, 1);
    }

    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        popBackStack();
    }

    @Override
    public void getOrder(OrderDetail orderDetail) {
        tvTreatmentTime.setText("预约时间：" + DateUtil.format(orderDetail.getOrderTimeStart(), "yyyy.MM.dd HH:mm") + "-" + DateUtil.format(orderDetail.getOrderTimeEnd(), "HH:mm"));
        tvReportTime.setText("取消诊疗时间：");
        tvExpert.setText("接诊专家：");
        tvPatient.setText("患者：");
        tvReason.setText("用户未接通");

    }


}
