package com.xiyoukeji.zhitingyun.widget;

import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import com.xiyoukeji.zhitingyun.ZhitingyunApplication;

import java.io.File;
import java.math.BigDecimal;

public class CacheUtil {
    private static final String CACHE_FILE_NAME = "min";
    private static SharedPreferences mSharedPreferences;

    public static void putBoolean(String key, boolean value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = ZhitingyunApplication.getContext().getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = ZhitingyunApplication.getContext().getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getBoolean(key, defValue);
    }

    public static void putString(String key, String value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = ZhitingyunApplication.getContext().getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putString(key, value).commit();
    }

    public static String getString(String key, String defValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = ZhitingyunApplication.getContext().getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getString(key, defValue);
    }

    public static void putInt(String key, int value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = ZhitingyunApplication.getContext().getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putInt(key, value).commit();
    }

    public static int getInt(String key, int defValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = ZhitingyunApplication.getContext().getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getInt(key, defValue);
    }

    public static void putLong(String key, long value) {
        if (mSharedPreferences == null) {
            mSharedPreferences = ZhitingyunApplication.getContext().getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        }
        mSharedPreferences.edit().putLong(key, value).commit();
    }

    public static long getLong(String key, int defValue) {
        if (mSharedPreferences == null) {
            mSharedPreferences = ZhitingyunApplication.getContext().getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getLong(key, defValue);
    }

    public static long getTotalCacheSizeNumber() throws Exception {
        long cacheSize = getFolderSize( ZhitingyunApplication.getContext().getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(ZhitingyunApplication.getContext().getExternalCacheDir());
        }
        return cacheSize;
    }

    public static String getTotalCacheSize() throws Exception {
        return getFormatSize(getTotalCacheSizeNumber());
    }

    public static void clearAllCache() {
        deleteDir(ZhitingyunApplication.getContext().getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(ZhitingyunApplication.getContext().getExternalCacheDir());
        }
    }

    private static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    public static long getMediaDuration(String path) {
        MediaPlayer player = new MediaPlayer();
        try {
            player.setDataSource(path);
            player.prepare();
            int duration = player.getDuration();
            player.release();
            player = null;
            return duration;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static boolean isFileExist(String path) {
        return new File(path).exists();
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    private static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "0KB";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    public static String getImageAbsolutePath(Uri imageUri) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(ZhitingyunApplication.getContext(), imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(contentUri, selection, selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            if (isGooglePhotosUri(imageUri))
                return imageUri.getLastPathSegment();
            return getDataColumn(imageUri, null, null);
        } else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }

        return null;
    }


    public static String getDataColumn(Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = ZhitingyunApplication.getContext().getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}