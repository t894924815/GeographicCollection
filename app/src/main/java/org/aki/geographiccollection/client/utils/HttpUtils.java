//package org.aki.geographiccollection.client.utils;
//
//import android.support.v4.util.ArrayMap;
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//
//import org.rxvolley.RxVolley;
//import org.rxvolley.client.HttpParams;
//import org.rxvolley.rx.Result;
//
//import java.util.Map;
//import java.util.Set;
//
//import rx.Observable;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.functions.Action1;
//import rx.functions.Func1;
//
//
//public class HttpUtils {
////        public static String URL = "http://120.76.101.37/webService";
////    public static String URL = "http://192.168.125.250:8080/smartlife/webService";
//
//    protected static String getUrl(String url, Map<String, String> params) {
//        if (url == null || params == null || params.size() == 0) {
//            return url;
//        }
//        StringBuilder urlBuilder = new StringBuilder(url);
//        Set<String> keys = params.keySet();
//        if (keys.size() > 0) {
//            urlBuilder.append("?");
//        }
//        for (String key : keys) {
//            String value = params.get(key);
//            urlBuilder.append(key).append("=").append(value).append("&");
//        }
//        if (keys.size() > 0) {
//            urlBuilder.setLength(urlBuilder.length() - 1);
//        }
//        return urlBuilder.toString();
//    }
//
//    //    public static final String URL = "192.168.125.250:8080/smartlife/webService";
//    public static void http(ArrayMap<String, String> param, Action1<JSONObject> subscriber) {
//        param.put("clientType", "0");
//        HttpParams httpParams = new HttpParams();
//        for (Map.Entry<String, String> entry : param.entrySet()) {
//            if (entry.getValue() != null) {
//
//                httpParams.put(entry.getKey(), entry.getValue());
//            }
//            if (TextUtils.equals(entry.getKey(), "key")) {
//                URL = "http://op.juhe.cn/onebox/weather/query";
//            }
//        }
//        Log.e("http", getUrl(URL, param));
//        param.clear();
//        Observable<Result> objectObservable = new RxVolley
//                .Builder()
//                .url(URL)
//                .shouldCache(false)
//                .params(httpParams)
//                .contentType(org.rxvolley.RxVolley.ContentType.JSON)
//                .encoding("UTF-8").httpMethod(org.rxvolley.RxVolley.Method.GET)
//                .getResult();
//        objectObservable.filter(new Func1<Result, Boolean>() {
//            @Override
//            public Boolean call(Result result) {
//                return result.data != null;
//            }
//        }).map(new Func1<Result, JSONObject>() {
//            @Override
//            public JSONObject call(Result result) {
//                Log.e("httpresult", new String(result.data));
//                return JSON.parseObject(new String(result.data));
//            }
//        }).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//                throwable.printStackTrace();
//                ToastUtil.showToast("稍后重试");
//            }
//        });
//        if (TextUtils.equals(URL, "http://op.juhe.cn/onebox/weather/query")) {
////            URL = "http://120.76.101.37/webService";
////            URL = "http://192.168.125.250:8080/smartlife/webService";
//        }
//    }
//}
