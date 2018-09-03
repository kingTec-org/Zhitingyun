package com.xiyoukeji.zhitingyun.bindings;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.xiyoukeji.zhitingyun.util.GlideUtil;
import com.xiyoukeji.zhitingyun.util.Utils;


public class ImageBindings {

    @BindingAdapter("avatar_url")
    public static void loadIc(ImageView imageView, String url) {
        GlideUtil.loadIc(Utils.getContext(), url, imageView);
    }

    @BindingAdapter("normal_url")
    public static void loadIv(ImageView imageView, String url) {
        GlideUtil.loadWithPlaceHolder(Utils.getContext(), url, imageView);
    }
}
