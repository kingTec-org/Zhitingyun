package com.example.zhitingyun.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
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
import coder.mylibrary.util.DateUtil;

/**
 * Created by dasiy on 2018/7/10.
 */
public class PersonInfoFragment extends BaseFragment implements ViewsContainer.PersonInfoView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.imgAvatar)
    QMUIRadiusImageView imgAvatar;
    @BindView(R.id.tvSex)
    TextView tvSex;
    @BindView(R.id.tvAge)
    TextView tvAge;
    @BindView(R.id.tvExperience)
    TextView tvExperience;
    Presenter presenter;


    @Override
    protected View onCreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_person_info, null);
        ButterKnife.bind(this, view);
        presenter = new Presenter(this);
        presenter.getInfo(new HashMap(), 1);
        tvTitle.setText("个人资料");

        return view;
    }


    @OnClick({R.id.imgBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:

                popBackStack();
                break;

        }
    }

    @Override
    public void getInfo(ProfessorInfo professorInfo) {
        Picasso.with(getContext())
                .load(ProjectConfig.BASE_URL + "/" + professorInfo.getHeadPic())
                .resize(100, 100)
                .error(R.mipmap.ic_launcher)           //设置错误图片
                .placeholder(R.mipmap.ic_launcher)
                .into(imgAvatar);
        switch (professorInfo.getSex()) {
            case 2:
                tvSex.setText("女");

                break;
            case 1:
                tvSex.setText("男");

                break;
        }


        tvAge.setText((DateUtil.getYear(System.currentTimeMillis()) - DateUtil.getYear(professorInfo.getAge())) + "");
        tvExperience.setText(professorInfo.getWorkTime() + "个月");

    }


}
