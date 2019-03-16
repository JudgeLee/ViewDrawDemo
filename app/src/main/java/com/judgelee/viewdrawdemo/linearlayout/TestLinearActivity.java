package com.judgelee.viewdrawdemo.linearlayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.judgelee.viewdrawdemo.R;
import com.judgelee.viewdrawdemo.linearlayout.activity.NormalLinearActivity;
import com.judgelee.viewdrawdemo.linearlayout.activity.TestCustomLinearActivity;
import com.judgelee.viewdrawdemo.linearlayout.activity.WeightLinearActivity;
import com.judgelee.viewdrawdemo.linearlayout.activity.WidthWrapLinearActivity;

/**
 * Author: lijiajie
 * Date: 2019/3/1
 * Desc: 测试LinearLayout的绘制流程
 */
public class TestLinearActivity extends AppCompatActivity {

  public static void start(Activity activity) {
    Intent intent = new Intent(activity, TestLinearActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_linear_test);
  }

  /**
   * 测试正常的LinearLayout
   * 1.无Weight属性
   * 2.宽度MATCH_PARENT或者固定宽度100dp
   * 3.高度MATCH_PARENT或者WRAP_PARENT或者固定高度
   */
  public void onClickNormalLinearLayout(View view) {
    NormalLinearActivity.start(this);
  }

  public void onClickWrapWidthLinearLayout(View view) {
    WidthWrapLinearActivity.start(this);
  }

  public void onClickWeightLinearLayout(View view) {
    WeightLinearActivity.start(this);
  }


  public void onClickCustomLinearLayout(View view) {
    TestCustomLinearActivity.start(this);
  }


}
