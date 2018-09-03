package com.xiyoukeji.zhitingyun.adapter;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.data.entity.AppointmentEntity;
import com.xiyoukeji.zhitingyun.databinding.ItemDoctorBinding;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import butterknife.BindView;

import static com.xiyoukeji.zhitingyun.R2.id.image;

public class DoctorAdapter extends BaseQuickAdapter<AppointmentEntity,BaseViewHolder>{


    public DoctorAdapter(List<AppointmentEntity> data) {
        super( R.layout.item_doctor, data );
    }



    @Override
    protected void convert(BaseViewHolder helper, AppointmentEntity item) {



        helper.setText( R.id.name,(item.getName()) )
                .setText( R.id.time_textview,item.getWorkTime()+"" );
        if(item.isCanBeenOrder()==true) {
            helper.setText( R.id.state,"可预约 ");
        }else {
            helper.setText( R.id.state,"暂无排班 ");
        }

        ItemDoctorBinding binding= DataBindingUtil.bind( helper.getConvertView() );
        binding.setDoctors( item );
        binding.executePendingBindings();
    }
}
