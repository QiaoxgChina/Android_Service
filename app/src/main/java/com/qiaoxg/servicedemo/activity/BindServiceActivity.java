package com.qiaoxg.servicedemo.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.qiaoxg.servicedemo.R;
import com.qiaoxg.servicedemo.adapter.MessageAdapter;
import com.qiaoxg.servicedemo.bean.MessageBean;
import com.qiaoxg.servicedemo.service.BindTypeService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindServiceActivity extends AppCompatActivity implements BindTypeService.MyBindServiceListener {

    private static final String TAG = "BindServiceActivity";

    @BindView(R.id.BindService_btn)
    Button BindServiceBtn;
    @BindView(R.id.UnbindService_btn)
    Button UnbindServiceBtn;
    @BindView(R.id.startSend_btn)
    Button startSendBtn;
    @BindView(R.id.stopSend_btn)
    Button stopSendBtn;
    @BindView(R.id.message_rv)
    RecyclerView messageRv;
    @BindView(R.id.one_btn)
    Button oneBtn;
    @BindView(R.id.five_btn)
    Button fiveBtn;

    private MessageAdapter mAdapter;

    private BindTypeService mService;

    private boolean isAutoScroll = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);
        ButterKnife.bind(this);
        initView();
        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new MessageAdapter();
        messageRv.setAdapter(mAdapter);
    }

    private void initView() {
        messageRv.setLayoutManager(new LinearLayoutManager(this));
        messageRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                isAutoScroll = isSlideToBottom(recyclerView);
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    /**
     * 判断recycleView是否滑动到底部
     *
     * @param recyclerView
     * @return
     */
    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    @OnClick({R.id.BindService_btn, R.id.UnbindService_btn, R.id.startSend_btn, R.id.stopSend_btn, R.id.one_btn, R.id.five_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BindService_btn:
                bindService();
                break;
            case R.id.UnbindService_btn:
                unBindService();
                break;
            case R.id.startSend_btn:
                sendMessage();
                break;
            case R.id.stopSend_btn:
                if (mService != null) {
                    mService.setStopSendMessage();
                }
                break;
            case R.id.one_btn:
                if (mService != null) {
                    mService.setSendMessageDelayedTime(1000);
                }
                break;
            case R.id.five_btn:
                if (mService != null) {
                    mService.setSendMessageDelayedTime(5000);
                }
                break;
        }
    }

    private void sendMessage() {
        if (mService != null) {
            mService.startSendMessage();
        }
    }

    private void bindService() {
        Intent i = new Intent(this, BindTypeService.class);
        bindService(i, MyServiceConn, BIND_AUTO_CREATE);
    }

    private void unBindService() {
        unbindService(MyServiceConn);
    }

    public ServiceConnection MyServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BindTypeService.MyBinder binder = (BindTypeService.MyBinder) service;
            mService = binder.getService();
            mService.addListener(BindServiceActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };

    @Override
    public void receiveMessage(MessageBean bean) {
        if (mAdapter != null) {
            mAdapter.addMessage(bean);
            int position = mAdapter.getItemCount();
            if (isAutoScroll) {
                //让recycleView自动滑动到指定位置
                messageRv.smoothScrollToPosition(position);
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //当activity销毁之前一定要解绑，否则logcat会报错
        unbindService(MyServiceConn);
        mService = null;
    }
}
