package com.raoqian.mobprosaleapplication.base;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.raoqian.mobprosaleapplication.bean.DaoMaster;
import com.raoqian.mobprosaleapplication.bean.DaoSession;
import com.raoqian.mobprosaleapplication.proxy_design.ProxySubject.FormatUtil;
import com.raoqian.mobprosaleapplication.proxy_design.ProxySubject.HttpUtils;
import com.raoqian.mobprosaleapplication.proxy_design.ProxySubject.ImageLoaderUtils;
import com.raoqian.mobprosaleapplication.proxy_design.RealSubject.GSONFormater;
import com.raoqian.mobprosaleapplication.proxy_design.RealSubject.GildeImageLoader;
import com.raoqian.mobprosaleapplication.proxy_design.RealSubject.OkHttpProcessor;
import com.raoqian.mobprosaleapplication.utils.StatusBarUtil;
import com.raoqian.mobprosaleapplication.utils.TLog;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2018/5/15.
 */

public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private static DaoSession daoSession;
    public volatile static int BASE_STATUS_BAR_HEIGHT;

    @Override
    public void onCreate() {
        super.onCreate();
        BASE_STATUS_BAR_HEIGHT = StatusBarUtil.getStatusBarHeight(this);
        FormatUtil.init(new GSONFormater());
        HttpUtils.init(new OkHttpProcessor());
        ImageLoaderUtils.init(new GildeImageLoader(this));
        registerActivityLifecycleCallbacks(this);

        LeakCanary.install(this);
        //配置数据库
        setupDatabase();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库shop.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        TLog.error("BaseApplication", "onActivityCreated." + activity.getLocalClassName());
    }

    @Override
    public void onActivityStarted(Activity activity) {
        TLog.error("BaseApplication", "onActivityStarted." + activity.getLocalClassName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        TLog.error("BaseApplication", "onActivityResumed." + activity.getLocalClassName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        TLog.error("BaseApplication", "onActivityPaused." + activity.getLocalClassName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        TLog.error("BaseApplication", "onActivityStopped." + activity.getLocalClassName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        TLog.error("BaseApplication", "onActivitySaveInstanceState." + activity.getLocalClassName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        TLog.error("BaseApplication", "onActivityDestroyed." + activity.getLocalClassName());
    }
}
