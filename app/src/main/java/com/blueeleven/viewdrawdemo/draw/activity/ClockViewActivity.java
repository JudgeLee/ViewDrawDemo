package com.blueeleven.viewdrawdemo.draw.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.blueeleven.viewdrawdemo.draw.view.StopWatchView;

/**
 * Author: lijiajie
 * Date: 2019/3/7
 * Desc: 实现一个无限走动的秒表，设计到View的动画
 */
public class ClockViewActivity extends AppCompatActivity {

  public static void start(Activity activity) {
    Intent intent = new Intent(activity, ClockViewActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(new StopWatchView(this));
  }
}
