package com.sjl.keeplive;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.sjl.keeplive.jobscheduler.DaemonService;
import com.sjl.keeplive.jobscheduler.JobSchedulerService;
import com.sjl.keeplive.jobscheduler.LiveJobSchedulerActivity;
import com.sjl.keeplive.onepixel.LiveOnePixelActivity;
import com.sjl.keeplive.onepixel.ScreenReceiver;

/**
 * MainActivity
 *
 * @author 林zero
 * @date 2018/9/3
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        findViewById(R.id.btnOnePixel).setOnClickListener(this);
        findViewById(R.id.btnJobScheduler).setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOnePixel:
                startActivity(new Intent(this, LiveOnePixelActivity.class));
                break;
            case R.id.btnJobScheduler:
                startActivity(new Intent(this, LiveJobSchedulerActivity.class));
                break;
        }
    }
}
