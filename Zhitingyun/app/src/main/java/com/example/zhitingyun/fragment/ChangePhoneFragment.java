package com.example.zhitingyun.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.activity.ResetPasswordFragment;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.manager.ActivityManager;
import coder.mylibrary.util.RxUtils;
import coder.mylibrary.util.SPUtil;
import coder.mylibrary.util.ToastUtil;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by dasiy on 2018/7/7.
 */

public class ChangePhoneFragment extends BaseFragment implements ViewsContainer.ChangePhoneView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.btCode)
    QMUIRoundButton btCode;
    Presenter presenter;

    Subscription subscription;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_change_phone, null);
        QMUIStatusBarHelper.setStatusBarLightMode(ActivityManager.getInstance().currentActivity());
        ButterKnife.bind(this, view);
        tvTitle.setText("修改手机号");
        presenter = new Presenter(this);
        return view;
    }


    @OnClick({R.id.imgBack, R.id.btCode, R.id.btLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                popBackStack();
                break;
            case R.id.btCode:
                if (etPhone.getText().toString().equals(""))
                    ToastUtil.showShort("请输入手机号码");
                else {
                    Map map = new HashMap();
                    map.put("phone", etPhone.getText().toString());
                    presenter.sendVerifyCode(map);

                }


                break;
            case R.id.btLogin:
                if (etPhone.getText().toString().equals("") || etCode.getText().toString().equals(""))
                    ToastUtil.showShort("将信息填写完整");
                else {
                    Map map = new HashMap();
                    map.put("newPhone", etPhone.getText());
                    map.put("code", etCode.getText().toString());
                    map.put("password", SPUtil.get(getContext(), "password", "").toString());
                    presenter.changePhone(map);
                }



                break;
        }
    }

    @Override
    public void sendVerifyCode() {
        subscription = RxUtils.countdown(60).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                btCode.setClickable(true);
                btCode.setText("获取验证码");

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onStart() {
                super.onStart();
                btCode.setClickable(false);
            }

            @Override
            public void onNext(Integer integer) {
                btCode.setText(integer + "秒");
            }
        });

    }

    @Override
    public void changePhone() {
        ((QMUIFragmentActivity) getActivity()).popBackStack(AccountManageFragment.class);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null)
            subscription.unsubscribe();
    }
}
