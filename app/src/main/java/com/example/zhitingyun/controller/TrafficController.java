package com.example.zhitingyun.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.zhitingyun.R;
import com.example.zhitingyun.model.EquipmentParamater;
import com.example.zhitingyun.model.ReturnMessage;
import com.example.zhitingyun.widget.WS;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.kyleduo.switchbutton.SwitchButton;
import com.qmuiteam.qmui.layout.QMUILinearLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import coder.mylibrary.util.SPUtil;

/**
 * Created by dasiy on 2018/7/9.
 */

public class TrafficController extends MainController {
    @BindView(R.id.sbCompress)
    SwitchButton sbCompress;
    @BindView(R.id.sbExtened)
    SwitchButton sbExtened;

    @BindView(R.id.tvNoise)
    TextView tvNoise;
    @BindView(R.id.tvFeedback)
    TextView tvFeedback;
    @BindView(R.id.imgNoise)
    ImageView imgNoise;
    @BindView(R.id.imgFeedback)
    ImageView imgFeedback;


    @BindView(R.id.sbVolume)
    SeekBar sbVolume;
    @BindView(R.id.sbHigh)
    SeekBar sbHigh;
    @BindView(R.id.sbMiddle)
    SeekBar sbMiddle;
    @BindView(R.id.sbLow)
    SeekBar sbLow;


    @BindView(R.id.tvVolume)
    TextView tvVolume;
    @BindView(R.id.tvHigh)
    TextView tvHigh;
    @BindView(R.id.tvLow)
    TextView tvLow;

    @BindView(R.id.tvLeft)
    TextView tvLeft;
    @BindView(R.id.tvRight)
    TextView tvRight;


    @BindView(R.id.linearlayout)
    QMUILinearLayout linearlayout;
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
    EquipmentParamater equipmentParamater;
    int from;
    int click = 0;
    int paramIndex = 0;
    List<EquipmentParamater> list;
    Gson gson;
    Integer userId;


    public TrafficController(@NonNull Context context, int from, List<EquipmentParamater> list, Integer userId) {
        super(context);
        this.from = from;
        this.list = list;
        this.userId = userId;
        gson = new Gson();
        switch (from) {
            case 0:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getClassify() == 1) {
                        equipmentParamater = list.get(i);
                        paramIndex = i;
                    }
                }

                break;
            case 1:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getClassify() == 2) {
                        equipmentParamater = list.get(i);
                        paramIndex = i;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getClassify() == 3) {
                        equipmentParamater = list.get(i);
                        paramIndex = i;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getClassify() == 4) {
                        equipmentParamater = list.get(i);
                        paramIndex = i;
                    }
                }
                break;
        }


        View view = LayoutInflater.from(context).inflate(R.layout.controller_traffic, this);
        ButterKnife.bind(this, view);
        imgFeedback.setBackgroundResource(R.mipmap.up_and_down_gray);
        imgNoise.setBackgroundResource(R.mipmap.up_and_down_gray);
        linearlayout.setRadius(10);
        initView();

    }

    private void pushData(ReturnMessage returnMessage) {
        returnMessage.setClassify(from + 1);

        list.set(paramIndex, equipmentParamater);
        Log.d("fff", gson.toJson(list));
        WS.getInStanceBlock().send("{\"classify\":" + "1" + ",\"id\":" + userId + ",\"EquipmentParam\":" + gson.toJson(returnMessage) + "}");

        SPUtil.put(getContext(), "equipmentParamaters", gson.toJson(list));

    }

    @OnClick({R.id.llFeedback, R.id.llNoises, R.id.tvLeft, R.id.tvRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llFeedback:
                String[] feedbackArray = null;
                switch (click) {
                    case 0:
                        feedbackArray = equipmentParamater.getL_P_AFSLVL().split(",");
                        break;
                    case 1:
                        feedbackArray = equipmentParamater.getR_P_AFSLVL().split(",");
                        break;
                }
                imgFeedback.setBackgroundResource(R.mipmap.up_and_down_blue);
                QMUIDialog dialog = new QMUIDialog.MenuDialogBuilder(getContext())
                        .addItems(feedbackArray, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                ReturnMessage returnMessage = new ReturnMessage();
                                switch (click) {
                                    case 0:
                                        tvFeedback.setText(equipmentParamater.getL_P_AFSLVL().split(",")[which]);
                                        equipmentParamater.setL_P_AFSLVL_VALUE(which);
                                        returnMessage.setKey("l_P_AFSLVL_VALUE");
                                        returnMessage.setValue(which);

                                        break;
                                    case 1:
                                        tvFeedback.setText(equipmentParamater.getR_P_AFSLVL().split(",")[which]);
                                        equipmentParamater.setR_P_AFSLVL_VALUE(which);
                                        returnMessage.setKey("r_P_AFSLVL_VALUE");
                                        returnMessage.setValue(which);
                                        break;
                                }
                                pushData(returnMessage);


                            }
                        })
                        .show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        imgFeedback.setBackgroundResource(R.mipmap.up_and_down_gray);

                    }
                });

                break;
            case R.id.llNoises:
                String[] noiseArray = null;
                switch (click) {
                    case 0:
                        noiseArray = equipmentParamater.getL_P_NRLVL().split(",");
                        break;
                    case 1:
                        noiseArray = equipmentParamater.getR_P_NRLVL().split(",");
                        break;
                }
                imgNoise.setBackgroundResource(R.mipmap.up_and_down_blue);
                QMUIDialog dialog0 = new QMUIDialog.MenuDialogBuilder(getContext())
                        .addItems(noiseArray, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                ReturnMessage returnMessage = new ReturnMessage();
                                switch (click) {
                                    case 0:
                                        tvNoise.setText(equipmentParamater.getL_P_NRLVL().split(",")[which]);
                                        equipmentParamater.setL_P_NRLVL_VALUE(which);
                                        returnMessage.setKey("l_P_NRLVL_VALUE");
                                        returnMessage.setValue(which);
                                        break;
                                    case 1:
                                        tvNoise.setText(equipmentParamater.getR_P_NRLVL().split(",")[which]);
                                        equipmentParamater.setR_P_NRLVL_VALUE(which);
                                        returnMessage.setKey("r_P_NRLVL_VALUE");
                                        returnMessage.setValue(which);
                                        break;
                                }
                                pushData(returnMessage);


                            }
                        })
                        .show();
                dialog0.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        imgNoise.setBackgroundResource(R.mipmap.up_and_down_gray);

                    }
                });
                break;
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

    private void initView() {

        Boolean compressState = false;
        Boolean extendState = false;
        String denoise = "";
        String feedback = "";
        Integer volume = 0;
        Integer high = 0;
        Integer low = 0;

        switch (click) {
            case 0:
                if (equipmentParamater.getL_SWITCH_P_CH())
                    compressState = true;
                if (equipmentParamater.getL_SWITCH_P_CHEXP())
                    extendState = true;
                denoise = equipmentParamater.getL_P_NRLVL().split(",")[equipmentParamater.getL_P_NRLVL_VALUE()];
                feedback = equipmentParamater.getL_P_AFSLVL().split(",")[equipmentParamater.getL_P_AFSLVL_VALUE()];
                volume = new Integer(equipmentParamater.getL_P_VC().split(",")[equipmentParamater.getL_P_VC_VALUE()]) + new Integer(equipmentParamater.getL_P_VC().split(",")[0]);
                high = new Integer(equipmentParamater.getL_P_TRB().split(",")[equipmentParamater.getL_P_TRB_VALUE()]) + new Integer(equipmentParamater.getL_P_TRB().split(",")[0]);
                low = new Integer(equipmentParamater.getL_P_BASS().split(",")[equipmentParamater.getL_P_BASS_VALUE()]) + new Integer(equipmentParamater.getL_P_BASS().split(",")[0]);
                final String[] volumeArrayLeft = equipmentParamater.getL_P_VC().split(",");
                final String[] HighArrayLeft = equipmentParamater.getL_P_TRB().split(",");
                final String[] LowArrayLeft = equipmentParamater.getL_P_BASS().split(",");

                linearlayout11.removeAllViews();
                linearlayout12.removeAllViews();
                linearlayout13.removeAllViews();
                linearlayout14.removeAllViews();
                linearlayout15.removeAllViews();
                linearlayout16.removeAllViews();
                linearlayout17.removeAllViews();

                final String[] chg_DATA = equipmentParamater.getChg_DATA().split(",");
                final String[] mpoArray = equipmentParamater.getL_P_CHMPO().split(",");
                final String[] midArray = equipmentParamater.getL_P_CHNG().split(",");
                final String[] lowArray = equipmentParamater.getL_P_CHSG().split(",");
                final String[] highArray = equipmentParamater.getL_P_CHLG().split(",");
                final String[] hezArrray = equipmentParamater.getHertz().split(",");
                final String[] pointArray = equipmentParamater.getL_P_CHETH().split(",");
                final String[] rateArray = equipmentParamater.getL_P_CHER().split(",");

                for (int i = -1; i < midArray.length; i++) {

                    for (int j = 0; j < 7; j++) {
                        View view = new View(getContext());
                        view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorGrayf0));
                        view.setLayoutParams(new LinearLayout.LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT));

                        TextView textView = new TextView(getContext());
                        textView.setGravity(Gravity.CENTER);


                        final LinearLayout linearLayout = new LinearLayout(getContext());
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
                        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2));

                        linearLayout.setTag(i + "" + j);

                        ImageView imageView = new ImageView(getContext());
                        imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        imageView.setBackgroundResource(R.mipmap.up_and_down_gray);


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

                            textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
                            linearLayout.addView(textView);

                            switch (j) {
                                case 0:
                                    linearlayout11.addView(view);
                                    textView.setText(hezArrray[i]);
                                    linearlayout11.addView(linearLayout);
                                    break;
                                case 1:
                                    linearLayout.addView(imageView);
                                    linearlayout12.addView(view);
                                    if (chg_DATA != null && mpoArray != null) {
                                        if (new Integer(mpoArray[i]) > 22)
                                            mpoArray[i] = "0";
                                        textView.setText(chg_DATA[new Integer(mpoArray[i])]);
                                    }
                                    linearlayout12.addView(linearLayout);
                                    break;
                                case 2:
                                    linearLayout.addView(imageView);
                                    linearlayout13.addView(view);
                                    if (chg_DATA != null && lowArray != null) {
                                        if (new Integer(lowArray[i]) > 22)
                                            lowArray[i] = "0";
                                        textView.setText(chg_DATA[new Integer(lowArray[i])]);
                                    }
                                    linearlayout13.addView(linearLayout);
                                    break;
                                case 3:
                                    linearLayout.addView(imageView);
                                    linearlayout14.addView(view);
                                    if (chg_DATA != null && midArray != null) {
                                        if (new Integer(midArray[i]) > 22)
                                            midArray[i] = "0";
                                        textView.setText(chg_DATA[new Integer(midArray[i])]);
                                    }
                                    linearlayout14.addView(linearLayout);
                                    break;
                                case 4:
                                    linearLayout.addView(imageView);
                                    linearlayout15.addView(view);
                                    if (chg_DATA != null && highArray != null) {
                                        if (new Integer(highArray[i]) > 22)
                                            highArray[i] = "0";
                                        textView.setText(chg_DATA[new Integer(highArray[i])]);
                                    }
                                    linearlayout15.addView(linearLayout);
                                    break;
                                case 5:
                                    linearlayout16.addView(view);
//                                    if (pointArray.length == 0)
                                    if (pointArray == null || pointArray.length == 0)

                                        textView.setText("");
                                    else
                                        textView.setText(pointArray[i]);
                                    linearlayout16.addView(linearLayout);
                                    break;
                                case 6:
                                    linearlayout17.addView(view);
                                    if (rateArray.length == 0)
                                        textView.setText("");
                                    else
                                        textView.setText(rateArray[i]);
                                    linearlayout17.addView(linearLayout);
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

                        linearLayout.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("sss", view.getTag() + "");

                                String tag = (String) view.getTag();
                                final ImageView upDown = (ImageView) linearLayout.getChildAt(1);
                                final TextView value = (TextView) linearLayout.getChildAt(0);
                                final Integer index = new Integer(String.valueOf(tag.charAt(0)));


                                switch (tag.charAt(1)) {
                                    case '1':
                                        upDown.setBackgroundResource(R.mipmap.up_and_down_blue);
                                        QMUIDialog dialog = new QMUIDialog.MenuDialogBuilder(getContext())
                                                .addItems(chg_DATA, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                        value.setText(chg_DATA[which]);
                                                        mpoArray[index] = which + "";
                                                        StringBuffer mpo = new StringBuffer("");
                                                        for (int i = 0; i < mpoArray.length; i++) {
                                                            mpo.append(mpoArray[i]);
                                                            if (i != mpoArray.length - 1)
                                                                mpo.append(",");
                                                        }
                                                        equipmentParamater.setL_P_CHMPO(mpo.toString());
                                                        ReturnMessage returnMessage = new ReturnMessage();
                                                        returnMessage.setKey("l_P_CHMPO");
                                                        returnMessage.setValue(mpo.toString());
                                                        pushData(returnMessage);

                                                    }
                                                })
                                                .show();
                                        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                            @Override
                                            public void onDismiss(DialogInterface dialogInterface) {
                                                upDown.setBackgroundResource(R.mipmap.up_and_down_gray);

                                            }
                                        });


                                        break;
                                    case '2':
                                        upDown.setBackgroundResource(R.mipmap.up_and_down_blue);
                                        QMUIDialog dialog0 = new QMUIDialog.MenuDialogBuilder(getContext())
                                                .addItems(chg_DATA, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                        value.setText(chg_DATA[which]);
                                                        lowArray[index] = which + "";
                                                        StringBuffer mpo = new StringBuffer("");
                                                        for (int i = 0; i < lowArray.length; i++) {
                                                            mpo.append(lowArray[i]);
                                                            if (i != lowArray.length - 1)
                                                                mpo.append(",");
                                                        }
                                                        equipmentParamater.setL_P_CHSG(mpo.toString());
                                                        ReturnMessage returnMessage = new ReturnMessage();
                                                        returnMessage.setKey("l_P_CHSG");
                                                        returnMessage.setValue(mpo.toString());
                                                        pushData(returnMessage);


                                                    }
                                                })
                                                .show();
                                        dialog0.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                            @Override
                                            public void onDismiss(DialogInterface dialogInterface) {
                                                upDown.setBackgroundResource(R.mipmap.up_and_down_gray);
                                            }
                                        });

                                        break;
                                    case '3':
                                        upDown.setBackgroundResource(R.mipmap.up_and_down_blue);
                                        QMUIDialog dialog1 = new QMUIDialog.MenuDialogBuilder(getContext())
                                                .addItems(chg_DATA, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                        value.setText(chg_DATA[which]);
                                                        midArray[index] = which + "";
                                                        StringBuffer mpo = new StringBuffer("");
                                                        for (int i = 0; i < midArray.length; i++) {
                                                            mpo.append(midArray[i]);
                                                            if (i != midArray.length - 1)
                                                                mpo.append(",");
                                                        }
                                                        equipmentParamater.setL_P_CHNG(mpo.toString());
                                                        ReturnMessage returnMessage = new ReturnMessage();
                                                        returnMessage.setKey("l_P_CHNG");
                                                        returnMessage.setValue(mpo.toString());
                                                        pushData(returnMessage);


                                                    }
                                                })
                                                .show();
                                        dialog1.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                            @Override
                                            public void onDismiss(DialogInterface dialogInterface) {
                                                upDown.setBackgroundResource(R.mipmap.up_and_down_gray);
                                            }
                                        });
                                        break;
                                    case '4':
                                        upDown.setBackgroundResource(R.mipmap.up_and_down_blue);
                                        QMUIDialog dialog2 = new QMUIDialog.MenuDialogBuilder(getContext())
                                                .addItems(chg_DATA, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                        value.setText(chg_DATA[which]);
                                                        highArray[index] = which + "";
                                                        StringBuffer mpo = new StringBuffer("");
                                                        for (int i = 0; i < highArray.length; i++) {
                                                            mpo.append(highArray[i]);
                                                            if (i != highArray.length - 1)
                                                                mpo.append(",");
                                                        }
                                                        equipmentParamater.setL_P_CHLG(mpo.toString());
                                                        ReturnMessage returnMessage = new ReturnMessage();
                                                        returnMessage.setKey("l_P_CHLG");
                                                        returnMessage.setValue(mpo.toString());
                                                        pushData(returnMessage);


                                                    }
                                                })
                                                .show();
                                        dialog2.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                            @Override
                                            public void onDismiss(DialogInterface dialogInterface) {
                                                upDown.setBackgroundResource(R.mipmap.up_and_down_gray);
                                            }
                                        });
                                        break;

                                }


                            }
                        });


                    }

                }
//                sbVolume.setMin(new Integer(volumeArrayLeft[1]));
                sbVolume.setMax(new Integer(volumeArrayLeft[volumeArrayLeft.length - 1]) - new Integer(volumeArrayLeft[0]));
                sbHigh.setMax(new Integer(HighArrayLeft[HighArrayLeft.length - 1]) - new Integer(HighArrayLeft[0]));
                sbLow.setMax(new Integer(LowArrayLeft[LowArrayLeft.length - 1]) - new Integer(LowArrayLeft[0]));


                sbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        ReturnMessage returnMessage = new ReturnMessage();
                        returnMessage.setKey("l_P_VC_VALUE");
                        if (seekBar.getProgress() < new Integer(volumeArrayLeft[volumeArrayLeft.length - 1]) - new Integer(volumeArrayLeft[0])) {
                            for (int i = 0; i < volumeArrayLeft.length; i++) {
                                if (new Integer(volumeArrayLeft[0]) + seekBar.getProgress() < new Integer(volumeArrayLeft[i])) {
                                    seekBar.setProgress(new Integer(volumeArrayLeft[i - 1]) - new Integer(volumeArrayLeft[0]));
                                    tvVolume.setText(new Integer(volumeArrayLeft[i - 1]) + "");

                                    equipmentParamater.setL_P_VC_VALUE(i - 1);
                                    returnMessage.setValue(i - 1);
                                    break;
                                }


                            }
                        } else {
                            seekBar.setProgress(new Integer(volumeArrayLeft[volumeArrayLeft.length - 1]) - new Integer(volumeArrayLeft[0]));

                            tvVolume.setText(new Integer(volumeArrayLeft[volumeArrayLeft.length - 1]) + "");
                            equipmentParamater.setL_P_VC_VALUE(volumeArrayLeft.length - 1);
                            returnMessage.setValue(volumeArrayLeft.length - 1);
                        }
                        pushData(returnMessage);

                    }
                });
                sbLow.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
//
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        ReturnMessage returnMessage = new ReturnMessage();
                        returnMessage.setKey("l_P_BASS_VALUE");
                        if (seekBar.getProgress() < new Integer(LowArrayLeft[LowArrayLeft.length - 1]) - new Integer(LowArrayLeft[0])) {
                            for (int i = 0; i < LowArrayLeft.length; i++) {
                                if (new Integer(LowArrayLeft[0]) + seekBar.getProgress() < new Integer(LowArrayLeft[i])) {
                                    seekBar.setProgress(new Integer(LowArrayLeft[i - 1]) - new Integer(LowArrayLeft[0]));
                                    tvLow.setText(new Integer(LowArrayLeft[i - 1]) + "");

                                    equipmentParamater.setL_P_BASS_VALUE(i - 1);
                                    returnMessage.setValue(i - 1);
                                    break;
                                }


                            }
                        } else {
                            seekBar.setProgress(new Integer(LowArrayLeft[LowArrayLeft.length - 1]) - new Integer(LowArrayLeft[0]));

                            tvLow.setText(new Integer(LowArrayLeft[LowArrayLeft.length - 1]) + "");
                            equipmentParamater.setL_P_BASS_VALUE(LowArrayLeft.length - 1);
                            returnMessage.setValue(LowArrayLeft.length - 1);
                        }
                        pushData(returnMessage);

                    }
                });
                sbHigh.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        ReturnMessage returnMessage = new ReturnMessage();
                        returnMessage.setKey("l_P_TRB_VALUE");
                        if (seekBar.getProgress() < new Integer(HighArrayLeft[HighArrayLeft.length - 1]) - new Integer(HighArrayLeft[0])) {
                            for (int i = 0; i < HighArrayLeft.length; i++) {
                                if (new Integer(HighArrayLeft[0]) + seekBar.getProgress() < new Integer(HighArrayLeft[i])) {
                                    seekBar.setProgress(new Integer(HighArrayLeft[i - 1]) - new Integer(HighArrayLeft[0]));
                                    tvHigh.setText(new Integer(HighArrayLeft[i - 1]) + "");

                                    equipmentParamater.setL_P_TRB_VALUE(i - 1);
                                    returnMessage.setValue(i - 1);
                                    break;
                                }


                            }
                        } else {
                            seekBar.setProgress(new Integer(HighArrayLeft[HighArrayLeft.length - 1]) - new Integer(HighArrayLeft[0]));

                            tvHigh.setText(new Integer(HighArrayLeft[HighArrayLeft.length - 1]) + "");
                            equipmentParamater.setL_P_TRB_VALUE(HighArrayLeft.length - 1);

                            returnMessage.setValue(HighArrayLeft.length - 1);
                        }
                        pushData(returnMessage);


                    }
                });
                sbCompress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        equipmentParamater.setL_SWITCH_P_CH(b);
                        ReturnMessage returnMessage = new ReturnMessage();
                        returnMessage.setKey("l_SWITCH_P_CH");
                        returnMessage.setValue(b);
                        pushData(returnMessage);

                    }
                });
                sbExtened.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                        equipmentParamater.setL_SWITCH_P_CHEXP(b);
                        if (b) {
                            linearlayout16.setVisibility(View.VISIBLE);
                            linearlayout17.setVisibility(View.VISIBLE);

                        } else {
                            linearlayout16.setVisibility(View.GONE);
                            linearlayout17.setVisibility(View.GONE);
                        }
                        ReturnMessage returnMessage = new ReturnMessage();
                        returnMessage.setKey("l_SWITCH_P_CHEXP");
                        returnMessage.setValue(b);
                        pushData(returnMessage);
                    }
                });


                break;
            case 1:
                if (equipmentParamater.getR_SWITCH_P_CH())
                    compressState = true;
                if (equipmentParamater.getL_SWITCH_P_CHEXP())
                    extendState = true;
                denoise = equipmentParamater.getR_P_NRLVL().split(",")[equipmentParamater.getR_P_NRLVL_VALUE()];
                feedback = equipmentParamater.getR_P_AFSLVL().split(",")[equipmentParamater.getR_P_AFSLVL_VALUE()];
                volume = new Integer(equipmentParamater.getR_P_VC().split(",")[equipmentParamater.getR_P_VC_VALUE()]) + new Integer(equipmentParamater.getR_P_VC().split(",")[0]);
                high = new Integer(equipmentParamater.getR_P_TRB().split(",")[equipmentParamater.getR_P_TRB_VALUE()]) + new Integer(equipmentParamater.getR_P_TRB().split(",")[0]);
                low = new Integer(equipmentParamater.getR_P_BASS().split(",")[equipmentParamater.getR_P_BASS_VALUE()]) + new Integer(equipmentParamater.getR_P_BASS().split(",")[0]);
                final String[] volumeArrayRight = equipmentParamater.getR_P_VC().split(",");
                final String[] HighArrayRight = equipmentParamater.getR_P_TRB().split(",");
                final String[] LowArrayRight = equipmentParamater.getR_P_BASS().split(",");
                linearlayout11.removeAllViews();
                linearlayout12.removeAllViews();
                linearlayout13.removeAllViews();
                linearlayout14.removeAllViews();
                linearlayout15.removeAllViews();
                linearlayout16.removeAllViews();
                linearlayout17.removeAllViews();
                final String[] _chg_DATA = equipmentParamater.getChg_DATA().split(",");
                final String[] _mpoArray = equipmentParamater.getR_P_CHMPO().split(",");
                final String[] _midArray = equipmentParamater.getR_P_CHNG().split(",");
                final String[] _lowArray = equipmentParamater.getR_P_CHSG().split(",");
                final String[] _highArray = equipmentParamater.getR_P_CHLG().split(",");
                final String[] _hezArrray = equipmentParamater.getHertz().split(",");
                final String[] _pointArray = equipmentParamater.getR_P_CHETH().split(",");
                final String[] _rateArray = equipmentParamater.getR_P_CHER().split(",");
                for (int i = -1; i < _midArray.length; i++) {

                    for (int j = 0; j < 7; j++) {
                        View view = new View(getContext());
                        view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorGrayf0));
                        view.setLayoutParams(new LinearLayout.LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT));

                        TextView textView = new TextView(getContext());
                        textView.setGravity(Gravity.CENTER);


                        final LinearLayout linearLayout = new LinearLayout(getContext());
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
                        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2));

                        linearLayout.setTag(i + "" + j);


                        ImageView imageView = new ImageView(getContext());
                        imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        imageView.setBackgroundResource(R.mipmap.up_and_down_gray);


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

                            textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
                            linearLayout.addView(textView);

                            switch (j) {
                                case 0:
                                    linearlayout11.addView(view);
                                    textView.setText(_hezArrray[i]);
                                    linearlayout11.addView(linearLayout);
                                    break;
                                case 1:
                                    linearLayout.addView(imageView);
                                    linearlayout12.addView(view);
                                    if (_chg_DATA != null && _mpoArray != null) {
                                        if (new Integer(_mpoArray[i]) > _chg_DATA.length-1)
                                            _mpoArray[i] = "0";
                                        textView.setText(_chg_DATA[new Integer(_mpoArray[i])]);
                                    }
                                    linearlayout12.addView(linearLayout);
                                    break;
                                case 2:
                                    linearLayout.addView(imageView);
                                    linearlayout13.addView(view);
                                    if (_chg_DATA != null && _lowArray != null) {
                                        if (new Integer(_lowArray[i]) > _chg_DATA.length-1)
                                            _lowArray[i] = "0";
                                        textView.setText(_chg_DATA[new Integer(_lowArray[i])]);
                                    }
                                    linearlayout13.addView(linearLayout);
                                    break;
                                case 3:
                                    linearLayout.addView(imageView);
                                    linearlayout14.addView(view);
                                    if (_chg_DATA != null && _midArray != null) {
                                        if (new Integer(_midArray[i]) > _chg_DATA.length-1)
                                            _midArray[i] = "0";
                                        textView.setText(_chg_DATA[new Integer(_midArray[i])]);
                                    }
                                    linearlayout14.addView(linearLayout);
                                    break;
                                case 4:
                                    linearLayout.addView(imageView);
                                    linearlayout15.addView(view);
                                    if (_chg_DATA != null && _midArray != null) {
                                        if (new Integer(_midArray[i]) > _chg_DATA.length-1)
                                            _midArray[i] = "0";
                                        textView.setText(_chg_DATA[new Integer(_midArray[i])]);
                                    }
                                    textView.setText(_chg_DATA[new Integer(_highArray[i])]);
                                    linearlayout15.addView(linearLayout);
                                    break;
                                case 5:
                                    linearlayout16.addView(view);
//                                    if (_pointArray.length == 0)
                                    if (_pointArray == null || _pointArray.length == 0)

                                        textView.setText("");
                                    else
                                        textView.setText(_pointArray[i]);
                                    linearlayout16.addView(linearLayout);
                                    break;
                                case 6:
                                    linearlayout17.addView(view);
                                    if (_rateArray.length == 0)
                                        textView.setText("");
                                    else
                                        textView.setText(_rateArray[i]);
                                    linearlayout17.addView(linearLayout);
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
                        linearLayout.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("sss", view.getTag() + "");

                                String tag = (String) view.getTag();
                                final ImageView upDown = (ImageView) linearLayout.getChildAt(1);
                                final TextView value = (TextView) linearLayout.getChildAt(0);
                                final Integer index = new Integer(String.valueOf(tag.charAt(0)));

                                switch (tag.charAt(1)) {
                                    case '1':
                                        upDown.setBackgroundResource(R.mipmap.up_and_down_blue);
                                        QMUIDialog dialog = new QMUIDialog.MenuDialogBuilder(getContext())
                                                .addItems(_chg_DATA, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                        value.setText(_chg_DATA[which]);
                                                        _mpoArray[index] = which + "";
                                                        StringBuffer mpo = new StringBuffer("");
                                                        for (int i = 0; i < _mpoArray.length; i++) {
                                                            mpo.append(_mpoArray[i]);
                                                            if (i != _mpoArray.length - 1)
                                                                mpo.append(",");
                                                        }
                                                        equipmentParamater.setR_P_CHMPO(mpo.toString());
                                                        ReturnMessage returnMessage = new ReturnMessage();
                                                        returnMessage.setKey("r_P_CHMPO");
                                                        returnMessage.setValue(mpo.toString());
                                                        pushData(returnMessage);

                                                    }
                                                })
                                                .show();
                                        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                            @Override
                                            public void onDismiss(DialogInterface dialogInterface) {
                                                upDown.setBackgroundResource(R.mipmap.up_and_down_gray);

                                            }
                                        });


                                        break;
                                    case '2':
                                        upDown.setBackgroundResource(R.mipmap.up_and_down_blue);
                                        QMUIDialog dialog0 = new QMUIDialog.MenuDialogBuilder(getContext())
                                                .addItems(_chg_DATA, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                        value.setText(_chg_DATA[which]);
                                                        _lowArray[index] = which + "";
                                                        StringBuffer mpo = new StringBuffer("");
                                                        for (int i = 0; i < _lowArray.length; i++) {
                                                            mpo.append(_lowArray[i]);
                                                            if (i != _lowArray.length - 1)
                                                                mpo.append(",");
                                                        }
                                                        equipmentParamater.setR_P_CHSG(mpo.toString());
                                                        ReturnMessage returnMessage = new ReturnMessage();
                                                        returnMessage.setKey("r_P_CHSG");
                                                        returnMessage.setValue(mpo.toString());
                                                        pushData(returnMessage);

                                                    }
                                                })
                                                .show();
                                        dialog0.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                            @Override
                                            public void onDismiss(DialogInterface dialogInterface) {
                                                upDown.setBackgroundResource(R.mipmap.up_and_down_gray);
                                            }
                                        });

                                        break;
                                    case '3':
                                        upDown.setBackgroundResource(R.mipmap.up_and_down_blue);
                                        QMUIDialog dialog1 = new QMUIDialog.MenuDialogBuilder(getContext())
                                                .addItems(_chg_DATA, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                        value.setText(_chg_DATA[which]);
                                                        _midArray[index] = which + "";
                                                        StringBuffer mpo = new StringBuffer("");
                                                        for (int i = 0; i < _midArray.length; i++) {
                                                            mpo.append(_midArray[i]);
                                                            if (i != _midArray.length - 1)
                                                                mpo.append(",");
                                                        }
                                                        equipmentParamater.setR_P_CHNG(mpo.toString());
                                                        ReturnMessage returnMessage = new ReturnMessage();
                                                        returnMessage.setKey("r_P_CHNG");
                                                        returnMessage.setValue(mpo.toString());
                                                        pushData(returnMessage);
                                                    }
                                                })
                                                .show();
                                        dialog1.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                            @Override
                                            public void onDismiss(DialogInterface dialogInterface) {
                                                upDown.setBackgroundResource(R.mipmap.up_and_down_gray);
                                            }
                                        });
                                        break;
                                    case '4':
                                        upDown.setBackgroundResource(R.mipmap.up_and_down_blue);
                                        QMUIDialog dialog2 = new QMUIDialog.MenuDialogBuilder(getContext())
                                                .addItems(_chg_DATA, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                        value.setText(_chg_DATA[which]);
                                                        _highArray[index] = which + "";
                                                        StringBuffer mpo = new StringBuffer("");
                                                        for (int i = 0; i < _highArray.length; i++) {
                                                            mpo.append(_highArray[i]);
                                                            if (i != _highArray.length - 1)
                                                                mpo.append(",");
                                                        }
                                                        equipmentParamater.setR_P_CHLG(mpo.toString());
                                                        ReturnMessage returnMessage = new ReturnMessage();
                                                        returnMessage.setKey("r_P_CHLG");
                                                        returnMessage.setValue(mpo.toString());
                                                        pushData(returnMessage);

                                                    }
                                                })
                                                .show();
                                        dialog2.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                            @Override
                                            public void onDismiss(DialogInterface dialogInterface) {
                                                upDown.setBackgroundResource(R.mipmap.up_and_down_gray);
                                            }
                                        });
                                        break;

                                }


                            }
                        });

                    }


                }

                sbVolume.setMax(new Integer(volumeArrayRight[volumeArrayRight.length - 1]) - new Integer(volumeArrayRight[0]));
                sbHigh.setMax(new Integer(HighArrayRight[HighArrayRight.length - 1]) - new Integer(HighArrayRight[0]));
                sbLow.setMax(new Integer(LowArrayRight[LowArrayRight.length - 1]) - new Integer(LowArrayRight[0]));


                sbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        ReturnMessage returnMessage = new ReturnMessage();
                        returnMessage.setKey("r_P_VC_VALUE");
                        if (seekBar.getProgress() < new Integer(volumeArrayRight[volumeArrayRight.length - 1]) - new Integer(volumeArrayRight[0])) {
                            for (int i = 0; i < volumeArrayRight.length; i++) {
                                if (new Integer(volumeArrayRight[0]) + seekBar.getProgress() < new Integer(volumeArrayRight[i])) {
                                    seekBar.setProgress(new Integer(volumeArrayRight[i - 1]) - new Integer(volumeArrayRight[0]));
                                    tvVolume.setText(new Integer(volumeArrayRight[i - 1]) + "");

                                    equipmentParamater.setR_P_VC_VALUE(i - 1);
                                    returnMessage.setValue(i - 1);

                                    break;
                                }


                            }
                        } else {
                            seekBar.setProgress(new Integer(volumeArrayRight[volumeArrayRight.length - 1]) - new Integer(volumeArrayRight[0]));

                            tvVolume.setText(new Integer(volumeArrayRight[volumeArrayRight.length - 1]) + "");
                            equipmentParamater.setR_P_VC_VALUE(volumeArrayRight.length - 1);
                            Map map = new HashMap();
                            map.put("r_P_VC_VALUE", volumeArrayRight.length - 1);

                            returnMessage.setValue(volumeArrayRight.length - 1);
                        }
                        pushData(returnMessage);


                    }
                });
                sbLow.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        ReturnMessage returnMessage = new ReturnMessage();
                        returnMessage.setKey("r_P_BASS_VALUE");
                        if (seekBar.getProgress() < new Integer(LowArrayRight[LowArrayRight.length - 1]) - new Integer(LowArrayRight[0])) {
                            for (int i = 0; i < LowArrayRight.length; i++) {
                                if (new Integer(LowArrayRight[0]) + seekBar.getProgress() < new Integer(LowArrayRight[i])) {
                                    seekBar.setProgress(new Integer(LowArrayRight[i - 1]) - new Integer(LowArrayRight[0]));
                                    tvLow.setText(new Integer(LowArrayRight[i - 1]) + "");

                                    equipmentParamater.setR_P_BASS_VALUE(i - 1);
                                    returnMessage.setValue(-1);
                                    break;
                                }


                            }
                        } else {
                            seekBar.setProgress(new Integer(LowArrayRight[LowArrayRight.length - 1]) - new Integer(LowArrayRight[0]));

                            tvLow.setText(new Integer(LowArrayRight[LowArrayRight.length - 1]) + "");
                            equipmentParamater.setR_P_BASS_VALUE(LowArrayRight.length - 1);
                            returnMessage.setValue(LowArrayRight.length - 1);
                        }
                        pushData(returnMessage);


                    }
                });
                sbHigh.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        ReturnMessage returnMessage = new ReturnMessage();
                        returnMessage.setKey("r_P_TRB_VALUE");

                        if (seekBar.getProgress() < new Integer(HighArrayRight[HighArrayRight.length - 1]) - new Integer(HighArrayRight[0])) {
                            for (int i = 0; i < HighArrayRight.length; i++) {
                                if (new Integer(HighArrayRight[0]) + seekBar.getProgress() < new Integer(HighArrayRight[i])) {
                                    seekBar.setProgress(new Integer(HighArrayRight[i - 1]) - new Integer(HighArrayRight[0]));
                                    tvHigh.setText(new Integer(HighArrayRight[i - 1]) + "");

                                    equipmentParamater.setR_P_TRB_VALUE(i - 1);
                                    returnMessage.setValue(i - 1);
                                    break;
                                }


                            }
                        } else {
                            seekBar.setProgress(new Integer(HighArrayRight[HighArrayRight.length - 1]) - new Integer(HighArrayRight[0]));

                            tvHigh.setText(new Integer(HighArrayRight[HighArrayRight.length - 1]) + "");
                            equipmentParamater.setR_P_TRB_VALUE(HighArrayRight.length - 1);


                            returnMessage.setValue(HighArrayRight.length - 1);
                        }
                        pushData(returnMessage);

                    }
                });
                sbCompress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        equipmentParamater.setR_SWITCH_P_CH(b);

                        ReturnMessage returnMessage = new ReturnMessage();
                        returnMessage.setKey("r_SWITCH_P_CH");
                        returnMessage.setValue(b);
                        pushData(returnMessage);
                    }
                });
                sbExtened.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        equipmentParamater.setR_SWITCH_P_CHEXP(b);
                        if (b) {
                            linearlayout16.setVisibility(View.VISIBLE);
                            linearlayout17.setVisibility(View.VISIBLE);
                        } else {
                            linearlayout16.setVisibility(View.GONE);
                            linearlayout17.setVisibility(View.GONE);
                        }
                        ReturnMessage returnMessage = new ReturnMessage();
                        returnMessage.setKey("r_SWITCH_P_CHEXP");
                        returnMessage.setValue(b);
                        pushData(returnMessage);

                    }
                });
                break;
        }
        sbCompress.setChecked(compressState);
        sbExtened.setChecked(extendState);
        tvNoise.setText(denoise);
        tvFeedback.setText(feedback);
        if (!extendState) {
            linearlayout16.setVisibility(View.GONE);
            linearlayout17.setVisibility(View.GONE);
        }

        sbVolume.setProgress(volume);
        sbHigh.setProgress(high);
        sbLow.setProgress(low);
        tvVolume.setText(volume + "");
        tvHigh.setText(high + "");
        tvLow.setText(low + "");


    }

    @Override
    protected String getTitle() {
        return null;
    }
}
