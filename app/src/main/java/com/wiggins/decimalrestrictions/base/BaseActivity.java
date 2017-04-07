package com.wiggins.decimalrestrictions.base;

import android.app.Activity;
import android.os.Bundle;

import com.wiggins.decimalrestrictions.app.DrApplication;

/**
 * @Description 所有Activity的基类
 * @Author 一花一世界
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DrApplication.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DrApplication.finishActivity(this);
    }
}
