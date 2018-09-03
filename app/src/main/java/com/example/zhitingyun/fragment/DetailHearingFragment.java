package com.example.zhitingyun.fragment;

import android.annotation.SuppressLint;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.model.NewestRecord;
import com.example.zhitingyun.model.OrderDetail;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.util.DateUtil;

/**
 * Created by dasiy on 2018/7/8.
 */

@SuppressLint("ValidFragment")
public class DetailHearingFragment extends BaseFragment {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.chart)
    LineChart mChart;
    NewestRecord newestRecord;
    List<String> mList = new ArrayList<>();
    List<String> mYList = new ArrayList<>();

    public DetailHearingFragment(NewestRecord newestRecord) {
        this.newestRecord = newestRecord;
    }

    @Override
    protected View onCreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_detail_hearing, null);
        ButterKnife.bind(this, view);
        mList.addAll(Arrays.asList(newestRecord.getLeft_hertz().split(",")));
        mYList.add("0");
        mYList.add("20");
        mYList.add("40");
        mYList.add("60");
        mYList.add("80");
        mYList.add("100");
        tvTitle.setText("听力图");
        tvTime.setText("绘制时间：" + DateUtil.format(newestRecord.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        mChart.setDrawGridBackground(false);
        mChart.getDescription().setEnabled(true);
        mChart.setDrawBorders(true);

        mChart.getAxisLeft().setEnabled(true);
        mChart.getXAxis().setDrawAxisLine(true);
        mChart.getXAxis().setDrawGridLines(true);
        mChart.getAxisRight().setEnabled(false);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.getXAxis().setLabelCount(mList.size(), true);
        mChart.getAxisLeft().setLabelCount(mYList.size(), true);
        mChart.getAxisLeft().setAxisMinimum(0);
        mChart.getAxisLeft().setAxisMaximum(100);


        mChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mList.get((int) value); //mList为存有月份的集合
            }
        });
        mChart.getAxisLeft().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Log.d("ddd", value + "");
                return (100 - (int) value) + "";
//                return mYList.get((int) value + mYList.size()); //mList为存有月份的集合
            }
        });
        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);


        Legend l = mChart.getLegend();
        l.setEnabled(false);
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(false);

        mChart.resetTracking();


        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        for (int z = 0; z < 2; z++) {

            ArrayList<Entry> values = new ArrayList<Entry>();

            switch (z) {
                case 0:
                    String left_data = newestRecord.getLeft_data();
                    String[] leftArray = left_data.split(",");
                    for (int i = 0; i < leftArray.length; i++) {
                        values.add(new Entry(i, 100 - new Float(leftArray[i])));
                    }

                    break;
                case 1:
                    String right_data = newestRecord.getRight_data();
                    String[] rightArray = right_data.split(",");
                    for (int i = 0; i < rightArray.length; i++) {
                        values.add(new Entry(i, 100 - new Float(rightArray[i])));
                    }
                    break;
            }

            LineDataSet d = new LineDataSet(values, "DataSet " + (z + 1));
            d.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return 100 - entry.getY() + "";
                }
            });
            d.setLineWidth(2.5f);
            d.setCircleRadius(4f);

//            int color = mColors[z % mColors.length];
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
            }

            dataSets.add(d);
        }


        LineData data = new LineData(dataSets);
        mChart.setData(data);
        mChart.invalidate();


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

    private int[] mColors = new int[]{
            ColorTemplate.VORDIPLOM_COLORS[0],
            ColorTemplate.VORDIPLOM_COLORS[1]
    };
}
