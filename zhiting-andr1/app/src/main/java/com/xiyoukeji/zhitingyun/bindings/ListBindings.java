package com.xiyoukeji.zhitingyun.bindings;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.xiyoukeji.zhitingyun.adapter.DoctorAdapter;
import com.xiyoukeji.zhitingyun.adapter.MallAdapter;
import com.xiyoukeji.zhitingyun.adapter.MessageAdapter;
import com.xiyoukeji.zhitingyun.adapter.ProfessorListAdapter;
import com.xiyoukeji.zhitingyun.adapter.QuickRecordAdapter;
import com.xiyoukeji.zhitingyun.adapter.RecordAdapter;
import com.xiyoukeji.zhitingyun.adapter.SchedualAdapter;
import com.xiyoukeji.zhitingyun.data.entity.AppointmentEntity;
import com.xiyoukeji.zhitingyun.data.entity.ExpertSchEntity;
import com.xiyoukeji.zhitingyun.data.entity.MallEntity;
import com.xiyoukeji.zhitingyun.data.entity.MessageEntity;
import com.xiyoukeji.zhitingyun.data.entity.Professor;
import com.xiyoukeji.zhitingyun.data.entity.RecordEntity;

import java.util.List;

public class ListBindings {

    @SuppressWarnings("unchecked")

    @BindingAdapter( "doctors_items" )
    public static void setDoctorsItems(RecyclerView recyclerView, List<AppointmentEntity> items) {
        DoctorAdapter adapter = (DoctorAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setNewData( items );
        }
    }

    @BindingAdapter( "schedual_item" )
    public static void setSchedualItems(RecyclerView recyclerView, List<ExpertSchEntity.WorkScheduleDtosBean> items) {
        SchedualAdapter adapter = (SchedualAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setNewData( items );
        }
    }

    @BindingAdapter( "info_items" )
    public static void setInfoItems(RecyclerView recyclerView, List<MessageEntity> items) {
        MessageAdapter adapter = (MessageAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setNewData( items );
        }
    }

    @BindingAdapter( "record_items" )
    public static void setRecordItems(RecyclerView recyclerView, List<RecordEntity> items) {
        RecordAdapter adapter = (RecordAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setNewData( items );
        }
    }

    @BindingAdapter( "quickrecord_items" )
    public static void setQuickRecordItems(RecyclerView recyclerView, List<RecordEntity> items) {
        QuickRecordAdapter adapter = (QuickRecordAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setNewData( items );
        }
    }

    @BindingAdapter( "mall_item" )
    public static void setMallItems(RecyclerView recyclerView, List<MallEntity> items){
        MallAdapter adapter = (MallAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setNewData( items );
        }
    }


    @BindingAdapter( "appoint_items" )
    public static void setAppoint(RecyclerView recyclerView, List<Professor> items){
        ProfessorListAdapter adapter = (ProfessorListAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setNewData( items );
        }
    }

}