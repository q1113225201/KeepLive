package com.sjl.keeplive.onepixel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 屏幕监听
 *
 * @author 林zero
 * @date 2018/9/3
 */
public class ScreenReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case Intent.ACTION_SCREEN_OFF:
                //熄屏
                Intent intent1 = new Intent(context, OnePixelActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
                break;
            case Intent.ACTION_SCREEN_ON:
                //亮屏
                context.sendBroadcast(new Intent("destory"));
                break;
        }
    }
}
