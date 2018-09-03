package com.xiyoukeji.zhitingyun.adapter;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuyu.gsyvideoplayer.render.effect.BrightnessEffect;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.data.entity.AppointmentEntity;
import com.xiyoukeji.zhitingyun.data.entity.TestRecordEntity;
import com.xiyoukeji.zhitingyun.databinding.ItemDoctorBinding;

import java.text.SimpleDateFormat;
import java.util.List;

public class TestRecordAdapter extends BaseQuickAdapter<TestRecordEntity, BaseViewHolder> {

    public TestRecordAdapter(List<TestRecordEntity> data) {
        super(R.layout.item_test_record, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, TestRecordEntity item) {
        final TextView tvCheck = helper.getView(R.id.tvCheck);

        String type = "";
        switch (item.getEquipmentHolder()) {
            case 1:
                type = "设备持有者";
                break;
            case 2:
                type = "其他监听者";
                break;
        }

//
        if(item.getEquipmentHolder()==1) {
            helper.setText( R.id.tvtType, "设备持有者" );
        }
        if(item.getEquipmentHolder()==2) {
            helper.setText( R.id.tvtType, "其他监听者" );
        }
              helper.setText(R.id.tvtTime, new SimpleDateFormat("yyyy.MM.dd HH:mm").format(item.getCreateTime()));
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOnItemChildClickListener().onItemChildClick(TestRecordAdapter.this, tvCheck, helper.getLayoutPosition());
            }
        });
    }
}
