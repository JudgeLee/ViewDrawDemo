package com.judgelee.viewdrawdemo.customdraw.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.judgelee.viewdrawdemo.customdraw.view.ShaderView;

/**
 * Author: lijiajie
 * Date: 2019/3/19
 * Desc:
 */
public class ShaderActivity extends AppCompatActivity {

  public static void start(Activity activity) {
    Intent intent = new Intent(activity, ShaderActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(new ShaderView(this));
  }
}
