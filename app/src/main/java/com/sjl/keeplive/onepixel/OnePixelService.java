package com.sjl.keeplive.onepixel;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
 * 一像素服务
 *
 * @author 林zero
 * @date 2018/9/9
 */
public class OnePixelService extends Service {
    private ScreenReceiver receiver;

    @Override
    public void onCreate() {
        super.onCreate();
        //广播监听屏幕亮熄
        receiver = new ScreenReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消广播
        unregisterReceiver(receiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
