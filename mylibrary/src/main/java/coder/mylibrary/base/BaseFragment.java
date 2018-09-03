package coder.mylibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import coder.mylibrary.R;


/**
 * Created by renlei on 2016/5/23.
 */
public abstract class BaseFragment extends Fragment {

    protected BaseActivity mActivity;

    protected abstract void initView(View view, Bundle savedInstanceState);

    //获取fragment布局文件ID
    protected abstract int getLayoutId();

    //获取宿主Activity
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity) activity;
    }

    //添加fragment
    protected void addFragment(BaseFragment fragment) {
        if (null != fragment) {
            getHoldingActivity().addFragment(fragment);
        }
    }

    //移除fragment
    protected void removeFragment() {
        getHoldingActivity().removeFragment();
    }

//    @Override
//    public void startActivity(Intent intent) {
//        super.startActivity(intent);
//        mActivity.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        Log.d("sss", "create");
        initView(view, savedInstanceState);
        return view;
    }
//
//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
//    }


//    @Override
//    public void startActivity(Intent intent) {
//        super.startActivity(intent);
//        mActivity.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
