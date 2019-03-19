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
import com.judgelee.viewdrawdemo.customdraw.activity.ShaderActivity;
import com.judgelee.viewdrawdemo.customdraw.activity.XfermodeActivity;

/**
 * Author: lijiajie
 * Date: 2019/3/7
 * Desc:
 */
public class CustomDrawActivity extends AppCompatActivity {

  public static void start(Activity activity) {
    Intent intent = new Intent(activity, CustomDrawActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_draw);
  }

  public void onClickPaint1(View view) {
    PaintActivity1.start(this);
  }

  public void onClickDrawClock(View view) {
    ClockViewActivity.start(this);
  }

  public void onClickCobweb(View view) {
    CobwebViewActivity.start(this);
  }

  public void onClickPaint2(View view) {
    PathEffectActivity.start(this);
  }

  public void onClickPath(View view) {
    PathActivity.start(this);
  }

  public void onClickCanvas(View view) {
    CanvasActivity.start(this);
  }

  public void onClickXfermode(View view) {
    XfermodeActivity.start(this);
  }

  public void onClickShader(View view) {
    ShaderActivity.start(this);
  }
}
