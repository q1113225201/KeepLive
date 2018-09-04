package com.sjl.keeplive.jobscheduler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.sjl.keeplive.R;

/**
 * LiveJobSchedulerActivity
 *
 * @author 林zero
 * @date 2018/9/4
 */
public class LiveJobSchedulerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_job_scheduler);

        initJobScheduler();
        finish();
    }

    /**
     * 初始化工作调度
     */
    private void initJobScheduler() {
        startService(new Intent(this, DaemonService.class));
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(this, DaemonService.class));
    }
}
