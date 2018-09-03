package com.sjl.keeplive;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
/**
 * OnePixelActivity
 *
 * @author 林zero
 * @date 2018/9/3
 */
public class OnePixelActivity extends Activity {
    private static final String TAG = "KeepLiveActivity";
    private DestoryReceiver receiver;

    class DestoryReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("destory".equalsIgnoreCase(intent.getAction())) {
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
//        setContentView(R.layout.activity_keep_live);
        //设置一像素
        Window window = getWindow();
        window.setGravity(Gravity.TOP|Gravity.LEFT);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.width = 1;
        layoutParams.height = 1;
        window.setAttributes(layoutParams);

        //广播监听
        receiver = new DestoryReceiver();
        registerReceiver(receiver, new IntentFilter("destory"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 第一次屏幕快速熄亮时，当前Activity可能在屏幕亮起之后才启动完成，需要手动退出当前Activity
        checkScreen();
    }

    /**
     * 检查屏幕亮熄
     */
    private void checkScreen() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = pm.isScreenOn();
        if (isScreenOn) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestory");
        unregisterReceiver(receiver);
    }
}
