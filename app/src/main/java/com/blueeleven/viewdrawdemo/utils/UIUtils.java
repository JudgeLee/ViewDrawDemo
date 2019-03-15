package com.blueeleven.viewdrawdemo.utils;

import android.content.Context;

/**
 * Author: lijiajie
 * Date: 2019/3/7
 * Desc:
 */
public class UIUtils {

  private static float scale;

  public static int dp2px(Context context, double dp) {
    if (scale == 0.0f) {
      scale = context.getResources().getDisplayMetrics().density;
    }
    return ((int) (dp * scale + 0.5f));
  }

  public static int px2dp(Context context, double px) {
    if (scale == 0.0f) {
      scale = context.getResources().getDisplayMetrics().density;
    }
    return ((int) (px / scale + 0.5f));
  }
}
