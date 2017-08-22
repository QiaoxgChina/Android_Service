package com.qiaoxg.servicedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.qiaoxg.servicedemo.utils.UIHelper;

/**
 * Created by admin on 2017/8/22.
 */

public class StartTypeService extends Service {

    private static final String TAG = "StartTypeService";

    private static final int SEND_TOAST_MESSAGE = 0;

    private boolean mServiceDestroy = false;
    private int mCount = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SEND_TOAST_MESSAGE:
                    if (mServiceDestroy || mHandler == null) {
                        return;
                    }

                    UIHelper.showToast(getApplicationContext(), "Show Times : " + mCount);
                    mCount++;
                    mHandler.sendEmptyMessageDelayed(SEND_TOAST_MESSAGE, 3000);

                    break;
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: ==========0000000000000=======");
        mHandler.sendEmptyMessage(SEND_TOAST_MESSAGE);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ========11111111111111111111========");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //在destroy方法里释放资源
        mServiceDestroy = true;
        mHandler = null;
        stopSelf();
        Log.e(TAG, "onDestroy: =======3333333333333333========");
    }
}
