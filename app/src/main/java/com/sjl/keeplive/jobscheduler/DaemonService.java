package com.sjl.keeplive.jobscheduler;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * DaemonService
 *
 * @author 林zero
 * @date 2018/9/4
 */
public class DaemonService extends Service {
    private static final String TAG = "DaemonService";

    public DaemonService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上用JobScheduler
            JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(getPackageName(), JobSchedulerService.class.getName()));
            //每隔15分钟
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) {
                builder.setPeriodic(15 * 60 * 1000);
            }else{
                builder.setPeriodic(5 * 1000);
            }
            builder.setRequiresCharging(true);
            //设置设备重启后，是否重新执行任务
            builder.setPersisted(true);
            builder.setRequiresDeviceIdle(true);
            //网络连接时执行
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
            JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
            jobScheduler.cancel(1);
            jobScheduler.schedule(builder.build());
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }
}
