package com.judgelee.viewdrawdemo.nativewidget.linearlayout.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.judgelee.viewdrawdemo.R;

/**
 * Author: lijiajie
 * Date: 2019/3/4
 * Desc: 无weight，EXACTLY宽度
 */
public class NormalLinearActivity extends AppCompatActivity {

  public static void start(Activity activity) {
    Intent intent = new Intent(activity, NormalLinearActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_linear_normal);
  }
}
