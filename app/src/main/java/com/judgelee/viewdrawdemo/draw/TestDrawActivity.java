package com.judgelee.viewdrawdemo.draw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.judgelee.viewdrawdemo.R;
import com.judgelee.viewdrawdemo.draw.activity.ClockViewActivity;
import com.judgelee.viewdrawdemo.draw.activity.CobwebViewActivity;
import com.judgelee.viewdrawdemo.draw.activity.PaintActivity1;
import com.judgelee.viewdrawdemo.draw.activity.PathEffectActivity;

/**
 * Author: lijiajie
 * Date: 2019/3/7
 * Desc:
 */
public class TestDrawActivity extends AppCompatActivity {

  public static void start(Activity activity) {
    Intent intent = new Intent(activity, TestDrawActivity.class);
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
}
