package com.mursitaffandi.kamusen_in;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.facebook.stetho.Stetho;
import com.mursitaffandi.kamusen_in.db.DDLKamus;

public class MyApplication extends Application {
    public static final String TAG_PREF_FRISTRUN = "firstrun";
    private static Context mContext;
    private static SharedPreferences mPreferences;
    private static MyApplication instance;
    private SharedPreferences.Editor editor;

    public MyApplication() {
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        createPreference();
        Stetho.initializeWithDefaults(this);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    private void createPreference() {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getInstance());
        editor = mPreferences.edit();
    }

    public static Context getContext() {
        return mContext;
    }

    public static SharedPreferences getmPreferences() {
        return getInstance().mPreferences;
    }

    public static SharedPreferences.Editor getEditor() {
        return instance.editor;
    }

    public static boolean isFirstTime() {
        boolean firstTime = true;
        if (firstTime) {
            firstTime = getmPreferences().getBoolean(MyApplication.TAG_PREF_FRISTRUN, true);
            if (firstTime) {
                getEditor().putBoolean(MyApplication.TAG_PREF_FRISTRUN, false).commit();
            }
        }
        return firstTime;
    }
}
