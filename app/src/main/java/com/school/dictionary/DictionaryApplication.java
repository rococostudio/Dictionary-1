package com.school.dictionary;

import android.app.Application;
import android.content.Context;

import com.school.dictionary.content.ContentManager;

public class DictionaryApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ContentManager.getInstance().initDB();
    }

    public static Context getContext() {
        return context;
    }
}
