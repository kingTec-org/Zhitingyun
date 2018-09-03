package com.example.zhitingyun.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.model.EquipmentParamater;
import com.example.zhitingyun.model.MessageEvent;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.google.gson.Gson;
import com.qmuiteam.qmui.layout.QMUILinearLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.util.SPUtil;
import coder.mylibrary.util.ToastUtil;

/**
 * Created by dasiy on 2018/7/9.
 */

@SuppressLint("ValidFragment")
public class ProblemFeedbackFragment extends BaseFragment implements ViewsContainer.FeedbackView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.linearlayout)
    QMUILinearLayout linearlayout;
    @BindView(R.id.etReason)
    EditText etReason;
    int id;
    Presenter presenter;
    String content;

    public ProblemFeedbackFragment() {
    }

    @Override
    protected View onCreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_problem_feedback, null);
        ButterKnife.bind(this, view);
        presenter = new Presenter(this);
        imgBack.setVisibility(View.VISIBLE);
        linearlayout.setRadius(10);
        tvTitle.setText("问题反馈");
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
                    ToastUtil.showShort("请填写反馈内容");
                else {
                    content = etReason.getText().toString();
                    Map map = new HashMap();
                    map.put("content", content);
                    presenter.addFeedback(map);

//                    Map map = new HashMap();
//                    map.put("")
                }

                break;
        }
    }


    @Override
    public void addFeedback() {
        ToastUtil.showShort("反馈成功");
        popBackStack();

    }
}
