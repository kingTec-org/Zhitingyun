package com.xiyoukeji.zhitingyun.adapter;

import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.constant.Urls;
import com.xiyoukeji.zhitingyun.data.entity.AppointmentEntity;
import com.xiyoukeji.zhitingyun.data.entity.Professor;
import com.xiyoukeji.zhitingyun.databinding.ItemProfessorListBinding;
import com.xiyoukeji.zhitingyun.widget.RoundImageView;

import java.util.List;

public class ProfessorListAdapter extends BaseQuickAdapter<Professor, BaseViewHolder> {


    public ProfessorListAdapter(List<Professor> data) {
        super(R.layout.item_professor_list, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, Professor item) {


        Glide.with(mContext).load(Urls.BASE_URL + "/"+item.getHeadPic()).into((RoundImageView) helper.getView(R.id.imgRound));


        helper.setText(R.id.tvName, (item.getName()))
                .setText(R.id.tvExpert, "服务经验：" + item.getWorkTime() + "个月");
        if (item.getCanBeenOrder() == true) {
            helper.setText(R.id.tvStatus, "可预约 ");
            ((TextView) helper.getView(R.id.tvStatus)).setTextColor(ContextCompat.getColor(mContext, R.color.mainColor));
        } else {
            helper.setText(R.id.tvStatus, "暂无排班 ");
            ((TextView) helper.getView(R.id.tvStatus)).setTextColor(ContextCompat.getColor(mContext, R.color.colorGray66));
        }

        ItemProfessorListBinding binding = DataBindingUtil.bind(helper.getConvertView());
        binding.setProfessor(item);
        binding.executePendingBindings();
    }
}
