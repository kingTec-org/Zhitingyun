package com.xiyoukeji.zhitingyun.view.main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class InputRightearFragment extends BaseFragment {
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
    private InputFragment.OnFeedBackListener onFeedBackListener;
    List<Integer> list = new ArrayList<>();
    List<Integer>list2=new ArrayList<>(  );
    private String n1=null,n2=null;

    public void setOnFeedBackListener(InputFragment.OnFeedBackListener onFeedBackListener) {
        this.onFeedBackListener = onFeedBackListener;
        onFeedBackListener.onFeedback(1, list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_inputtest_right;
    }

    public static Fragment newInstance() {
        return new InputRightearFragment();
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

//        if(n1!=null){
//            etHz125.setText( n1 );
//            n1=null;
//        }
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
                onFeedBackListener.onFeedback(1, list);

//                onFeedBackListener.onFeedback( 2,list );

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
                onFeedBackListener.onFeedback(1, list);

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
                onFeedBackListener.onFeedback(1, list);

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
                onFeedBackListener.onFeedback(1, list);

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
                onFeedBackListener.onFeedback(1, list);

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
                onFeedBackListener.onFeedback(1, list);

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
                onFeedBackListener.onFeedback(1, list);

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
                onFeedBackListener.onFeedback(1, list);

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
                onFeedBackListener.onFeedback(1, list);

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
                onFeedBackListener.onFeedback(1, list);

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
                onFeedBackListener.onFeedback(1, list);

            }
        });
    }


}
