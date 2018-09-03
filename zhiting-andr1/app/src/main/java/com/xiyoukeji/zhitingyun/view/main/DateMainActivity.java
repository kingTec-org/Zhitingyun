package com.xiyoukeji.zhitingyun.view.main;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.data.entity.DeviceEntity;
import com.xiyoukeji.zhitingyun.data.entity.ExpertSchEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.SubmitEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivityLoginBinding;
import com.xiyoukeji.zhitingyun.databinding.ActivityMainBinding;
import com.xiyoukeji.zhitingyun.util.ActivityManager;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.view.login.LoginActivity;
import com.xiyoukeji.zhitingyun.viewmodel.login.LoginViewModel;
import com.xiyoukeji.zhitingyun.viewmodel.main.MainViewModel;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;
import com.xiyoukeji.zhitingyun.yunxin.SPUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class DateMainActivity extends BaseActivity {
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.etDiscription)
    EditText etDiscription;
    ExpertSchEntity.WorkScheduleDtosBean entity;
    private MainViewModel mViewModel;
    int index = 0;
    String ids = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        ActivityManager.addActivity( this );
        int from = getIntent().getIntExtra( "from", 7 );

        if (savedInstanceState == null) {
            initData();
            initView();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white); }
    }
    public DateMainActivity() {
        super(R.layout.activity_date_main);
    }

    @OnClick({R.id.imgBack, R.id.llDiscription, R.id.tvSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.llDiscription:
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    etDiscription.requestFocus();
                    imm.showSoftInput(etDiscription, InputMethodManager.SHOW_FORCED);
                }
                break;
            case R.id.tvSubmit:
                if (etDiscription.getText().equals(""))
                    ToastUtils.showShort("请输入问题描述");
                else {
                    Gson gson = new Gson();
                    final List<DeviceEntity> list = new ArrayList<>();
                    String session1 = SPUtil.get(DateMainActivity.this, "session1", "").toString();
                    if (session1.equals(""))
                        ToastUtils.showShort("请连接设备");
                    else {
                        ids = "";
                        index = 0;
                        DeviceEntity entity1 = gson.fromJson(session1, DeviceEntity.class);
                        list.add(entity1);
                        String session2 = SPUtil.get(DateMainActivity.this, "session2", "").toString();
                        if (session2.equals("")) {
                            DeviceEntity deviceEntity = entity1.createDeviceEntity();
                            deviceEntity.setClassify(2);
                            list.add(deviceEntity);
                        } else {
                            DeviceEntity entity2 = gson.fromJson(session2, DeviceEntity.class);
                            list.add(entity2);
                        }
                        String session3 = SPUtil.get(DateMainActivity.this, "session3", "").toString();
                        if (session3.equals("")) {
                            DeviceEntity deviceEntity = entity1.createDeviceEntity();
                            deviceEntity.setClassify(3);
                            list.add(deviceEntity);
                        } else {
                            DeviceEntity entity3 = gson.fromJson(session3, DeviceEntity.class);
                            list.add(entity3);
                        }
                        String session4 = SPUtil.get(DateMainActivity.this, "session4", "").toString();
                        if (session4.equals("")) {
                            DeviceEntity deviceEntity = entity1.createDeviceEntity();
                            deviceEntity.setClassify(4);
                            list.add(deviceEntity);
                        } else {
                            DeviceEntity entity4 = gson.fromJson(session4, DeviceEntity.class);
                            list.add(entity4);
                        }

                        for (int i = 0; i < list.size(); i++) {
                            mViewModel.submitParamater(list.get(i), new BaseObserver<Model0<DeviceEntity>>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    addDisposable(d);
                                }

                                @Override
                                public void onNext(Model0<DeviceEntity> model0) {
//                                    SharedPreferences sharedPreferences = getSharedPreferences("mySP", Context.MODE_PRIVATE);
//                                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                                    editor.putInt("orderId",model0.getComeback().getId() );
//                                    editor.commit();
                                    index++;
                                    ids += model0.getComeback().getId();
                                    if (index != list.size()) {
                                        ids += ",";

                                    }
                                    if (index == list.size()) {
//                                        SPUtil.put(DateMainActivity.this, "session1", "");
//                                        SPUtil.put(DateMainActivity.this, "session2", "");
//                                        SPUtil.put(DateMainActivity.this, "session3", "");
//                                        SPUtil.put(DateMainActivity.this, "session4", "");
                                        submit();

                                    }
                                }
                            });
                        }
                    }


                }

                break;
        }
    }


    private void submit() {
        index = 0;
        mViewModel.submit(entity.getId(), etDiscription.getText().toString(), ids, new BaseObserver<Model0<SubmitEntity>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(Model0<SubmitEntity> submitEntityModel0) {
//                Intent intent=new Intent(DateMainActivity.this,EvaluationActivity.class);
//                intent.putExtra( "getId");
                ids = "";
                finish();

            }
        });

    }

    @Override
    protected void bindingData() {
        super.bindingData();
//        mBinding = DataBindingUtil.setContentView(mActivity, layoutId);
    }

    @Override
    protected void initData() {
        super.initData();
        obtainViewModel();
//        mBinding.setViewModel(mViewModel);
        mViewModel.getLoadingState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    showLoading(mViewModel.mTip.get());
                } else {
                    dismissLoading();
                }
            }
        });
    }

    public void obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(mActivity.getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }


    @Override
    protected void initView() {
        super.initView();
        entity = (ExpertSchEntity.WorkScheduleDtosBean) getIntent().getSerializableExtra("data");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(entity.getStart());
        calendar.setTime(date);
        tvTime.setText(format(entity.getStart(), "MM月dd日 " + getCalendarWeek(calendar) + " " + format(entity.getStart(), "HH:mm") + "-" + format(entity.getEnd(), "HH:mm")));

    }

    public String format(Long aLong, String format) {
        return new SimpleDateFormat(format).format(aLong);
    }


    private String getCalendarWeek(Calendar calendar) {
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";

        }
    }

}
