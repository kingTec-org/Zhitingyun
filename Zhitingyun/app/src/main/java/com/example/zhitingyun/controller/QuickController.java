package com.example.zhitingyun.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhitingyun.R;
import com.example.zhitingyun.activity.TreattingActivity;
import com.example.zhitingyun.adapter.QuickListAdapter;
import com.example.zhitingyun.model.OrderDetail;
import com.example.zhitingyun.model.Quick;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.netease.nimlib.sdk.avchat.model.AVChatData;
import com.qmuiteam.qmui.layout.QMUILinearLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import c.springviewlibrary.container.AliHeader;
import c.springviewlibrary.widget.SpringView;
import coder.mylibrary.util.ToastUtil;

import static com.qmuiteam.qmui.layout.IQMUILayout.HIDE_RADIUS_SIDE_TOP;

/**
 * Created by dasiy on 2018/7/7.
 */

public class QuickController extends MainController implements ViewsContainer.QuickView {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.springview)
    SpringView springView;
    @BindView(R.id.linearlayout0)
    QMUILinearLayout linearlayout0;
    QuickListAdapter adapter;
    List<Quick> quicks = new ArrayList<>();
    Context context;
    List<AVChatData> avChatDatas = new ArrayList<>();
    Presenter presenter;
    int deletePosition = -1;

    private long lastTime;
    private long duration = 2000L;

//    public SendMessageListener sendMessageListener = new SendMessageListener() {
//        @Override
//        public void onSend(Object object, int flag) {
//            if (adapter != null) {
//                switch (flag) {
//                    case 0:
//                        AVChatData avChatData = (AVChatData) object;
//                        avChatDatas.add(avChatData);
//                        Quick quick = new Quick();
//                        quick.setUserName("果蝇");
//                        quicks.add(quick);
//                        adapter.notifyDataSetChanged();
//                        break;
//                    case 1:
//                        AVChatCommonEvent avChatHangUpInfo = (AVChatCommonEvent) object;
//                        for (int i = 0; i < avChatDatas.size(); i++) {
//                            if (avChatDatas.get(i).getChatId() == avChatHangUpInfo.getChatId()) {
//                                avChatDatas.remove(i);
//                                quicks.remove(i);
//                            }
//                        }
//                        break;
//                    case 2:
//                        for (int i = 0; i < avChatDatas.size(); i++) {
//                            if (avChatDatas.get(i).getAccount().equals(object.toString())) {
//                                avChatDatas.remove(i);
//                                quicks.remove(i);
//                            }
//                        }
//                        break;
//                }
//
//                adapter.notifyDataSetChanged();
//            }
//
//        }
//    };

    private void getData() {
        presenter.getRequestList(new HashMap());


    }

    public QuickController(@NonNull Context context) {
        super(context);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.controller_quick, this);
        ButterKnife.bind(this, view);
        presenter = new Presenter(this);
        linearlayout0.setRadius(10, HIDE_RADIUS_SIDE_TOP);
        recycler.setLayoutManager(new LinearLayoutManager(context));
        adapter = new QuickListAdapter(quicks);
        adapter.bindToRecyclerView(recycler);
        View emptyView = View.inflate(getContext(), R.layout.item_quick_empty, null);
        adapter.setEmptyView(emptyView);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                onBackPressed();
                deletePosition = position;

//                getContext().startActivity(new Intent(getContext(), TreattingActivity.class).putExtra("orderDetail", (Serializable) avChatDatas.get(position)).putExtra("receiverId", "11155025a5d7d21c"));
                Map map = new HashMap();
                map.put("quickOrderId", quicks.get(position).getQuickOrderId());
                presenter.receiveRequest(map);
            }
        });
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                page = 1;
                getData();
            }

            @Override
            public void onLoadmore() {
//                page++;
                getData();
            }
        });

        springView.setHeader(new AliHeader(getContext(), false));   //参数为：logo图片资源，是否显示文字
//        springView.setFooter(new AliFooter(getContext(), false));
        getData();

    }


    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    public void getRequestList(List<Quick> list) {
        springView.onFinishFreshAndLoad();
        quicks.clear();
        quicks.addAll(list);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void receiveRequest(OrderDetail orderDetail) {
        if (deletePosition != -1)
            quicks.remove(deletePosition);
        adapter.notifyDataSetChanged();
        orderDetail.setUserNewestRecord(orderDetail.getUserNewestListenRecord());

        getContext().startActivity(new Intent(getContext(), TreattingActivity.class).putExtra("orderDetail", (Serializable) orderDetail).putExtra("receiverId", orderDetail.getUserAccid()).putExtra("from", 1));

    }

    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastTime) > duration) {
            lastTime = currentTime;
        } else {

        }
    }
}
