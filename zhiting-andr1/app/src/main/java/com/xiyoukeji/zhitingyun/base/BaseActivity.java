package com.xiyoukeji.zhitingyun.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.constant.AppConstant;
import com.xiyoukeji.zhitingyun.util.ActivityManager;
import com.xiyoukeji.zhitingyun.util.SPUtil;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.view.login.LoginActivity;
import com.xiyoukeji.zhitingyun.view.main.MainActivity;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;



public class BaseActivity extends AppCompatActivity {

    protected int layoutId;
    protected Context mContext;
    protected Activity mActivity;
    protected ProgressDialog dialog;
    protected Unbinder mUnbinder;
    private CompositeDisposable compositeDisposable;

    public BaseActivity(int layoutId) {
        this.layoutId = layoutId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        mContext = this;
        mActivity = this;
        bindingData();
        mUnbinder = ButterKnife.bind(this);
        initView();
        initData();
        ActivityManager.addActivity(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            StatusBarUtil.setStatusBarColor(BaseActivity.this, R.color.white); }
    }

    //databinding在前ButterKnife.bind(this);在后才全有效.  不然会无响应
    protected void bindingData() {

    }

    protected void initData() {
    }

    protected void initView() {
    }

    public void startActivity(Class<?> activity) {
        startActivity(activity, null);
    }

    public void startActivity(Class<?> activity, Object data) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), activity);
        if (data != null)
            intent.putExtra("data", (Serializable) data);
        startActivity(intent);
    }

    public void startActivityForResult(Class<?> activity, int requstcode) {
        startActivityForResult(activity, null, requstcode);
    }

    public void startActivityForResult(Class<?> activity, Object data, int requstcode) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), activity);
        if (data != null)
            intent.putExtra("data", (Serializable) data);
        startActivityForResult(intent, requstcode);
    }

    protected boolean checkPhone(String str) {
        String pattern = "0?(13|14|15|17|18)[0-9]{9}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }

    public void showLoading() {
        if (dialog != null && dialog.isShowing()) return;
        dialog = new ProgressDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("请求中...");
        dialog.show();
    }

    public void showLoading(String msg) {
        if (dialog != null && dialog.isShowing()) return;
        dialog = new ProgressDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(msg);
        dialog.show();
    }

    public void dismissLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
        mUnbinder.unbind();
        ActivityManager.removeActivity(this);
    }

//    @Override
    public void addDisposable(Disposable disposable) {
        if (mContext == null) {
            return;
        }
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void finishAll() {
        ActivityManager.exitApp();
    }

//    @Override
    public void dispose() {
        if (compositeDisposable != null) compositeDisposable.dispose();
    }

    public boolean isLoginFinish() {
        if (SPUtil.getObjectFromShare(AppConstant.USER_MSG) != null) {
            return true;
        } else {
            finish();
            ToastUtils.showShort("请先登录");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return false;
        }
    }

    public boolean isLogin() {
        if (SPUtil.getObjectFromShare(AppConstant.USER_MSG) != null) {

            return true;
        } else {
            Toast.makeText(mContext.getApplicationContext(), "请先登录", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return false;
        }
    }

    public boolean isLoginSimple() {
        if (SPUtil.getObjectFromShare(AppConstant.USER_MSG) != null) {
            return true;
        } else {
            return false;
        }
    }
}
