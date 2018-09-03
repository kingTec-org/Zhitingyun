package com.example.zhitingyun.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.activity.TreattingActivity;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.model.EquipmentParamater;
import com.example.zhitingyun.model.MessageEvent;
import com.example.zhitingyun.model.OrderDetail;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.example.zhitingyun.widget.WS;
import com.example.zhitingyun.yunxin.NimUIKit;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.base.ProjectConfig;
import coder.mylibrary.util.DateUtil;
import coder.mylibrary.util.ToastUtil;

/**
 * Created by dasiy on 2018/7/8.
 */

@SuppressLint("ValidFragment")
public class OrderDetailFragment extends BaseFragment implements ViewsContainer.OrderDetailView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvFunction)
    TextView tvFunction;
    @BindView(R.id.tvTip)
    TextView tvTip;
    @BindView(R.id.imgAvatar)
    QMUIRadiusImageView imgAvatar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvSex)
    TextView tvSex;
    @BindView(R.id.tvAge)
    TextView tvAge;
    @BindView(R.id.tvExperience)
    TextView tvExperience;
    @BindView(R.id.tvTimes)
    TextView tvTimes;
    @BindView(R.id.linearlayout)
    QMUILinearLayout linearlayout;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.linearlayout0)
    QMUILinearLayout linearlayout0;
    @BindView(R.id.tvDiscription)
    TextView tvDiscription;
    @BindView(R.id.linearlayout1)
    QMUILinearLayout linearlayout1;
    @BindView(R.id.linearlayout2)
    QMUILinearLayout linearlayout2;
    @BindView(R.id.linearlayout3)
    QMUILinearLayout linearlayout3;
    @BindView(R.id.linearlayout4)
    QMUILinearLayout linearlayout4;
    @BindView(R.id.btRefuse)
    QMUIRoundButton btRefuse;
    @BindView(R.id.btAccept)
    QMUIRoundButton btAccept;
    @BindView(R.id.btStart)
    QMUIRoundButton btStart;
    @BindView(R.id.btCancel)
    QMUIRoundButton btCancel;
    @BindView( R.id.btUnStart )
    QMUIRoundButton btUnStart;
    @BindView(R.id.llTip)
    LinearLayout llTip;
    @BindView(R.id.llShow)
    ScrollView llShow;
    @BindView(R.id.llGone)
    LinearLayout llGone;
    int from;//0未确认的 1已确认的 2线上诊疗
    int id;
    Presenter presenter;
    OrderDetail orderDetail;

    public OrderDetailFragment(int id, int from) {
        this.id = id;
        this.from = from;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getBaseFragmentActivity().startFragment(new WriteReportFragment(1,orderDetail.getId(), orderDetail.getClassify()));

        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
//        if (messageEvent.getFlag() == 1) {
//            btStart.setVisibility(View.GONE);
//            btCancel.setVisibility(View.GONE);
//            btAccept.setVisibility(View.GONE);
//            btRefuse.setVisibility(View.GONE);
//
//
//
//
//        }
        if (messageEvent.getFlag() == 3) {
            /*用户接通之后状态变为开始诊疗*/
            if (orderDetail.getStatus() == 1) {
                Map map = new HashMap();
                map.put("orderId", orderDetail.getId());
                presenter.startCure(map);
            }


        } else if (messageEvent.getFlag() == 4) {
            /*接通过程中异常 再次拨打用户未接通*/
            btStart.setText("继续诊疗");

        } else if (messageEvent.getFlag() == 5) {

            btStart.setVisibility(View.GONE);
            btCancel.setVisibility(View.GONE);
            btAccept.setVisibility(View.GONE);
            btRefuse.setVisibility(View.GONE);
            btUnStart.setVisibility( View.GONE );
            Map map = new HashMap();
            map.put("orderId", orderDetail.getId());
            map.put("classify", orderDetail.getClassify());
            presenter.finishCure(map, 0);


//
//            switch (orderDetail.getClassify()) {
//                case 1:
////                    new Thread(new Runnable() {
////                        @Override
////                        public void run() {
////                            try {
////                                Thread.sleep(100);
////
////
////                            } catch (Exception e) {
////                                e.printStackTrace();
////                            }
////                        }
////                    }).start();
//                    Message message = new Message();
//                    message.what = 1;
//                    handler.sendMessageDelayed(message, 100);
//                    break;
//                case 2:
//                    Map map = new HashMap();
//                    Log.d("ggg", orderDetail.getId() + "");
//                    map.put("orderId", orderDetail.getId());
//                    map.put("classify", "");
//                    presenter.finishCure(map, 0);
//                    break;
//            }


        } else if (messageEvent.getFlag() == 6) {
//            挂断电话  or  首次未接通 or 对方忙碌
            if (orderDetail.getClassify() == 1) {//快速诊疗
                switch (orderDetail.getStatus()) {
                    case 1:
                        /*未接通状态*/
                        btStart.setText("开始诊疗");
                        break;

                    case 2:
                        /*诊疗过程中挂断*/
                        btStart.setText("继续诊疗");

                        break;


                }


            } else if (orderDetail.getClassify() == 2) {
                switch (orderDetail.getStatus()) {
                    case 1:
                        /*未接通状态*/
                        btStart.setText("开始诊疗");
                        break;

                    case 3:
                        /*诊疗过程中挂断*/
                        btStart.setText("继续诊疗");

                        break;


                }
            }


        } else if (messageEvent.getFlag() == 100)
            Log.d("ccc", messageEvent.toString());
    }

    @Override
    protected View onCreateView() {
        super.onCreateView();
        View view = View.inflate(getActivity(), R.layout.fragment_order_detail, null);
        ButterKnife.bind(this, view);
        presenter = new Presenter(this);
        linearlayout.setRadius(10);
        linearlayout0.setRadius(10);
        linearlayout1.setRadius(10);
        linearlayout2.setRadius(10);
        linearlayout3.setRadius(10);
        linearlayout4.setRadius(10);
        switch (from) {
            case 0:
//                预约列表
                btRefuse.setVisibility(View.GONE);
                btAccept.setVisibility(View.GONE);
                btStart.setVisibility(View.GONE);
                btCancel.setVisibility(View.GONE);
                llTip.setVisibility(View.GONE);
                btUnStart.setVisibility( View.GONE );

                tvTitle.setText("预约详情");
                tvFunction.setVisibility(View.GONE);
                break;
            case 1:
                btRefuse.setVisibility(View.GONE);
                btAccept.setVisibility(View.GONE);
                btStart.setVisibility(View.VISIBLE);
                btCancel.setVisibility(View.VISIBLE);
                llTip.setVisibility(View.GONE);
                btUnStart.setVisibility( View.GONE );

                tvTitle.setText("预约详情");
                tvFunction.setVisibility(View.GONE);
                break;
            case 2:

                btRefuse.setVisibility(View.GONE);
                btAccept.setVisibility(View.GONE);
                btStart.setVisibility(View.VISIBLE);
                btCancel.setVisibility(View.VISIBLE);
                btUnStart.setVisibility( View.GONE );

                llTip.setVisibility(View.GONE);
                tvTitle.setText("线上诊疗");
                tvFunction.setVisibility(View.VISIBLE);
                break;
        }
        getData();


        return view;
    }

    private void getData() {
        Map map = new HashMap();
        if (from == 2) {
            presenter.getRecentlyOrder(map);

        } else {
            map.put("id", id);
            presenter.getOrder(map, 0);
        }


    }


    @OnClick({R.id.tvFunction, R.id.btCancel, R.id.imgBack, R.id.linearlayout2, R.id.linearlayout3, R.id.linearlayout4, R.id.btRefuse, R.id.btAccept, R.id.btStart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                popBackStack();
                break;
            case R.id.linearlayout2:
                if (orderDetail.getUserNewestRecord() != null)
                    startFragment(new DetailHearingFragment(orderDetail.getUserNewestRecord()));
                else if (orderDetail.getUserNewestListenRecord() != null)
                    startFragment(new DetailHearingFragment(orderDetail.getUserNewestListenRecord()));
                else
                    ToastUtil.showShort("无听力数据");
                break;
            case R.id.linearlayout3:
                startFragment(new DetailEquipmentFragment(orderDetail.getEquParamBefore()));
                break;
            case R.id.linearlayout4:
                if (orderDetail.getHistoryOrderId() != null) {
                    startFragment(new OrderHistoryFragment(1,orderDetail.getHistoryOrderType() - 1, orderDetail.getHistoryOrderId()));
                } else
                    ToastUtil.showShort("无历史诊疗记录");
                break;
            case R.id.btRefuse:
//                WS.getInStanceBlock().send("hi~~~");
//                Map map0 = new HashMap();
//                map0.put("equipmentIds", "43,44,45,46");
//                presenter.getParamaterList(map0, 1);


                startFragment(new OrderRefuseFragment(orderDetail.getId()));
                break;
            case R.id.btAccept:
                Map map = new HashMap();
                map.put("id", orderDetail.getId());
                presenter.receiveOrder(map);
                break;
            case R.id.btStart:
                switch (orderDetail.getClassify()) {
                    case 1://快速
                        getActivity().startActivity(new Intent(getActivity(), TreattingActivity.class).putExtra("orderDetail", (Serializable) orderDetail).putExtra("receiverId", this.orderDetail.getUserAccid()).putExtra("from", 2));
                        break;
                    case 2://普通
                        Map map1 = new HashMap();
                        map1.put("orderId", orderDetail.getId());
                        presenter.recordCure(map1);
                        break;
                }

//                if (orderDetail.getStatus() == 1) {
//                    Map map1 = new HashMap();
//                    map1.put("orderId", orderDetail.getId());
//                    presenter.recordCure(map1);
//                } else {
//                    getActivity().startActivity(new Intent(getActivity(), TreattingActivity.class).putExtra("orderDetail", (Serializable) orderDetail).putExtra("receiverId", this.orderDetail.getUserAccid()));
//                }

                break;
            case R.id.btCancel:
                Map map2 = new HashMap();
                map2.put("classify", orderDetail.getClassify());
                map2.put("orderId", orderDetail.getId());
                if (orderDetail.getClassify() == 1) {
                    presenter.cancelOrder(map2);
                } else if (orderDetail.getClassify() == 2) {
                    switch (orderDetail.getStatus()) {
                        case 1:
                        /*未接通状态取消诊疗*/
                            presenter.cancelHaveNotBegunOrder(map2);
                            break;
                        case 3:
                        /*诊疗过程中取消诊疗*/
                            presenter.cancelOrder(map2);
                            break;
                    }
                }
//                startFragment(new OrderRefuseFragment(orderDetail.getId()));
                break;
            case R.id.tvFunction:
                startFragment(new OrderListFragment());
                break;
        }
    }


    @Override
    public void getOrder(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
        if (from == 0) {
            switch (orderDetail.getStatus()) {
                case 0://正在预约，等待接受
                    btRefuse.setVisibility(View.VISIBLE);
                    btAccept.setVisibility(View.VISIBLE);
                    btStart.setVisibility(View.GONE);
                    btCancel.setVisibility(View.GONE);
                    btUnStart.setVisibility( View.GONE );

                    break;
                case 1://预约成功 接受预约
//                    btRefuse.setVisibility(View.GONE);
//                    btAccept.setVisibility(View.GONE);
//                    btStart.setVisibility(View.VISIBLE);
//                    btCancel.setVisibility(View.VISIBLE);
//                    btUnStart.setVisibility( View.GONE );
                    Date curDate =new Date(System.currentTimeMillis());
                    Date startTime=new Date( orderDetail.getOrderTimeStart() );
                    long yy=startTime.getTime()-curDate.getTime();

                    if(yy/1000/60<0){
                        btRefuse.setVisibility( View.GONE );
                        btAccept.setVisibility( View.GONE );
                        btStart.setVisibility( View.VISIBLE );
                        btCancel.setVisibility( View.VISIBLE );
                        btUnStart.setVisibility( View.GONE );
                    }
//                    else if(yy/1000/60>0&&yy/1000/60<=10) {
//                        btRefuse.setVisibility( View.GONE );
//                        btAccept.setVisibility( View.GONE );
//                        btStart.setVisibility( View.VISIBLE );
//                        btCancel.setVisibility( View.VISIBLE );
//                        btUnStart.setVisibility( View.GONE );
//                    }
                    else{
                        btRefuse.setVisibility( View.GONE );
                        btAccept.setVisibility( View.GONE );
                        btStart.setVisibility( View.GONE );
                        btCancel.setVisibility( View.GONE );
                        btUnStart.setVisibility( View.VISIBLE );
                    }


                    break;
                case 2://预约失败 拒绝预约
                    btRefuse.setVisibility(View.GONE);
                    btAccept.setVisibility(View.GONE);
                    btStart.setVisibility(View.GONE);
                    btCancel.setVisibility(View.GONE);
                    btUnStart.setVisibility( View.GONE );

                    break;
                case 3://正在诊疗中
                    btRefuse.setVisibility(View.GONE);
                    btAccept.setVisibility(View.GONE);
                    btStart.setVisibility(View.VISIBLE);
                    btCancel.setVisibility(View.VISIBLE);
                    btUnStart.setVisibility( View.GONE );

                    break;
                case 4://已诊疗 提交诊疗报告
                    btRefuse.setVisibility(View.GONE);
                    btAccept.setVisibility(View.GONE);
                    btStart.setVisibility(View.GONE);
                    btCancel.setVisibility(View.GONE);
                    btUnStart.setVisibility( View.GONE );

                    break;
                case 5://未诊疗 没有接通
                    btRefuse.setVisibility(View.GONE);
                    btAccept.setVisibility(View.GONE);
                    btStart.setVisibility(View.GONE);
                    btCancel.setVisibility(View.GONE);
                    btUnStart.setVisibility( View.GONE );

                    break;
            }
        }
        if (from == 1) {
            Date curDate =new Date(System.currentTimeMillis());
            Date startTime=new Date( orderDetail.getOrderTimeStart() );
            long yy=startTime.getTime()-curDate.getTime();

            if(yy/1000/60<0){
                btRefuse.setVisibility( View.GONE );
                btAccept.setVisibility( View.GONE );
                btStart.setVisibility( View.VISIBLE );
                btCancel.setVisibility( View.VISIBLE );
                btUnStart.setVisibility( View.GONE );
            }
//            else if(yy/1000/60>0&&yy/1000/60<=10) {
//                btRefuse.setVisibility( View.GONE );
//                btAccept.setVisibility( View.GONE );
//                btStart.setVisibility( View.VISIBLE );
//                btCancel.setVisibility( View.VISIBLE );
//                btUnStart.setVisibility( View.GONE );
//            }
            else{
                btRefuse.setVisibility( View.GONE );
                btAccept.setVisibility( View.GONE );
                btStart.setVisibility( View.GONE );
                btCancel.setVisibility( View.GONE );
                btUnStart.setVisibility( View.VISIBLE );
            }
        }
        if (from == 2) {
            if(orderDetail.getOrderTimeStart()!=null) {
                Date curDate = new Date( System.currentTimeMillis() );
                Date startTime = new Date( orderDetail.getOrderTimeStart() );
                long yy = startTime.getTime() - curDate.getTime();

                if(yy/1000/60<0){
                    btRefuse.setVisibility( View.GONE );
                    btAccept.setVisibility( View.GONE );
                    btStart.setVisibility( View.VISIBLE );
                    btCancel.setVisibility( View.VISIBLE );
                    btUnStart.setVisibility( View.GONE );
                }
//                else if (yy > 0 && yy / 1000 / 60 <= 10) {
//                    btRefuse.setVisibility( View.GONE );
//                    btAccept.setVisibility( View.GONE );
//                    btStart.setVisibility( View.VISIBLE );
//                    btCancel.setVisibility( View.VISIBLE );
//                    btUnStart.setVisibility( View.GONE );
//                }
                else {
                    btRefuse.setVisibility( View.GONE );
                    btAccept.setVisibility( View.GONE );
                    btStart.setVisibility( View.GONE );
                    btCancel.setVisibility( View.GONE );
                    btUnStart.setVisibility( View.VISIBLE );
                }
            }
            if(orderDetail.getCureTimeStart()!=null) {
                    btRefuse.setVisibility( View.GONE );
                    btAccept.setVisibility( View.GONE );
                    btStart.setVisibility( View.VISIBLE );
                    btCancel.setVisibility( View.VISIBLE );
                    btUnStart.setVisibility( View.GONE );

            }
        }

            if (orderDetail != null) {
            llShow.setVisibility(View.VISIBLE);
            llGone.setVisibility(View.GONE);
            Picasso.with(getContext())
                    .load(ProjectConfig.BASE_URL + "/" + orderDetail.getUserDto().getHeadPic())
                    .resize(100, 100)
                    .error(R.mipmap.ic_launcher)           //设置错误图片
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imgAvatar);
            tvName.setText(orderDetail.getUserDto().getName());
            if (orderDetail.getUserDto().getSex() != null) {
                switch (orderDetail.getUserDto().getSex()) {
                    case 1:
                        tvSex.setText("男");

                        break;
                    case 2:
                        tvSex.setText("女");

                        break;
                }
            }

            if (orderDetail.getUserDto().getAge() != null)
                tvAge.setText(orderDetail.getUserDto().getAge() + "岁");
            if (orderDetail.getUserDto().getWearTimeEnum() != null)
                tvExperience.setText(orderDetail.getUserDto().getWearTimeEnum() + "个月");
            if (orderDetail.getUserDto().getCureCount() != null)
                tvTimes.setText(orderDetail.getUserDto().getCureCount() + "次");
            if (orderDetail.getClassify() == 2) {
                linearlayout0.setVisibility(View.VISIBLE);
                tvTime.setText(DateUtil.format(orderDetail.getOrderTimeStart(), "yyyy年MM月dd日") + "  " + DateUtil.getWeek(orderDetail.getOrderTimeStart()) + "  " + DateUtil.format(orderDetail.getOrderTimeStart(), "HH:mm") + "-" + DateUtil.format(orderDetail.getOrderTimeEnd(), "HH:mm"));
                if (from == 1 && System.currentTimeMillis() > orderDetail.getOrderTimeStart()) {
                    llTip.setVisibility(View.VISIBLE);
                    tvTip.setText("患者已等待" + (System.currentTimeMillis() - orderDetail.getOrderTimeStart()) / 1000 / 60 + "分钟，请尽快处理本次预约");
                }
                if (orderDetail.getReturnVisit())
                    tvStatus.setText("复诊");
                else
                    tvStatus.setText("");
            } else linearlayout0.setVisibility(View.GONE);


            if(from==2){
                tvDiscription.setText( orderDetail.getProblemDesc() );
                btStart.setText( "继续诊疗" );
            }else {
                tvDiscription.setText( orderDetail.getProblemDesc() );
                switch (orderDetail.getStatus()) {
                    case 1:
                        btStart.setText( "开始诊疗" );
                        break;
                    case 3:
                        btStart.setText( "继续诊疗" );
                        break;
                }
            }
        } else {
            llShow.setVisibility(View.GONE);
            llGone.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void receiveOrder(OrderDetail orderDetail) {
//        long timeStamp = System.currentTimeMillis();
        Date curDate =new Date(System.currentTimeMillis());
        Date startTime=new Date( orderDetail.getOrderTimeStart() );
        long yy=startTime.getTime()-curDate.getTime();

        if(yy>0&&yy/1000/60<=10) {
            btRefuse.setVisibility( View.GONE );
            btAccept.setVisibility( View.GONE );
            btStart.setVisibility( View.VISIBLE );
            btCancel.setVisibility( View.VISIBLE );
            btUnStart.setVisibility( View.GONE );

            this.orderDetail.setStatus( orderDetail.getStatus() );
            EventBus.getDefault().post( new MessageEvent( 2 ) );
        }else{
            btRefuse.setVisibility( View.GONE );
            btAccept.setVisibility( View.GONE );
            btStart.setVisibility( View.GONE );
            btCancel.setVisibility( View.GONE );
            btUnStart.setVisibility( View.VISIBLE );
        }
    }



    @Override
    public void finishCure() {
        handler.sendMessageDelayed(new Message(), 100);
//        startFragment(new WriteReportFragment(orderDetail.getId()));

    }

    @Override
    public void recordCure(OrderDetail orderDetail) {
        getActivity().startActivity(new Intent(getActivity(), TreattingActivity.class).putExtra("orderDetail", (Serializable) this.orderDetail).putExtra("receiverId", this.orderDetail.getUserAccid()));


    }

    @Override
    public void startCure(OrderDetail orderDetail) {
        this.orderDetail.setStatus(orderDetail.getStatus());
    }

    @Override
    public void getParamaterList(List<EquipmentParamater> list) {
        startFragment(new MonitorEquipmentFragment(orderDetail, list));

    }

    @Override
    public void cancelOrder() {
        EventBus.getDefault().post(new MessageEvent(2, null));
        popBackStack();

    }
}
