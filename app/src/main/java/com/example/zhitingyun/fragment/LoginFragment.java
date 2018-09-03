package com.example.zhitingyun.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Process;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.activity.MainActivity;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.model.ProfessorInfo;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButtonDrawable;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.manager.ActivityManager;
import coder.mylibrary.util.SPUtil;
import coder.mylibrary.util.ToastUtil;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by dasiy on 2018/7/7.
 */

@SuppressLint("ValidFragment")
@RuntimePermissions
public class LoginFragment extends BaseFragment implements ViewsContainer.LoginView {
    @BindView(R.id.imgLogo)
    ImageView imgLogo;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView( R.id.btLogin )
    QMUIRoundButton btnLogin;

    private long lastTime;
    private long duration = 2000L;
    int from;
    Presenter presenter;

    public LoginFragment(int from) {
        this.from = from;
    }

    private AbortableFuture<LoginInfo> loginRequest;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_login, null);
        QMUIStatusBarHelper.setStatusBarLightMode(ActivityManager.getInstance().currentActivity());
        ButterKnife.bind(this, view);
        presenter = new Presenter(this);

        if (from == 1) {
            new QMUIDialog.MessageDialogBuilder(getActivity())
                    .setMessage("该账号已在另一台设备上登录，您的账号密码可能已被盗用")
                    .addAction("确认", new QMUIDialogAction.ActionListener() {
                        @Override
                        public void onClick(QMUIDialog dialog, int index) {
                            dialog.dismiss();
                        }
                    })

                    .create(com.qmuiteam.qmui.R.style.QMUI_Dialog).show();
        }
        LoginFragmentPermissionsDispatcher.showChoiceWithCheck(this);
        return view;
    }


    @OnClick({R.id.tvForget, R.id.btLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvForget:
                startFragment(new ForgetPasswordFragment());

                break;
            case R.id.btLogin:
                onBackPressed();
//                if (etPassword.getText().toString().equals("") || etPhone.getText().toString().equals(""))
//                    ToastUtil.showShort("将信息填写完整");
//                else {
//                    Map map = new HashMap();
//                    map.put("phone", etPhone.getText().toString());
//                    map.put("password", etPassword.getText().toString());
//                    presenter.login(map);
//                }


//                loginRequest = NimUIKit.login(new LoginInfo("18258139327", MD5.getStringMD5("aa111111")), new RequestCallback<LoginInfo>() {
//                    @Override
//                    public void onSuccess(LoginInfo param) {
//                        startActivity(new Intent(getActivity(), MainActivity.class));
//                        getActivity().finish();
//
//                    }
//
//                    @Override
//                    public void onFailed(int code) {
//                        Log.d("vvv", code + "");
//                    }
//
//                    @Override
//                    public void onException(Throwable exception) {
//                        Log.d("vvv", exception.toString() + "");
//                    }
//                });

                break;
        }
    }


    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECORD_AUDIO})
    void showChoice() {

    }

    @OnPermissionDenied({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECORD_AUDIO})
    void denied() {
    }

    @OnNeverAskAgain({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECORD_AUDIO})
    void nerverAskAgain() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LoginFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    RequestCallback<LoginInfo> callback =
            new RequestCallback<LoginInfo>() {
                // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用

                @Override
                public void onSuccess(LoginInfo param) {
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    getActivity().finish();

                }

                @Override
                public void onFailed(int code) {

                }

                @Override
                public void onException(Throwable exception) {

                }
            };

    @Override
    public void login(ProfessorInfo professorInfo) {
        SPUtil.put(getContext(), "userId", professorInfo.getId() + "");
        SPUtil.put(getContext(), "accid", professorInfo.getAccid());
        SPUtil.put(getContext(), "imToken", professorInfo.getImToken());
        SPUtil.put(getContext(), "token", professorInfo.getToken());
        SPUtil.put(getContext(), "password", etPassword.getText().toString());

        LoginInfo info = new LoginInfo(professorInfo.getAccid(), professorInfo.getImToken()); // config...

        NIMClient.getService(AuthService.class).login(info)
                .setCallback(callback);


    }

//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == event.KEYCODE_BACK
//                && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if (from == 0)
//                popBackStack();
//
//
//            return true;
//        }
//        return false;
//    }

    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastTime) > duration) {
            lastTime = currentTime;
            if (etPassword.getText().toString().equals("") || etPhone.getText().toString().equals(""))
                ToastUtil.showShort("将信息填写完整");
            else {
                Map map = new HashMap();
                map.put("phone", etPhone.getText().toString());
                map.put("password", etPassword.getText().toString());
                presenter.login(map);
            }
        } else {
//            ToastUtil.showShort( "别点击太快，休息会儿。" );
        }
    }

}
