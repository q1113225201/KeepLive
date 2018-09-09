package com.sjl.keeplive.onepixel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sjl.keeplive.R;

/**
 * LiveOnePixelActivity
 *
 * @author 林zero
 * @date 2018/9/4
 */
public class LiveOnePixelActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_one_pixel);
        findViewById(R.id.btnStart).setOnClickListener(this);
        findViewById(R.id.btnStop).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                startService(new Intent(this, OnePixelService.class));
                break;
            case R.id.btnStop:
                stopService(new Intent(this, OnePixelService.class));
                break;
        }
    }
}
