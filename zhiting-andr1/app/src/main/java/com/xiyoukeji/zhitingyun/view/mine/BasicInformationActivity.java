package com.xiyoukeji.zhitingyun.view.mine;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.utils.FileUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.constant.Urls;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.entity.UserEntity;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivityBasicInformationBinding;
import com.xiyoukeji.zhitingyun.util.L;
import com.xiyoukeji.zhitingyun.util.SizeUtils;
import com.xiyoukeji.zhitingyun.util.ToastUtils;
import com.xiyoukeji.zhitingyun.view.main.MainActivity;
import com.xiyoukeji.zhitingyun.viewmodel.mine.BasicInfoViewModel;
import com.xiyoukeji.zhitingyun.widget.RoundImageView;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.netease.nimlib.sdk.msg.constant.MsgTypeEnum.tip;
import static com.xiyoukeji.zhitingyun.constant.Urls.BASE_URL;

public class BasicInformationActivity extends BaseActivity {

    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.ageTv)
    TextView ageTv;
    @BindView(R.id.wearTv)
    TextView wearTv;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.male)
    RadioButton male;
    @BindView(R.id.femle)
    RadioButton femle;
    @BindView( R.id.name )
    EditText mNameet;

    @BindView(R.id.gender_tv)
    TextView genderTv;
    @BindView(R.id.wear_spinner)
    Spinner wearSp;
    @BindView(R.id.ageTv0)
    TextView ageTv0;
    @BindView(R.id.wear2)
    TextView wear2;
    @BindView(R.id.sui)
    TextView sui;
    @BindView( R.id.avatar )
    ImageView mAvatar;

    private ActivityBasicInformationBinding mBinding;
    private BasicInfoViewModel mViewModel;
    private final int CHANGE_NAME = 0x002;
    private DatePicker datePicker;
    private NumberPicker numberPicker;
    private BottomSheetDialog mDialog;
    private BottomSheetDialog mDialog0;
    private BottomSheetDialog mDialog2;
    private String mName;
    private String mSex;
    private String mAge;
    private String mWearTimeEnum;
    private int year;
    private int month;
    private int day;
    public static int temp = -1;
    private int cyear;
    private int cmonth;
    private int cday;
    private Calendar cal;
    private String str;
    private String mPath;
    private String headpic;
    UserEntity entity;
    private Integer id;
    private String ifName;


    private final int REQUEST_CODE_SELECT = 0x001;
    private final int IMAGE_PICKER = 0x001;


    public BasicInformationActivity() {
        super( R.layout.activity_basic_information );
    }

    @Override
    protected void bindingData() {
        super.bindingData();
        mBinding = DataBindingUtil.setContentView( mActivity, layoutId );
    }

    public static String getStrTime(long cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        // 例如：
        re_StrTime = sdf.format( new Date( cc_time ) );
        return re_StrTime;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR );
            StatusBarUtil.setStatusBarColor( this, R.color.white );
        }
    }

    @Override
    protected void initView() {
        super.initView();
        cal = Calendar.getInstance();
        cyear = cal.get( Calendar.YEAR );
        cmonth = cal.get( Calendar.MONTH ) + 1;
        cday = cal.get( Calendar.DAY_OF_MONTH );

        genderTv.setText( "2" );
        rg.setOnCheckedChangeListener( new RadioGroupListener() );
    }

    class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == male.getId()) {
                genderTv.setText( "1" );
//                    Toast.makeText( getApplicationContext(), "你选择了男", Toast.LENGTH_LONG ).show();
            } else if (checkedId == femle.getId()) {
                genderTv.setText( "2" );
//                    Toast.makeText( getApplicationContext(), "你选择了女", Toast.LENGTH_LONG ).show();
            }
        }
    }


    @OnClick({R.id.back, R.id.btn_submit, R.id.age_layout, R.id.experience_layout, R.id.avatar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.avatar:
                if (mDialog2 == null) {
                    initDialog2();
                }
                mDialog2.show();
                break;
            case R.id.back:
                finish();
                break;
            case R.id.btn_submit:
                mViewModel.completeInfo( mName, mSex, mAge, mWearTimeEnum,headpic, new BaseObserver<Model0<UserEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable( d );
                    }

                    @Override
                    public void onNext(Model0<UserEntity> userEntityModel0) {
                        mViewModel.setLoadingState( false );
                        SharedPreferences sharedPreferences = getSharedPreferences("mySP", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString( "usernames",userEntityModel0.getComeback().getName() );
                        editor.commit();
                        finish();
//                        startActivity( MineActivity.class );
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError( e );
                        mViewModel.setLoadingState( false );
                    }
                } );
                break;
            case R.id.age_layout:
                if (mDialog == null) {
                    initDialog();
                }
                mDialog.show();
                break;
            case R.id.experience_layout:
                if (mDialog0 == null) {
                    initDialog0();
                }
                mDialog0.show();
                break;

        }
    }

    @Override
    protected void initData() {
        super.initData();
        obtainViewModel();
        mBinding.setViewModel( mViewModel );
        mViewModel.getLoadingState().observe( this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    showLoading( mViewModel.mTip.get() );
                } else {
                    dismissLoading();
                }
            }
        } );


        SharedPreferences sharedPreferences = getSharedPreferences("mySP", Context.MODE_PRIVATE);
        ifName=sharedPreferences.getString( "usernames" ,"");
        if(ifName.isEmpty()) {
            ToastUtils.showShort( "请填写资料" );
        }else {
            id = sharedPreferences.getInt( "id", 1 );

            mViewModel.getUserInfo( id, new BaseObserver<Model0<UserEntity>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Model0<UserEntity> userEntityModel0) {
                    mViewModel.mUserEntity.set( userEntityModel0 );
                    mNameet.setText( userEntityModel0.getComeback().getName() );
                    Glide.with( mContext ).load( Urls.BASE_URL + "/" + userEntityModel0.getComeback().getHeadPic() ).into( mAvatar );
                    ageTv0.setText( userEntityModel0.getComeback().getAge() + "" );
                    if (userEntityModel0.getComeback().getAge() != 0) {
                        sui.setVisibility( View.VISIBLE );
                    } else {
                        sui.setVisibility( View.GONE );

                    }

                    if (userEntityModel0.getComeback().getSex() == 1) {
                        male.setChecked( true );
                        genderTv.setText( "1" );
                    } else {
                        femle.setChecked( true );
                        genderTv.setText( "2" );
                    }
                    if (userEntityModel0.getComeback().getWearTimeEnum() == 1) {
                        wearTv.setText( "3个月" );
                        wear2.setText( "1" );
                    } else if (userEntityModel0.getComeback().getWearTimeEnum() == 2) {
                        wearTv.setText( "3-6个月" );
                        wear2.setText( "2" );
                    } else if (userEntityModel0.getComeback().getWearTimeEnum() == 3) {
                        wearTv.setText( "6-12个月" );
                        wear2.setText( "3" );
                    } else if (userEntityModel0.getComeback().getWearTimeEnum() == 3) {
                        wearTv.setText( "一年以上" );
                        wear2.setText( "4" );
                    }

                }
            } );
        }

    }



    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals( "null" )) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat( format );
        return sdf.format( new Date( Long.valueOf( seconds ) ) );
    }

    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat( format );
            return String.valueOf( sdf.parse( date_str ).getTime() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    public static String timeStamp() {
        long time = System.currentTimeMillis();
        String t = String.valueOf( time / 1000 );
        return t;
    }

    public static long getStringToDate(String dateString) {

        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Date date = new Date();
        try {
            date = dateFormat.parse( dateString );
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    private void initDialog() {
        mDialog = new BottomSheetDialog( mContext );
        mDialog.setCancelable( true );
        mDialog.setCanceledOnTouchOutside( true );
        View dialogView = LayoutInflater.from( mContext ).inflate( R.layout.birthday, null );
        TextView cancelDate = dialogView.findViewById( R.id.cancel_date );
        TextView makeSure = dialogView.findViewById( R.id.done );
        DatePicker datePicker = dialogView.findViewById( R.id.dpPicker );

        Calendar dateAndTime = Calendar.getInstance( Locale.CHINA );

        datePicker.init( cyear, cmonth - 1, cday, new DatePicker.OnDateChangedListener() {
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                setTitle( i + "-" + (i1 + 1) + "-" + i2 + "-" );
                BasicInformationActivity.this.year = i;
                BasicInformationActivity.this.month = i1;
                BasicInformationActivity.this.day = i2;
                // 显示当前时间


                cal = Calendar.getInstance();
                cyear = cal.get( Calendar.YEAR );
                String time = timeStamp2Date( cyear + "", "yyyy" );
                int nowT = Integer.parseInt( time ) + 48;
                ageTv0.setText( nowT - year + "" );
                if (ageTv0.equals( "" )) {
                    sui.setVisibility( View.GONE );
                } else {
                    sui.setVisibility( View.VISIBLE );
                }

                ageTv.setText( year + "" );


            }
        } );




        cancelDate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog != null) {
                    mDialog.dismiss();
                }
            }
        } );
        makeSure.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog != null) {
                    mDialog.dismiss();
                }
            }
        } );
        mDialog.setContentView( dialogView );
    }


    private void showDate(int year, int month, int day) {
        ageTv.setText( year + "-" + (month + 1) + "-" + day );

    }




    public void obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance( mActivity.getApplication() );
        mViewModel = ViewModelProviders.of( this, factory ).get( BasicInfoViewModel.class );
    }


    private void initDialog0() {
        mDialog0 = new BottomSheetDialog( mContext );
        mDialog0.setCancelable( true );
        mDialog0.setCanceledOnTouchOutside( true );
        final View dialogView = LayoutInflater.from( mContext ).inflate( R.layout.dialog_wearing, null );
        TextView three = dialogView.findViewById( R.id.three );
        TextView threesix = dialogView.findViewById( R.id.threesix );
        TextView sixdec = dialogView.findViewById( R.id.sixtodec );
        TextView year = dialogView.findViewById( R.id.year );

        three.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog0 != null) {
                    wearTv.setText( "3个月" );
                    wear2.setText( "1" );
                    mDialog0.dismiss();
                }
            }
        } );

        threesix.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog0 != null) {
                    wearTv.setText( "3-6个月" );
                    wear2.setText( "2" );
                    mDialog0.dismiss();
                }
            }
        } );

        sixdec.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog0 != null) {
                    wearTv.setText( "6-12个月" );
                    wear2.setText( "3" );
                    mDialog0.dismiss();
                }
            }
        } );
        year.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog0 != null) {
                    wearTv.setText( "一年以上" );
                    wear2.setText( "4" );
                    mDialog0.dismiss();
                }
            }
        } );

        mDialog0.setContentView( dialogView );
    }


    private void initDialog2() {
        mDialog2 = new BottomSheetDialog( mContext );
        mDialog2.setCancelable( true );
        mDialog2.setCanceledOnTouchOutside( true );
        View dialogView = LayoutInflater.from( mContext ).inflate( R.layout.dialog_select_avatar, null );
        TextView takePhoto = dialogView.findViewById( R.id.take_photo );
        TextView photoAlbum = dialogView.findViewById( R.id.photo_album );
        TextView cancelTv = dialogView.findViewById( R.id.cancel_tv );
        takePhoto.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog2 != null) {
                    mDialog2.dismiss();
                }
                ImagePicker imagePicker = ImagePicker.getInstance();
                imagePicker.setMultiMode( false );
                Intent intent = new Intent( mContext, ImageGridActivity.class );
                intent.putExtra( ImageGridActivity.EXTRAS_TAKE_PICKERS, true ); // 是否是直接打开相机
                startActivityForResult( intent, REQUEST_CODE_SELECT );
            }
        } );
        photoAlbum.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog2 != null) {
                    mDialog2.dismiss();
                }
                ImagePicker imagePicker = ImagePicker.getInstance();
                imagePicker.setShowCamera( true );  //显示拍照按钮
                imagePicker.setCrop( true );        //允许裁剪（单选才有效）
                imagePicker.setStyle( CropImageView.Style.RECTANGLE );
                imagePicker.setFocusHeight( SizeUtils.dp2px( 300f ) );
                imagePicker.setFocusWidth( SizeUtils.dp2px( 300f ) );
                imagePicker.setMultiMode( false );
                Intent intent = new Intent( mContext, ImageGridActivity.class );
                startActivityForResult( intent, IMAGE_PICKER );
            }
        } );
        cancelTv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog2 != null) {
                    mDialog2.dismiss();
                }
            }
        } );
        mDialog2.setContentView( dialogView );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra( ImagePicker.EXTRA_RESULT_ITEMS );
                updateAvatar(images.get( 0 ).path );
            } else {
                L.e( "没有选择图片" );
            }
        }
    }

    public void updateAvatar(String path) {
        mViewModel.uploadAvatar( path, new BaseObserver<Model0<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable( d );
            }

            @Override
            public void onNext(Model0<String> stringModel0) {
                mViewModel.setLoadingState( false );
                ToastUtils.showShort("上传头像成功");
                Glide.with(mContext).load( Urls.BASE_URL + "/"+stringModel0.getComeback()).into(mAvatar);
                headpic=stringModel0.getComeback();

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mViewModel.setLoadingState(false);
            }
        } );
    }

}
