package com.blueeleven.viewdrawdemo.draw.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.blueeleven.viewdrawdemo.draw.view.PaintView1;

/**
 * Author: lijiajie
 * Date: 2019/3/7
 * Desc:
 */
public class PaintActivity1 extends AppCompatActivity {

  public static void start(Activity activity) {
    Intent intent = new Intent(activity, PaintActivity1.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(new PaintView1(this));
  }

}
