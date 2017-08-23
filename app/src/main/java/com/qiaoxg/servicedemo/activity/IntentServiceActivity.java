package com.qiaoxg.servicedemo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qiaoxg.servicedemo.R;
import com.qiaoxg.servicedemo.service.IntentTypeService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntentServiceActivity extends AppCompatActivity {

    private static final String TAG = "IntentServiceActivity";

    public static final String MY_SERVICE_ACTION = "MY_SERVICE_ACTION";
    public static final String MY_THREAD_ACTION = "MY_THREAD_ACTION";

    @BindView(R.id.serviceState_tv)
    TextView serviceStateTv;
    @BindView(R.id.threadState_tv)
    TextView threadStateTv;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.progressNumber_tv)
    TextView progressNumberTv;
    @BindView(R.id.startService_btn)
    Button startServiceBtn;
    @BindView(R.id.stopService_btn)
    Button stopServiceBtn;


    private LocalBroadcastManager mBroadcastManager;
    private MyBroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        ButterKnife.bind(this);
        mBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //动态注册BroadcastReceiver，还有一种方式就是在manifest中静态注册
        //本处是交给LocalBroadcastManager统一处理，也可以资金进行全局注册
        //使用LocalBroadcastManager统一处理的，在收发broadcast的时候要保持一致，否则就会收不到消息等情况
        IntentFilter filter = new IntentFilter();
        filter.addAction(MY_SERVICE_ACTION);
        filter.addAction(MY_THREAD_ACTION);
        mReceiver = new MyBroadcastReceiver();
        mBroadcastManager.registerReceiver(mReceiver, filter);
    }

    @OnClick({R.id.startService_btn, R.id.stopService_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.startService_btn:
                startService(new Intent(this, IntentTypeService.class));
                break;
            case R.id.stopService_btn:
                stopService(new Intent(this, IntentTypeService.class));
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        //注销BroadcastReceiver
        mBroadcastManager.unregisterReceiver(mReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            switch (intent.getAction()) {
                case MY_SERVICE_ACTION:
                    serviceStateTv.setText("服务状态: " + intent.getStringExtra("SERVICE_MSG"));
                    break;
                case MY_THREAD_ACTION:
                    threadStateTv.setText("线程状态: " + intent.getStringExtra("THREAD_MSG"));
                    int progress = intent.getIntExtra("THREAD_PROGRESS", 0);
                    progressBar.setProgress(progress);
                    progressNumberTv.setText(progress + "%");
                    break;
            }
        }
    }
}
