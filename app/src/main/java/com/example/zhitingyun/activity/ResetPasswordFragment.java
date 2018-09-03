package com.example.zhitingyun.activity;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.fragment.LoginFragment;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import coder.mylibrary.manager.ActivityManager;
import coder.mylibrary.util.ToastUtil;

/**
 * Created by dasiy on 2018/7/7.
 */

@SuppressLint("ValidFragment")
public class ResetPasswordFragment extends BaseFragment implements ViewsContainer.ResetPasswordView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etConfirm)
    EditText etConfirm;
    String code, phone;
    Presenter presenter;

    public ResetPasswordFragment(String phone, String code) {
        this.code = code;
        this.phone = phone;
    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_reset_password, null);
        QMUIStatusBarHelper.setStatusBarLightMode(ActivityManager.getInstance().currentActivity());
        ButterKnife.bind(this, view);
        tvTitle.setText("设置密码");
        presenter = new Presenter(this);
        return view;
    }


    @OnClick({R.id.imgBack, R.id.btLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                popBackStack();
                break;
            case R.id.btLogin:
                if (etPassword.getText().toString().equals("") || etConfirm.getText().toString().equals(""))
                    ToastUtil.showShort("将信息填写完整");
                else if (!etPassword.getText().toString().equals(etConfirm.getText().toString()))
                    ToastUtil.showShort("两次密码输入不一致");
                else {
                    Map map = new HashMap();
                    map.put("phone", phone);
                    map.put("code", code);
                    map.put("newPassword", etPassword.getText().toString());
                    presenter.resetPassword(map);
                }

                break;
        }
    }

    @Override
    public void resetPassword() {
        ((QMUIFragmentActivity) getActivity()).popBackStack(LoginFragment.class);


    }
}
