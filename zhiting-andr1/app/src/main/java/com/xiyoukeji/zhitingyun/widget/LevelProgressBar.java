package com.xiyoukeji.zhitingyun.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xiyoukeji.zhitingyun.R;

/**
 * Created by Administrator on 2018/1/19.
 */

public class LevelProgressBar extends ProgressBar {
    private int current = 0;
    private final int max = 100;
    private PopupWindow mPopupWindow;
    private TextView mTextView;

    public LevelProgressBar(Context context) {
        super(context);
    }

    public LevelProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
//        if (mPopupWindow == null) {
//            mPopupWindow = new PopupWindow(context);
//            View view = LayoutInflater.from(context).inflate(R.layout.pop_level, null);
//            mTextView = view.findViewById(R.id.pop_progress_value);
//            mPopupWindow.setContentView(view);
//        }
    }

    public LevelProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public synchronized void setProgress(int progress) {
//        if (progress > current) {
//
//        }
//        current = progress;
//        super.setProgress(progress);
//        mTextView.setText(String.valueOf(progress));
//        mPopupWindow.showAsDropDown(this);
    }
}
