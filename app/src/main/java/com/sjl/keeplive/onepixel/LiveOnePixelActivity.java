package com.sjl.keeplive.onepixel;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.sjl.keeplive.R;

/**
 * LiveOnePixelActivity
 *
 * @author 林zero
 * @date 2018/9/4
 */
public class LiveOnePixelActivity extends Activity {

    private ScreenReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_one_pixel);

        initOnePixel();
    }

    /**
     * 初始化一像素保活
     */
    private void initOnePixel() {
        //广播监听屏幕亮熄
        receiver = new ScreenReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
