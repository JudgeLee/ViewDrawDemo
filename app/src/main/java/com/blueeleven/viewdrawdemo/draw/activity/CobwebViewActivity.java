package com.blueeleven.viewdrawdemo.draw.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.blueeleven.viewdrawdemo.draw.view.CobwebView;

/**
 * Author: lijiajie
 * Date: 2019/3/7
 * Desc:
 */
public class CobwebViewActivity extends AppCompatActivity {

  public static void start(Activity activity) {
    Intent intent = new Intent(activity, CobwebViewActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(new CobwebView(this, new int[]{0, 1, 4, 5}));
  }
}
