package com.example.zhitingyun.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.fragment.AccountManageFragment;
import com.example.zhitingyun.fragment.PersonInfoFragment;
import com.example.zhitingyun.fragment.ProblemFeedbackFragment;
import com.example.zhitingyun.model.ProfessorInfo;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.base.ProjectConfig;

/**
 * Created by dasiy on 2018/7/7.
 */

public class PersoninfoController extends MainController implements ViewsContainer.UserInfoView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.imgAvatar)
    QMUIRadiusImageView imgAvatar;
    @BindView(R.id.tvName)
    TextView tvName;
    Presenter presenter;


    public void getInfo() {
        presenter.getInfo(new HashMap(), 0);


    }

    public PersoninfoController(@NonNull Context context,final int p) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.controller_personinfo, this);
        ButterKnife.bind(this, view);
        presenter = new Presenter(this);
        imgBack.setVisibility(View.GONE);
        tvTitle.setText("个人中心");
        getInfo();

    }

    @Override
    protected String getTitle() {
        return null;
    }

    @OnClick({R.id.imgAvatar, R.id.llAccount, R.id.llProblem})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgAvatar:
                listener.startFragment(new PersonInfoFragment());
                break;
            case R.id.llAccount:
                listener.startFragment(new AccountManageFragment());
                break;
            case R.id.llProblem:
                listener.startFragment(new ProblemFeedbackFragment());
                break;

        }
    }

    @Override
    public void getInfo(ProfessorInfo professorInfo) {
        tvName.setText(professorInfo.getName());
        Picasso.with(getContext())
                .load(ProjectConfig.BASE_URL + "/" + professorInfo.getHeadPic())
                .resize(100, 100)
                .error(R.mipmap.ic_launcher)           //设置错误图片
                .placeholder(R.mipmap.ic_launcher)
                .into(imgAvatar);
    }
}
