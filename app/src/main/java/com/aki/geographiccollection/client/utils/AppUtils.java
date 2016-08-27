package com.aki.geographiccollection.client.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.alibaba.fastjson.JSONObject;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;

import com.aki.geographiccollection.client.GeoApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class AppUtils {

    public static Spannable getTime(int size, String time) {
        Spannable wordtoSpan = new SpannableString(time);
        wordtoSpan.setSpan(new AbsoluteSizeSpan(2 * size), wordtoSpan.length() - 2, wordtoSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan.setSpan(new AbsoluteSizeSpan(size), wordtoSpan.length() - 3, wordtoSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return wordtoSpan;
    }

    public static String get36UUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static int getScreenWidth() {
        WindowManager mWindowManager = (WindowManager) GeoApplication.application
                .getSystemService(Context.WINDOW_SERVICE);
        Point mPoint = new Point();
        mWindowManager.getDefaultDisplay().getSize(mPoint);

        return mPoint.x;
    }

    public static int getScreenHeight() {
        WindowManager mWindowManager = (WindowManager) GeoApplication.application
                .getSystemService(Context.WINDOW_SERVICE);
        Point mPoint = new Point();
        mWindowManager.getDefaultDisplay().getSize(mPoint);

        return mPoint.y;
    }

    public static void saveImageToGallery(Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(GeoApplication.application.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        GeoApplication.application.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
    }

    private static LiteOrm liteOrm;

    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return "";
    }

    public static LiteOrm getSignificanceData(Context context) {
        String SD_CARD = null, DB_NAME = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist) {
            SD_CARD = Environment.getExternalStorageDirectory().toString();//获取跟目录
        } else {
            SD_CARD = "";
        }
        DB_NAME = SD_CARD + "/smartlife/orm/app.db";
        if (liteOrm == null) {
            // 使用级联操作
            DataBaseConfig config = new DataBaseConfig(context, DB_NAME);
            config.debugged = true;
            config.dbVersion = 1;
            config.onUpdateListener = null;
            liteOrm = LiteOrm.newCascadeInstance(config);
        }
        return liteOrm;
    }

    public static String stringFilter(String str) throws PatternSyntaxException {
        String regEx = "[^a-zA-Z0-9\u4E00-\u9FA5_]";
        return str.replaceAll(regEx, "").trim();
    }

    /**
     * textview显示不同颜色
     *
     * @param context
     * @return
     */
    public static SpannableString setDifTvColoc(Context context, int color, String showText, int start, int end) {
        SpannableString msp = null;
        msp = new SpannableString(showText);
        msp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, color)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return msp;
    }

    public static boolean isMobileNo(String mobileNo) {
        Pattern mobilePattern = Pattern.compile("^((13[0-9])|(14[5,7])|(15[0-3,5-8])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$");
        return mobilePattern.matcher(mobileNo).matches();
    }

    public static final String getProcessName() {
        String processName = null;

        // ActivityManager

        ActivityManager am = ((ActivityManager) GeoApplication.application.getSystemService(Context.ACTIVITY_SERVICE));

        while (true) {
            for (ActivityManager.RunningAppProcessInfo info : am.getRunningAppProcesses()) {
                if (info.pid == android.os.Process.myPid()) {
                    processName = info.processName;

                    break;
                }
            }
            // go home
            if (!TextUtils.isEmpty(processName)) {
                return processName;
            }
            // take a rest and again
            try {
                Thread.sleep(100L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static long lastClickTime;

    public static int getImageMaxEdge() {
        return (int) (165.0 / 320.0 * DisplayUtil.getScreanWidth());
    }

    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 750) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static void saveJsontoSp(JSONObject jsonObject) {
        Map<String, Object> map = jsonObject.getInnerMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                SpUtil.getInstance().put(entry.getKey(), entry.getValue() + "");
            }
        }
    }

    public static long getSecondsByMilliseconds(long milliseconds) {
        long seconds = new BigDecimal((float) milliseconds / (float) 1000).setScale(0,
                BigDecimal.ROUND_HALF_UP).intValue();
        // if (seconds == 0) {
        // seconds = 1;
        // }
        return seconds;
    }

    public static float getDensity() {
        return GeoApplication.application.getResources().getDisplayMetrics().density;
    }

    public static String getIp() {
        WifiManager wifi = (WifiManager) GeoApplication.application.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        int ipAddress = info.getIpAddress();
        String ip = intToIp(ipAddress);
        return ip;
    }

    public static String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
    }


    public static int getTimeWidth(int time) {
        if (time <= 2)
            return 80;
        if (time < 10)
            return (80 + 9 * (time - 2));
        if (time < 60)
            return (80 + 9 * (7 + time / 10));
        return 204;
    }

    public static boolean muteAudioFocus(boolean bMute) {
        boolean bool;
        AudioManager am = (AudioManager) GeoApplication.application
                .getSystemService(Context.AUDIO_SERVICE);
        if (bMute) {
            int result = am.requestAudioFocus(null, AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            bool = result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
        } else {
            int result = am.abandonAudioFocus(null);
            bool = result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
        }
        return bool;
    }

    public static String getFormatTime(long time) {
        long difference = new Date().getTime() - time;
        long minutes = (difference / (1000 * 60));
        if (minutes < 5) {
            return "刚刚";
        } else if (minutes < 60) {
            return minutes + "分钟前";
        } else if (minutes < 60 * 24) {
            return minutes / 60 + "小时前";
        } else if (minutes < 60 * 24 * 30) {
            return minutes / (60 * 24) + "天前";
        } else {
            return "很久了";
        }
    }

    public static String getFormatTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());//2016-05-25 17:51:14
        try {
            long millisecond = 0;

            millisecond = sdf.parse(time).getTime();

            long difference = new Date().getTime() - millisecond;
            long minutes = (difference / (1000 * 60));

            if (minutes < 5) {
                return "刚刚";
            } else if (minutes < 60) {
                return minutes + "分钟前";
            } else if (minutes < 60 * 24) {
                return minutes / 60 + "小时前";
            } else if (minutes < 60 * 24 * 30) {
                return minutes / (60 * 24) + "天前";
            } else {
                return "很久了";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "...";
    }

    public static String getMessageDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

//    public static int isYeaterday(Date oldTime, Date newTime) {
//        if (newTime == null) {
//            newTime = new Date();
//        }
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        String todayStr = format.format(newTime);
//        Date today = null;
//        try {
//            today = format.parse(todayStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (today != null) {
//            if ((today.getTime() - oldTime.getTime()) > 0 && (today.getTime() - oldTime.getTime()) <= 86400000) {
//                return 0;
//            } else if ((today.getTime() - oldTime.getTime()) <= 0) { //至少是今天
//                return -1;
//            } else { //至少是前天
//                return 1;
//            }
//        }
//        return 1;
//    }

    public static int counterChars(String str) {
        // return
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            int tmp = (int) str.charAt(i);
            if (tmp > 0 && tmp < 127) {
                count += 1;
            } else {
                count += 2;
            }
        }
        return count;
    }

    public static String getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return simpleDateFormat.format(now.getTime());
    }



    public static long getAvailaleSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize, availableBlocks;
        if (Build.VERSION.SDK_INT > 18) {
            blockSize = stat.getBlockSizeLong();
            availableBlocks = stat.getAvailableBlocksLong();
        } else {
            blockSize = stat.getBlockSize();
            availableBlocks = stat.getAvailableBlocks();
        }
        return (availableBlocks * blockSize) / 1024 / 1024;
    }

    public static String getJSonKey(JSONObject jsonObject, String key, boolean save) {
        if (jsonObject.containsKey(key)) {
            if (save) {
                SpUtil.getInstance().put(key, jsonObject.getString(key));
                return "";
            }
            return jsonObject.getString(key);
        }
        return "";
    }

    public static String getUUID() {
        String androidId = Settings.Secure.getString(GeoApplication.application.getContentResolver(), Settings.Secure.ANDROID_ID);
        String uuid = SpUtil.getInstance().get("uuid");
        if (!TextUtils.isEmpty(uuid)) {
            return uuid;
        }
        if (!TextUtils.equals(androidId, "9774d56d682e549c")) {
            try {
                uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8")).toString();
                SpUtil.getInstance().put("uuid", uuid);
            } catch (Exception e) {
                uuid = UUID.randomUUID().toString();
                SpUtil.getInstance().put("uuid", uuid);
            }
        } else {
            uuid = UUID.randomUUID().toString();
            SpUtil.getInstance().put("uuid", uuid);
        }
        return uuid;
    }

    public static void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f;
        Object obj_get;
        for (int i = 0; i < arr.length; i++) {
            String param = arr[i];
            try {
                f = imm.getClass().getDeclaredField(param);
                if (!f.isAccessible()) {
                    f.setAccessible(true);
                }
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    if (v_get.getContext() == destContext) {
                        f.set(imm, null);
                    } else {
                        break;
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    public static String getImg(String string) {
        if (TextUtils.isEmpty(string)) {
            string = "http://222.com";
        }
        return string;
    }

//    public static String getAppVersionName() {
//        PackageManager pm = SmartApplication.application.getPackageManager();
//        try {
//            PackageInfo info = pm.getPackageInfo(SmartApplication.application.getPackageName(), 0);
//            return info.versionName;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//            return "1";
//        }
//    }

    public static String stringToUnicode(String strText) {
        if (TextUtils.isEmpty(strText))
            return "";
        char c;
        String strRet = "";
        int intAsc;
        String strHex;
        for (int i = 0; i < strText.length(); i++) {
            c = strText.charAt(i);
            intAsc = (int) c;
            strHex = Integer.toHexString(intAsc);
            if (intAsc > 128) {
                strRet += "\\u" + strHex;
            } else {
                // 低位在前面补00
                strRet += "\\u00" + strHex;
            }
        }
        return strRet;
    }
}
