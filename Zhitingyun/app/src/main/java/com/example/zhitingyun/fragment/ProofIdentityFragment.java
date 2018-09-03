package com.example.zhitingyun.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.util.SPUtil;
import coder.mylibrary.util.ToastUtil;

/**
 * Created by dasiy on 2018/7/10.
 */

public class ProofIdentityFragment extends BaseFragment {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etPassword)
    EditText etPassword;

    @Override
    protected View onCreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_proof_identity, null);
        ButterKnife.bind(this, view);
        tvTitle.setText("验证身份");
        return view;
    }


    @OnClick({R.id.imgBack, R.id.btNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                popBackStack();
                break;
            case R.id.btNext:
                if (etPassword.getText().toString().equals(""))
                    ToastUtil.showShort("请填写密码");
                else if (!etPassword.getText().toString().equals(SPUtil.get(getContext(), "password", "").toString())) {
                    ToastUtil.showShort("密码不正确");
                } else
                    startFragment(new ChangePhoneFragment());
                break;
        }
    }
}
