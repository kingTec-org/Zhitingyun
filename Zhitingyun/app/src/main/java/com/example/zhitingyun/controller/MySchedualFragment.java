package com.example.zhitingyun.controller;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhitingyun.R;
import com.example.zhitingyun.adapter.SchedualItemAdapter;
import com.example.zhitingyun.adapter.SchedualListAdapter;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.model.Schedual;
import com.example.zhitingyun.model.SchedualItem;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.util.DateUtil;
import coder.mylibrary.util.ToastUtil;

import static com.qmuiteam.qmui.layout.IQMUILayout.HIDE_RADIUS_SIDE_BOTTOM;
import static com.qmuiteam.qmui.layout.IQMUILayout.HIDE_RADIUS_SIDE_TOP;

/**
 * Created by dasiy on 2018/7/12.
 */

public class MySchedualFragment extends BaseFragment implements ViewsContainer.MySchedualView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    List<Schedual> scheduals = new ArrayList<>();
    Integer today, firstDay;
    SchedualListAdapter schedualListAdapter;
    SchedualItemAdapter schedualItemAdapter;
    HeadHolder headHolder;
    FootHolder footHolder;
    List<SchedualItem> schedualItems = new ArrayList<>();
    ArrayList<String> options2Items_01 = new ArrayList<>();
    ArrayList<String> options2Items_02 = new ArrayList<>();
    ArrayList<String> options2Items_03 = new ArrayList<>();
    Long firstMillions = 0L, endMillions = 0L, todayMillion = 0L;
    OptionsPickerView pvOptions;
    Integer addDayPosition = 0, selectPosition0 = -1, selectPosition2 = -1;
    Presenter presenter;
    Date firstDate = null;
    Calendar firstCalendar = null, changeCalendar = null, changeCalendar0 = null;


    private void getData() {
//        Long startTime = getMillion(firstDay, 0, 0, 0);
//        Long endTime = getMillion(firstDay + 7, 24, 0, 0);

        Map map = new HashMap();
        map.put("startTime", firstMillions);
        map.put("endTime", endMillions);
        presenter.getScheduleList(map);
    }

    private void addData() {
        Long startTime = getCurrentMillion(addDayPosition, new Integer(options2Items_01.get(selectPosition0)), new Integer(options2Items_03.get(selectPosition2)), 0);
        Long endTime = getCurrentMillion(addDayPosition, new Integer(options2Items_01.get(selectPosition0)) + 1, new Integer(options2Items_03.get(selectPosition2)), 0);
        Log.d("sss", startTime + "");
        Log.d("sss", endTime + "");
        Map map = new HashMap();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        presenter.addSchedule(map);

    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_my_schedual, null);
        ButterKnife.bind(this, view);
        tvTitle.setText("我的排班");
        presenter = new Presenter(this);
        options2Items_01.add("00");
        options2Items_01.add("01");
        options2Items_01.add("02");
        options2Items_01.add("03");
        options2Items_01.add("04");
        options2Items_01.add("05");
        options2Items_01.add("06");
        options2Items_01.add("07");
        options2Items_01.add("08");
        options2Items_01.add("09");
        options2Items_01.add("10");
        options2Items_01.add("11");
        options2Items_01.add("12");
        options2Items_01.add("13");
        options2Items_01.add("14");
        options2Items_01.add("15");
        options2Items_01.add("16");
        options2Items_01.add("17");
        options2Items_01.add("18");
        options2Items_01.add("19");
        options2Items_01.add("20");
        options2Items_01.add("21");
        options2Items_01.add("22");
        options2Items_01.add("23");
        options2Items_02.add(":");
        options2Items_03.add("00");
        options2Items_03.add("15");
        options2Items_03.add("30");
        options2Items_03.add("45");


        getToday();
        getFirstDayOfWeek();
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        schedualItemAdapter = new SchedualItemAdapter(schedualItems);
        schedualListAdapter = new SchedualListAdapter(scheduals);
        schedualItemAdapter.bindToRecyclerView(recycler);
        final View head = View.inflate(getContext(), R.layout.item_schedul_head, null);
        schedualItemAdapter.setHeaderView(head);
        headHolder = new HeadHolder(head);
        final View foot = View.inflate(getContext(), R.layout.item_schedual_foot, null);
        schedualItemAdapter.setFooterView(foot);

        schedualItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                Log.d("sss", position + "");
                if (schedualItems.get(position).getStatus() == 1) {
                    new QMUIDialog.MessageDialogBuilder(getActivity())
                            .setTitle("提示")
                            .setMessage("是否删除本条预约时间")
                            .addAction("取消", new QMUIDialogAction.ActionListener() {
                                @Override
                                public void onClick(QMUIDialog dialog, int index) {
                                    dialog.dismiss();
                                }
                            })
                            .addAction("删除", new QMUIDialogAction.ActionListener() {
                                @Override
                                public void onClick(QMUIDialog dialog, int index) {
                                    dialog.dismiss();
                                    delete(schedualItems.get(position).getId());

                                }
                            })

                            .create(com.qmuiteam.qmui.R.style.QMUI_Dialog).show();
                }

            }
        });

        footHolder = new FootHolder(foot);

        headHolder.tvWeek.setText("本周");
        headHolder.imgArrTime.setBackgroundResource(R.mipmap.down_white);
        headHolder.linearlayout0.setRadius(10, HIDE_RADIUS_SIDE_TOP);
        headHolder.linearlayout.setRadius(10, HIDE_RADIUS_SIDE_BOTTOM);
        headHolder.recycler.setLayoutManager(new GridLayoutManager(getContext(), 7));
        schedualListAdapter.bindToRecyclerView(headHolder.recycler);

        initData();


        schedualListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                addDayPosition = position;
                headHolder.tvTime.setText(scheduals.get(position).getStrDate());
                for (int i = 0; i < scheduals.size(); i++) {
                    Schedual schedual = scheduals.get(i);
                    if (i != position && schedual.getStatus() == 2)
                        schedual.setStatus(3);
//                    else if (i!=position && schedual.getStatus() == 0)
//                        schedual.setStatus(4);
                    else if (i != position && schedual.getStatus() == 4)
                        schedual.setStatus(0);
//                    else if (i == position && schedual.getStatus() == 4)
//                        schedual.setStatus(0);
                    else if (i == position && schedual.getStatus() == 0)
                        schedual.setStatus(4);
//                    else if (i == position && schedual.getStatus() == 5) {
//
//                    }
                    else if (i == position && schedual.getStatus() == 3)
                        schedual.setStatus(2);
                    scheduals.set(i, schedual);

                }

                adapter.notifyDataSetChanged();
//                if (scheduals.get(position).getSchedualItems().size() > 0) {
                schedualItems.clear();
                schedualItems.addAll(scheduals.get(position).getSchedualItems());
                if (schedualItems.size() % 2 != 0)
                    schedualItems.add(new SchedualItem("", 4));
                schedualItemAdapter.notifyDataSetChanged();


//                }
                if (scheduals.get(position).getCanAdd() == 0)
                    footHolder.btAdd.setVisibility(View.GONE);
                else
                    footHolder.btAdd.setVisibility(View.VISIBLE);

            }
        });
        pvOptions = new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                selectPosition0 = options1;
                selectPosition2 = options3;
                addData();

//                scheduals.get(addDayPosition).getSchedualItems().add(new SchedualItem(options2Items_01.get(options1) + options2Items_02.get(0) + options2Items_03.get(options3) + "-" + options2Items_01.get(options1 + 1) + options2Items_02.get(0) + options2Items_03.get(options3), 1));
//                schedualItems.clear();
//                schedualItems.addAll(scheduals.get(addDayPosition).getSchedualItems());
//                if (schedualItems.size() % 2 == 1)
//                    schedualItems.add(new SchedualItem("", 3));
//                schedualItemAdapter.notifyDataSetChanged();
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1)
//                        + options2Items.get(options1).get(options2)
//                       /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
//                tvLast.setText(tx);
            }
        })
                .setTitleText("预约时间")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.GRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.WHITE)
                .setTitleColor(Color.BLACK)
                .setCancelColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary))
                .setSubmitColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary))
                .setTextColorCenter(Color.BLACK)
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
                    }
                })
                .build();
//        pvOptions.setPicker(options2Items_01);
//        pvOptions.setPicker(options2Items_02);

//        pvOptions.setSelectOptions(1,1);
        /*pvOptions.setPicker(options1Items);//一级选择器*/
        pvOptions.setNPicker(options2Items_01, options2Items_02, options2Items_03);//二级选择器
        Log.d("vvv", today + "");
        Log.d("vvv", firstDay + "");
        getData();
        return view;
    }

    private void delete(Integer id) {
        Map map = new HashMap();
        map.put("id", id);
        presenter.deleteSchedule(map);
    }

    private void repeat() {
        Map map = new HashMap();
        map.put("startTime", firstMillions);
        map.put("endTime", endMillions);
        presenter.repeatSchedule(map);

    }

    private void initData() {
//        List<SchedualItem> list = new ArrayList<>();
//        list.add(new SchedualItem("9:30-10:30", 0));
//        list.add(new SchedualItem("10:30-11:30", 1));
//        list.add(new SchedualItem("11:30-12:30", 0));
        scheduals.clear();
        for (int i = 0; i < 7; i++) {
            Calendar tempCalendar = Calendar.getInstance();
//            tempCalendar.set(Calendar.DAY_OF_WEEK, firstCalendar.get(Calendar.DAY_OF_WEEK));
            tempCalendar.set(Calendar.DAY_OF_MONTH, firstCalendar.get(Calendar.DAY_OF_MONTH));
            tempCalendar.set(Calendar.HOUR_OF_DAY, firstCalendar.get(Calendar.HOUR_OF_DAY));
            tempCalendar.set(Calendar.MINUTE, firstCalendar.get(Calendar.MINUTE));
            tempCalendar.set(Calendar.SECOND, firstCalendar.get(Calendar.SECOND));

            tempCalendar.add(Calendar.DATE, i);
            int date = tempCalendar.get(Calendar.DAY_OF_MONTH);
            String ymd = new SimpleDateFormat("yyyy年MM月dd日").format(tempCalendar.getTime());

            if (Math.abs(Math.abs(firstMillions + i * 24 * 3600 * 1000) - Math.abs(todayMillion)) < 1000)
                scheduals.add(new Schedual(date, 0, ymd, getCalendarWeek(tempCalendar), 1));
            else if (firstMillions + i * 24 * 3600 * 1000 < todayMillion)
                scheduals.add(new Schedual(date, 3, ymd, getCalendarWeek(tempCalendar), 0));
            else
                scheduals.add(new Schedual(date, 3, ymd, getCalendarWeek(tempCalendar), 1));

//            if (firstDay + i == today)
//                scheduals.add(new Schedual(firstDay + i, 3, getYMD(firstDay + i), getWeek(firstDay + i), 1));
//            else if (i == 0)
//                scheduals.add(new Schedual(firstDay + i, 3, getYMD(firstDay + i), getWeek(firstDay + i), 1, list));
//            else
//                scheduals.add(new Schedual(firstDay + i, 3, getYMD(firstDay + i), getWeek(firstDay + i), 0));
        }
        headHolder.tvTime.setText(scheduals.get(addDayPosition).getStrDate());
        if (scheduals.get(0).getCanAdd() == 0)
            footHolder.btAdd.setVisibility(View.GONE);
        else
            footHolder.btAdd.setVisibility(View.VISIBLE);

        schedualListAdapter.notifyDataSetChanged();

    }

    private void getToday() {
        Calendar cal = Calendar.getInstance();
        Date date1 = new Date();
        cal.setTime(date1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        today = cal.get(Calendar.DAY_OF_MONTH);
        todayMillion = cal.getTimeInMillis();
    }

    private void getFirstDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        firstDay = cal.get(Calendar.DAY_OF_MONTH);
        firstCalendar = cal;
        changeCalendar = cal;
        firstDate = cal.getTime();
        firstMillions = cal.getTimeInMillis();
        endMillions = firstMillions + 24 * 60 * 60 * 7 * 1000;

        changeCalendar = Calendar.getInstance();
        changeCalendar.set(Calendar.DAY_OF_WEEK, firstCalendar.get(Calendar.DAY_OF_WEEK));
        changeCalendar.set(Calendar.HOUR_OF_DAY, firstCalendar.get(Calendar.HOUR_OF_DAY));
        changeCalendar.set(Calendar.MINUTE, firstCalendar.get(Calendar.MINUTE));
        changeCalendar.set(Calendar.SECOND, firstCalendar.get(Calendar.SECOND));

    }

    private String getYMD(int day) {
        Calendar cal = Calendar.getInstance();
        Date date1 = new Date();
        cal.setTime(date1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        Date date = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);

    }

    private Long getMillion(int day, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        Date date1 = new Date();
        cal.setTime(date1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        return cal.getTimeInMillis();

    }

    private Long getCurrentMillion(int addDayPosition, int hour, int minute, int second) {
        Calendar addPositionCalendar = Calendar.getInstance();
        addPositionCalendar.setTime(firstCalendar.getTime());
        addPositionCalendar.add(Calendar.DATE, addDayPosition);
//        Calendar cal = Calendar.getInstance();
//        Date date1 = new Date();
//        cal.setTime(date1);
//        cal.set(Calendar.DAY_OF_MONTH, day);
//        cal.set(Calendar.HOUR, hour);
//        cal.set(Calendar.MINUTE, minute);
//        cal.set(Calendar.SECOND, second);

        Long aLong = DateUtil.date2TimeStamp(addPositionCalendar.get(Calendar.YEAR) + "-" + (addPositionCalendar.get(Calendar.MONTH) + 1) + "-" + addPositionCalendar.get(Calendar.DAY_OF_MONTH) + " " + hour + ":" + minute + ":" + second, null);
        return aLong;
    }


    @OnClick(R.id.imgBack)
    public void onViewClicked() {
        popBackStack();
    }

    @Override
    public void addSchedule() {
        Log.d("sss", "aaa");
        getData();
        schedualItemAdapter.notifyDataSetChanged();

    }

    @Override
    public void getScheduleList(List<SchedualItem> list) {
        for (int i = 0; i < scheduals.size(); i++)
            scheduals.get(i).getSchedualItems().clear();
//        Long startTime = getMillion(firstDay, 0, 0, 0);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Integer belongTo = new Long((list.get(i).getEnd() - firstMillions) / 24 / 3600 / 1000).intValue();
                if (Math.abs(list.get(i).getEnd() - firstMillions - 24 * 3600 * 1000 * 7) < 1000)
                    belongTo = belongTo - 1;
                Schedual schedual = scheduals.get(belongTo);
                List<SchedualItem> list1 = schedual.getSchedualItems();
                list1.add(list.get(i));
                schedual.setSchedualItems(list1);
                if (firstMillions + belongTo * 24 * 3600 * 1000 == todayMillion)
                    schedual.setStatus(5);
                else
                    schedual.setStatus(1);
            }
        }

//        int initDataPosition = 0;
//        if (addDayPosition != -1)
//            initDataPosition = addDayPosition;
        schedualItems.clear();
        schedualItems.addAll(scheduals.get(addDayPosition).getSchedualItems());
        if (schedualItems.size() % 2 == 1)
            schedualItems.add(new SchedualItem("", 4));
        schedualListAdapter.notifyDataSetChanged();
        schedualItemAdapter.notifyDataSetChanged();
        if (scheduals.get(addDayPosition).getCanAdd() == 1)
            footHolder.btAdd.setVisibility(View.VISIBLE);


        schedualItemAdapter.notifyDataSetChanged();

    }

    @Override
    public void deleteSchedule() {
        initData();
        schedualListAdapter.notifyDataSetChanged();
        getData();
        schedualItemAdapter.notifyDataSetChanged();

    }

    @Override
    public void repeatSchedule() {
        Log.d("aaa", "aaa");
        ToastUtil.showShort("重复成功");
        initData();
        schedualListAdapter.notifyDataSetChanged();
        getData();
        schedualItemAdapter.notifyDataSetChanged();


    }

    class HeadHolder {
        @BindView(R.id.recycler)
        RecyclerView recycler;
        @BindView(R.id.tvWeek)
        TextView tvWeek;
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.imgArrTime)
        ImageView imgArrTime;
        @BindView(R.id.linearlayout)
        QMUILinearLayout linearlayout;
        @BindView(R.id.linearlayout0)
        QMUILinearLayout linearlayout0;

        public HeadHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @OnClick({R.id.imgArrTime, R.id.tvRepeat})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.imgArrTime:
                    imgArrTime.setBackgroundResource(R.mipmap.up_white);
                    final String[] items = new String[]{"本周", "下周"};

                    QMUIDialog dialog = new QMUIDialog.MenuDialogBuilder(getContext())
                            .addItems(items, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    tvWeek.setText(items[which]);
                                    switch (which) {
                                        case 0:
                                            firstCalendar.setTime(changeCalendar.getTime());
//                                            firstCalendar.set(Calendar.DAY_OF_MONTH, changeCalendar.get(Calendar.DAY_OF_MONTH));
//                                            firstCalendar.set(Calendar.HOUR_OF_DAY, changeCalendar.get(Calendar.HOUR_OF_DAY));
//                                            firstCalendar.set(Calendar.MINUTE, changeCalendar.get(Calendar.MINUTE));
//                                            firstCalendar.set(Calendar.SECOND, changeCalendar.get(Calendar.SECOND));
//                                    firstCalendar.add(Calendar.DATE, -7);
                                            firstDay = firstCalendar.get(Calendar.DAY_OF_MONTH);
                                            firstDate = firstCalendar.getTime();
                                            firstMillions = firstCalendar.getTimeInMillis();
                                            endMillions = firstMillions + 24 * 60 * 60 * 7 * 1000;
                                            initData();
                                            getData();

                                            break;
                                        case 1:
                                            firstCalendar.setTime(changeCalendar.getTime());
//                                            Calendar nextWeek = Calendar.getInstance();
//                                            nextWeek.setTime(firstCalendar.getTime());

//                                            firstCalendar.set(Calendar.DAY_OF_MONTH, changeCalendar.get(Calendar.DAY_OF_MONTH));
//                                            firstCalendar.set(Calendar.HOUR_OF_DAY, changeCalendar.get(Calendar.HOUR_OF_DAY));
//                                            firstCalendar.set(Calendar.MINUTE, changeCalendar.get(Calendar.MINUTE));
//                                            firstCalendar.set(Calendar.SECOND, changeCalendar.get(Calendar.SECOND));

//                                    changeCalendar = Calendar.getInstance();
//                                    changeCalendar.set(Calendar.DAY_OF_WEEK, firstCalendar.get(Calendar.DAY_OF_WEEK));
//                                    changeCalendar.set(Calendar.HOUR_OF_DAY, firstCalendar.get(Calendar.HOUR_OF_DAY));
//                                    changeCalendar.set(Calendar.MINUTE, firstCalendar.get(Calendar.MINUTE));
//                                    changeCalendar.set(Calendar.SECOND, firstCalendar.get(Calendar.SECOND));
                                            firstCalendar.add(Calendar.DATE, 7);
                                            firstDay = firstCalendar.get(Calendar.DAY_OF_MONTH);
                                            firstDate = firstCalendar.getTime();
                                            firstMillions = firstCalendar.getTimeInMillis();
                                            endMillions = firstMillions + 24 * 60 * 60 * 7 * 1000;
                                            initData();
                                            getData();
                                            break;
                                    }
//                            initData();
//                            schedualListAdapter.notifyDataSetChanged();

//                            getData();

                                }
                            })
                            .show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            imgArrTime.setBackgroundResource(R.mipmap.down_white);
                        }
                    });
                    break;
                case R.id.tvRepeat:
                    repeat();
                    break;
            }


        }
    }

    class FootHolder {
        @BindView(R.id.btAdd)
        QMUIRoundButton btAdd;

        public FootHolder(View view) {
            ButterKnife.bind(this, view);
        }


        @OnClick(R.id.btAdd)
        public void onViewClicked() {
            pvOptions.show();

        }

    }

    private String getCalendarWeek(Calendar calendar) {
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";

        }
    }

    private String getWeek(int day) {
        Calendar cal = Calendar.getInstance();
        Date date1 = new Date();
        cal.setTime(date1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";

        }
    }
}
