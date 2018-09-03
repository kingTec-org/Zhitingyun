package com.xiyoukeji.zhitingyun.view.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.ViewModelFactory;
import com.xiyoukeji.zhitingyun.base.BaseActivity;
import com.xiyoukeji.zhitingyun.constant.AppConstant;
import com.xiyoukeji.zhitingyun.constant.Urls;
import com.xiyoukeji.zhitingyun.data.entity.MessageEntity;
import com.xiyoukeji.zhitingyun.data.entity.Model0;
import com.xiyoukeji.zhitingyun.data.remote.BaseObserver;
import com.xiyoukeji.zhitingyun.databinding.ActivityMsgDetailBinding;
import com.xiyoukeji.zhitingyun.util.ActivityManager;
import com.xiyoukeji.zhitingyun.util.ImageUtils;
import com.xiyoukeji.zhitingyun.util.L;
import com.xiyoukeji.zhitingyun.util.TimeUtils;
import com.xiyoukeji.zhitingyun.view.login.LoginActivity;
import com.xiyoukeji.zhitingyun.viewmodel.main.MsgDetailViewModel;
import com.xiyoukeji.zhitingyun.widget.StatusBarUtil;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrichtext.ImageLoader;
import cn.droidlover.xrichtext.XRichText;
import io.reactivex.disposables.Disposable;

import static android.provider.Telephony.Mms.Part.TEXT;

public class MessageDetailActivity extends BaseActivity{
    @BindView( R.id.title_tv )
    TextView mTitle;
    @BindView( R.id.treat_time )
    TextView mTime;
    @BindView( R.id.richText )
    XRichText mRichText;
    @BindView( R.id.content_tv )
    TextView mContent;


    private ActivityMsgDetailBinding mBinding;
    private MsgDetailViewModel mViewModel;
    MessageEntity entity;
    private Integer newsId;
    private Integer newsType;


    public MessageDetailActivity() {
        super( R.layout.activity_msg_detail );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        int from = getIntent().getIntExtra( "from", 6 );


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(this, R.color.white); }
    }
    @Override
    protected void bindingData() {
        super.bindingData();
        mBinding = DataBindingUtil.setContentView(mActivity, layoutId);
    }

    @Override
    protected void initView() {
        super.initView();
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



        entity = (MessageEntity) getIntent().getSerializableExtra( "data");

        if (entity != null) {
            newsId=entity.getNewsId();
            newsType=entity.getNewsType();

            mViewModel.getMsgDetail(newsId , newsType, new BaseObserver<Model0<MessageEntity>>() {
                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable( d );
                }

                @Override
                public void onNext(Model0<MessageEntity> messageEntityModel0) {
                    mViewModel.setLoadingState( false );
                    mViewModel.mMessageEntity.set( messageEntityModel0 );
                    mTitle.setText( "对于您反馈的："+messageEntityModel0.getComeback().getContent()+"的答复" );
                    mRichText.setVisibility( View.GONE );
                    mContent.setVisibility( View.VISIBLE );
                    mContent.setText( messageEntityModel0.getComeback().getReply());
                    mTime.setText( TimeUtils.millis2String( messageEntityModel0.getComeback().getLastPushTime(), new SimpleDateFormat("yyyy-MM-dd HH:mm")));


                    if(messageEntityModel0.getComeback().getRichText()==null) {
                        L.d( "aaa" );
                    }
                    else {
                        mContent.setVisibility( View.GONE );
                        mRichText.setVisibility( View.VISIBLE );
                        mTitle.setText( messageEntityModel0.getComeback().getTitle());
                        mRichText.text( messageEntityModel0.getComeback().getRichText() );
                        mTime.setText( TimeUtils.millis2String( messageEntityModel0.getComeback().getLastPushTime(), new SimpleDateFormat("yyyy-MM-dd HH:mm")));
                    }


                }
            } );
        }
    }


    public void obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(mActivity.getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(MsgDetailViewModel.class);
    }

    @OnClick(R.id.back_layout)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
        }
    }

    public static List<String> cutStringByImgTag(String targetStr) {
        List<String> splitTextList = new ArrayList<String>();
        Pattern pattern = Pattern.compile("<img.*?src=\\\'(.*?)\\\'.*?>");
        Matcher matcher = pattern.matcher(targetStr);
        int lastIndex = 0;
        while (matcher.find()) {
            if (matcher.start() > lastIndex) {
                splitTextList.add(targetStr.substring(lastIndex, matcher.start()));
            }
            splitTextList.add(targetStr.substring(matcher.start(), matcher.end()));
            lastIndex = matcher.end();
        }
        if (lastIndex != targetStr.length()) {
            splitTextList.add(targetStr.substring(lastIndex, targetStr.length()));
        }
        return splitTextList;
    }


}
