package com.example.zhitingyun.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.activity.LoginActivity;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.model.ProfessorInfo;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.example.zhitingyun.widget.WS;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.util.SPUtil;

/**
 * Created by dasiy on 2018/7/10.
 */

public class AccountManageFragment extends BaseFragment implements ViewsContainer.AccountManageView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.llPassword)
    LinearLayout llPassword;
    Presenter presenter;

    @Override
    protected View onCreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_account_manage, null);
        ButterKnife.bind(this, view);
        presenter = new Presenter(this);
        tvTitle.setText("账户管理");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        presenter.getInfo(new HashMap(), 2);

    }


    @OnClick({R.id.imgBack, R.id.llPhone, R.id.llPassword, R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                popBackStack();

                break;
            case R.id.llPhone:
                startFragment(new ProofIdentityFragment());
                break;
            case R.id.llPassword:
                startFragment(new SetPasswordFragment());

                break;
            case R.id.btLogout:
                presenter.logout(new HashMap());

                break;
        }
    }


    @Override
    public void logout() {
        SPUtil.put(getContext(), "userId", "");
        SPUtil.put(getContext(), "accid", "");
        SPUtil.put(getContext(), "imToken", "");
        SPUtil.put(getContext(), "token", "");
        SPUtil.put(getContext(), "password", "");
        WS.getInStanceBlock().disConnect();
        getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();

    }

    @Override
    public void getInfo(ProfessorInfo professorInfo) {
        tvPhone.setText(professorInfo.getPhone());


    }
}
