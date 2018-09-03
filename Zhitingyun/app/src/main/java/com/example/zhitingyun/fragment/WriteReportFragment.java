package com.example.zhitingyun.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.base.BaseFragment;
import com.example.zhitingyun.model.EquipmentParamater;
import com.example.zhitingyun.model.MessageEvent;
import com.example.zhitingyun.model.OrderDetail;
import com.example.zhitingyun.mvp.Presenter;
import com.example.zhitingyun.mvp.ViewsContainer;
import com.google.gson.Gson;
import com.qmuiteam.qmui.layout.QMUILinearLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.util.SPUtil;
import coder.mylibrary.util.ToastUtil;

/**
 * Created by dasiy on 2018/7/9.
 */

@SuppressLint("ValidFragment")
public class WriteReportFragment extends BaseFragment implements ViewsContainer.WriteReportView {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.imgBack)
    ImageView imgBack;
    @BindView(R.id.linearlayout)
    QMUILinearLayout linearlayout;
    @BindView(R.id.etReason)
    EditText etReason;
    int id;
    Gson gson;
    Presenter presenter;
    int index = 0;
    StringBuffer idsBuffer;
    String content;
    int classify = 0;//1快速2普通
    int from;

    public WriteReportFragment(int from, int id, int classify) {
        this.id = id;
        this.from = from;
        this.classify = classify;
    }

    @Override
    protected View onCreateView() {
        View view = View.inflate(getActivity(), R.layout.fragment_write_report, null);
        ButterKnife.bind(this, view);
        idsBuffer = new StringBuffer();
        presenter = new Presenter(this);
        gson = new Gson();
        imgBack.setVisibility(View.VISIBLE);
        linearlayout.setRadius(10);
        tvTitle.setText("填写诊疗报告");
        return view;
    }


    @OnClick({R.id.imgBack, R.id.linearlayout, R.id.btSend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
//                popBackStack();
                break;
            case R.id.linearlayout:
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    etReason.requestFocus();
                    imm.showSoftInput(etReason, InputMethodManager.SHOW_FORCED);
                }
                break;
            case R.id.btSend:
                if (etReason.getText().toString().equals(""))
                    ToastUtil.showShort("请填写诊疗报告");
                else {
                    content = etReason.getText().toString();
                    String listJson = SPUtil.get(getContext(), "equipmentParamaters", "").toString();
                    if (listJson.equals("")) {
                        Map map = new HashMap();
                        map.put("id", id);
                        presenter.getOrder(map, 2);
                    } else {
                        EquipmentParamater[] array = gson.fromJson(listJson, EquipmentParamater[].class);
                        for (int i = 0; i < array.length; i++) {
                            Map map = new HashMap();
                            map.put("userId", new Integer(SPUtil.get(getContext(), "userId", "0").toString()));
                            map.put("classify", array[i].getClassify());
                            map.put("hertz", array[i].getHertz());
                            map.put("chg_DATA", array[i].getChg_DATA());
                            map.put("P_S_DL", array[i].getP_S_DL());
                            map.put("L_P_VC", array[i].getL_P_VC());
                            map.put("L_P_VC_VALUE", array[i].getL_P_VC_VALUE());
                            map.put("L_P_BASS", array[i].getL_P_BASS());
                            map.put("L_P_BASS_VALUE", array[i].getL_P_BASS_VALUE());
                            map.put("L_P_TRB", array[i].getL_P_TRB());
                            map.put("L_P_TRB_VALUE", array[i].getL_P_TRB_VALUE());
                            map.put("L_P_NRLVL", array[i].getL_P_NRLVL());
                            map.put("L_P_NRLVL_VALUE", array[i].getL_P_NRLVL_VALUE());
                            map.put("L_P_AFSLVL", array[i].getL_P_AFSLVL());
                            map.put("L_P_AFSLVL_VALUE", array[i].getL_P_AFSLVL_VALUE());
                            map.put("L_SWITCH_P_CH", array[i].getL_SWITCH_P_CH());
                            map.put("L_P_CHMPO", array[i].getL_P_CHMPO());
                            map.put("L_P_CHSW", array[i].getL_P_CHSW());
                            map.put("L_P_CHSG", array[i].getL_P_CHSG());
                            map.put("L_P_CHNG", array[i].getL_P_CHNG());
                            map.put("L_P_CHLG", array[i].getL_P_CHLG());
                            map.put("L_SWITCH_P_CHEXP", array[i].getL_SWITCH_P_CHEXP());
                            map.put("L_P_CHEXP", array[i].getL_P_CHEXP());
                            map.put("L_P_CHETH", array[i].getL_P_CHETH());
                            map.put("L_P_CHER", array[i].getL_P_CHER());
                            map.put("R_P_VC", array[i].getR_P_VC());
                            map.put("R_P_VC_VALUE", array[i].getR_P_VC_VALUE());
                            map.put("R_P_BASS", array[i].getR_P_BASS());
                            map.put("R_P_BASS_VALUE", array[i].getR_P_BASS_VALUE());
                            map.put("R_P_TRB", array[i].getR_P_TRB());
                            map.put("R_P_TRB_VALUE", array[i].getR_P_TRB_VALUE());
                            map.put("R_P_NRLVL", array[i].getR_P_NRLVL());
                            map.put("R_P_NRLVL_VALUE", array[i].getR_P_NRLVL_VALUE());
                            map.put("R_P_AFSLVL", array[i].getR_P_AFSLVL());
                            map.put("R_P_AFSLVL_VALUE", array[i].getR_P_AFSLVL_VALUE());
                            map.put("R_SWITCH_P_CH", array[i].getR_SWITCH_P_CH());
                            map.put("R_P_CHMPO", array[i].getR_P_CHMPO());
                            map.put("R_P_CHSW", array[i].getR_P_CHSW());
                            map.put("R_P_CHSG", array[i].getR_P_CHSG());
                            map.put("R_P_CHNG", array[i].getR_P_CHNG());
                            map.put("R_P_CHLG", array[i].getR_P_CHLG());
                            map.put("R_SWITCH_P_CHEXP", array[i].getR_SWITCH_P_CHEXP());
                            map.put("R_P_CHEXP", array[i].getR_P_CHEXP());
                            map.put("R_P_CHETH", array[i].getR_P_CHETH());
                            map.put("R_P_CHER", array[i].getR_P_CHER());
                            presenter.submitParamater(map);
                        }
                    }
//                    Map map = new HashMap();
//                    map.put("")
                }

                break;
        }
    }

    @Override
    public void submitParamater(EquipmentParamater equipmentParamater) {
        index++;
        idsBuffer.append(equipmentParamater.getId() + ",");
        if (index == 4) {
            String ids = idsBuffer.toString();
            Map map = new HashMap();
            map.put("equParamAfterId", ids.substring(0, ids.length() - 1));
            map.put("professorReport", content);
            switch (classify) {
                case 1:
                    map.put("id", id);
                    map.put("cureTimeEnd", System.currentTimeMillis());
                    presenter.submitQuickOrder(map);
                    break;
                case 2:
                    map.put("orderId", id);
                    map.put("reportTime", System.currentTimeMillis());
                    presenter.cureReport(map);
                    break;
            }

        }
    }

    @Override
    public void getOrder(OrderDetail orderDetail) {
        for (int i = 0; i < orderDetail.getEquParamBefore().size(); i++) {
            EquipmentParamater equipmentParamater = orderDetail.getEquParamBefore().get(i);
            Map map = new HashMap();
            map.put("userId", new Integer(SPUtil.get(getContext(), "userId", "0").toString()));
            map.put("classify", equipmentParamater.getClassify());
            map.put("hertz", equipmentParamater.getHertz());
            map.put("chg_DATA", equipmentParamater.getChg_DATA());
            map.put("P_S_DL", equipmentParamater.getP_S_DL());
            map.put("L_P_VC", equipmentParamater.getL_P_VC());
            map.put("L_P_VC_VALUE", equipmentParamater.getL_P_VC_VALUE());
            map.put("L_P_BASS", equipmentParamater.getL_P_BASS());
            map.put("L_P_BASS_VALUE", equipmentParamater.getL_P_BASS_VALUE());
            map.put("L_P_TRB", equipmentParamater.getL_P_TRB());
            map.put("L_P_TRB_VALUE", equipmentParamater.getL_P_TRB_VALUE());
            map.put("L_P_NRLVL", equipmentParamater.getL_P_NRLVL());
            map.put("L_P_NRLVL_VALUE", equipmentParamater.getL_P_NRLVL_VALUE());
            map.put("L_P_AFSLVL", equipmentParamater.getL_P_AFSLVL());
            map.put("L_P_AFSLVL_VALUE", equipmentParamater.getL_P_AFSLVL_VALUE());
            map.put("L_SWITCH_P_CH", equipmentParamater.getL_SWITCH_P_CH());
            map.put("L_P_CHMPO", equipmentParamater.getL_P_CHMPO());
            map.put("L_P_CHSW", equipmentParamater.getL_P_CHSW());
            map.put("L_P_CHSG", equipmentParamater.getL_P_CHSG());
            map.put("L_P_CHNG", equipmentParamater.getL_P_CHNG());
            map.put("L_P_CHLG", equipmentParamater.getL_P_CHLG());
            map.put("L_SWITCH_P_CHEXP", equipmentParamater.getL_SWITCH_P_CHEXP());
            map.put("L_P_CHEXP", equipmentParamater.getL_P_CHEXP());
            map.put("L_P_CHETH", equipmentParamater.getL_P_CHETH());
            map.put("L_P_CHER", equipmentParamater.getL_P_CHER());
            map.put("R_P_VC", equipmentParamater.getR_P_VC());
            map.put("R_P_VC_VALUE", equipmentParamater.getR_P_VC_VALUE());
            map.put("R_P_BASS", equipmentParamater.getR_P_BASS());
            map.put("R_P_BASS_VALUE", equipmentParamater.getR_P_BASS_VALUE());
            map.put("R_P_TRB", equipmentParamater.getR_P_TRB());
            map.put("R_P_TRB_VALUE", equipmentParamater.getR_P_TRB_VALUE());
            map.put("R_P_NRLVL", equipmentParamater.getR_P_NRLVL());
            map.put("R_P_NRLVL_VALUE", equipmentParamater.getR_P_NRLVL_VALUE());
            map.put("R_P_AFSLVL", equipmentParamater.getR_P_AFSLVL());
            map.put("R_P_AFSLVL_VALUE", equipmentParamater.getR_P_AFSLVL_VALUE());
            map.put("R_SWITCH_P_CH", equipmentParamater.getR_SWITCH_P_CH());
            map.put("R_P_CHMPO", equipmentParamater.getR_P_CHMPO());
            map.put("R_P_CHSW", equipmentParamater.getR_P_CHSW());
            map.put("R_P_CHSG", equipmentParamater.getR_P_CHSG());
            map.put("R_P_CHNG", equipmentParamater.getR_P_CHNG());
            map.put("R_P_CHLG", equipmentParamater.getR_P_CHLG());
            map.put("R_SWITCH_P_CHEXP", equipmentParamater.getR_SWITCH_P_CHEXP());
            map.put("R_P_CHEXP", equipmentParamater.getR_P_CHEXP());
            map.put("R_P_CHETH", equipmentParamater.getR_P_CHETH());
            map.put("R_P_CHER", equipmentParamater.getR_P_CHER());
            presenter.submitParamater(map);
        }

//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < orderDetail.getEquParamBefore().size(); i++) {
//            list.add(orderDetail.getEquParamBefore().get(i).getClassify());
//        }
//        if (!list.contains(new Integer(1)))


    }

    @Override
    public void cureReport() {
        if (classify == 2)
            EventBus.getDefault().post(new MessageEvent(2, null));
        index = 0;
        idsBuffer = new StringBuffer("");
        switch (from) {
            case 0:
                getBaseFragmentActivity().popBackStack(MainFragment.class);
                break;
            case 1:
                if (classify == 2)
                    getBaseFragmentActivity().popBackStack(OrderManagerFragment.class);
                else if (classify == 1)
                    getBaseFragmentActivity().popBackStack(MainFragment.class);
                break;
        }
//        popBackStack();
    }


}
