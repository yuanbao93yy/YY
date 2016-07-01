package com.yuanbao.yy.base;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

/**
 * Created by lyy on 2016/7/1.
 */
public abstract class BaseActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //设置布局
        setContentView(findContentView());

        //找控件
        findViews();
        //设置控件
        setViews(savedInstanceState);
        //为控件设置监听事件
        registerListeners();
        //做其他事件
        doOtherEvents();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    /**
     * 销毁的时候 释放list
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     /**
     * 返回layout的id
     */
    protected abstract int findContentView();

    /**
     * 查找Views
     */
    protected abstract void findViews();
    /**
     * 查找和强转某个View
     */
    protected <T extends View> T findAndCastView(int resId)
    {
        return (T) findViewById(resId);
    }

    /**
     * 初始化数据，向View填充，设置View属性
     * @param savedInstanceState
     */
    protected abstract void setViews(Bundle savedInstanceState);

    /**
     * View添加事件监听
     */
    protected abstract void registerListeners();

    /**
     * 添加其他业务逻辑，如注册广播、启动定时器等
     */
    protected abstract void doOtherEvents();

    /**
     * 显示Toast
     */
    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int resId){
        showToast(getString(resId));
    }

    /**
     * 获取运行中的Activity的名字
     * @return
     */
    private String getRunningActivityName(){
        ActivityManager activityManager=(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity=activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        return runningActivity;
    }
}
