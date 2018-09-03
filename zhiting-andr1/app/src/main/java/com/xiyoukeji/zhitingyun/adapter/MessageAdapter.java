package com.xiyoukeji.zhitingyun.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.constant.Urls;
import com.xiyoukeji.zhitingyun.data.entity.MessageEntity;
import com.xiyoukeji.zhitingyun.databinding.ItemImformationBinding;
import com.xiyoukeji.zhitingyun.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.List;

public class MessageAdapter extends BaseQuickAdapter<MessageEntity,BaseViewHolder> {
    public MessageAdapter(List<MessageEntity> data) {
        super( R.layout.item_imformation, data );
    }

    @Override
    protected void convert(final BaseViewHolder helper, MessageEntity item) {

        final LinearLayout detailLayout=helper.getView( R.id.detail_layout );
        final TextView mContent=helper.getView( R.id.content );


        if(item.getNewsType()==3) {
            helper.setVisible( R.id.info_picture,true );
            helper.setVisible( R.id.content,false );
            Glide.with( mContext ).load( Urls.BASE_URL + "/" + item.getCoverPic() ).into( (ImageView) helper.getView( R.id.info_picture ) );
        }else {
            helper.setVisible( R.id.info_picture,false );
        }

        helper.setText( R.id.time, TimeUtils.millis2String(item.getCreateTime(), new SimpleDateFormat("yyyy-MM-dd HH:mm")))
                .setText( R.id.content,item.getContent() );
        if(item.getNewsType()==1){
            helper.setText( R.id.title,"系统通知" );
            if(item.getContent().indexOf("成功")!=-1){
                return;
            }
            else {
                mContent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getOnItemChildClickListener().onItemChildClick(MessageAdapter.this, mContent, helper.getLayoutPosition());
                    }
                });
            }
        }if(item.getNewsType()==2){
            helper.setText( R.id.title,"反馈提醒" );
            detailLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getOnItemChildClickListener().onItemChildClick(MessageAdapter.this, detailLayout, helper.getLayoutPosition());
                }
            });
        }else if(item.getNewsType()==3) {
            helper.setText( R.id.title,item.getTitle() );
            detailLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getOnItemChildClickListener().onItemChildClick(MessageAdapter.this, detailLayout, helper.getLayoutPosition());
                }
            });
        }


        ItemImformationBinding binding=DataBindingUtil.bind( helper.getConvertView() );
        binding.setInformation( item );
        binding.executePendingBindings();
    }
}
