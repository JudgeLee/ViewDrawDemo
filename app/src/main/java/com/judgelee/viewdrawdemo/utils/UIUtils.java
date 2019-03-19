package com.judgelee.viewdrawdemo.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Author: lijiajie
 * Date: 2019/3/7
 * Desc:
 */
public class UIUtils {

  private static Context sContext;

  public static void init(Context context) {
    sContext = context;
  }

  public static Resources getResources() {
    return sContext.getResources();
  }

  public static int dp2px(double dp) {
    return ((int) (dp * sContext.getResources().getDisplayMetrics().density + 0.5f));
  }

  public static int px2dp(double px) {
    return ((int) (px / sContext.getResources().getDisplayMetrics().density + 0.5f));
  }

  public static Drawable getDrawable(int resId) {
    return sContext.getResources().getDrawable(resId);
  }

  public static Bitmap createBitmap(int resId) {
    Drawable dr = getDrawable(resId);

    if (dr == null) {
      return null;
    }
    Bitmap bm = Bitmap.createBitmap(dr.getIntrinsicWidth(), dr.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bm);
    dr.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
    dr.draw(canvas);
    return bm;

  }

}
