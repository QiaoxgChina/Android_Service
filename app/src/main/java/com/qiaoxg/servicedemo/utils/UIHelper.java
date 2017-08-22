package com.qiaoxg.servicedemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by admin on 2017/8/22.
 */

public class UIHelper {

    private static Toast mToast;

    public static void showToast(Context context, String message) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

}
