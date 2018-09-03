package com.xiyoukeji.zhitingyun.task;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.widget.ImageView;

import com.xiyoukeji.zhitingyun.util.EncodeUtils;
import com.xiyoukeji.zhitingyun.util.GlideApp;
import com.xiyoukeji.zhitingyun.util.L;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;



public class ImageLoadTask implements ITask {

    private ImageView mImageView;
    private Context mContext;
    private String mPath;

    public ImageLoadTask(@NonNull ImageView imageView, @NonNull Context context, @NonNull String path) {
        mImageView = imageView;
        mContext = context;
        mPath = path;
    }

    @Override
    public void run() {
        final File file = getOutputMediaFile(mContext, mPath);
        Activity activity = (Activity) mContext;
        if (file == null) {
            L.e("file path not exist");
            return;
        }
        if (file.exists()) {
            if (!activity.isFinishing()) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mContext == null) {
                            L.e("activity is already destroy");
                            return;
                        }
                        GlideApp.with(mContext).load(file).into(mImageView);
                    }
                });
            }
            return;
        }
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(mPath, new HashMap<String, String>());
        Bitmap bitmap = mmr.getFrameAtTime();

        //保存图片
        final File f = getOutputMediaFile(mContext, mPath);
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!activity.isFinishing()) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mContext == null) {
                        L.e("activity is already destroy");
                        return;
                    }
                    GlideApp.with(mContext).load(f).into(mImageView);
                }
            });
        }
        mmr.release();
    }

    //7.0适配  有需要的话fileProvide已经写好
    public static Uri getOutputMediaFileUri(Context context, String path) {
        Uri uri = null;
        //适配Android N
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".fileProvider", getOutputMediaFile(context, path));
        } else {
            return Uri.fromFile(getOutputMediaFile(context, path));
        }
    }

    public static File getOutputMediaFile(Context context, String path) {
        boolean isSdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        File mediaStorageDir;
        if (isSdCardExist) {
            mediaStorageDir = new File(context.getExternalCacheDir(), "image");
        } else {
            mediaStorageDir = new File(context.getCacheDir(), "image");
        }
//        File mediaStorageDir = new File(context.getExternalCacheDir(), "image");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String urlEncodePath = EncodeUtils.urlEncode(path);
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + urlEncodePath + ".jpg");
        return mediaFile;
    }
}
