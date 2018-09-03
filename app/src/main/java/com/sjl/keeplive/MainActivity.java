package com.sjl.keeplive;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * MainActivity
 *
 * @author 林zero
 * @date 2018/9/3
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private long time;

    private ScreenReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initReceiver();
        initData();
    }

    /**
     * 初始化广播监听
     */
    private void initReceiver() {
        //广播监听屏幕亮熄
        receiver = new ScreenReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(receiver, intentFilter);
    }

    private void initData() {
        time = System.currentTimeMillis();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e(TAG, "live:" + (System.currentTimeMillis() - time) / 1000);
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
