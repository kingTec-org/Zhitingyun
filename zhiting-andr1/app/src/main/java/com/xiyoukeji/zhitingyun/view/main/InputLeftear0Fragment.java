package com.xiyoukeji.zhitingyun.view.main;

import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InputLeftear0Fragment extends BaseFragment {
    @BindView(R.id.etHz125)
    EditText etHz125;
    @BindView(R.id.etHz250)
    EditText etHz250;
    @BindView(R.id.etHz500)
    EditText etHz500;
    @BindView(R.id.etHz750)
    EditText etHz750;
    @BindView(R.id.etHz1000)
    EditText etHz1000;
    @BindView(R.id.etHz1500)
    EditText etHz1500;
    @BindView(R.id.etHz2000)
    EditText etHz2000;
    @BindView(R.id.etHz3000)
    EditText etHz3000;
    @BindView(R.id.etHz4000)
    EditText etHz4000;
    @BindView(R.id.etHz6000)
    EditText etHz6000;
    @BindView(R.id.etHz8000)
    EditText etHz8000;

    private Input0Fragment.OnFeedBackListener onFeedBackListener;
    List<Integer> list = new ArrayList<>();

    public void setOnFeedBackListener(Input0Fragment.OnFeedBackListener onFeedBackListener) {
        this.onFeedBackListener = onFeedBackListener;
        onFeedBackListener.onFeedback(0, list);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_inputtest_left;
    }

    public static Fragment newInstance() {
        return new InputLeftear0Fragment();
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        etHz125.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list.set(0, new Integer(editable.toString()));
                onFeedBackListener.onFeedback(0, list);

                onFeedBackListener.onFeedback( 2,list);
            }
        });
        etHz250.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list.set(1, new Integer(editable.toString()));
                onFeedBackListener.onFeedback(0, list);

                onFeedBackListener.onFeedback( 2,list);

            }
        });
        etHz500.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list.set(2, new Integer(editable.toString()));
                onFeedBackListener.onFeedback(0, list);

            }
        });
        etHz750.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list.set(3, new Integer(editable.toString()));
                onFeedBackListener.onFeedback(0, list);

            }
        });
        etHz1000.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list.set(4, new Integer(editable.toString()));
                onFeedBackListener.onFeedback(0, list);

            }
        });
        etHz1500.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list.set(5, new Integer(editable.toString()));
                onFeedBackListener.onFeedback(0, list);

            }
        });
        etHz2000.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list.set(6, new Integer(editable.toString()));
                onFeedBackListener.onFeedback(0, list);

            }
        });
        etHz3000.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list.set(7, new Integer(editable.toString()));
                onFeedBackListener.onFeedback(0, list);

            }
        });
        etHz4000.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list.set(8, new Integer(editable.toString()));
                onFeedBackListener.onFeedback(0, list);

            }
        });
        etHz6000.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list.set(9, new Integer(editable.toString()));
                onFeedBackListener.onFeedback(0, list);

            }
        });
        etHz8000.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list.set(10, new Integer(editable.toString()));
                onFeedBackListener.onFeedback(0, list);

            }
        });
    }

}
