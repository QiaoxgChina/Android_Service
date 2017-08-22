package com.qiaoxg.servicedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.qiaoxg.servicedemo.activity.BindServiceActivity;
import com.qiaoxg.servicedemo.activity.IntentServiceActivity;
import com.qiaoxg.servicedemo.activity.LifeCircleActivity;
import com.qiaoxg.servicedemo.activity.OverviewActivity;
import com.qiaoxg.servicedemo.activity.StartServiceActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.overview_btn)
    Button overviewBtn;
    @BindView(R.id.lifeCircle_btn)
    Button lifeCircleBtn;
    @BindView(R.id.startType_btn)
    Button startTypeBtn;
    @BindView(R.id.bindType_btn)
    Button bindTypeBtn;
    @BindView(R.id.IntentType_btn)
    Button IntentTypeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.overview_btn, R.id.lifeCircle_btn,
            R.id.startType_btn, R.id.bindType_btn, R.id.IntentType_btn})
    public void onViewClicked(View view) {
        Intent i = new Intent();
        switch (view.getId()) {
            case R.id.overview_btn:
                i.setClass(this, OverviewActivity.class);
                break;
            case R.id.lifeCircle_btn:
                i.setClass(this, LifeCircleActivity.class);
                break;
            case R.id.startType_btn:
                i.setClass(this, StartServiceActivity.class);
                break;
            case R.id.bindType_btn:
                i.setClass(this, BindServiceActivity.class);
                break;
            case R.id.IntentType_btn:
                i.setClass(this, IntentServiceActivity.class);
                break;
        }
        startActivity(i);
    }
}
