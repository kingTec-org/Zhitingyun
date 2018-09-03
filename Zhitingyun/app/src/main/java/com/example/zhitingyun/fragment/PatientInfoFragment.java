package com.example.zhitingyun.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.model.OrderDetail;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.base.ProjectConfig;
import coder.mylibrary.util.ToastUtil;

/**
 * Created by dasiy on 2018/7/10.
 */

@SuppressLint("ValidFragment")
public class PatientInfoFragment extends BaseFragment {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.imgAvatar)
    ImageView imgAvatar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvSex)
    TextView tvSex;
    @BindView(R.id.tvAge)
    TextView tvAge;
    @BindView(R.id.tvExperience)
    TextView tvExperience;
    @BindView(R.id.tvTimes)
    TextView tvTimes;
    @BindView(R.id.linearlayout)
    QMUILinearLayout linearlayout;
    @BindView(R.id.linearlayout2)
    QMUILinearLayout linearlayout2;
    @BindView(R.id.linearlayout3)
    QMUILinearLayout linearlayout3;
    @BindView(R.id.linearlayout4)
    QMUILinearLayout linearlayout4;
    OrderDetail orderDetail;

    public PatientInfoFragment(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    protected View onCreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_patient_info, null);
        ButterKnife.bind(this, view);
        linearlayout.setRadius(10);
        linearlayout2.setRadius(10);
        linearlayout3.setRadius(10);
        linearlayout4.setRadius(10);
        tvTitle.setText("患者资料");
        Picasso.with(getContext())
                .load(ProjectConfig.BASE_URL + "/" + orderDetail.getUserDto().getHeadPic())
                .resize(100, 100)
                .error(R.mipmap.ic_launcher)           //设置错误图片
                .placeholder(R.mipmap.ic_launcher)
                .into(imgAvatar);
        if (orderDetail.getUserDto().getSex() != null) {
            switch (orderDetail.getUserDto().getSex()) {
                case 2:
                    tvSex.setText("女");

                    break;
                case 1:
                    tvSex.setText("男");

                    break;
            }
        }

        if (orderDetail.getUserDto().getAge() != null)
            tvAge.setText(orderDetail.getUserDto().getAge() + "岁");
        if (orderDetail.getUserDto().getWearTimeEnum() != null)
            tvExperience.setText(orderDetail.getUserDto().getWearTimeEnum() + "个月");
        if (orderDetail.getUserDto().getCureCount() != null)
            tvTimes.setText(orderDetail.getUserDto().getCureCount() + "次");
        return view;
    }

    @OnClick({R.id.imgBack, R.id.linearlayout2, R.id.linearlayout3, R.id.linearlayout4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                popBackStack();
                break;
            case R.id.linearlayout2:
                startFragment(new DetailHearingFragment(orderDetail.getUserNewestRecord()));
                break;
            case R.id.linearlayout3:
                if (orderDetail.getEquParamBefore() != null)
                    startFragment(new DetailEquipmentFragment(orderDetail.getEquParamBefore()));
                else
                    ToastUtil.showShort("无设备参数");
                break;
            case R.id.linearlayout4:
                if (orderDetail.getHistoryOrderId() != null) {
                    startFragment(new OrderHistoryFragment(1,orderDetail.getHistoryOrderType() - 1, orderDetail.getHistoryOrderId()));
                } else
                    ToastUtil.showShort("无历史诊疗报告");

//
                break;
        }
    }
}
