package com.judgelee.viewdrawdemo.customdraw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.judgelee.viewdrawdemo.R;
import com.judgelee.viewdrawdemo.customdraw.activity.CanvasActivity;
import com.judgelee.viewdrawdemo.customdraw.activity.ClockViewActivity;
import com.judgelee.viewdrawdemo.customdraw.activity.CobwebViewActivity;
import com.judgelee.viewdrawdemo.customdraw.activity.PaintActivity1;
import com.judgelee.viewdrawdemo.customdraw.activity.PathActivity;
import com.judgelee.viewdrawdemo.customdraw.activity.PathEffectActivity;

/**
 * Author: lijiajie
 * Date: 2019/3/7
 * Desc:
 */
public class TestCustomDrawActivity extends AppCompatActivity {

  public static void start(Activity activity) {
    Intent intent = new Intent(activity, TestCustomDrawActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_draw);
  }

  public void onClickTestPaint1(View view) {
    PaintActivity1.start(this);
  }

  public void onClickDrawClock(View view) {
    ClockViewActivity.start(this);
  }

  public void onClickCobweb(View view) {
    CobwebViewActivity.start(this);
  }

  public void onClickTestPaint2(View view) {
    PathEffectActivity.start(this);
  }

  public void onClickTestPath(View view) {
    PathActivity.start(this);
  }

  public void onClickTestCanvas(View view) {
    CanvasActivity.start(this);
  }
}
