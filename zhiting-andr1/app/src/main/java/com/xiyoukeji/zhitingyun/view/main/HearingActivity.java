package com.xiyoukeji.zhitingyun.view.main;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.data.entity.TestRecordEntity;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HearingActivity extends BaseActivity {
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.chart)
    LineChart mChart;
    List<String> mList = new ArrayList<>();
    List<String> mYList = new ArrayList<>();

    private TestRecordEntity testRecordEntity;

    public HearingActivity() {
        super(R.layout.activity_hearing2);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white); }

        testRecordEntity = (TestRecordEntity) getIntent().getSerializableExtra("data");
        mList.addAll(Arrays.asList(testRecordEntity.getLeft_hertz().split(",")));
        mYList.add("0");
        mYList.add("20");
        mYList.add("40");
        mYList.add("60");
        mYList.add("80");
        mYList.add("100");


        String name = "";
        switch (testRecordEntity.getEquipmentHolder()) {
            case 1:
                name = "设备持有者";
                break;
            case 2:
                name = "其他测听者";
                break;
        }

        tvTime.setText("测试人：" + name);
        mChart.setDrawGridBackground(false);
        mChart.getDescription().setEnabled(true);
        mChart.setDrawBorders(true);

        mChart.getAxisLeft().setEnabled(true);
        mChart.getXAxis().setDrawAxisLine(true);
        mChart.getXAxis().setDrawGridLines(true);
        mChart.getAxisRight().setEnabled(false);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.getAxisLeft().setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        mChart.getXAxis().setLabelCount(mList.size(), true);
        mChart.getAxisLeft().setLabelCount(mYList.size(), true);
        mChart.getAxisLeft().setAxisMinimum(0);
        mChart.getAxisLeft().setAxisMaximum(100);


        mChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Log.d("xxx", value + "");

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


        mChart.resetTracking();


        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        for (int z = 0; z < 2; z++) {

            ArrayList<Entry> values = new ArrayList<Entry>();

            switch (z) {
                case 0:
                    String left_data = testRecordEntity.getLeft_data();
                    String[] leftArray = left_data.split(",");
                    for (int i = 0; i < leftArray.length; i++) {
                        values.add(new Entry(i, 100 - new Float(leftArray[i])));
                    }

                    break;
                case 1:
                    String right_data = testRecordEntity.getRight_data();
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
                    return 100-entry.getY() + "";
                }
            });
            d.setLineWidth(2.5f);
            d.setCircleRadius(4f);

//            int color = mColors[z % mColors.length];
            switch (z) {
                case 0:
                    d.setColor(ContextCompat.getColor(HearingActivity.this, R.color.mainColor));
                    d.setCircleColorHole(ContextCompat.getColor(HearingActivity.this, R.color.mainColor));
                    d.setCircleColor(ContextCompat.getColor(HearingActivity.this, R.color.mainColor));
                    break;
                case 1:
                    d.setColor(ContextCompat.getColor(HearingActivity.this, R.color.colorRedff));
                    d.setCircleColorHole(ContextCompat.getColor(HearingActivity.this, R.color.colorRedff));
                    d.setCircleColor(ContextCompat.getColor(HearingActivity.this, R.color.colorRedff));
                    break;
            }

            dataSets.add(d);
        }


        LineData data = new LineData(dataSets);
        mChart.setData(data);
        mChart.invalidate();


    }


    @OnClick({R.id.back_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
        }
    }
}
