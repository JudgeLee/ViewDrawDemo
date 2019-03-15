package com.blueeleven.viewdrawdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.blueeleven.viewdrawdemo.draw.TestDrawActivity;
import com.blueeleven.viewdrawdemo.linearlayout.TestLinearActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void onClickTestMeasure(View view) {
    TestLinearActivity.start(this);
  }


  public void onClickNativeLinearLayout(View view) {
    NativeLinearLayoutActivity.start(this);
  }

  public void onClickTestDraw(View view) {
    TestDrawActivity.start(this);
  }
}
