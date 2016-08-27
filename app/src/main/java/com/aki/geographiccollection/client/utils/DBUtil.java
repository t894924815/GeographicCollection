package com.aki.geographiccollection.client.utils;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;

import com.aki.geographiccollection.client.GeoApplication;

public class DBUtil {
    private static DataBaseConfig config = new DataBaseConfig(GeoApplication.application, "app", true, 1, null);
    private static LiteOrm liteOrm = LiteOrm.newCascadeInstance(config);

    private DBUtil() {
    }

    public static LiteOrm getDbUtil() {
        return liteOrm;
    }
}
