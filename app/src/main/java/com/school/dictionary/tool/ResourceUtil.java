package com.school.dictionary.tool;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

public class ResourceUtil {

    private static boolean sUseResourceUtil = false;

    public static int getDrawable(Context context, String key) {
        return getResource(context, "drawable", key);
    }

    public static int getMipmap(Context context, String key) {
        return getResource(context, "mipmap", key);
    }

    public static int getId(Context context, String key) {
        return getResource(context, "id", key);
    }

    public static int getString(Context context, String key) {
        return getResource(context, "string", key);
    }

    public static int getColor(Context context, String key) {
        return getResource(context, "color", key);
    }

    public static int getLayout(Context context, String key) {
        return getResource(context, "layout", key);
    }

    public static int getDimen(Context context, String key) {
        return getResource(context, "dimen", key);
    }

    public static int getStyle(Context context, String key) {
        return getResource(context, "style", key);
    }

    public static int getRaw(Context context, String key) {
        return getResource(context, "raw", key);
    }

    public static int getAnim(Context context, String key) {
        return getResource(context, "anim", key);
    }

    public static int getArray(Context context, String key) {
        return getResource(context, "array", key);
    }

    private static int getResource(Context context, String type, String key) {
        Resources resource = context.getResources();
        String pkgName = context.getPackageName();
        int id = resource.getIdentifier(key, type, pkgName);
        return id;
    }
}
