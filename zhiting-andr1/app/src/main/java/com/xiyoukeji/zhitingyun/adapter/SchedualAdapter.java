package com.xiyoukeji.zhitingyun.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiyoukeji.zhitingyun.R;
import com.xiyoukeji.zhitingyun.data.entity.ExpertSchEntity;
import com.xiyoukeji.zhitingyun.databinding.ItemSchedualBinding;
import com.xiyoukeji.zhitingyun.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SchedualAdapter extends BaseQuickAdapter<ExpertSchEntity.WorkScheduleDtosBean,BaseViewHolder> {



    public SchedualAdapter(List<ExpertSchEntity.WorkScheduleDtosBean> data) {
        super( R.layout.item_schedual, data );

    }


    @Override
    protected void convert(BaseViewHolder helper, ExpertSchEntity.WorkScheduleDtosBean item) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(item.getStart());
        calendar.setTime(date);
        helper.setText( R.id.start_time, format(item.getStart(), "MM月dd日 " + getCalendarWeek(calendar) + " " + format(item.getStart(), "HH:mm") + "-" + format(item.getEnd(), "HH:mm")));
//                .setText( R.id.end_time,TimeUtils.millis2String(item.getEnd(), new SimpleDateFormat("HH:mm")) );
        ItemSchedualBinding binding= DataBindingUtil.bind( helper.getConvertView() );
        binding.setSchedual(item );
        binding.executePendingBindings();
    }

    public String format(Long aLong, String format) {
        return new SimpleDateFormat(format).format(aLong);
    }


    private String getCalendarWeek(Calendar calendar) {
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";

        }
    }
}
