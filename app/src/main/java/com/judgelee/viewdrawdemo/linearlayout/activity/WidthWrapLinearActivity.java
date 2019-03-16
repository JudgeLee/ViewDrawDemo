package com.judgelee.viewdrawdemo.linearlayout.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.judgelee.viewdrawdemo.R;

/**
 * Author: lijiajie
 * Date: 2019/3/4
 * Desc: 无Weight，Wrap宽度
 *
 * 测量宽度的方式通过内容来测量：
 * 1.内部View全是match，则取内部最大宽度为容器的宽度，并重新measure，使内部所有view宽度为容器宽度
 * 2.内部View全是wrap，各自衡量，容器宽度取内部最大View宽度
 * 3.内部混合，以Wrap的宽度和match的margin为准，去最大，然后重新衡量match的宽度
 */
public class WidthWrapLinearActivity extends AppCompatActivity {

  public static void start(Activity activity) {
    Intent intent = new Intent(activity, WidthWrapLinearActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_linear_wrap_width);
  }
}
