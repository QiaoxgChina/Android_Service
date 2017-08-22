package com.qiaoxg.servicedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.qiaoxg.servicedemo.bean.MessageBean;

/**
 * Created by admin on 2017/8/22.
 */

public class BindTypeService extends Service {

    private static final String TAG = "BindTypeService";

    private static final int SEND_MESSAGE = 0;
    private int mMessageCount = 0;
    private int mDelayedTime = 1000;
    private boolean mIsStop = false;

    public interface MyBindServiceListener {
        void receiveMessage(MessageBean bean);
    }

    private MyBindServiceListener mListener;

    public BindTypeService() {

    }

    public void addListener(MyBindServiceListener listener) {
        this.mListener = listener;
    }

    public MyBinder myBinder = new MyBinder();

    public class MyBinder extends Binder {
        public BindTypeService getService() {
            return new BindTypeService();
        }
    }

    public void startSendMessage() {
        mHandler.sendEmptyMessage(SEND_MESSAGE);
    }

    public void setStopSendMessage() {
        this.mIsStop = true;
    }

    public void setSendMessageDelayedTime(int count) {
        this.mDelayedTime = count;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (mIsStop || mHandler == null) {
                return;
            }

            switch (msg.what) {
                case SEND_MESSAGE:
                    MessageBean bean = new MessageBean();
                    int number = mMessageCount % 4;
                    bean.setHeaderId(number);
                    String title = "";
                    String content = "";
                    if (number == 0) {
                        title = "Jone";
                        content = "有人在吗？我需要帮忙";
                    } else if (number == 1) {
                        title = "Tom";
                        content = "我在，你怎么了？";
                    } else if (number == 2) {
                        title = "Jerry";
                        content = "我不知道这附近哪儿有电影院，你可以帮我吗？我请你看电影";
                    } else if (number == 3) {
                        title = "Lily";
                        content = "我知道，请我吧";
                    }
                    bean.setTitle(title);
                    bean.setType(number);
                    bean.setContent(content);
                    mListener.receiveMessage(bean);
                    mMessageCount++;
                    mHandler.sendEmptyMessageDelayed(SEND_MESSAGE, mDelayedTime);
                    break;
            }
        }
    };

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate: 0000000000000000000");
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: 111111111111111111111111");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: 22222222222222222222222222222");

        mIsStop = true;
        mHandler = null;
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: 33333333333333333333333333333333333333");
    }
}
