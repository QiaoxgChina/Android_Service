package com.qiaoxg.servicedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.qiaoxg.servicedemo.R;
import com.qiaoxg.servicedemo.service.StartTypeService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartServiceActivity extends AppCompatActivity {

    @BindView(R.id.startService_btn)
    Button startServiceBtn;
    @BindView(R.id.stopService_btn)
    Button stopServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.startService_btn, R.id.stopService_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.startService_btn:
                startService(new Intent(this, StartTypeService.class));
                break;
            case R.id.stopService_btn:
                stopService(new Intent(this, StartTypeService.class));
                break;
        }
    }
}
