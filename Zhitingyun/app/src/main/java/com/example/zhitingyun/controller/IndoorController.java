package com.example.zhitingyun.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.model.EquipmentParamater;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.qmuiteam.qmui.layout.QMUILinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dasiy on 2018/7/9.
 */

public class IndoorController extends MainController {
    @BindView(R.id.progressBar0)
    ProgressBar progressBar0;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar2;
    @BindView(R.id.progressBar3)
    ProgressBar progressBar3;
    @BindView(R.id.tvStatus0)
    TextView tvStatus0;
    @BindView(R.id.tvStatus1)
    TextView tvStatus1;
    @BindView(R.id.tvStatus4)
    TextView tvStatus4;
    @BindView(R.id.tvStatus5)
    TextView tvStatus5;
    @BindView(R.id.tvVolume)
    TextView tvVolume;
    @BindView(R.id.tvHigh)
    TextView tvHigh;
    @BindView(R.id.tvLow)
    TextView tvLow;
    @BindView(R.id.chart)
    LineChart mChart;
    @BindView(R.id.linearlayout)
    QMUILinearLayout linearlayout;
    @BindView(R.id.tvLeft)
    TextView tvLeft;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.linearlayout11)
    LinearLayout linearlayout11;
    @BindView(R.id.linearlayout12)
    LinearLayout linearlayout12;
    @BindView(R.id.linearlayout13)
    LinearLayout linearlayout13;
    @BindView(R.id.linearlayout14)
    LinearLayout linearlayout14;
    @BindView(R.id.linearlayout15)
    LinearLayout linearlayout15;
    @BindView(R.id.linearlayout16)
    LinearLayout linearlayout16;
    @BindView(R.id.linearlayout17)
    LinearLayout linearlayout17;
    ArrayList<ArrayList<String>> mTableDatas = new ArrayList<ArrayList<String>>();
    EquipmentParamater equipmentParamater;
    int from;
    int click = 0;

    public IndoorController(@NonNull Context context, int from, EquipmentParamater equipmentParamater) {
        super(context);
        this.from = from;
        this.equipmentParamater = equipmentParamater;
        View view = LayoutInflater.from(context).inflate(R.layout.controller_indoor, this);
        ButterKnife.bind(this, view);
        tvLeft.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        tvRight.setBackgroundResource(R.drawable.shape_blue_button);
        tvLeft.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
        tvRight.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        progressBar0.setProgress(50);
        progressBar1.setProgress(50);
        progressBar2.setProgress(50);
        progressBar3.setProgress(50);
        linearlayout.setRadius(10);


        mChart.setDrawGridBackground(false);
        mChart.getDescription().setEnabled(true);
        mChart.setDrawBorders(false);

        mChart.getAxisLeft().setEnabled(true);
        mChart.getXAxis().setDrawAxisLine(true);
        mChart.getXAxis().setDrawGridLines(false);
        mChart.getAxisRight().setEnabled(false);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(false);
        mChart.setScaleEnabled(false);
        mChart.setPinchZoom(false);
        Legend l = mChart.getLegend();
        l.setEnabled(false);

        mChart.resetTracking();
        initView();


    }

    private void initView() {
        String compressState = "关";
        String extendState = "关";
        String denoise = "";
        String feedback = "";
        Integer volume = 0;
        Integer high = 0;
        Integer low = 0;
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        switch (click) {
            case 0:
                if (equipmentParamater.getL_SWITCH_P_CH())
                    compressState = "开";
                if (equipmentParamater.getL_SWITCH_P_CHEXP())
                    extendState = "开";
                if (equipmentParamater.getL_P_NRLVL() != null && equipmentParamater.getL_P_NRLVL_VALUE() != null)
                    denoise = equipmentParamater.getL_P_NRLVL().split(",")[equipmentParamater.getL_P_NRLVL_VALUE()];
                if (equipmentParamater.getL_P_AFSLVL() != null && equipmentParamater.getL_P_AFSLVL_VALUE() != null)
                    feedback = equipmentParamater.getL_P_AFSLVL().split(",")[equipmentParamater.getL_P_AFSLVL_VALUE()];
                if (equipmentParamater.getL_P_VC() != null && equipmentParamater.getL_P_VC_VALUE() != null)
                    volume = new Integer(equipmentParamater.getL_P_VC().split(",")[equipmentParamater.getL_P_VC_VALUE()]);
                if (equipmentParamater.getL_P_TRB() != null && equipmentParamater.getL_P_TRB_VALUE() != null)
                    high = new Integer(equipmentParamater.getL_P_TRB().split(",")[equipmentParamater.getL_P_TRB_VALUE()]);
                if (equipmentParamater.getL_P_BASS() != null && equipmentParamater.getL_P_BASS_VALUE() != null)
                    low = new Integer(equipmentParamater.getL_P_BASS().split(",")[equipmentParamater.getL_P_BASS_VALUE()]);
                linearlayout11.removeAllViews();
                linearlayout12.removeAllViews();
                linearlayout13.removeAllViews();
                linearlayout14.removeAllViews();
                linearlayout15.removeAllViews();
                linearlayout16.removeAllViews();
                linearlayout17.removeAllViews();
                String[] chg_DATA = null, mpoArray = null, midArray = null, lowArray = null, highArray = null, pointArray = null, rateArray = null;
                if (equipmentParamater.getChg_DATA() != null)
                    chg_DATA = equipmentParamater.getChg_DATA().split(",");
                if (equipmentParamater.getL_P_CHMPO() != null)
                    mpoArray = equipmentParamater.getL_P_CHMPO().split(",");
                if (equipmentParamater.getL_P_CHNG() != null)
                    midArray = equipmentParamater.getL_P_CHNG().split(",");
                if (equipmentParamater.getL_P_CHSG() != null)
                    lowArray = equipmentParamater.getL_P_CHSG().split(",");
                if (equipmentParamater.getL_P_CHLG() != null)
                    highArray = equipmentParamater.getL_P_CHLG().split(",");
                if (equipmentParamater.getL_P_CHETH() != null)
                    pointArray = equipmentParamater.getL_P_CHETH().split(",");
                if (equipmentParamater.getL_P_CHER() != null)
                    rateArray = equipmentParamater.getL_P_CHER().split(",");
                final String[] hezArrray = equipmentParamater.getHertz().split(",");
                if (midArray != null) {
                    for (int i = -1; i < midArray.length; i++) {

                        for (int j = 0; j < 7; j++) {
                            View view = new View(getContext());
                            view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorGrayf0));
                            view.setLayoutParams(new LinearLayout.LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT));

                            TextView textView = new TextView(getContext());
                            textView.setGravity(Gravity.CENTER);

                            if (i == -1) {
                                textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 3));

                                switch (j) {
                                    case 0:
                                        linearlayout11.addView(view);
                                        textView.setText("");
                                        linearlayout11.addView(textView);
                                        break;
                                    case 1:
                                        linearlayout12.addView(view);
                                        textView.setText("MPO");
                                        linearlayout12.addView(textView);
                                        break;
                                    case 2:
                                        linearlayout13.addView(view);
                                        textView.setText("小声增益");
                                        linearlayout13.addView(textView);
                                        break;
                                    case 3:
                                        linearlayout14.addView(view);
                                        textView.setText("中声增益");
                                        linearlayout14.addView(textView);
                                        break;
                                    case 4:
                                        linearlayout15.addView(view);
                                        textView.setText("大声增益");
                                        linearlayout15.addView(textView);
                                        break;
                                    case 5:
                                        linearlayout16.addView(view);
                                        textView.setText("扩展拐点");
                                        linearlayout16.addView(textView);
                                        break;
                                    case 6:
                                        linearlayout17.addView(view);
                                        textView.setText("扩展比");
                                        linearlayout17.addView(textView);
                                        break;
                                }
                            } else {
                                textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2));

                                switch (j) {
                                    case 0:
                                        linearlayout11.addView(view);
                                        textView.setText(hezArrray[i]);
                                        linearlayout11.addView(textView);
                                        break;
                                    case 1:
                                        linearlayout12.addView(view);
                                        if (chg_DATA != null && mpoArray != null) {
                                            if (new Integer(mpoArray[i]) > chg_DATA.length-1)
                                                mpoArray[i] = "0";
                                            textView.setText(chg_DATA[new Integer(mpoArray[i])]);
                                        }
                                        linearlayout12.addView(textView);
                                        break;
                                    case 2:
                                        linearlayout13.addView(view);
                                        if (chg_DATA != null && lowArray != null) {
                                            if (new Integer(lowArray[i]) > chg_DATA.length-1)
                                                lowArray[i] = "0";
                                            textView.setText(chg_DATA[new Integer(lowArray[i])]);
                                        }
                                        linearlayout13.addView(textView);
                                        break;
                                    case 3:
                                        linearlayout14.addView(view);
                                        if (chg_DATA != null && midArray != null) {
                                            if (new Integer(midArray[i]) > chg_DATA.length-1)
                                                midArray[i] = "0";
                                            textView.setText(chg_DATA[new Integer(midArray[i])]);
                                        }
                                        linearlayout14.addView(textView);
                                        break;
                                    case 4:
                                        linearlayout15.addView(view);
                                        if (chg_DATA != null && highArray != null) {
                                            if (new Integer(highArray[i]) > chg_DATA.length-1)
                                                highArray[i] = "0";
                                            textView.setText(chg_DATA[new Integer(highArray[i])]);
                                        }
                                        linearlayout15.addView(textView);
                                        break;
                                    case 5:
                                        linearlayout16.addView(view);
                                        if (pointArray == null || pointArray.length == 0)
                                            textView.setText("");
                                        else
                                            textView.setText(pointArray[i]);
                                        linearlayout16.addView(textView);
                                        break;
                                    case 6:
                                        linearlayout17.addView(view);
                                        if (rateArray == null || rateArray.length == 0)
                                            textView.setText("");
                                        else
                                            textView.setText(rateArray[i]);
                                        linearlayout17.addView(textView);
                                        break;
                                }


                            }
                            if (i == midArray.length - 1) {
                                for (int k = 0; k < 7; k++) {
                                    View view0 = new View(getContext());
                                    view0.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorGrayf0));
                                    view0.setLayoutParams(new LinearLayout.LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT));
                                    switch (k) {
                                        case 0:
                                            linearlayout11.addView(view0);
                                            break;
                                        case 1:
                                            linearlayout12.addView(view0);
                                            break;
                                        case 2:
                                            linearlayout13.addView(view0);
                                            break;
                                        case 3:
                                            linearlayout14.addView(view0);
                                            break;
                                        case 4:
                                            linearlayout15.addView(view0);
                                            break;
                                        case 5:
                                            linearlayout16.addView(view0);
                                            break;
                                        case 6:
                                            linearlayout17.addView(view0);
                                            break;
                                    }
                                }
                            }


                        }


                    }
                }

                mChart.getXAxis().setLabelCount(hezArrray.length, true);
                mChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {

                        return hezArrray[(int) value]; //mList为存有月份的集合
                    }
                });
//                mChart.getXAxis().setAxisMaximum((float) hezArrray.length);
                for (int z = 0; z < 4; z++) {

                    ArrayList<Entry> values = new ArrayList<Entry>();


                    for (int i = 0; i < midArray.length; i++) {
                        switch (z) {
                            case 0:
                                if (chg_DATA != null && midArray != null) {
                                    if (new Integer(midArray[i]) > chg_DATA.length-1)
                                        midArray[i] = "0";
                                    values.add(new Entry(i, new Float(chg_DATA[new Integer(midArray[i])])));
                                } else
                                    values.add(new Entry(i, new Float(0)));
                                break;
                            case 1:
                                if (chg_DATA != null && lowArray != null) {
                                    if (new Integer(lowArray[i]) > chg_DATA.length-1)
                                        lowArray[i] = "0";
                                    values.add(new Entry(i, new Float(chg_DATA[new Integer(lowArray[i])])));
                                } else
                                    values.add(new Entry(i, new Float(0)));
                                break;
                            case 2:
                                if (chg_DATA != null && mpoArray != null) {
                                    if (new Integer(mpoArray[i]) > chg_DATA.length-1)
                                        mpoArray[i] = "0";
                                    values.add(new Entry(i, new Float(chg_DATA[new Integer(mpoArray[i])])));
                                } else
                                    values.add(new Entry(i, new Float(0)));
                                break;
                            case 3:
                                if (chg_DATA != null && highArray != null) {
                                    if (new Integer(highArray[i]) > chg_DATA.length-1)
                                        highArray[i] = "0";
                                    values.add(new Entry(i, new Float(chg_DATA[new Integer(highArray[i])])));
                                } else
                                    values.add(new Entry(i, new Float(0)));
                                break;
                        }
                    }

                    LineDataSet d = new LineDataSet(values, "DataSet " + (z + 1));
                    d.setLineWidth(2.5f);
                    d.setCircleRadius(4f);

                    switch (z) {
                        case 0:
                            d.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                            d.setCircleColorHole(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                            d.setCircleColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                            break;
                        case 1:
                            d.setColor(ContextCompat.getColor(getContext(), R.color.colorRedff));
                            d.setCircleColorHole(ContextCompat.getColor(getContext(), R.color.colorRedff));
                            d.setCircleColor(ContextCompat.getColor(getContext(), R.color.colorRedff));
                            break;
                        case 2:
                            d.setColor(ContextCompat.getColor(getContext(), R.color.colorOrangeff));
                            d.setCircleColorHole(ContextCompat.getColor(getContext(), R.color.colorOrangeff));
                            d.setCircleColor(ContextCompat.getColor(getContext(), R.color.colorOrangeff));
                            break;
                        case 3:
                            d.setColor(ContextCompat.getColor(getContext(), R.color.colorGreen57));
                            d.setCircleColorHole(ContextCompat.getColor(getContext(), R.color.colorGreen57));
                            d.setCircleColor(ContextCompat.getColor(getContext(), R.color.colorGreen57));
                            break;
                    }

                    dataSets.add(d);
                }

                break;
            case 1:
                if (equipmentParamater.getR_SWITCH_P_CH())
                    compressState = "开";
                if (equipmentParamater.getL_SWITCH_P_CHEXP())
                    extendState = "开";
                if (equipmentParamater.getR_P_NRLVL() != null && equipmentParamater.getR_P_NRLVL_VALUE() != null)
                    denoise = equipmentParamater.getR_P_NRLVL().split(",")[equipmentParamater.getR_P_NRLVL_VALUE()];
                if (equipmentParamater.getR_P_AFSLVL() != null && equipmentParamater.getR_P_AFSLVL_VALUE() != null)
                    feedback = equipmentParamater.getR_P_AFSLVL().split(",")[equipmentParamater.getR_P_AFSLVL_VALUE()];
                if (equipmentParamater.getR_P_VC() != null && equipmentParamater.getR_P_VC_VALUE() != null)
                    volume = new Integer(equipmentParamater.getR_P_VC().split(",")[equipmentParamater.getR_P_VC_VALUE()]);
                if (equipmentParamater.getR_P_TRB() != null && equipmentParamater.getR_P_TRB_VALUE() != null)
                    high = new Integer(equipmentParamater.getR_P_TRB().split(",")[equipmentParamater.getR_P_TRB_VALUE()]);
                if (equipmentParamater.getR_P_BASS() != null && equipmentParamater.getR_P_BASS_VALUE() != null)
                    low = new Integer(equipmentParamater.getR_P_BASS().split(",")[equipmentParamater.getR_P_BASS_VALUE()]);
                linearlayout11.removeAllViews();
                linearlayout12.removeAllViews();
                linearlayout13.removeAllViews();
                linearlayout14.removeAllViews();
                linearlayout15.removeAllViews();
                linearlayout16.removeAllViews();
                linearlayout17.removeAllViews();
                String[] _chg_DATA = null, _mpoArray = null, _midArray = null, _lowArray = null, _highArray = null, _pointArray = null, _rateArray = null;
                _chg_DATA = equipmentParamater.getChg_DATA().split(",");
                _mpoArray = equipmentParamater.getR_P_CHMPO().split(",");
                _midArray = equipmentParamater.getR_P_CHNG().split(",");
                _lowArray = equipmentParamater.getR_P_CHSG().split(",");
                _highArray = equipmentParamater.getR_P_CHLG().split(",");
                _pointArray = equipmentParamater.getR_P_CHETH().split(",");
                _rateArray = equipmentParamater.getR_P_CHER().split(",");
                final String[] _hezArrray = equipmentParamater.getHertz().split(",");
                if (_midArray != null) {
                    for (int i = -1; i < _midArray.length; i++) {

                        for (int j = 0; j < 7; j++) {
                            View view = new View(getContext());
                            view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorGrayf0));
                            view.setLayoutParams(new LinearLayout.LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT));

                            TextView textView = new TextView(getContext());
                            textView.setGravity(Gravity.CENTER);

                            if (i == -1) {
                                textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 3));

                                switch (j) {
                                    case 0:
                                        linearlayout11.addView(view);
                                        textView.setText("");
                                        linearlayout11.addView(textView);
                                        break;
                                    case 1:
                                        linearlayout12.addView(view);
                                        textView.setText("MPO");
                                        linearlayout12.addView(textView);
                                        break;
                                    case 2:
                                        linearlayout13.addView(view);
                                        textView.setText("小声增益");
                                        linearlayout13.addView(textView);
                                        break;
                                    case 3:
                                        linearlayout14.addView(view);
                                        textView.setText("中声增益");
                                        linearlayout14.addView(textView);
                                        break;
                                    case 4:
                                        linearlayout15.addView(view);
                                        textView.setText("大声增益");
                                        linearlayout15.addView(textView);
                                        break;
                                    case 5:
                                        linearlayout16.addView(view);
                                        textView.setText("扩展拐点");
                                        linearlayout16.addView(textView);
                                        break;
                                    case 6:
                                        linearlayout17.addView(view);
                                        textView.setText("扩展比");
                                        linearlayout17.addView(textView);
                                        break;
                                }
                            } else {
                                textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2));

                                switch (j) {
                                    case 0:
                                        linearlayout11.addView(view);
                                        if (_hezArrray != null)
                                            textView.setText(_hezArrray[i]);
                                        linearlayout11.addView(textView);
                                        break;
                                    case 1:
                                        linearlayout12.addView(view);
                                        if (_chg_DATA != null && _mpoArray != null) {
                                            if (new Integer(_mpoArray[i]) > _chg_DATA.length-1)
                                                _mpoArray[i] = "0";
                                            textView.setText(_chg_DATA[new Integer(_mpoArray[i])]);
                                        }
                                        linearlayout12.addView(textView);
                                        break;
                                    case 2:
                                        linearlayout13.addView(view);
                                        if (_chg_DATA != null && _lowArray != null) {
                                            if (new Integer(_lowArray[i]) > _chg_DATA.length-1)
                                                _lowArray[i] = "0";
                                            textView.setText(_chg_DATA[new Integer(_lowArray[i])]);
                                        }
                                        linearlayout13.addView(textView);
                                        break;
                                    case 3:
                                        linearlayout14.addView(view);
                                        if (_chg_DATA != null && _midArray != null) {
                                            if (new Integer(_midArray[i]) > _chg_DATA.length-1)
                                                _midArray[i] = "0";
                                            textView.setText(_chg_DATA[new Integer(_midArray[i])]);
                                        }
                                        linearlayout14.addView(textView);
                                        break;
                                    case 4:
                                        linearlayout15.addView(view);
                                        if (_chg_DATA != null && _highArray != null) {
                                            if (new Integer(_highArray[i]) > _chg_DATA.length-1)
                                                _highArray[i] = "0";
                                            textView.setText(_chg_DATA[new Integer(_highArray[i])]);
                                        }
                                        linearlayout15.addView(textView);
                                        break;
                                    case 5:
                                        linearlayout16.addView(view);
                                        if (_pointArray == null || _pointArray.length == 0)
                                            textView.setText("");
                                        else
                                            textView.setText(_pointArray[i]);
                                        linearlayout16.addView(textView);
                                        break;
                                    case 6:
                                        linearlayout17.addView(view);
                                        if (_rateArray == null || _rateArray.length == 0)
                                            textView.setText("");
                                        else
                                            textView.setText(_rateArray[i]);
                                        linearlayout17.addView(textView);
                                        break;
                                }


                            }
                            if (i == _midArray.length - 1) {
                                for (int k = 0; k < 7; k++) {
                                    View view0 = new View(getContext());
                                    view0.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorGrayf0));
                                    view0.setLayoutParams(new LinearLayout.LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT));
                                    switch (k) {
                                        case 0:
                                            linearlayout11.addView(view0);
                                            break;
                                        case 1:
                                            linearlayout12.addView(view0);
                                            break;
                                        case 2:
                                            linearlayout13.addView(view0);
                                            break;
                                        case 3:
                                            linearlayout14.addView(view0);
                                            break;
                                        case 4:
                                            linearlayout15.addView(view0);
                                            break;
                                        case 5:
                                            linearlayout16.addView(view0);
                                            break;
                                        case 6:
                                            linearlayout17.addView(view0);
                                            break;
                                    }
                                }
                            }


                        }


                    }
                }

                mChart.getXAxis().setLabelCount(_hezArrray.length, true);
                mChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {

                        return _hezArrray[(int) value]; //mList为存有月份的集合
                    }
                });
//                mChart.getXAxis().setAxisMaximum((float) hezArrray.length);
                for (int z = 0; z < 4; z++) {

                    ArrayList<Entry> values = new ArrayList<Entry>();


                    for (int i = 0; i < _midArray.length; i++) {
                        switch (z) {
                            case 0:
                                if (_chg_DATA != null && _midArray != null) {
                                    if (new Integer(_midArray[i]) > _chg_DATA.length-1)
                                        _midArray[i] = "0";
                                    values.add(new Entry(i, new Float(_chg_DATA[new Integer(_midArray[i])])));
                                } else
                                    values.add(new Entry(i, new Float(0)));
                                break;
                            case 1:
                                if (_chg_DATA != null && _lowArray != null) {
                                    if (new Integer(_lowArray[i]) > _chg_DATA.length-1)
                                        _lowArray[i] = "0";
                                    values.add(new Entry(i, new Float(_chg_DATA[new Integer(_lowArray[i])])));
                                } else
                                    values.add(new Entry(i, new Float(0)));
                                break;
                            case 2:
                                if (_chg_DATA != null && _mpoArray != null) {
                                    if (new Integer(_mpoArray[i]) > _chg_DATA.length-1)
                                        _mpoArray[i] = "0";
                                    values.add(new Entry(i, new Float(_chg_DATA[new Integer(_mpoArray[i])])));
                                } else
                                    values.add(new Entry(i, new Float(0)));
                                break;
                            case 3:
                                if (_chg_DATA != null && _highArray != null) {
                                    if (new Integer(_highArray[i]) > _chg_DATA.length-1)
                                        _highArray[i] = "0";
                                    values.add(new Entry(i, new Float(_chg_DATA[new Integer(_highArray[i])])));
                                } else
                                    values.add(new Entry(i, new Float(0)));
                                break;
                        }
                    }

                    LineDataSet d = new LineDataSet(values, "DataSet " + (z + 1));
                    d.setLineWidth(2.5f);
                    d.setCircleRadius(4f);

                    switch (z) {
                        case 0:
                            d.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                            d.setCircleColorHole(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                            d.setCircleColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                            break;
                        case 1:
                            d.setColor(ContextCompat.getColor(getContext(), R.color.colorRedff));
                            d.setCircleColorHole(ContextCompat.getColor(getContext(), R.color.colorRedff));
                            d.setCircleColor(ContextCompat.getColor(getContext(), R.color.colorRedff));
                            break;
                        case 2:
                            d.setColor(ContextCompat.getColor(getContext(), R.color.colorOrangeff));
                            d.setCircleColorHole(ContextCompat.getColor(getContext(), R.color.colorOrangeff));
                            d.setCircleColor(ContextCompat.getColor(getContext(), R.color.colorOrangeff));
                            break;
                        case 3:
                            d.setColor(ContextCompat.getColor(getContext(), R.color.colorGreen57));
                            d.setCircleColorHole(ContextCompat.getColor(getContext(), R.color.colorGreen57));
                            d.setCircleColor(ContextCompat.getColor(getContext(), R.color.colorGreen57));
                            break;
                    }

                    dataSets.add(d);
                }
                break;
        }
        tvStatus0.setText(compressState);
        tvStatus1.setText(extendState);
        tvStatus4.setText(denoise);
        tvStatus5.setText(feedback);
        progressBar0.setProgress(volume);
        progressBar1.setProgress(high);
        progressBar3.setProgress(low);
        tvVolume.setText(volume + "");
        tvHigh.setText(high + "");
        tvLow.setText(low + "");


        LineData data = new LineData(dataSets);
        mChart.setData(data);
        mChart.invalidate();


    }


    @Override
    protected String getTitle() {
        return null;
    }

    @OnClick({R.id.tvLeft, R.id.tvRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvLeft:
                tvLeft.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                tvRight.setBackgroundResource(R.drawable.shape_blue_button);
                tvLeft.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
                tvRight.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                click = 0;
                initView();
                break;
            case R.id.tvRight:
                tvRight.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorRedff));
                tvLeft.setBackgroundResource(R.drawable.shape_red_button);
                tvRight.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
                tvLeft.setTextColor(ContextCompat.getColor(getContext(), R.color.colorRedff));
                click = 1;
                initView();
                break;
        }
    }
}
