package com.blueeleven.viewdrawdemo.linearlayout.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.blueeleven.viewdrawdemo.R;

/**
 * Author: lijiajie
 * Date: 2019/3/4
 * Desc:
 */
public class WeightLinearActivity extends AppCompatActivity {
  
  public static void start(Activity activity) {
    Intent intent = new Intent(activity, WeightLinearActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_linear_weight);
  }
}
