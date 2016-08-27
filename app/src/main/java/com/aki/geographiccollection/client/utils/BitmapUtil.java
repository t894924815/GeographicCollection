package com.aki.geographiccollection.client.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;


import com.aki.geographiccollection.client.GeoApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;

public class BitmapUtil {

    public final static String DIR = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/images";
    /***
     * 保存图片至SD卡
     */
    private static int FREE_SD_SPACE_NEEDED_TO_CACHE = 1;
    private static int MB = 1024 * 1024;

    /**
     * 获取文件二进制
     */
    public static byte[] getBitmapByte(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /***
     * 等比例压缩图片
     */
    public static Bitmap getBitmap(Bitmap bitmap, int screenWidth,
                                   int screenHight) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Log.e("jj", "图片宽度" + w + ",screenWidth=" + screenWidth);
        Matrix matrix = new Matrix();
        float scale = (float) screenWidth / w;
        float scale2 = (float) screenHight / h;

        // scale = scale < scale2 ? scale : scale2;

        // 保证图片不变形.
        matrix.postScale(scale, scale2);
        // w,h是原图的属性.
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }

    public static void saveBitmap(Bitmap bm, String filename, int quantity) {
        // 判断sdcard上的空间
        if (FREE_SD_SPACE_NEEDED_TO_CACHE > getFreeSize()) {
            return;
        }
        if (!Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState()))
            return;

        // 目录不存在就创建
        File dirPath = new File(DIR);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }

        File file = new File(DIR + "/" + filename);
        try {
            file.createNewFile();
            OutputStream outStream = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, quantity, outStream);
            outStream.flush();
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int h = options.outHeight;
        int w = options.outWidth;
        int inSampleSize = 0;
        if (h > reqHeight || w > reqWidth) {
            float ratioW = (float) w / reqWidth;
            float ratioH = (float) h / reqHeight;
            inSampleSize = (int) Math.min(ratioH, ratioW);
        }
        inSampleSize = Math.max(1, inSampleSize);
        return inSampleSize;
    }

    public static Bitmap getSmallBitmap(int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(GeoApplication.application.getResources(), resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(GeoApplication.application.getResources(), resId, options);
    }

    /***
     * 获取SD卡图片
     */
    public static Bitmap getBitmap(String filename, int quantity) {
        InputStream inputStream = null;
        Bitmap map = null;
        URL url_Image = null;
        String LOCALURL = "";
        if (filename == null)
            return null;

        LOCALURL = URLEncoder.encode(filename);
        try {
            if (exist(LOCALURL)) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;// 这样就只返回图片参数
                // 获取这个图片的宽和高
                map = BitmapFactory.decodeFile(DIR + "/" + LOCALURL, options); // 此时返回bm为空
                options.inJustDecodeBounds = false;// 上面操作完后,要设回来,不然下面同样获取不到实际图片
                // 计算缩放比
                int be = (int) (options.outHeight / (float) 200);
                // 上面算完后一下如果比200大,那就be就大于1,那么就压缩be,如果比200小,那图片肯定很小了,直接按原图比例显示就行
                if (be <= 0) {
                    be = 1;
                }
                options.inSampleSize = be;// be=2.表示压缩为原来的1/2,以此类推
                // 重新读入图片，注意这次要把options.inJustDecodeBounds 设为
                // false,不然返回的还是一个空bitmap
                map = BitmapFactory.decodeFile(DIR + "/" + LOCALURL, options);

            } else {
                url_Image = new URL(filename);
                inputStream = url_Image.openStream();
                map = BitmapFactory.decodeStream(inputStream);
                // url = URLEncoder.encode(url, "UTF-8");
                if (map != null) {
                    saveBitmap(map, LOCALURL, quantity);
                }
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }

    /***
     * 判断图片是存在
     */
    public static boolean exist(String filename) {
        File file = new File(DIR + "/" + filename);
        return file.exists();
    }

    /**
     * 计算sdcard上的剩余空间 * @return
     */
    private static int getFreeSize() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory()
                .getPath());
        double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat
                .getBlockSize()) / MB;

        return (int) sdFreeMB;
    }

}
