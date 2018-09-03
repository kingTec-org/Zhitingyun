package com.xiyoukeji.zhitingyun.view.main;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ZhitingyunApplication;
import com.xiyoukeji.zhitingyun.databinding.FragmentSlidetestLeftBinding;
import com.xiyoukeji.zhitingyun.hadeviceapi.PureToneManager;

import com.xiyoukeji.zhitingyun.base.BaseFragment;
import com.xiyoukeji.zhitingyun.viewmodel.main.MainViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import cn.earhear.hadevicelib.constant.FittingAlgorithm;
import cn.earhear.hadevicelib.constant.SideSelect;

@SuppressLint("ValidFragment")
public class LeftEarFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.hz125)
    SeekBar sb125;
    @BindView(R.id.hz250)
    SeekBar sb250;
    @BindView(R.id.hz500)
    SeekBar sb500;
    @BindView(R.id.hz750)
    SeekBar sb750;
    @BindView(R.id.hz1000)
    SeekBar sb1000;
    @BindView(R.id.hz1500)
    SeekBar sb1500;
    @BindView(R.id.hz2000)
    SeekBar sb2000;
    @BindView(R.id.hz3000)
    SeekBar sb3000;
    @BindView(R.id.hz4000)
    SeekBar sb4000;
    @BindView(R.id.hz6000)
    SeekBar sb6000;
    @BindView(R.id.hz8000)
    SeekBar sb8000;
    @BindView(R.id.show_125)
    TextView show125;
    @BindView(R.id.show_250)
    TextView show250;
    @BindView(R.id.show_500)
    TextView show500;
    @BindView(R.id.show_750)
    TextView show750;
    @BindView(R.id.show_1000)
    TextView show1000;
    @BindView(R.id.show_1500)
    TextView show1500;
    @BindView(R.id.show_2000)
    TextView show2000;
    @BindView(R.id.show_3000)
    TextView show3000;
    @BindView(R.id.show_4000)
    TextView show4000;
    @BindView(R.id.show_6000)
    TextView show6000;
    @BindView(R.id.show_8000)
    TextView show8000;


    @BindView(R.id.resource_layout)
    LinearLayout mResource;

    @BindView(R.id.type)
    TextView type;

    @BindView( R.id.resource )
    ImageView resImg;

    private PureToneManager pureToneManager;
    private int[] left_hl;
    private int[] right_hl;

    private FragmentSlidetestLeftBinding mBinding;
    private MainViewModel mViewModel;


    private SlideFragment.OnFeedBackListener onFeedBackListener;
    List<Integer> list = new ArrayList<>();



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_slidetest_left;
    }

    public LeftEarFragment(SlideFragment.OnFeedBackListener onFeedBackListener) {
        this.onFeedBackListener = onFeedBackListener;
    }



    @Override
    protected View bindingData(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentSlidetestLeftBinding.inflate(inflater, container, false);
        mViewModel = MainActivity.obtainViewModel(getActivity());
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prepareHearingTest();
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(0);
        onFeedBackListener.onFeedback( 0,list );


    }

    @Override
    protected void initData() {
        super.initData();
    }



    private void prepareHearingTest() {
        pureToneManager = new PureToneManager(mContext);
        left_hl = new int[FittingAlgorithm.FA_HL_ARR_SIZE];
        Arrays.fill(left_hl, FittingAlgorithm.INVALID_HL_VAL);
        right_hl = new int[FittingAlgorithm.FA_HL_ARR_SIZE];
        Arrays.fill(right_hl, FittingAlgorithm.INVALID_HL_VAL);
    }




    private int getPureToneFreqByIndex(int index) {
        switch (index) {
            case 0:
            case 1:
                return (1 + index) * 125;
            case 2:
            case 3:
            case 4:
                return index * 250;
            case 5:
            case 6:
                return (index - 2) * 500;
            case 7:
            case 8:
                return (index - 4) * 1000;
            case 9:
            case 10:
                return (index - 6) * 2000;
            default:
                return 8000;
        }
    }



    @Override
    protected void initView(View view) {
        super.initView(view);

        mResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resImg.setBackgroundResource( R.mipmap.up_key );
                PopupMenu popup = new PopupMenu(mContext, mResource);
                popup.getMenuInflater()
                        .inflate(R.menu.resource_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.item_content_one:
                                type.setText("纯音");
                                break;
                            case R.id.item_content_two:
                                type.setText("调频音");
                        }
                        return true;
                    }
                });
                popup.show(); //showing popup menu

                popup.setOnDismissListener( new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu menu) {
                        resImg.setBackgroundResource( R.mipmap.down_key );
                    }
                } );
            }
        });


        sb125.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            private int getPureToneDb(int side) {
                if (side == SideSelect.S_UNILATERAL_LEFT) {
                    String dbString = show125.getText().toString();
                    if (!dbString.isEmpty()) {
                        int dbVal = Integer.valueOf(dbString);
                        if (dbVal >= FittingAlgorithm.MIN_HL_VAL && dbVal <= FittingAlgorithm.MAX_HL_VAL)
                            return dbVal;
                    }
                }
                return FittingAlgorithm.INVALID_HL_VAL;
            }

            private int getPureToneFreq() {
                return getPureToneFreqByIndex(getPureToneIndex());
            }

            private int getPureToneIndex() {
                return 0;
            }


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                show125.setText(String.valueOf(sb125.getProgress()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int seekProgress = sb125.getProgress();
                if(seekProgress>3&&seekProgress<8){
                    sb125.setProgress(5);
                } else if (seekProgress >= 8 && seekProgress < 13) {
                    sb125.setProgress(10);
                } else if (seekProgress >= 13 && seekProgress < 18) {
                    sb125.setProgress(15);
                } else if (seekProgress >= 18 && seekProgress < 23) {
                    sb125.setProgress(20);
                } else if (seekProgress >= 23 && seekProgress < 28) {
                    sb125.setProgress(25);
                } else if (seekProgress >= 28 && seekProgress < 33) {
                    sb125.setProgress(30);
                } else if (seekProgress >= 33 && seekProgress < 38) {
                    sb125.setProgress(35);
                } else if (seekProgress >= 38 && seekProgress < 43) {
                    sb125.setProgress(40);
                } else if (seekProgress >= 43 && seekProgress < 48) {
                    sb125.setProgress(45);
                } else if (seekProgress >= 48 && seekProgress < 53) {
                    sb125.setProgress(50);
                } else if (seekProgress >= 53 && seekProgress < 58) {
                    sb125.setProgress(55);
                } else if (seekProgress >= 58 && seekProgress < 63) {
                    sb125.setProgress(60);
                } else if (seekProgress >= 63 && seekProgress < 68) {
                    sb125.setProgress(65);
                } else if (seekProgress >= 68 && seekProgress < 73) {
                    sb125.setProgress(70);
                } else if (seekProgress >= 73 && seekProgress < 78) {
                    sb125.setProgress(75);
                } else if (seekProgress >= 78 && seekProgress < 83) {
                    sb125.setProgress(80);
                } else if (seekProgress >= 83 && seekProgress < 88) {
                    sb125.setProgress(85);
                } else if (seekProgress >= 88 && seekProgress < 93) {
                    sb125.setProgress(90);
                } else if (seekProgress >= 93) {
                    sb125.setProgress(100);
                }

                pureToneManager.start(SideSelect.S_UNILATERAL_LEFT, getPureToneFreq(), getPureToneDb(SideSelect.S_UNILATERAL_LEFT));
                list.set(0, sb125.getProgress());
                onFeedBackListener.onFeedback(0, list);

            }
        });

        sb250.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private int getPureToneDb(int side) {
                if (side == SideSelect.S_UNILATERAL_LEFT) {
                    String dbString = show250.getText().toString();
                    if (!dbString.isEmpty()) {
                        int dbVal = Integer.valueOf(dbString);
                        if (dbVal >= FittingAlgorithm.MIN_HL_VAL && dbVal <= FittingAlgorithm.MAX_HL_VAL)
                            return dbVal;
                    }
                }
                return FittingAlgorithm.INVALID_HL_VAL;
            }

            private int getPureToneFreq() {
                return getPureToneFreqByIndex(getPureToneIndex());
            }

            private int getPureToneIndex() {
                return 1;
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                show250.setText(String.valueOf(sb250.getProgress()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int seekProgress = sb250.getProgress();
                if(seekProgress>3&&seekProgress<8){
                    sb250.setProgress(5);
                } else if (seekProgress >= 8 && seekProgress < 13) {
                    sb250.setProgress(10);
                } else if (seekProgress >= 13 && seekProgress < 18) {
                    sb250.setProgress(15);
                } else if (seekProgress >= 18 && seekProgress < 23) {
                    sb250.setProgress(20);
                } else if (seekProgress >= 23 && seekProgress < 28) {
                    sb250.setProgress(25);
                } else if (seekProgress >= 28 && seekProgress < 33) {
                    sb250.setProgress(30);
                } else if (seekProgress >= 33 && seekProgress < 38) {
                    sb250.setProgress(35);
                } else if (seekProgress >= 38 && seekProgress < 43) {
                    sb250.setProgress(40);
                } else if (seekProgress >= 43 && seekProgress < 48) {
                    sb250.setProgress(45);
                } else if (seekProgress >= 48 && seekProgress < 53) {
                    sb250.setProgress(50);
                } else if (seekProgress >= 53 && seekProgress < 58) {
                    sb250.setProgress(55);
                } else if (seekProgress >= 58 && seekProgress < 63) {
                    sb250.setProgress(60);
                } else if (seekProgress >= 63 && seekProgress < 68) {
                    sb250.setProgress(65);
                } else if (seekProgress >= 68 && seekProgress < 73) {
                    sb250.setProgress(70);
                } else if (seekProgress >= 73 && seekProgress < 78) {
                    sb250.setProgress(75);
                } else if (seekProgress >= 78 && seekProgress < 83) {
                    sb250.setProgress(80);
                } else if (seekProgress >= 83 && seekProgress < 88) {
                    sb250.setProgress(85);
                } else if (seekProgress >= 88 && seekProgress < 93) {
                    sb250.setProgress(90);
                } else if (seekProgress >= 93) {
                    sb250.setProgress(100);
                }
//                show250.setText(String.valueOf(sb250.getProgress()));
                pureToneManager.start(SideSelect.S_UNILATERAL_LEFT, getPureToneFreq(), getPureToneDb(SideSelect.S_UNILATERAL_LEFT));
                list.set(1, sb250.getProgress());
                onFeedBackListener.onFeedback(0, list);
            }
        });

        sb500.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private int getPureToneDb(int side) {
                if (side == SideSelect.S_UNILATERAL_LEFT) {
                    String dbString = show500.getText().toString();
                    if (!dbString.isEmpty()) {
                        int dbVal = Integer.valueOf(dbString);
                        if (dbVal >= FittingAlgorithm.MIN_HL_VAL && dbVal <= FittingAlgorithm.MAX_HL_VAL)
                            return dbVal;
                    }
                }
                return FittingAlgorithm.INVALID_HL_VAL;
            }

            private int getPureToneFreq() {
                return getPureToneFreqByIndex(getPureToneIndex());
            }

            private int getPureToneIndex() {
                return 2;
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                show500.setText(String.valueOf(sb500.getProgress()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int seekProgress = sb500.getProgress();
                if(seekProgress>3&&seekProgress<8){
                    sb500.setProgress(5);
                } else if (seekProgress >= 8 && seekProgress < 13) {
                    sb500.setProgress(10);
                } else if (seekProgress >= 13 && seekProgress < 18) {
                    sb500.setProgress(15);
                } else if (seekProgress >= 18 && seekProgress < 23) {
                    sb500.setProgress(20);
                } else if (seekProgress >= 23 && seekProgress < 28) {
                    sb500.setProgress(25);
                } else if (seekProgress >= 28 && seekProgress < 33) {
                    sb500.setProgress(30);
                } else if (seekProgress >= 33 && seekProgress < 38) {
                    sb500.setProgress(35);
                } else if (seekProgress >= 38 && seekProgress < 43) {
                    sb500.setProgress(40);
                } else if (seekProgress >= 43 && seekProgress < 48) {
                    sb500.setProgress(45);
                } else if (seekProgress >= 48 && seekProgress < 53) {
                    sb500.setProgress(50);
                } else if (seekProgress >= 53 && seekProgress < 58) {
                    sb500.setProgress(55);
                } else if (seekProgress >= 58 && seekProgress < 63) {
                    sb500.setProgress(60);
                } else if (seekProgress >= 63 && seekProgress < 68) {
                    sb500.setProgress(65);
                } else if (seekProgress >= 68 && seekProgress < 73) {
                    sb500.setProgress(70);
                } else if (seekProgress >= 73 && seekProgress < 78) {
                    sb500.setProgress(75);
                } else if (seekProgress >= 78 && seekProgress < 83) {
                    sb500.setProgress(80);
                } else if (seekProgress >= 83 && seekProgress < 88) {
                    sb500.setProgress(85);
                } else if (seekProgress >= 88 && seekProgress < 93) {
                    sb500.setProgress(90);
                } else if (seekProgress >= 93) {
                    sb500.setProgress(100);
                }
//                show500.setText(String.valueOf(sb500.getProgress()));
                pureToneManager.start(SideSelect.S_UNILATERAL_LEFT, getPureToneFreq(), getPureToneDb(SideSelect.S_UNILATERAL_LEFT));
                list.set(2, sb500.getProgress());
                onFeedBackListener.onFeedback(0, list);
            }
        });

        sb750.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private int getPureToneDb(int side) {
                if (side == SideSelect.S_UNILATERAL_LEFT) {
                    String dbString = show750.getText().toString();
                    if (!dbString.isEmpty()) {
                        int dbVal = Integer.valueOf(dbString);
                        if (dbVal >= FittingAlgorithm.MIN_HL_VAL && dbVal <= FittingAlgorithm.MAX_HL_VAL)
                            return dbVal;
                    }
                }
                return FittingAlgorithm.INVALID_HL_VAL;
            }

            private int getPureToneFreq() {
                return getPureToneFreqByIndex(getPureToneIndex());
            }

            private int getPureToneIndex() {
                return 3;
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                show750.setText(String.valueOf(sb750.getProgress()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int seekProgress = sb750.getProgress();
                if(seekProgress>3&&seekProgress<8){
                    sb750.setProgress(5);
                } else if (seekProgress >= 8 && seekProgress < 13) {
                    sb750.setProgress(10);
                } else if (seekProgress >= 13 && seekProgress < 18) {
                    sb750.setProgress(15);
                } else if (seekProgress >= 18 && seekProgress < 23) {
                    sb750.setProgress(20);
                } else if (seekProgress >= 23 && seekProgress < 28) {
                    sb750.setProgress(25);
                } else if (seekProgress >= 28 && seekProgress < 33) {
                    sb750.setProgress(30);
                } else if (seekProgress >= 33 && seekProgress < 38) {
                    sb750.setProgress(35);
                } else if (seekProgress >= 38 && seekProgress < 43) {
                    sb750.setProgress(40);
                } else if (seekProgress >= 43 && seekProgress < 48) {
                    sb750.setProgress(45);
                } else if (seekProgress >= 48 && seekProgress < 53) {
                    sb750.setProgress(50);
                } else if (seekProgress >= 53 && seekProgress < 58) {
                    sb750.setProgress(55);
                } else if (seekProgress >= 58 && seekProgress < 63) {
                    sb750.setProgress(60);
                } else if (seekProgress >= 63 && seekProgress < 68) {
                    sb750.setProgress(65);
                } else if (seekProgress >= 68 && seekProgress < 73) {
                    sb750.setProgress(70);
                } else if (seekProgress >= 73 && seekProgress < 78) {
                    sb750.setProgress(75);
                } else if (seekProgress >= 78 && seekProgress < 83) {
                    sb750.setProgress(80);
                } else if (seekProgress >= 83 && seekProgress < 88) {
                    sb750.setProgress(85);
                } else if (seekProgress >= 88 && seekProgress < 93) {
                    sb750.setProgress(90);
                } else if (seekProgress >= 93) {
                    sb750.setProgress(100);
                }
//                show750.setText(String.valueOf(sb750.getProgress()));
                pureToneManager.start(SideSelect.S_UNILATERAL_LEFT, getPureToneFreq(), getPureToneDb(SideSelect.S_UNILATERAL_LEFT));
                list.set(3, sb750.getProgress());
                onFeedBackListener.onFeedback(0, list);
            }
        });

        sb1000.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private int getPureToneDb(int side) {
                if (side == SideSelect.S_UNILATERAL_LEFT) {
                    String dbString = show1000.getText().toString();
                    if (!dbString.isEmpty()) {
                        int dbVal = Integer.valueOf(dbString);
                        if (dbVal >= FittingAlgorithm.MIN_HL_VAL && dbVal <= FittingAlgorithm.MAX_HL_VAL)
                            return dbVal;
                    }
                }
                return FittingAlgorithm.INVALID_HL_VAL;
            }

            private int getPureToneFreq() {
                return getPureToneFreqByIndex(getPureToneIndex());
            }

            private int getPureToneIndex() {
                return 4;
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                show1000.setText(String.valueOf(sb1000.getProgress()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                show125.setText( String.valueOf( sb125.getProgress() ) );

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int seekProgress = sb1000.getProgress();
                if(seekProgress>3&&seekProgress<8){
                    sb1000.setProgress(5);
                } else if (seekProgress >= 8 && seekProgress < 13) {
                    sb1000.setProgress(10);
                } else if (seekProgress >= 13 && seekProgress < 18) {
                    sb1000.setProgress(15);
                } else if (seekProgress >= 18 && seekProgress < 23) {
                    sb1000.setProgress(20);
                } else if (seekProgress >= 23 && seekProgress < 28) {
                    sb1000.setProgress(25);
                } else if (seekProgress >= 28 && seekProgress < 33) {
                    sb1000.setProgress(30);
                } else if (seekProgress >= 33 && seekProgress < 38) {
                    sb1000.setProgress(35);
                } else if (seekProgress >= 38 && seekProgress < 43) {
                    sb1000.setProgress(40);
                } else if (seekProgress >= 43 && seekProgress < 48) {
                    sb1000.setProgress(45);
                } else if (seekProgress >= 48 && seekProgress < 53) {
                    sb1000.setProgress(50);
                } else if (seekProgress >= 53 && seekProgress < 58) {
                    sb1000.setProgress(55);
                } else if (seekProgress >= 58 && seekProgress < 63) {
                    sb1000.setProgress(60);
                } else if (seekProgress >= 63 && seekProgress < 68) {
                    sb1000.setProgress(65);
                } else if (seekProgress >= 68 && seekProgress < 73) {
                    sb1000.setProgress(70);
                } else if (seekProgress >= 73 && seekProgress < 78) {
                    sb1000.setProgress(75);
                } else if (seekProgress >= 78 && seekProgress < 83) {
                    sb1000.setProgress(80);
                } else if (seekProgress >= 83 && seekProgress < 88) {
                    sb1000.setProgress(85);
                } else if (seekProgress >= 88 && seekProgress < 93) {
                    sb1000.setProgress(90);
                } else if (seekProgress >= 93) {
                    sb1000.setProgress(100);
                }
//                show1000.setText(String.valueOf(sb1000.getProgress()));
                pureToneManager.start(SideSelect.S_UNILATERAL_LEFT, getPureToneFreq(), getPureToneDb(SideSelect.S_UNILATERAL_LEFT));
                list.set(4, sb1000.getProgress());
                onFeedBackListener.onFeedback(0, list);
            }
        });

        sb1500.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private int getPureToneDb(int side) {
                if (side == SideSelect.S_UNILATERAL_LEFT) {
                    String dbString = show1500.getText().toString();
                    if (!dbString.isEmpty()) {
                        int dbVal = Integer.valueOf(dbString);
                        if (dbVal >= FittingAlgorithm.MIN_HL_VAL && dbVal <= FittingAlgorithm.MAX_HL_VAL)
                            return dbVal;
                    }
                }
                return FittingAlgorithm.INVALID_HL_VAL;
            }

            private int getPureToneFreq() {
                return getPureToneFreqByIndex(getPureToneIndex());
            }

            private int getPureToneIndex() {
                return 5;
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                show1500.setText(String.valueOf(sb1500.getProgress()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                show125.setText( String.valueOf( sb125.getProgress() ) );

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int seekProgress = sb1500.getProgress();
                if(seekProgress>3&&seekProgress<8){
                    sb1500.setProgress(5);
                } else if (seekProgress >= 8 && seekProgress < 13) {
                    sb1500.setProgress(10);
                } else if (seekProgress >= 13 && seekProgress < 18) {
                    sb1500.setProgress(15);
                } else if (seekProgress >= 18 && seekProgress < 23) {
                    sb1500.setProgress(20);
                } else if (seekProgress >= 23 && seekProgress < 28) {
                    sb1500.setProgress(25);
                } else if (seekProgress >= 28 && seekProgress < 33) {
                    sb1500.setProgress(30);
                } else if (seekProgress >= 33 && seekProgress < 38) {
                    sb1500.setProgress(35);
                } else if (seekProgress >= 38 && seekProgress < 43) {
                    sb1500.setProgress(40);
                } else if (seekProgress >= 43 && seekProgress < 48) {
                    sb1500.setProgress(45);
                } else if (seekProgress >= 48 && seekProgress < 53) {
                    sb1500.setProgress(50);
                } else if (seekProgress >= 53 && seekProgress < 58) {
                    sb1500.setProgress(55);
                } else if (seekProgress >= 58 && seekProgress < 63) {
                    sb1500.setProgress(60);
                } else if (seekProgress >= 63 && seekProgress < 68) {
                    sb1500.setProgress(65);
                } else if (seekProgress >= 68 && seekProgress < 73) {
                    sb1500.setProgress(70);
                } else if (seekProgress >= 73 && seekProgress < 78) {
                    sb1500.setProgress(75);
                } else if (seekProgress >= 78 && seekProgress < 83) {
                    sb1500.setProgress(80);
                } else if (seekProgress >= 83 && seekProgress < 88) {
                    sb1500.setProgress(85);
                } else if (seekProgress >= 88 && seekProgress < 93) {
                    sb1500.setProgress(90);
                } else if (seekProgress >= 93) {
                    sb1500.setProgress(100);
                }
//                show1500.setText(String.valueOf(sb1500.getProgress()));
                pureToneManager.start(SideSelect.S_UNILATERAL_LEFT, getPureToneFreq(), getPureToneDb(SideSelect.S_UNILATERAL_LEFT));
                list.set(5, sb1500.getProgress());
                onFeedBackListener.onFeedback(0, list);
            }
        });

        sb2000.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private int getPureToneDb(int side) {
                if (side == SideSelect.S_UNILATERAL_LEFT) {
                    String dbString = show2000.getText().toString();
                    if (!dbString.isEmpty()) {
                        int dbVal = Integer.valueOf(dbString);
                        if (dbVal >= FittingAlgorithm.MIN_HL_VAL && dbVal <= FittingAlgorithm.MAX_HL_VAL)
                            return dbVal;
                    }
                }
                return FittingAlgorithm.INVALID_HL_VAL;
            }

            private int getPureToneFreq() {
                return getPureToneFreqByIndex(getPureToneIndex());
            }

            private int getPureToneIndex() {
                return 6;
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                show2000.setText(String.valueOf(sb2000.getProgress()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                show125.setText( String.valueOf( sb125.getProgress() ) );

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int seekProgress = sb2000.getProgress();
                if(seekProgress>3&&seekProgress<8){
                    sb2000.setProgress(5);
                } else if (seekProgress >= 8 && seekProgress < 13) {
                    sb2000.setProgress(10);
                } else if (seekProgress >= 13 && seekProgress < 18) {
                    sb2000.setProgress(15);
                } else if (seekProgress >= 18 && seekProgress < 23) {
                    sb2000.setProgress(20);
                } else if (seekProgress >= 23 && seekProgress < 28) {
                    sb2000.setProgress(25);
                } else if (seekProgress >= 28 && seekProgress < 33) {
                    sb2000.setProgress(30);
                } else if (seekProgress >= 33 && seekProgress < 38) {
                    sb2000.setProgress(35);
                } else if (seekProgress >= 38 && seekProgress < 43) {
                    sb2000.setProgress(40);
                } else if (seekProgress >= 43 && seekProgress < 48) {
                    sb2000.setProgress(45);
                } else if (seekProgress >= 48 && seekProgress < 53) {
                    sb2000.setProgress(50);
                } else if (seekProgress >= 53 && seekProgress < 58) {
                    sb2000.setProgress(55);
                } else if (seekProgress >= 58 && seekProgress < 63) {
                    sb2000.setProgress(60);
                } else if (seekProgress >= 63 && seekProgress < 68) {
                    sb2000.setProgress(65);
                } else if (seekProgress >= 68 && seekProgress < 73) {
                    sb2000.setProgress(70);
                } else if (seekProgress >= 73 && seekProgress < 78) {
                    sb2000.setProgress(75);
                } else if (seekProgress >= 78 && seekProgress < 83) {
                    sb2000.setProgress(80);
                } else if (seekProgress >= 83 && seekProgress < 88) {
                    sb2000.setProgress(85);
                } else if (seekProgress >= 88 && seekProgress < 93) {
                    sb2000.setProgress(90);
                } else if (seekProgress >= 93) {
                    sb2000.setProgress(100);
                }
                show2000.setText(String.valueOf(sb2000.getProgress()));
                pureToneManager.start(SideSelect.S_UNILATERAL_LEFT, getPureToneFreq(), getPureToneDb(SideSelect.S_UNILATERAL_LEFT));
                list.set(6, sb2000.getProgress());
                onFeedBackListener.onFeedback(0, list);
            }
        });

        sb3000.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private int getPureToneDb(int side) {
                if (side == SideSelect.S_UNILATERAL_LEFT) {
                    String dbString = show3000.getText().toString();
                    if (!dbString.isEmpty()) {
                        int dbVal = Integer.valueOf(dbString);
                        if (dbVal >= FittingAlgorithm.MIN_HL_VAL && dbVal <= FittingAlgorithm.MAX_HL_VAL)
                            return dbVal;
                    }
                }
                return FittingAlgorithm.INVALID_HL_VAL;
            }

            private int getPureToneFreq() {
                return getPureToneFreqByIndex(getPureToneIndex());
            }

            private int getPureToneIndex() {
                return 7;
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                show3000.setText(String.valueOf(sb3000.getProgress()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                show125.setText( String.valueOf( sb125.getProgress() ) );

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int seekProgress = sb3000.getProgress();
                if(seekProgress>3&&seekProgress<8){
                    sb3000.setProgress(5);
                } else if (seekProgress >= 8 && seekProgress < 13) {
                    sb3000.setProgress(10);
                } else if (seekProgress >= 13 && seekProgress < 18) {
                    sb3000.setProgress(15);
                } else if (seekProgress >= 18 && seekProgress < 23) {
                    sb3000.setProgress(20);
                } else if (seekProgress >= 23 && seekProgress < 28) {
                    sb3000.setProgress(25);
                } else if (seekProgress >= 28 && seekProgress < 33) {
                    sb3000.setProgress(30);
                } else if (seekProgress >= 33 && seekProgress < 38) {
                    sb3000.setProgress(35);
                } else if (seekProgress >= 38 && seekProgress < 43) {
                    sb3000.setProgress(40);
                } else if (seekProgress >= 43 && seekProgress < 48) {
                    sb3000.setProgress(45);
                } else if (seekProgress >= 48 && seekProgress < 53) {
                    sb3000.setProgress(50);
                } else if (seekProgress >= 53 && seekProgress < 58) {
                    sb3000.setProgress(55);
                } else if (seekProgress >= 58 && seekProgress < 63) {
                    sb3000.setProgress(60);
                } else if (seekProgress >= 63 && seekProgress < 68) {
                    sb3000.setProgress(65);
                } else if (seekProgress >= 68 && seekProgress < 73) {
                    sb3000.setProgress(70);
                } else if (seekProgress >= 73 && seekProgress < 78) {
                    sb3000.setProgress(75);
                } else if (seekProgress >= 78 && seekProgress < 83) {
                    sb3000.setProgress(80);
                } else if (seekProgress >= 83 && seekProgress < 88) {
                    sb3000.setProgress(85);
                } else if (seekProgress >= 88 && seekProgress < 93) {
                    sb3000.setProgress(90);
                } else if (seekProgress >= 93) {
                    sb3000.setProgress(100);
                }
//                show3000.setText(String.valueOf(sb3000.getProgress()));
                pureToneManager.start(SideSelect.S_UNILATERAL_LEFT, getPureToneFreq(), getPureToneDb(SideSelect.S_UNILATERAL_LEFT));
                list.set(7, sb3000.getProgress());
                onFeedBackListener.onFeedback(0, list);
            }
        });

        sb4000.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private int getPureToneDb(int side) {
                if (side == SideSelect.S_UNILATERAL_LEFT) {
                    String dbString = show4000.getText().toString();
                    if (!dbString.isEmpty()) {
                        int dbVal = Integer.valueOf(dbString);
                        if (dbVal >= FittingAlgorithm.MIN_HL_VAL && dbVal <= FittingAlgorithm.MAX_HL_VAL)
                            return dbVal;
                    }
                }
                return FittingAlgorithm.INVALID_HL_VAL;
            }

            private int getPureToneFreq() {
                return getPureToneFreqByIndex(getPureToneIndex());
            }

            private int getPureToneIndex() {
                return 8;
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                show4000.setText(String.valueOf(sb4000.getProgress()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int seekProgress = sb4000.getProgress();
                if(seekProgress>3&&seekProgress<8){
                    sb4000.setProgress(5);
                } else if (seekProgress >= 8 && seekProgress < 13) {
                    sb4000.setProgress(10);
                } else if (seekProgress >= 13 && seekProgress < 18) {
                    sb4000.setProgress(15);
                } else if (seekProgress >= 18 && seekProgress < 23) {
                    sb4000.setProgress(20);
                } else if (seekProgress >= 23 && seekProgress < 28) {
                    sb4000.setProgress(25);
                } else if (seekProgress >= 28 && seekProgress < 33) {
                    sb4000.setProgress(30);
                } else if (seekProgress >= 33 && seekProgress < 38) {
                    sb4000.setProgress(35);
                } else if (seekProgress >= 38 && seekProgress < 43) {
                    sb4000.setProgress(40);
                } else if (seekProgress >= 43 && seekProgress < 48) {
                    sb4000.setProgress(45);
                } else if (seekProgress >= 48 && seekProgress < 53) {
                    sb4000.setProgress(50);
                } else if (seekProgress >= 53 && seekProgress < 58) {
                    sb4000.setProgress(55);
                } else if (seekProgress >= 58 && seekProgress < 63) {
                    sb4000.setProgress(60);
                } else if (seekProgress >= 63 && seekProgress < 68) {
                    sb4000.setProgress(65);
                } else if (seekProgress >= 68 && seekProgress < 73) {
                    sb4000.setProgress(70);
                } else if (seekProgress >= 73 && seekProgress < 78) {
                    sb4000.setProgress(75);
                } else if (seekProgress >= 78 && seekProgress < 83) {
                    sb4000.setProgress(80);
                } else if (seekProgress >= 83 && seekProgress < 88) {
                    sb4000.setProgress(85);
                } else if (seekProgress >= 88 && seekProgress < 93) {
                    sb4000.setProgress(90);
                } else if (seekProgress >= 93) {
                    sb4000.setProgress(100);
                }
//                show4000.setText(String.valueOf(sb4000.getProgress()));
                pureToneManager.start(SideSelect.S_UNILATERAL_LEFT, getPureToneFreq(), getPureToneDb(SideSelect.S_UNILATERAL_LEFT));
                list.set(8, sb4000.getProgress());
                onFeedBackListener.onFeedback(0, list);
            }
        });

        sb6000.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private int getPureToneDb(int side) {
                if (side == SideSelect.S_UNILATERAL_LEFT) {
                    String dbString = show6000.getText().toString();
                    if (!dbString.isEmpty()) {
                        int dbVal = Integer.valueOf(dbString);
                        if (dbVal >= FittingAlgorithm.MIN_HL_VAL && dbVal <= FittingAlgorithm.MAX_HL_VAL)
                            return dbVal;
                    }
                }
                return FittingAlgorithm.INVALID_HL_VAL;
            }

            private int getPureToneFreq() {
                return getPureToneFreqByIndex(getPureToneIndex());
            }

            private int getPureToneIndex() {
                return 9;
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                show6000.setText(String.valueOf(sb6000.getProgress()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int seekProgress = sb6000.getProgress();
                if(seekProgress>3&&seekProgress<8){
                    sb6000.setProgress(5);
                } else if (seekProgress >= 8 && seekProgress < 13) {
                    sb6000.setProgress(10);
                } else if (seekProgress >= 13 && seekProgress < 18) {
                    sb6000.setProgress(15);
                } else if (seekProgress >= 18 && seekProgress < 23) {
                    sb6000.setProgress(20);
                } else if (seekProgress >= 23 && seekProgress < 28) {
                    sb6000.setProgress(25);
                } else if (seekProgress >= 28 && seekProgress < 33) {
                    sb6000.setProgress(30);
                } else if (seekProgress >= 33 && seekProgress < 38) {
                    sb6000.setProgress(35);
                } else if (seekProgress >= 38 && seekProgress < 43) {
                    sb6000.setProgress(40);
                } else if (seekProgress >= 43 && seekProgress < 48) {
                    sb6000.setProgress(45);
                } else if (seekProgress >= 48 && seekProgress < 53) {
                    sb6000.setProgress(50);
                } else if (seekProgress >= 53 && seekProgress < 58) {
                    sb6000.setProgress(55);
                } else if (seekProgress >= 58 && seekProgress < 63) {
                    sb6000.setProgress(60);
                } else if (seekProgress >= 63 && seekProgress < 68) {
                    sb6000.setProgress(65);
                } else if (seekProgress >= 68 && seekProgress < 73) {
                    sb6000.setProgress(70);
                } else if (seekProgress >= 73 && seekProgress < 78) {
                    sb6000.setProgress(75);
                } else if (seekProgress >= 78 && seekProgress < 83) {
                    sb6000.setProgress(80);
                } else if (seekProgress >= 83 && seekProgress < 88) {
                    sb6000.setProgress(85);
                } else if (seekProgress >= 88 && seekProgress < 93) {
                    sb6000.setProgress(90);
                } else if (seekProgress >= 93) {
                    sb6000.setProgress(100);
                }
                pureToneManager.start(SideSelect.S_UNILATERAL_LEFT, getPureToneFreq(), getPureToneDb(SideSelect.S_UNILATERAL_LEFT));
                list.set(9, sb6000.getProgress());
                onFeedBackListener.onFeedback(0, list);
            }
        });

        sb8000.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private int getPureToneDb(int side) {
                if (side == SideSelect.S_UNILATERAL_LEFT) {
                    String dbString = show8000.getText().toString();
                    if (!dbString.isEmpty()) {
                        int dbVal = Integer.valueOf(dbString);
                        if (dbVal >= FittingAlgorithm.MIN_HL_VAL && dbVal <= FittingAlgorithm.MAX_HL_VAL)
                            return dbVal;
                    }
                }
                return FittingAlgorithm.INVALID_HL_VAL;
            }

            private int getPureToneFreq() {
                return getPureToneFreqByIndex(getPureToneIndex());
            }

            private int getPureToneIndex() {
                return 10;
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                show8000.setText(String.valueOf(sb8000.getProgress()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int seekProgress = sb8000.getProgress();
                if(seekProgress>3&&seekProgress<8){
                    sb8000.setProgress(5);
                } else if (seekProgress >= 8 && seekProgress < 13) {
                    sb8000.setProgress(10);
                } else if (seekProgress >= 13 && seekProgress < 18) {
                    sb8000.setProgress(15);
                } else if (seekProgress >= 18 && seekProgress < 23) {
                    sb8000.setProgress(20);
                } else if (seekProgress >= 23 && seekProgress < 28) {
                    sb8000.setProgress(25);
                } else if (seekProgress >= 28 && seekProgress < 33) {
                    sb8000.setProgress(30);
                } else if (seekProgress >= 33 && seekProgress < 38) {
                    sb8000.setProgress(35);
                } else if (seekProgress >= 38 && seekProgress < 43) {
                    sb8000.setProgress(40);
                } else if (seekProgress >= 43 && seekProgress < 48) {
                    sb8000.setProgress(45);
                } else if (seekProgress >= 48 && seekProgress < 53) {
                    sb8000.setProgress(50);
                } else if (seekProgress >= 53 && seekProgress < 58) {
                    sb8000.setProgress(55);
                } else if (seekProgress >= 58 && seekProgress < 63) {
                    sb8000.setProgress(60);
                } else if (seekProgress >= 63 && seekProgress < 68) {
                    sb8000.setProgress(65);
                } else if (seekProgress >= 68 && seekProgress < 73) {
                    sb8000.setProgress(70);
                } else if (seekProgress >= 73 && seekProgress < 78) {
                    sb8000.setProgress(75);
                } else if (seekProgress >= 78 && seekProgress < 83) {
                    sb8000.setProgress(80);
                } else if (seekProgress >= 83 && seekProgress < 88) {
                    sb8000.setProgress(85);
                } else if (seekProgress >= 88 && seekProgress < 93) {
                    sb8000.setProgress(90);
                } else if (seekProgress >= 93) {
                    sb8000.setProgress(100);
                }
                pureToneManager.start(SideSelect.S_UNILATERAL_LEFT, getPureToneFreq(), getPureToneDb(SideSelect.S_UNILATERAL_LEFT));
                list.set(10, sb8000.getProgress());
                onFeedBackListener.onFeedback(0, list);


            }
        });
    }


    @Override
    public void onLoadMoreRequested() {

    }




}
