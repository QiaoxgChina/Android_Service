package com.qiaoxg.servicedemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.qiaoxg.servicedemo.R;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LifeCircleActivity extends AppCompatActivity {

    @BindView(R.id.lifeCircle_iv)
    ImageView lifeCircleIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_circle);
        ButterKnife.bind(this);

        try {
            InputStream path = getResources().getAssets().open("service.png");
            Bitmap bitmap = BitmapFactory.decodeStream(path);
            lifeCircleIv.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
