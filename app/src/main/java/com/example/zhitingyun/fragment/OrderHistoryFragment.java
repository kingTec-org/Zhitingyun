package com.example.zhitingyun.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.controller.CommonController;
import com.example.zhitingyun.controller.IndoorHistoryController;
import com.example.zhitingyun.controller.MainController;
import com.example.zhitingyun.controller.OutdoorController;
import com.example.zhitingyun.listener.ControlListener;
import com.example.zhitingyun.model.EquipmentParamater;
import com.example.zhitingyun.model.OrderDetail;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
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
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.util.DateUtil;

/**
 * Created by dasiy on 2018/7/9.
 */

@SuppressLint("ValidFragment")
public class OrderHistoryFragment extends BaseFragment implements ViewsContainer.OrderHistoryView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tabs)
    QMUITabSegment mTabSegment;
    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.linearlayout)
    QMUILinearLayout linearlayout;
    @BindView(R.id.linearlayout0)
    QMUILinearLayout linearlayout0;
    @BindView(R.id.linearlayout1)
    QMUILinearLayout linearlayout1;
    @BindView(R.id.linearlayout2)
    QMUILinearLayout linearlayout2;
    @BindView(R.id.linearlayout3)
    QMUILinearLayout linearlayout3;
    int from;
    @BindView(R.id.tvTreatmentTime)
    TextView tvTreatmentTime;
    @BindView(R.id.tvReportTime)
    TextView tvReportTime;
    @BindView(R.id.tvExpert)
    TextView tvExpert;
    @BindView(R.id.tvPatient)
    TextView tvPatient;
    @BindView(R.id.tvDiscription)
    TextView tvDiscription;
    @BindView(R.id.tvReport)
    TextView tvReport;
    @BindView(R.id.chart)
    LineChart mChart;
    private HashMap<Pager, MainController> mPages;
    MainController indoorController, trafficController, outdoorController, commonController;
    private Integer id;
    Presenter presenter;
    int flag;
    EquipmentParamater indoorEquipment, trafficEquipment, outdoorEquipment, commonEquipment;

    List<String> mYList = new ArrayList<>();

    public OrderHistoryFragment(int flag, int from, int id) {
        this.flag = flag;//来自哪个页面
        this.from = from;//普通or快速
        this.id = id;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getData();
        }
    };

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.controller_history, null);
        ButterKnife.bind(this, view);
        mYList.add("0");
        mYList.add("20");
        mYList.add("40");
        mYList.add("60");
        mYList.add("80");
        mYList.add("100");

        presenter = new Presenter(this);
        switch (flag) {
            case 1:
                tvTitle.setText("历史诊疗报告");
                break;
            case 0:
                tvTitle.setText("诊疗报告");
                break;
        }
        linearlayout.setRadius(10);
        linearlayout0.setRadius(10);
        linearlayout1.setRadius(10);
        linearlayout2.setRadius(10);
        linearlayout3.setRadius(10);

        initTabs();
        initPagers();
        mChart.setDrawGridBackground(false);
        mChart.getDescription().setEnabled(true);
        mChart.setDrawBorders(true);

        mChart.getAxisLeft().setEnabled(true);
        mChart.getXAxis().setDrawAxisLine(true);
        mChart.getXAxis().setDrawGridLines(true);
        mChart.getAxisRight().setEnabled(false);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.getAxisLeft().setLabelCount(mYList.size(), true);
        mChart.getAxisLeft().setAxisMinimum(0);
        mChart.getAxisLeft().setAxisMaximum(100);

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
        mChart.setDragEnabled(false);
        mChart.setScaleEnabled(false);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);


        Legend l = mChart.getLegend();
        l.setEnabled(false);
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(false);

        mChart.resetTracking();

        handler.sendMessageDelayed(new Message(), 300);

        return view;
    }

    private void getData() {
        Map map = new HashMap();
        switch (from) {
            case 0:
                map.put("id", id);

                presenter.getOrder(map, 1);

                break;
            case 1:
                map.put("quickOrderId", id);

                presenter.getQuickOrder(map);

                break;
        }


    }

    private void initTabs() {
        int normalColor = QMUIResHelper.getAttrColor(getContext(), R.attr.qmui_config_color_gray_6);
        int selectColor = QMUIResHelper.getAttrColor(getContext(), R.attr.qmui_config_color_blue);
        mTabSegment.setDefaultNormalColor(normalColor);
        mTabSegment.setDefaultSelectedColor(selectColor);
        mTabSegment.notifyDataChanged();
        mTabSegment.addTab(new QMUITabSegment.Tab("室内"))
                .addTab(new QMUITabSegment.Tab("交通"))
                .addTab(new QMUITabSegment.Tab("户外"))
                .addTab(new QMUITabSegment.Tab("通用"));

    }

    private void initPagers() {

        mPages = new HashMap<>();
//        indoorController = new IndoorHistoryController(getContext(), 0);
//        indoorController.setListener(listener);
//        mPages.put(Pager.INDOOR, indoorController);
//
//        trafficController = new IndoorHistoryController(getContext(), 1);
//        trafficController.setListener(listener);
//        mPages.put(Pager.TRAFFIC, trafficController);
//
//        outdoorController = new IndoorHistoryController(getContext(), 2);
//        outdoorController.setListener(listener);
//        mPages.put(Pager.OUTDOOR, outdoorController);
//
//        commonController = new IndoorHistoryController(getContext(), 3);
//        commonController.setListener(listener);
//        mPages.put(Pager.COMMON, commonController);


        mViewPager.setAdapter(mPagerAdapter);
        mTabSegment.setupWithViewPager(mViewPager, false);
//        mViewPager.setCurrentItem(0);

    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {

        private int mChildCount = 0;

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return mPages.size();
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
            MainController page = mPages.get(Pager.getPagerFromPositon(position));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(page, params);
            return page;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            if (mChildCount == 0) {
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
        }

        @Override
        public void notifyDataSetChanged() {
            mChildCount = getCount();
            super.notifyDataSetChanged();
        }
    };

    @OnClick({R.id.imgBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                popBackStack();
                break;
        }

    }

    @Override
    public void getOrder(OrderDetail orderDetail) {
        List<EquipmentParamater> equipmentParamaters = orderDetail.getEquParamBefore();
        for (int i = 0; i < equipmentParamaters.size(); i++) {
            switch (equipmentParamaters.get(i).getClassify()) {
                case 1:
                    indoorEquipment = equipmentParamaters.get(i);
                    break;
                case 2:
                    trafficEquipment = equipmentParamaters.get(i);
                    break;
                case 3:
                    outdoorEquipment = equipmentParamaters.get(i);
                    break;
                case 4:
                    commonEquipment = equipmentParamaters.get(i);
                    break;
            }
        }
//        ((IndoorHistoryController) indoorController).setEquipmentParamater(indoorEquipment);
//        ((IndoorHistoryController) trafficController).setEquipmentParamater(trafficEquipment == null ? indoorEquipment : trafficEquipment);
//        ((IndoorHistoryController) outdoorController).setEquipmentParamater(outdoorEquipment == null ? indoorEquipment : outdoorEquipment);
//        ((IndoorHistoryController) commonController).setEquipmentParamater(commonEquipment == null ? indoorEquipment : commonEquipment);

        ControlListener listener = new ControlListener() {
            @Override
            public void startFragment(BaseFragment fragment) {
                OrderHistoryFragment.this.startFragment(fragment);
            }
        };


        indoorController = new IndoorHistoryController(getContext(), 0, indoorEquipment);
        indoorController.setListener(listener);
        mPages.put(Pager.INDOOR, indoorController);

        trafficController = new IndoorHistoryController(getContext(), 1, trafficEquipment == null ? indoorEquipment : trafficEquipment);
        trafficController.setListener(listener);
        mPages.put(Pager.TRAFFIC, trafficController);

        outdoorController = new IndoorHistoryController(getContext(), 2, outdoorEquipment == null ? indoorEquipment : outdoorEquipment);
        outdoorController.setListener(listener);
        mPages.put(Pager.OUTDOOR, outdoorController);

        commonController = new IndoorHistoryController(getContext(), 3, commonEquipment == null ? indoorEquipment : commonEquipment);
        commonController.setListener(listener);
        mPages.put(Pager.COMMON, commonController);
        mPagerAdapter.notifyDataSetChanged();


        tvTreatmentTime.setText("诊疗时间：" + DateUtil.format(orderDetail.getCureTimeStart(), "yyyy.MM.dd HH:mm") + "-" + DateUtil.format(orderDetail.getCureTimeEnd(), "HH:mm"));
        tvReportTime.setText("报告时间：" + DateUtil.format(orderDetail.getCureTimeStart(), "yyyy.MM.dd HH:mm"));
        tvExpert.setText("接诊专家：" + orderDetail.getProfessorDto().getName());
        tvPatient.setText("患者：" + orderDetail.getUserDto().getName());
        tvDiscription.setText(orderDetail.getProblemDesc());
        tvReport.setText(orderDetail.getProfessorReport());

//        switch (from) {
//            case 0:
//                tvTreatmentTime.setText("诊疗时间：" + DateUtil.format(orderDetail.getCureTimeStart(), "yyyy.MM.dd HH:mm") + "  " + DateUtil.format(orderDetail.getCureTimeStart(), "HH:mm"));
//                tvReportTime.setText("报告时间：" + DateUtil.format(orderDetail.getCureTimeStart(), "yyyy.MM.dd HH:mm"));
//                tvExpert.setText("接诊专家：" + orderDetail.getProfessorDto().getName());
//                tvPatient.setText("患者：" + orderDetail.getUserDto().getName());
//                tvDiscription.setText(orderDetail.getProblemDesc());
//                tvReport.setText(orderDetail.getProfessorReport());
//                break;
//            case 1:
//                tvTreatmentTime.setText("预约时间：");
//                tvReportTime.setText("取消诊疗时间：");
//                tvExpert.setText("接诊专家：");
//                tvPatient.setText("患者：");
//                break;
//
//        }

        final List<String> mList = new ArrayList<>();
        if (orderDetail.getUserNewestRecord() != null)
            mList.addAll(Arrays.asList(orderDetail.getUserNewestRecord().getLeft_hertz().split(",")));
        else if (orderDetail.getUserNewestListenRecord() != null)
            mList.addAll(Arrays.asList(orderDetail.getUserNewestListenRecord().getLeft_hertz().split(",")));
        mChart.getXAxis().setLabelCount(mList.size(), true);


        mChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mList.get((int) value); //mList为存有月份的集合
            }
        });

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

        for (int z = 0; z < 2; z++) {

            ArrayList<Entry> values = new ArrayList<Entry>();

            switch (z) {
                case 0:
                    String left_data = "";
                    if (orderDetail.getUserNewestRecord() != null)
                        left_data = orderDetail.getUserNewestRecord().getLeft_data();
                    else if (orderDetail.getUserNewestListenRecord() != null)
                        left_data = orderDetail.getUserNewestListenRecord().getLeft_data();
                    String[] leftArray = left_data.split(",");
                    for (int i = 0; i < leftArray.length; i++) {
                        values.add(new Entry(i, 100 - new Float(leftArray[i])));
                    }

                    break;
                case 1:
                    String right_data = "";
                    if (orderDetail.getUserNewestRecord() != null)
                        right_data = orderDetail.getUserNewestRecord().getRight_data();
                    else if (orderDetail.getUserNewestListenRecord() != null)
                        right_data = orderDetail.getUserNewestListenRecord().getRight_data();
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


    }


    enum Pager {
        INDOOR, TRAFFIC, OUTDOOR, COMMON;

        public static Pager getPagerFromPositon(int position) {
            switch (position) {
                case 0:
                    return INDOOR;
                case 1:
                    return TRAFFIC;
                case 2:
                    return OUTDOOR;
                case 3:
                    return COMMON;
                default:
                    return INDOOR;
            }
        }
    }

}
