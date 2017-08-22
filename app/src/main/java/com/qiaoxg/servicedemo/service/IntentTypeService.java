package com.qiaoxg.servicedemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by admin on 2017/8/22.
 */

public class IntentTypeService extends IntentService {

    public IntentTypeService() {
        super("IntentTypeService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
