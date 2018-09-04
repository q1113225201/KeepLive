package com.sjl.keeplive.jobscheduler;

import android.app.ActivityManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * JobSchedulerService
 *
 * @author 沈建林
 * @date 2018/9/4
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobSchedulerService extends JobService {
    private static final String TAG = "JobSchedulerService";
    private JobParameters jobParameters;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e(TAG, "onStartJob");
        this.jobParameters = params;
        /**
         * 返回true，表示该工作耗时，工作结束后需手动调用{@link #jobFinished(JobParameters, boolean)}销毁，否则只执行一次
         * 返回false，非耗时工作，到return时已完成
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "doJob");
                if (!isServiceLive()) {
                    startService(new Intent(JobSchedulerService.this, DaemonService.class));
                }
                jobFinished(jobParameters, false);
            }
        }, 5 * 1000);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e(TAG, "onStopJob");
        // 当onStartJob返回true时，需调用onStopJob来销毁job
        // 返回false来销毁这个工作
        if (!isServiceLive()) {
            startService(new Intent(JobSchedulerService.this, DaemonService.class));
        }
        return false;
    }

    /**
     * 判断守护服务是否活着
     *
     * @return
     */
    private boolean isServiceLive() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (DaemonService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
