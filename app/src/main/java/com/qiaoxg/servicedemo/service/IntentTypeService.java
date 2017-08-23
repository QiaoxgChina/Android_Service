package com.qiaoxg.servicedemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import static com.qiaoxg.servicedemo.activity.IntentServiceActivity.MY_SERVICE_ACTION;
import static com.qiaoxg.servicedemo.activity.IntentServiceActivity.MY_THREAD_ACTION;

/**
 * Created by admin on 2017/8/22.
 */

public class IntentTypeService extends IntentService {

    private static final String TAG = "IntentTypeService";

    private LocalBroadcastManager mBroadcastManager;
    private boolean isRunning = true;
    private int count = 0;

    public IntentTypeService() {
        super("IntentTypeService");
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate: 00000000000000000000");
        super.onCreate();
        mBroadcastManager = LocalBroadcastManager.getInstance(this);
        sendServiceStateMsg("服务启动");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e(TAG, "onHandleIntent: 2222222222222222222");
        try {
            sendThreadStateMsg("线程启动", count);
            Thread.sleep(500);
            sendServiceStateMsg("服务运行中...");
            isRunning = true;
            while (isRunning) {
                count++;
                sendThreadStateMsg("线程运行中...", count);
                if (count >= 100) {
                    isRunning = false;
                }
                Thread.sleep(100);
                sendThreadStateMsg("线程运行中...", count);
            }
            sendThreadStateMsg("线程结束", count);

        } catch (InterruptedException e) {
            Log.e(TAG, "onHandleIntent: error is : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: 33333333333333333");
        super.onDestroy();
        sendServiceStateMsg("服务停止");
    }

    private void sendServiceStateMsg(String msg) {
        Intent i = new Intent();
        i.setAction(MY_SERVICE_ACTION);
        i.putExtra("SERVICE_MSG", msg);
        mBroadcastManager.sendBroadcast(i);
    }

    private void sendThreadStateMsg(String msg, int progress) {
        Intent i = new Intent();
        i.setAction(MY_THREAD_ACTION);
        i.putExtra("THREAD_MSG", msg);
        i.putExtra("THREAD_PROGRESS", progress);
        mBroadcastManager.sendBroadcast(i);
    }
}
