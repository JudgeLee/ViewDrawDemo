package com.judgelee.viewdrawdemo;

import android.app.Application;
import com.judgelee.viewdrawdemo.utils.UIUtils;

/**
 * Author: lijiajie
 * Date: 2019/3/19
 * Desc:
 */
public class MyApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    UIUtils.init(this);

  }
}
