package com.judgelee.viewdrawdemo.customdraw.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.judgelee.viewdrawdemo.R;

/**
 * Author: lijiajie
 * Date: 2019/3/20
 * Desc:
 */
public class TestCustomViewActivity extends AppCompatActivity {

  public static void start(Activity activity) {
    Intent intent = new Intent(activity, TestCustomViewActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_custom_view);
  }
}
