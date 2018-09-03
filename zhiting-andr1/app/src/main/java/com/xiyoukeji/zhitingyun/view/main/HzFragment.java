package com.xiyoukeji.zhitingyun.view.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ZhitingyunApplication;
import com.xiyoukeji.zhitingyun.base.BaseFragment;
import com.xiyoukeji.zhitingyun.data.entity.DeviceEntity;
import com.xiyoukeji.zhitingyun.hadeviceapi.AllParameter;
import com.xiyoukeji.zhitingyun.hadeviceapi.ParamInfoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.earhear.hadevicelib.HAParameter;
import cn.earhear.hadevicelib.HAParameterList;
import cn.earhear.hadevicelib.constant.SideSelect;
import static com.xiyoukeji.zhitingyun.ZhitingyunApplication.deviceManager;

@SuppressLint("ValidFragment")
public class HzFragment extends BaseFragment {

    @BindView( R.id.left_highsb )
    SeekBar leftHigh;
    @BindView( R.id.left_lowsb )
    SeekBar leftLow;
    @BindView( R.id.right_highsb )
    SeekBar rightHigh;
    @BindView( R.id.right_lowsb )
    SeekBar rightLow;
    @BindView( R.id.left_high )
    TextView showlh;
    @BindView( R.id.left_low )
    TextView showll;
    @BindView( R.id.right_high )
    TextView showrh;
    @BindView( R.id.right_low )
    TextView showrl;

    private ParamInfoAdapter paramInfoAdapter;
    private List<HAParameterList> sysParameterList;
    private List<HAParameterList> memParameterList;
    private static final String TYPE_SYS = "sys";
    private static final String TYPE_LEFT = "left";
    private static final String TYPE_RIGHT = "right";

    private List<AllParameter> allParamList;
    private List<AllParameter> allParameterList;
    private AutoRegulationActivity autoRegulationActivity;
    DeviceEntity entity;

    private AutoRegulationActivity.OngetInfo ongetInfo;
    List<Integer> list=new ArrayList<>(  );


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hezi;
    }


    public HzFragment(AutoRegulationActivity.OngetInfo ongetInfo) {
        this.ongetInfo = ongetInfo;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        entity = new DeviceEntity();

        list.add(0);
        list.add( 0 );
        list.add(0);
        list.add( 0 );
        ongetInfo.ongetInfo( 1,list );
    }


    public void setAllParameterList(List<AllParameter> parameter) {
        this.allParamList = parameter;
        notify();
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public AllParameter getItem(int position) {
        if (allParamList == null || position > allParamList.size())
            return null;
        return allParamList.get(position);
    }



    @Override
    protected void initView(View view) {
        super.initView(view);

        paramInfoAdapter = new ParamInfoAdapter(getContext());
        paramInfoAdapter.setOnValueChangeListener(new ParamInfoAdapter.OnValueChangeListener() {
            @Override
            public void onValueChanging(int pos, int value) {

            }

            @Override
            public void onValueChanged(int pos, int value) {
                AllParameter parameter = paramInfoAdapter.getItem(pos);
                switch (parameter.getType()) {
                    case TYPE_SYS:
                        sysParameterList.get(0).getHAParameterById(parameter.getParameter().getId()).setValue(value);
                        break;
                    case TYPE_LEFT:
                        memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getHAParameterById(parameter.getParameter().getId()).setValue(value);
                        break;
                    case TYPE_RIGHT:
                        memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getHAParameterById(parameter.getParameter().getId()).setValue(value);
                        break;
                    default:
                        break;
                }
            }
        });

    }




    public void fillAllParameterList( List<HAParameterList> sysParameterList, List<HAParameterList> memParameterList,final int currentMemory) {
        if (allParameterList == null)
            allParameterList = new ArrayList<>();
        else
            allParameterList.clear();

        if (ZhitingyunApplication.curState.getHaSide() == SideSelect.S_BINAURAL) {
            for (final HAParameter parameter : sysParameterList.get( 0 ).getParameterList()) {
                AllParameter tmp = new AllParameter( parameter, TYPE_SYS );
                allParameterList.add( tmp );
            }

            for (final HAParameter parameter : memParameterList.get( SideSelect.S_UNILATERAL_LEFT ).getParameterList()) {
                AllParameter tmp = new AllParameter( parameter, TYPE_LEFT );
                allParameterList.add( tmp );

                if(parameter.getId().equals( "TREBLE" )){
                    leftHigh.setMax( parameter.getMax() );
                    leftHigh.setProgress(parameter.getValue() );
                    showlh.setText( String.valueOf(parameter.getValue()) );
                    leftHigh.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            parameter.setValue( leftHigh.getProgress() );
                            deviceManager.writeDevice(currentMemory);
                            showlh.setText( String.valueOf(parameter.getValue()) );

                            list.set( 0,leftHigh.getProgress() );
                            ongetInfo.ongetInfo( 1,list );
                        }
                    } );
                }

                if(parameter.getId().equals( "BASS" )){
                    leftLow.setMax( parameter.getMax() );
                    leftLow.setProgress(parameter.getValue() );
                    showll.setText( String.valueOf(parameter.getValue()) );
                    leftLow.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            parameter.setValue( leftLow.getProgress() );
                            deviceManager.writeDevice(currentMemory);
                            showll.setText( String.valueOf(parameter.getValue()) );

                            list.set( 1,leftLow.getProgress() );
                            ongetInfo.ongetInfo( 1,list );
                        }
                    } );

                }

            }

            for (final HAParameter parameter : memParameterList.get( SideSelect.S_UNILATERAL_RIGHT ).getParameterList()) {
                AllParameter tmp = new AllParameter( parameter, TYPE_RIGHT );
                allParameterList.add( tmp );

                if(parameter.getId().equals( "TREBLE" )){
                    rightHigh.setMax( parameter.getMax() );
                    rightHigh.setProgress(parameter.getValue() );
                    rightHigh.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            parameter.setValue( rightHigh.getProgress() );
                            deviceManager.writeDevice(currentMemory);
                            showrh.setText( String.valueOf(parameter.getValue()) );

                            list.set( 2,rightHigh.getProgress() );
                            ongetInfo.ongetInfo( 1,list );
                        }
                    } );
                }


                if(parameter.getId().equals( "BASS" )){
                    rightLow.setMax( parameter.getMax() );
                    rightLow.setProgress(parameter.getValue() );
                    rightLow.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            parameter.setValue( rightLow.getProgress() );
                            deviceManager.writeDevice(currentMemory);
                            showrl.setText( String.valueOf(parameter.getValue()) );

                            list.set( 3,rightLow.getProgress() );
                            ongetInfo.ongetInfo( 1,list );
                        }
                    } );
                }
            }
        }
        paramInfoAdapter.setAllParameterList( allParameterList );


    }
}
