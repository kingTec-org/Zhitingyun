package com.xiyoukeji.zhitingyun.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.constant.Urls;
import com.xiyoukeji.zhitingyun.data.entity.RecordEntity;
import com.xiyoukeji.zhitingyun.databinding.ItemAppointmentRecord0Binding;
import com.xiyoukeji.zhitingyun.util.TimeUtils;
import com.xiyoukeji.zhitingyun.widget.RoundImageView;

import java.text.SimpleDateFormat;
import java.util.List;

public class RecordAdapter extends BaseQuickAdapter<RecordEntity, BaseViewHolder> {


    public RecordAdapter(@Nullable List<RecordEntity> data) {
        super(R.layout.item_appointment_record0, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, RecordEntity item) {
        final Button btnCancel = helper.getView(R.id.btn_cancel);
        final Button btCheck = helper.getView(R.id.btn_check_report);
        final Button btRetreat = helper.getView(R.id.btn_retreat);
        final Button btFinish = helper.getView(R.id.btn_finish);
        final Button btSue=helper.getView( R.id.btn_sue );
        final Button btnReason=helper.getView(R.id.btn_reason);
        final View apart=helper.getView( R.id.apart );

//        final Button bt

        Glide.with(mContext).load(Urls.BASE_URL + "/"+item.getProHeadPic()).into((ImageView) helper.getView(R.id.imgRound));

        helper.setText(R.id.name, item.getProfessorName())
                .setText(R.id.count_textview, item.getProServiceCount() + "");
        helper.setText(R.id.time_textview, TimeUtils.millis2String(item.getOrderTimeStart(), new SimpleDateFormat("yyyy-MM-dd HH:mm")))
                .setText(R.id.end_textview, TimeUtils.millis2String(item.getOrderTimeEnd(), new SimpleDateFormat("HH:mm")));
        if (item.getStatus() == 0) {
            helper.setText(R.id.status, "正在预约");
            ((TextView) helper.getView(R.id.status)).setTextColor( ContextCompat.getColor(mContext, R.color.redmain));
            helper.setVisible(R.id.finished_layout, true);
            helper.setVisible(R.id.btn_cancel, true);
            helper.setVisible(R.id.btn_reason, false);
            helper.setVisible(R.id.btn_sue, false);
            helper.setVisible(R.id.btn_retreat, false);
            helper.setVisible(R.id.btn_check_report, false);
            helper.setVisible( R.id.btn_finish,false );
            helper.setVisible( R.id.apart,true );


        } else if (item.getStatus() == 1) {
            helper.setText(R.id.status, "预约成功");
            ((TextView) helper.getView(R.id.status)).setTextColor( ContextCompat.getColor(mContext, R.color.greenmain));
            helper.setVisible(R.id.finished_layout, false);
            helper.setVisible(R.id.btn_cancel, true);
            helper.setVisible(R.id.btn_reason, false);
            helper.setVisible(R.id.btn_sue, false);
            helper.setVisible(R.id.btn_retreat, false);
            helper.setVisible(R.id.btn_check_report, false);
            helper.setVisible( R.id.btn_finish,false );
            helper.setVisible( R.id.apart,false );



        } else if (item.getStatus() == 2) {
            helper.setText(R.id.status, "预约失败");
            ((TextView) helper.getView(R.id.status)).setTextColor( ContextCompat.getColor(mContext, R.color.redmain));
            helper.setVisible(R.id.finished_layout, true);
            helper.setVisible(R.id.btn_cancel, false);
            helper.setVisible(R.id.btn_reason, true);
            helper.setVisible(R.id.btn_sue, false);
            helper.setVisible( R.id.btn_finish,false );
            helper.setVisible(R.id.btn_check_report, false);
            helper.setVisible(R.id.btn_retreat, false);
            helper.setVisible( R.id.apart,true );


        } else if (item.getStatus() == 3) {
            helper.setText(R.id.status, "正在诊疗");
            ((TextView) helper.getView(R.id.status)).setTextColor( ContextCompat.getColor(mContext, R.color.bluemain));
            helper.setVisible(R.id.finished_layout, true);
            helper.setVisible(R.id.btn_cancel, false);
            helper.setVisible(R.id.btn_reason, false);
            helper.setVisible(R.id.btn_sue, false);
            helper.setVisible( R.id.btn_finish,true );
            helper.setVisible(R.id.btn_check_report, false);
            helper.setVisible(R.id.btn_retreat, false);
            helper.setVisible( R.id.apart,true );



        } else if (item.getStatus() == 4) {
            helper.setText(R.id.status, "已诊疗");
            ((TextView) helper.getView(R.id.status)).setTextColor( ContextCompat.getColor(mContext, R.color.black));
            helper.setVisible(R.id.finished_layout, true);
            helper.setVisible(R.id.btn_cancel, false);
            helper.setVisible(R.id.btn_reason, false);
            helper.setVisible(R.id.btn_sue, false);
            helper.setVisible(R.id.btn_check_report, true);
            helper.setVisible(R.id.btn_retreat, true);
            helper.setVisible( R.id.btn_finish,false );
            helper.setVisible( R.id.apart,true );


        } else if (item.getStatus() == 5) {
            helper.setText(R.id.status, "未诊疗");
            ((TextView) helper.getView(R.id.status)).setTextColor( ContextCompat.getColor(mContext, R.color.redmain));
            helper.setVisible(R.id.finished_layout, true);
            helper.setVisible(R.id.btn_cancel, false);
            helper.setVisible(R.id.btn_reason, false);
            helper.setVisible(R.id.btn_sue, true);
            helper.setVisible( R.id.btn_finish,false );
            helper.setVisible(R.id.btn_check_report, false);
            helper.setVisible(R.id.btn_retreat, false);
            helper.setVisible( R.id.apart,true );




        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOnItemChildClickListener().onItemChildClick(RecordAdapter.this, btnCancel, helper.getLayoutPosition());

            }
        });
        btCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOnItemChildClickListener().onItemChildClick(RecordAdapter.this, btCheck, helper.getLayoutPosition());
            }
        });
        btRetreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOnItemChildClickListener().onItemChildClick(RecordAdapter.this, btRetreat, helper.getLayoutPosition());
            }
        });
        btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOnItemChildClickListener().onItemChildClick(RecordAdapter.this, btFinish, helper.getLayoutPosition());
            }
        });
        btnCancel.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getOnItemChildClickListener().onItemChildClick( RecordAdapter.this,btnCancel,helper.getLayoutPosition() );
            }
        } );
        btSue.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getOnItemChildClickListener().onItemChildClick( RecordAdapter.this,btSue,helper.getLayoutPosition() );
            }
        } );
        btnReason.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getOnItemChildClickListener().onItemChildClick( RecordAdapter.this,btnReason,helper.getLayoutPosition() );
            }
        } );

        ItemAppointmentRecord0Binding binding = DataBindingUtil.bind(helper.getConvertView());
        binding.setRecords(item);
        binding.executePendingBindings();

    }


}
