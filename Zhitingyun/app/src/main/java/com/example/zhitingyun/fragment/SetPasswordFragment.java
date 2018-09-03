package com.example.zhitingyun.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.util.ToastUtil;

/**
 * Created by dasiy on 2018/7/10.
 */

public class SetPasswordFragment extends BaseFragment implements ViewsContainer.ChangePasswordView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etOldPassword)
    EditText etOldPassword;
    @BindView(R.id.etNewPassword)
    EditText etNewPassword;
    @BindView(R.id.etConfirm)
    EditText etConfirm;
    Presenter presenter;

    @Override
    protected View onCreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_set_password, null);
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
                if (etOldPassword.getText().toString().equals("") || etNewPassword.getText().toString().equals("") || etConfirm.getText().toString().equals(""))
                    ToastUtil.showShort("将信息填写完整");
                else if (!etNewPassword.getText().toString().equals(etConfirm.getText().toString()))
                    ToastUtil.showShort("两次密码输入不一致");
                else {
                    Map map = new HashMap();
                    map.put("oldPassword", etOldPassword.getText().toString());
                    map.put("newPassword", etNewPassword.getText().toString());
                    presenter.changePassword(map);

                }
                break;
        }
    }

    @Override
    public void changePassword() {
        ToastUtil.showShort("修改成功");
        popBackStack();

    }
}
