package com.example.zhitingyun.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.model.MessageEvent;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.qmuiteam.qmui.layout.QMUILinearLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.util.ToastUtil;

/**
 * Created by dasiy on 2018/7/9.
 */

@SuppressLint("ValidFragment")
public class OrderRefuseFragment extends BaseFragment implements ViewsContainer.OrderRefuseView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.linearlayout)
    QMUILinearLayout linearlayout;
    @BindView(R.id.etReason)
    EditText etReason;
    int id;
    Presenter presenter;

    public OrderRefuseFragment(int id) {
        this.id = id;
    }

    @Override
    protected View onCreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_order_refuse, null);
        ButterKnife.bind(this, view);
        presenter = new Presenter(this);
        linearlayout.setRadius(10);
        tvTitle.setText("回拒预约");
        return view;
    }


    @OnClick({R.id.imgBack, R.id.linearlayout, R.id.btSend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                popBackStack();
                break;
            case R.id.linearlayout:
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    etReason.requestFocus();
                    imm.showSoftInput(etReason, InputMethodManager.SHOW_FORCED);
                }

                break;
            case R.id.btSend:
                if (etReason.getText().toString().equals(""))
                    ToastUtil.showShort("请填写回拒原因");
                else {
                    Map map = new HashMap();
                    map.put("id", id);
                    map.put("refuseReason", etReason.getText().toString());
                    presenter.refuseOrder(map);
                }

                break;
        }
    }

    @Override
    public void refuseOrder() {
        getBaseFragmentActivity().popBackStack(OrderManagerFragment.class);
        EventBus.getDefault().post(new MessageEvent(2));
        popBackStack();


    }
}
