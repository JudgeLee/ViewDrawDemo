package com.blueeleven.viewdrawdemo.draw.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.blueeleven.viewdrawdemo.draw.view.PathView;

/**
 * Author: lijiajie
 * Date: 2019/3/15
 * Desc:
 */
public class PathActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(new PathView(this));
  }
}
