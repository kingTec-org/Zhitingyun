package com.example.zhitingyun.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.activity.LoginActivity;
import com.example.zhitingyun.activity.MainActivity;
import com.example.zhitingyun.base.BaseFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import coder.mylibrary.util.RxUtils;
import coder.mylibrary.util.SPUtil;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by dasiy on 2018/7/15.
 */

public class SplashFragment extends BaseFragment {
    @BindView(R.id.imgBg)
    ImageView imgBg;
    Subscription subscription;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null)
            subscription.unsubscribe();
    }

    @Override
    protected View onCreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_splash, null);
        ButterKnife.bind(this, view);
        Picasso.with(getContext())
                .load(R.mipmap.splash_bg)
                .fit()
                .error(R.mipmap.splash_bg)           //设置错误图片
                .placeholder(R.mipmap.splash_bg)
                .into(imgBg);

        subscription = RxUtils.countdown(3).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                if (SPUtil.get(getContext(), "token", "").toString().equals("")) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));

                } else {
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
                getActivity().finish();


            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onNext(Integer integer) {
            }
        });
        return view;
    }

}
